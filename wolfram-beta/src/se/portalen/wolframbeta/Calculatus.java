package se.portalen.wolframbeta;

import java.util.ArrayList;

public class Calculatus {
	static int test101 = 0;

	
	public static void main(String[]args){
		System.out.println(trimmer("(((5*((5*(5+3))/7))))"));
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
		
		input = input.replace(" ", "");
		
		for (int i = 0; i < input.length(); i++) {
			System.out.println(i);
			if (String.valueOf(input.charAt(i)).equals(")")){
				System.out.println(input.charAt(i));
				for (int j = i; j >= 0; j--) {
					System.out.println(j);
					if (String.valueOf(input.charAt(j)).equals("(")){
						System.out.println(j);
						System.out.println(input.charAt(j));
						String parameter = input.substring(j, i+1);
						String parameterBuffer = parameter.substring(1, i-1);
						System.out.println(input);
						input = input.replace(parameter, (splitter(parameterBuffer) + ""));
						System.out.println(input);
					}
				}
			} 
		}
		input = splitter(input) + "";
		
		return input;
	}
	
	public static String trimmer(String input){
		
		String trim = ""; 
		
		for (int i = 0; i < input.length(); i++) {	
			if (String.valueOf(input.charAt(i)).equals(")")){
				for (int j = i; j >= 0; j--) {
					if (String.valueOf(input.charAt(j)).equals("(")){
						trim = input.substring(j+1, i);
						System.out.println(input);
						input = input.replace("("+trim+")", splitter(trim)+"");
						i = 0;
						break;
					}
				}
			} 
		}
	
		return input;
	}
	
	public static double splitter(String parameter){
		
		double returnValue = 0;
		
		String[] tehArray = new String[0];
		
		
		for (int i = 0; i < parameter.length(); i++) {	
			if(String.valueOf(parameter.charAt(i)).equals("*")){
				tehArray = parameter.split("\\*");
				returnValue = Double.parseDouble(tehArray[0]) * Double.parseDouble(tehArray[1]);
				
			}
			
			if(String.valueOf(parameter.charAt(i)).equals("/")){
				tehArray = parameter.split("\\/");
				returnValue = Double.parseDouble(tehArray[0]) / Double.parseDouble(tehArray[1]);
				
			} 
			
			if(String.valueOf(parameter.charAt(i)).equals("+")){
				tehArray = parameter.split("\\+");
				returnValue = Double.parseDouble(tehArray[0]) + Double.parseDouble(tehArray[1]);;
			
			}
			
			if(String.valueOf(parameter.charAt(i)).equals("-")){
				tehArray = parameter.split("\\-");
				returnValue = Double.parseDouble(tehArray[0]) + Double.parseDouble(tehArray[1]);
				
			}
		}
		
		return returnValue;
	}
}
