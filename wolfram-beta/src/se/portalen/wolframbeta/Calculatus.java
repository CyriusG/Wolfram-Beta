package se.portalen.wolframbeta;

import java.util.ArrayList;

public class Calculatus {
	
	public static void main(String[]args){
		System.out.println(prioritizer("53+(95+(65*5))"));
	}
	
	public static String mathing(String input){
		
		ArrayList<String> mathConstruction = new ArrayList<String>();
		ArrayList<String> constructedBlocks = new ArrayList<String>();
		String constructBlock = "";
		int lastBlock = -1;
		String result = "";
		
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
			result = result + "\n" + constructedBlocks.get(i);
		}
			
		return result;
	}
	
	public static String prioritizer(String input) {
		for (int i = 0; i < input.length(); i++) {	
			if (String.valueOf(input.charAt(i)).equals(")")){
				for (int j = i; j > 0; j--) {
					if (String.valueOf(input.charAt(j)).equals("(")){
						String parameter = input.substring(j, i+1);
						String parameterBuffer = parameter.substring(1, parameter.length()-1);
						input = input.replace(parameter, splitter(parameterBuffer));
						break;
					}
				}
			}
		}
		
		return "";
	}
	
	public static String splitter(String parameter){
		
		String[] tehArray = new String[parameter.length()];
		
		for (int i = 0; i < parameter.length(); i++) {	
			if(String.valueOf(parameter.charAt(i)).equals("/")||String.valueOf(parameter.charAt(i)).equals("*")){
				tehArray = parameter.split("*");
			}
		}
		return parameter;
	}
}