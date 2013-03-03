package se.portalen.wolframbeta;

import java.util.ArrayList;

import sun.text.normalizer.CharTrie.FriendAgent;

public class TeXMaker {
	
	public TeXMaker() {}
	
	/**
	 * Create and initilize all the variables used by the class.
	 */
	private ArrayList<String> mathConstruction = new ArrayList<String>();
	private ArrayList<String> constructedBlocks = new ArrayList<String>();
	String newBlock;
	// Doesn't include / because it requires some special formating.
	private String[] signs = {"+", "-", "*",  "%", "(", ")"};
	
	public String parseTex(String input) {
		
		if(input != null) {
		
			mathConstruction.clear();
			constructedBlocks.clear();
			String result = "";
			
			// Remove all the spaces from the input.
			input = input.replace(" ", "");
			
			/**
			 * Add every single character into its own index in a list.
			 */
			for (int i = 0; i < input.length(); i++) {
				mathConstruction.add(String.valueOf(input.charAt(i)));
			}
			
			// Analyse the input and make it into separate blocks
			blockInput();
			//System.out.println(constructedBlocks);
			
			// Detect where brackets are located and parse it
			//detectBracktes();
			//System.out.println("2: " + constructedBlocks);
			
			// Format the special signs into something the renderer can read.
			//formatSpecialSigns();
			//System.out.println("3: " + constructedBlocks);
	
			for (int i = 0; i < constructedBlocks.size(); i++) {	
				result = result + constructedBlocks.get(i);
			}
			
			result = result.replace("pi", "$\\pi$");
			result = result.replace("*", "$\\times$");
			result = result.replace("^e$", "$\\mathrm{e}$");
			
			return result;
		}
		else
			return "";
	}
	
	private boolean containSigns(String input) {
		boolean result = false;
		
		for (int i = 0; i < signs.length; i++) {
			if(input.equals(signs[i])) {
				result = true;
				break;
			}
			else
				result = false;
		}
		return result;
	}
	
	/**
	 * Takes what the user inputs and recognizes patterns in it and 
	 * breaks everything into smaller blocks for the computers to
	 * render later.
	 */
	private void blockInput() {		
		
		for (int i = 0; i < mathConstruction.size(); i++) {
			if(
					(mathConstruction.get(mathConstruction.size() - 1).equals("(") || mathConstruction.get(mathConstruction.size() - 1).equals(")")) &&
					(mathConstruction.get(0).equals("(") || mathConstruction.get(0).equals(")")) &&
					(mathConstruction.get(1).equals(")") || mathConstruction.get(1).equals("("))) 
			{
				mathConstruction.remove(mathConstruction.size() - 1);
			}
			
			if(
					(mathConstruction.get(0).equals("(") || mathConstruction.get(0).equals(")")) && 
					((mathConstruction.get(1).equals("(") || mathConstruction.get(1).equals(")")))
			  ) 
			{
				mathConstruction.remove(0);
			}
		}
		
		int extraBrackets;
		
		for (int i = 0; i < mathConstruction.size(); i++) {
			newBlock = "";
			extraBrackets = 0;
			
			if(mathConstruction.get(i).equals("(")) {
				
				for (int j = i; j < mathConstruction.size(); j++) {
					newBlock += mathConstruction.get(j);
					
					if(mathConstruction.get(j).equals("("))
						extraBrackets++;
					
					if(mathConstruction.get(j).equals(")") && extraBrackets > 0) {
						extraBrackets--;
						if(extraBrackets == 0) {
							i = j + 1;
							break;
						}
					}					
				}
				constructedBlocks.add(newBlock);
				
				if(i == mathConstruction.size() && mathConstruction.get(mathConstruction.size() - 1).equals(")")) {
					break;
				}
			}
			
			
			int startIndex;
			int endIndex;
			if(containSigns(mathConstruction.get(i))) {
				
				endIndex = i - 1;
				startIndex = endIndex;
				newBlock = "";
				
				for (int j = endIndex; j >= 0; j--) {
					if(containSigns(mathConstruction.get(j)) || j == 0) {
						
						if(j != 0) {
							startIndex = j + 1;
						}
						else {
							startIndex = j;
						}
						for (int j2 = startIndex; j2 <= endIndex; j2++) {
							newBlock += mathConstruction.get(j2);
						}
						constructedBlocks.add(newBlock);
						constructedBlocks.add(mathConstruction.get(i));
						break;
					}
				}
			}
			
			if(i == mathConstruction.size() - 1) {
				endIndex = i;
				startIndex = endIndex;
				newBlock = "";
				
				for (int j = endIndex; j >= 0; j--) {
					
					if(containSigns(mathConstruction.get(j)) || j == 0) {
						
						for (int j2 = 0; j2 < mathConstruction.size(); j2++) {
							if(j2 == mathConstruction.size() - 1 && !containSigns(mathConstruction.get(j2))) {
								startIndex = j;
							}
							else {
								startIndex = j + 1;
							}
						}
						for (int j2 = startIndex; j2 <= endIndex; j2++) {
							newBlock += mathConstruction.get(j2);
						}
						System.out.println(newBlock);
						constructedBlocks.add(newBlock);
						break;
					}
				}
			}
		}
		
		for (int j = 0; j < constructedBlocks.size(); j++) {
			if(constructedBlocks.get(j).contains("/")) {
				String currentBlock = constructedBlocks.get(j);
				
				int signIndex = currentBlock.indexOf("/");
				String firstBlock = currentBlock.substring(0, signIndex);
				String secondBlock = currentBlock.substring(signIndex + 1, currentBlock.length());
				constructedBlocks.set(j, firstBlock);
				constructedBlocks.add(j + 1, "/");
				constructedBlocks.add(j + 2, secondBlock);
				
				j += 3;
			}
		}
		
		for (int j = 0; j < constructedBlocks.size(); j++) {
			String result;
			
			if(constructedBlocks.get(j).equals("/")) {
				String firstBlock = constructedBlocks.get(j - 1);
				String secondBlock = constructedBlocks.get(j + 1);
				if(firstBlock.contains("(") && secondBlock.contains(")")) {
					firstBlock = firstBlock.replace("(", "");
					secondBlock = secondBlock.replace(")", "");
					result = "\\left( \\frac{" + firstBlock + "}{" + secondBlock + "} \\right)";
				}
				else {
					result = "\\frac{" + firstBlock + "}{" + secondBlock + "}";
				}
				
				constructedBlocks.set(j - 1, result);
				constructedBlocks.remove(j);
				constructedBlocks.remove(j);
				j++;
			}
		}
		
//		// Resets the indexes.
//		int startIndex = 0;
//		int endIndex = 0;
//		
//		for (int i = 0; i < mathConstruction.size(); i++) {
//			if(mathConstruction.get(i).equals("(") && i != 0) {
//				mathConstruction.add(i, "*");
//				break;
//			}
//		}
//		
//		// The main loop that goes through every character in the list.
//		for (int i = 0; i < mathConstruction.size(); i++) {
//			// If it finds a /, *, + or - it have found its first block and makes everything to the left into its own block
//			// and everything to the right that before the next /, *, + or - into another block. It also makes the
//			// sign found it own block.
//			if(mathConstruction.get(i).equals("/") || mathConstruction.get(i).equals("*") || mathConstruction.get(i).equals("+") 
//					|| mathConstruction.get(i).equals("-") || mathConstruction.get(i).equals("%")) 
//			{
//				// It starts counting back to find the last sign or if it's the beginning.
//				for (int j = i; j > 0; j--) {
//					// When it reaches the start or the last sign it have found a new block and takes note of the
//					// index.
//					if(mathConstruction.get(j).equals("/") || mathConstruction.get(j).equals("*") || mathConstruction.get(j).equals("+") 
//							|| mathConstruction.get(j).equals("-") || mathConstruction.get(i).equals("%"))
//					{
//						startIndex = lastBlock + 1;
//						endIndex = j;
//						lastBlock = i;
//						break;
//					}
//				}
//				// Makes sure the string that constructs the block is empty.
//				constructBlock = "";
//				// Loops through the list where it found a block and adds all the characters
//				// into a string and then add the string into a list that contains the blocks.
//				for (int j = startIndex; j < endIndex; j++) {
//					
//					constructBlock = constructBlock + mathConstruction.get(j);
//					if(j == endIndex - 1) {
//						constructedBlocks.add(constructBlock);
//						constructedBlocks.add(mathConstruction.get(endIndex));
//					}
//				}
//			}
//			
//			// Pretty much same as above. It's just changed for the last block.
//			if(i == mathConstruction.size() - 1) {
//				constructBlock = "";
//				startIndex = lastBlock + 1;
//				endIndex = mathConstruction.size();
//				
//				for (int j = startIndex; j < endIndex; j++) {
//					constructBlock = constructBlock + mathConstruction.get(j);
//				}
//				
//				constructedBlocks.add(constructBlock);
//			}
//		}
	}
	
//	/**
//	 * Detects where brackets are and then puts everything within them into its own block.
//	 */
//	private void detectBracktes() {
//		// Outer loops that goes through the whole list.
//		for (int i = 0; i < constructedBlocks.size(); i++) {
//			// Makes sure it doesn't perform this on the last block which would 
//			// create an error.
//			if(i != constructedBlocks.size() - 1) {
//				// The inner loop that also goes through the whole loop but searches also for brackets.
//				for (int j = 0; j < constructedBlocks.size(); j++) {
//					// When the first loop have found a left bracket and the other loop found a right bracket...
//					if(constructedBlocks.get(i).startsWith("(") && constructedBlocks.get(j).endsWith(")")) {
//						// Remove the brackets and...
//						constructedBlocks.set(i, constructedBlocks.get(i).replace("(", ""));
//						constructedBlocks.set(j, constructedBlocks.get(j).replace(")", ""));
//						
//						// Merge all the blocks that's found within the brackets and...
//						mergedBlock = constructedBlocks.get(i);
//						for (int j2 = i + 1; j2 < j + 1; j2++) {						
//							mergedBlock = mergedBlock + constructedBlocks.get(j2);
//						}
//						
//						// Remove the not used blocks.
//						constructedBlocks.set(i, mergedBlock); 
//						for (int j2 = i + 1; j2 < j + 1; j2++) {
//							constructedBlocks.remove(i + 1);
//						}
//					}
//				}
//			}
//		}
//	}
//	
//	public void formatSpecialSigns() {
//		for (int i = 0; i < constructedBlocks.size(); i++) {
//			if(constructedBlocks.get(i).equals("/") && i > 0) {
//				String firstBlock = constructedBlocks.get(i - 1);
//				String secondBlock = constructedBlocks.get(i + 1);
//				
//				constructedBlocks.set(i - 1, "$\\frac {" + firstBlock + "}{" + secondBlock + "}$");
//				constructedBlocks.remove(i);
//				constructedBlocks.remove(i);
//			}
//		}
//	}
}
