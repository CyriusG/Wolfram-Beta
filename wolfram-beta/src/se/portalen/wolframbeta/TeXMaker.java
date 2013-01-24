package se.portalen.wolframbeta;

import java.util.ArrayList;

public class TeXMaker {
	
	public TeXMaker() {}
	
	/**
	 * Create and initilize all the variables used by the class.
	 */
	private ArrayList<String> mathConstruction = new ArrayList<String>();
	private ArrayList<String> constructedBlocks = new ArrayList<String>();
	private String constructBlock = "";
	private int lastBlock = -1;
	private String mergedBlock = "";
	
	public String parseTex(String input) {
		
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
		
		// Detect where brackets are located and parse it
		detectBracktes();
		
		// Format the special signs into something the renderer can read.
		formatSpecialSigns();
		
		for (int i = 0; i < constructedBlocks.size(); i++) {	
			result = result + constructedBlocks.get(i);
		}
		
		result = result.replace("pi", "$\\pi$");
		
		return result;
	}
	
	/**
	 * Takes what the user inputs and recognizes patterns in it and 
	 * breaks everything into smaller blocks for the computers to
	 * render later.
	 */
	private void blockInput() {
		// Resets the indexes.
		int startIndex = 0;
		int endIndex = 0;
		
		// The main loop that goes through every character in the list.
		for (int i = 0; i < mathConstruction.size(); i++) {
			// If it finds a /, *, + or - it have found its first block and makes everything to the left into its own block
			// and everything to the right that before the next /, *, + or - into another block. It also makes the
			// sign found it own block.
			if(mathConstruction.get(i).equals("/") || mathConstruction.get(i).equals("*") || mathConstruction.get(i).equals("+") || mathConstruction.get(i).equals("-")) {
				// It starts counting back to find the last sign or if it's the beginning.
				for (int j = i; j > 0; j--) {
					// When it reaches the start or the last sign it have found a new block and takes note of the
					// index.
					if(mathConstruction.get(j).equals("/") || mathConstruction.get(j).equals("*") || mathConstruction.get(j).equals("+") || mathConstruction.get(j).equals("-")) {
						startIndex = lastBlock + 1;
						endIndex = j;
						lastBlock = i;
						break;
					}
				}
				// Makes sure the string that constructs the block is empty.
				constructBlock = "";
				// Loops through the list where it found a block and adds all the characters
				// into a string and then add the string into a list that contains the blocks.
				for (int j = startIndex; j < endIndex; j++) {
					constructBlock = constructBlock + mathConstruction.get(j);
					if(j == endIndex - 1) {
						constructedBlocks.add(constructBlock);
						constructedBlocks.add(mathConstruction.get(endIndex));
					}
				}
				
				
			}
			// Pretty much same as above. It's just changed for the last block.
			if(i == mathConstruction.size() - 1) {
				constructBlock = "";
				startIndex = lastBlock + 1;
				endIndex = mathConstruction.size();
				
				for (int j = startIndex; j < endIndex; j++) {
					constructBlock = constructBlock + mathConstruction.get(j);
				}
				
				constructedBlocks.add(constructBlock);
			}
		}
	}
	
	/**
	 * Detects where brackets are and then puts everything within them into its own block.
	 */
	private void detectBracktes() {
		// Outer loops that goes through the whole list.
		for (int i = 0; i < constructedBlocks.size(); i++) {
			// Makes sure it doesn't perform this on the last block which would 
			// create an error.
			if(i != constructedBlocks.size() - 1) {
				// The inner loop that also goes through the whole loop but searches also for brackets.
				for (int j = 0; j < constructedBlocks.size(); j++) {
					// When the first loop have found a left bracket and the other loop found a right bracket...
					if(constructedBlocks.get(i).startsWith("(") && constructedBlocks.get(j).endsWith(")")) {
						// Remove the brackets and...
						constructedBlocks.set(i, constructedBlocks.get(i).replace("(", ""));
						constructedBlocks.set(j, constructedBlocks.get(j).replace(")", ""));
						
						// Merge all the blocks that's found within the brackets and...
						mergedBlock = constructedBlocks.get(i);
						for (int j2 = i + 1; j2 < j + 1; j2++) {						
							mergedBlock = mergedBlock + constructedBlocks.get(j2);
						}
						// Remove the not used blocks.
						constructedBlocks.set(i, mergedBlock); 
						for (int j2 = i + 1; j2 < j + 1; j2++) {
							constructedBlocks.remove(i + 1);
						}
					}
				}
			}
		}
	}
	
	public void formatSpecialSigns() {
		for (int i = 0; i < constructedBlocks.size(); i++) {
			if(constructedBlocks.get(i).equals("/") && i > 0) {
				String firstBlock = constructedBlocks.get(i - 1);
				String secondBlock = constructedBlocks.get(i + 1);
				
				constructedBlocks.set(i - 1, "$\\frac {" + firstBlock + "}{" + secondBlock + "}$");
				constructedBlocks.remove(i);
				constructedBlocks.remove(i);
			}
		}
	}
}
