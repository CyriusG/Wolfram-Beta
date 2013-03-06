package se.portalen.wolframbeta;

import java.util.ArrayList;

public class TeXMaker {
	
	/**
	 * Create and initilize all the variables used by the class.
	 */
	private ArrayList<String> mathConstruction = new ArrayList<String>();
	private ArrayList<String> constructedBlocks = new ArrayList<String>();
	String newBlock;
	// Doesn't include / because it requires some special formating.
	private String[] signs = {"+", "-", "*", "/", "\\",  "%"};
	
	public String parseTex(String input) {
		
		// Makes sure that the program doesn't crash if the user didn't input anything.
		if(input != null) {
			
			// Reset all the variables.
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
			
			// Analyse the input and make it into separate blocks and format special signs.
			clearBrackets();
			detectBrackets();
			formatSigns();
	
			// Put the all the final block into a single String.
			for (int i = 0; i < constructedBlocks.size(); i++) {	
				result = result + constructedBlocks.get(i);
			}
			
			// And at last replace certain signs with their equivalent mathematic symbols.
			result = result.replace("pi", "$\\pi$");
			result = result.replace("*", "$\\times$");
			result = result.replace("^e$", "$\\mathrm{e}$");
			
			return result;
		}
		else
			return "";
	}
	
	/**
	 * Used to determine if a String contains some of the special signs used 
	 * contained in an array at the top of the class..
	 * 
	 * @param input
	 * @return result
	 */
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
	 * Removes junk brackets.
	 */
	private void clearBrackets() {		
		
		// At the moment can only remove the first two junk brackets.
		// It also checks so that the last bracket isn't part of the equation.
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
	}
	
	/**
	 * Goes through the entire String and checks for brackets and signs
	 * and breaks everything into smaller blocks for later formating.
	 */
	private void detectBrackets() {
		int extraBrackets;
		
		// The main loop that goes through the entire list of characters.
		for (int i = 0; i < mathConstruction.size(); i++) {
			// Resets variables.
			newBlock = "";
			extraBrackets = 0;
			
			// If the program finds a bracket start a new block there.
			if(mathConstruction.get(i).equals("(")) {
				
				// Loops through until it finds the last bracket.
				for (int j = i; j < mathConstruction.size(); j++) {
					// Add every character gone by to a temporary String which later goes in the list that
					// holds all the blocks.
					newBlock += mathConstruction.get(j);
					
					// If it finds another left bracket tell the program to skip the next right bracket.
					if(mathConstruction.get(j).equals("("))
						extraBrackets++;
					
					// If it finds a right bracket and the program have found more than one bracket
					// lower the counts of brackets.
					if(mathConstruction.get(j).equals(")") && extraBrackets > 0) {
						extraBrackets--;
						// If the number of extra brackets equals 0 the end has been found so break the loop and 
						// make the main loop go over all the characters this loop went through.
						if(extraBrackets == 0) {
							i = j + 1;
							break;
						}
					}					
				}
				// At last add the new block to the list.
				constructedBlocks.add(newBlock);
				
				// If it reached the end and the last character is a right bracket break the loop to avoid further problems.
				if(i == mathConstruction.size() && mathConstruction.get(mathConstruction.size() - 1).equals(")")) {
					break;
				}
			}
			
			
			int startIndex;
			int endIndex;
			// If it finds any of the special signs listed in the array at the top
			// mark that as a separation point and make everything between them their own block.
			if(containSigns(mathConstruction.get(i))) {
				
				// For the first block set the last index to where it found the sign - 1.
				endIndex = i - 1;
				// Just temporary set the start index to the last index.
				startIndex = endIndex;
				// Reset the variable.
				newBlock = "";
				
				// Loops through the characters until it finds another sign or a bracket.
				for (int j = endIndex; j >= 0; j--) {
					if(containSigns(mathConstruction.get(j)) || j == 0) {
						
						// If the start index doesn't fall at the beginning
						// set it to j + 1.
						if(j != 0) {
							startIndex = j + 1;
						}
						// If it does fall at the beginning set it just to j.
						else {
							startIndex = j;
						}
						// Add the related characters into a String.
						for (int j2 = startIndex; j2 <= endIndex; j2++) {
							newBlock += mathConstruction.get(j2);
						}
						// Add that String to the list and also add the sign it found.
						constructedBlocks.add(newBlock);
						constructedBlocks.add(mathConstruction.get(i));
						break;
					}
				}
			}
			
			// If it's at the end add the last characters into a block.
			// Works the same as above except it's for the end.
			if(i == mathConstruction.size() - 1) {
				endIndex = i;
				startIndex = endIndex;
				newBlock = "";

				for (int j = endIndex; j >= 0; j--) {
					if(containSigns(mathConstruction.get(j)) || j == 0) {
						// Checks if it's a single digit or more. 
						// :I not really sure here
						for (int j2 = 0; j2 < mathConstruction.size(); j2++) {
							if(j2 == mathConstruction.size() - 1 && !containSigns(mathConstruction.get(j2))) {
								startIndex = j;
								break;
							}
							else {
								startIndex = j + 1;
								break;
							}
						}
						// And at last add the characters into a String.
						for (int j2 = startIndex; j2 <= endIndex; j2++) {
							newBlock += mathConstruction.get(j2);
						}
						// And add that String to the list.
						constructedBlocks.add(newBlock);
						break;
					}
				}
			}
		}
	}
	
	/**
	 * Some of the signs need special formating.
	 * That occurs here.
	 */
	private void formatSigns() {
		// Go through all the blocks and search for a divition sign.
		for (int j = 0; j < constructedBlocks.size(); j++) {
			if(constructedBlocks.get(j).equals("/") || constructedBlocks.get(j).equals("\\")) {
				
				// The first block is the block in front of it.
				String firstBlock = constructedBlocks.get(j - 1);
				// And the second block is the one behind it.
				String secondBlock = constructedBlocks.get(j + 1);
				// At the location of the first block merge the two blocks and put a
				// divition sign between them.
				constructedBlocks.set(j - 1, firstBlock + "/" + secondBlock);
				// Remove the other two unused blocks.
				constructedBlocks.remove(j);
				constructedBlocks.remove(j);
				j++;
			}
		}
		
		for (int j = 0; j < constructedBlocks.size(); j++) {
			String result;
			
			// If a block contains a divition sign.
			if(constructedBlocks.get(j).contains("/")) {
				// Get all the characters in front of it.
				String firstString = "";
				for (int k = 0; k < constructedBlocks.get(j).indexOf("/"); k++) {
					firstString += Character.toString(constructedBlocks.get(j).charAt(k));
				}
				// And all the characters after it into two seperate Strings.
				String secondString = "";
				for (int k = constructedBlocks.get(j).indexOf("/") + 1 ; k < constructedBlocks.get(j).length(); k++) {
					secondString += Character.toString(constructedBlocks.get(j).charAt(k));
				}
				// If they contain brackets add that to the formating.
				if(firstString.contains("(") && secondString.contains(")")) {
					// Remove the already existing brackets.
					firstString = firstString.replace("(", "");
					secondString = secondString.replace(")", "");
					// And format it.
					result = "\\left( \\frac{" + firstString + "}{" + secondString + "} \\right)";
				}
				else {
					// Format it the same way as above but without the brackets.
					result = "\\frac{" + firstString + "}{" + secondString + "}";
				}
				
				// Add it to the list.
				constructedBlocks.set(j, result);
				j++;
			}
		}
	}
}
