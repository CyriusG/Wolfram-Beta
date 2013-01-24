package se.portalen.wolframbeta;

import java.util.ArrayList;

public class Calculatus {
	
	public static void main(String[]args){
		System.out.println(structuring("25+45*97-13"));
	}
	
	public static String structuring(String input){
		
		ArrayList<String> mathConstruction = new ArrayList<String>();
		ArrayList<String> constructedBlocks = new ArrayList<String>();
		String constructBlock = "";
		int lastBlock = -1;
		String result = "";
		String mergedBlock = "";
		
		input = input.replace(" ", "");
		
		for (int i = 0; i < input.length(); i++) {
			mathConstruction.add(String.valueOf(input.charAt(i)));
		}
		
		int startIndex = 0;
		int endIndex = 0;
		for (int i = 0; i < mathConstruction.size(); i++) {
			if(mathConstruction.get(i).equals("/") || mathConstruction.get(i).equals("*") || mathConstruction.get(i).equals("+") || mathConstruction.get(i).equals("-")) {
				for (int j = i; j > 0; j--) {
					if(mathConstruction.get(j).equals("/") || mathConstruction.get(j).equals("*") || mathConstruction.get(j).equals("+") || mathConstruction.get(j).equals("-")) {
						startIndex = lastBlock + 1;
						endIndex = j;
						lastBlock = i;
						break;
					}
				}
				constructBlock = "";
				for (int j = startIndex; j < endIndex; j++) {
					constructBlock = constructBlock + mathConstruction.get(j);
					if(j == endIndex - 1) {
						constructedBlocks.add(constructBlock);
						constructedBlocks.add(mathConstruction.get(endIndex));
					}
				}
				
				
			}
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
		
		for (int i = 0; i < constructedBlocks.size(); i++) {
			if(i != constructedBlocks.size() - 1) {
				for (int j = 0; j < constructedBlocks.size(); j++) {
					if(constructedBlocks.get(i).startsWith("(") && constructedBlocks.get(j).endsWith(")")) {
						constructedBlocks.set(i, constructedBlocks.get(i).replace("(", ""));
						constructedBlocks.set(j, constructedBlocks.get(j).replace(")", ""));
						
						mergedBlock = constructedBlocks.get(i);
						for (int j2 = i + 1; j2 < j + 1; j2++) {						
							mergedBlock = mergedBlock + constructedBlocks.get(j2);
						}
						constructedBlocks.set(i, mergedBlock); 
						for (int j2 = i + 1; j2 < j + 1; j2++) {
							constructedBlocks.remove(i + 1);
						}
					}
				}
			}
		}
		
		for (int i = 0; i < constructedBlocks.size(); i++) {
			if(constructedBlocks.get(i).equals("/") && i > 0) {
				String firstBlock = constructedBlocks.get(i - 1);
				String secondBlock = constructedBlocks.get(i + 1);
				
				constructedBlocks.set(i - 1, "$\\frac {" + firstBlock + "}{" + secondBlock + "}$");
				constructedBlocks.remove(i);
				constructedBlocks.remove(i);
			}
		}
		
		for (int i = 0; i < constructedBlocks.size(); i++) {	
			result = result + "\n" + constructedBlocks.get(i);
		}
			
		return result;
	}	
	
	public String mathing(String input) {
		
		return "";
	}
}