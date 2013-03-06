package se.portalen.wolframbeta;

import java.awt.List;
import java.util.ArrayList;

public class Calculatus {

	public static void main(String[]args){
		System.out.println(parStructurer("(5*5(5+5())*3"));
	}
	
	
	
	public static String parMultiplyLogic(String input) {
		
		input = input.replace(" ", "");
		input = input.replace("pi", "p");
		input = input.replace("Pi", "p");
		input = input.replace("pI", "p");
		input = input.replace("PI", "p");
		input = input.replace("e", "e");
		input = input.replace("E", "e");
		
		ArrayList<Character> list = new ArrayList<Character>();
		
		for(char c : input.toCharArray()) {
		    list.add(c);
		}
		
		for(int i = 0; i < list.size(); i++){
			if(String.valueOf(list.get(i)).matches("[a-z]")){
				try {
					if(String.valueOf(list.get(i-1)).matches("[0-9]") || String.valueOf(list.get(i-1)).matches("\\)")
						|| String.valueOf(list.get(i-1)).matches("[a-z]")){
							list.add(i, '*');
					}
					
				} catch (IndexOutOfBoundsException e){
				}
			}
		}
		
		for(int i = 0; i < list.size(); i++){
			if(String.valueOf(list.get(i)).matches("[a-z]")){
				try {
					if(String.valueOf(list.get(i+1)).matches("[0-9]") || String.valueOf(list.get(i+1)).matches("\\(")
						|| String.valueOf(list.get(i+1)).matches("[a-z]")){
							list.add(i+1, '*');
					}
					
				} catch (IndexOutOfBoundsException e){
				}
			}
		}
		
		for(int i = 0; i < list.size(); i++){
			if(String.valueOf(list.get(i)).matches("[0-9]")){
				try {
					if(String.valueOf(list.get(i-1)).matches("\\)")){
							list.add(i, '*');
					}
					
				} catch (IndexOutOfBoundsException e){
				}
			}
		}
		
		for(int i = 0; i < list.size(); i++){
			if(String.valueOf(list.get(i)).matches("[0-9]")){
				try {
					if(String.valueOf(list.get(i+1)).matches("\\(")){
							list.add(i+1, '*');
					}
					
				} catch (IndexOutOfBoundsException e){
				}
			}
		}
		
		for(int i = 0; i < list.size(); i++){
			if(String.valueOf(list.get(i)).matches("\\)")){
				try {
					if(String.valueOf(list.get(i+1)).matches("\\(")){
							list.add(i+1, '*');
					}
					
				} catch (IndexOutOfBoundsException e){
				}
			}
		}
		
		String reString = "";
		
		for(char c : list){
			reString = reString + c;
		}
		
		return reString;
	}
	
	public static String parCleaner(String input) {
		input = input.replace(" ", "");
		char[] cArray = input.toCharArray();
		boolean counter = false;
		String returnVal = "";
		
		for(int i = 0; i < cArray.length; i++){
			if(String.valueOf(input.charAt(i)).equals("(")){
				if(String.valueOf(input.charAt(i+1)).equals(")")){
					cArray[i] = ' ';
					cArray[i+1] = ' ';
				}
			}
		}
		
		for(int i = 0; i < cArray.length; i++){
			if(((String.valueOf(input.charAt(i)).equals("\\(")) || (String.valueOf(input.charAt(i)).equals("\\)"))) && (counter==false)){
				counter=true;
			} else if ((String.valueOf(input.charAt(i)).equals("\\(")) && (counter==true)){
				cArray[i] = ' ';
			} else if ((String.valueOf(input.charAt(i)).equals("\\)")) && (counter==true)){
				cArray[i] = ' ';
			} else {
				counter=false;
			}
		}
		
		for(char a : cArray) {
		    returnVal = returnVal + a;
		}

		return returnVal.replace(" ", "");
	}
	
	public static String parSupplement(String input) {
		
		char[] cArray = input.toCharArray();
		int length = cArray.length;
		int leftCount = 0;
		int rightCount = 0;
		String returnValue = "";
		
		for(int i = 0; i < length; i++){
			if(String.valueOf(input.charAt(i)).equals("(")){
				leftCount++;
			} else if(String.valueOf(input.charAt(i)).equals(")")){
				rightCount++;
			}
		}
		
		int difference = leftCount-rightCount;
		
		if(difference>0){
			char[] tempArray = new char[length+difference];
			
			for(int i = 0; i < length; i++){
				tempArray[i] = cArray[i];
			}
			for(int j = 0; j < difference; j++){
				tempArray[length+j] = ')';
			}
			for(char a : tempArray){
				returnValue = returnValue + a;
			}
			
		} else if(difference<0){
			difference = difference*(-1);
			char[] tempArray = new char[length+difference];
			
			for(int i = 0; i < difference; i++){
				tempArray[i] = '(';
			}
			for(int j = 0; j < length; j++){
				tempArray[difference+j] = cArray[j];
			}
			for(char a : tempArray){
				returnValue = returnValue + a;
			}
		} else {
			returnValue = input;
		}

		return returnValue;
	}
	
	public static String parStructurer(String input){
	
		input = parCleaner(input);
		input = parSupplement(input);
		input = parMultiplyLogic(input);
		
		input = input.replace("p", Math.PI + "");
		input = input.replace("e", Math.E + "");
		
		String trim = ""; 
		
		for (int i = 0; i < input.length(); i++) {	
			if (String.valueOf(input.charAt(i)).equals(")")){
				for (int j = i; j >= 0; j--) {
					if (String.valueOf(input.charAt(j)).equals("(")){
						trim = input.substring(j+1, i);
						input = input.replace("("+trim+")", splitter(trim)+"");
						i = 0;
						break;
					} 
				}
			}	
		}
	
		return input;
	}
	
	public static String cleaner(String input, boolean extra) {
		
		if(extra==true){
			ArrayList<Character> list = new ArrayList<Character>();
			
			for(char c : input.toCharArray()) {
			    list.add(c);
			}
			
			for(int i = 0; i < list.size(); i++){
				int size = list.size()-1;
				
				if(String.valueOf(list.get(1)).equals(")")){
					list.remove(1);
					i = 0;
				}
				
				if(String.valueOf(list.get(size-1)).equals("(")){
					list.remove(size-1);
					i = 0;
				}
			}
			
			input = "";
			for(char c : list) {
			    input = input + c;
			}
			
		} else {
			input = input.replace(" ", "");
			input = input.replace("p", Math.PI+"");
			input = input.replace("e", Math.E+"");
			
			input = "(" + input + ")";
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
		
		if(returnValue==0.0) {
			returnValue = Double.parseDouble(parameter);
		}
		
		return returnValue;
	}

	public static String wutwut(String input) {
		
		input = parMultiplyLogic(input);
		input = cleaner(input, false);
		input = parSupplement(input);
		
		char[] inputArray = input.toCharArray();
		String[] sortArray = new String[input.length()];
		int counter = 0;
		int lastIndex;
		int firstIndex;
		String magic = input;
		System.out.println("First magic: " + magic);
		
		for(int a = 0; a < inputArray.length; a++){
			if(String.valueOf(inputArray[a]).equals(")")){
				lastIndex = a;
				for(int b = lastIndex; b >= 0; b--){
					if(String.valueOf(inputArray[b]).equals("(")){
						firstIndex = b;
						String temp = "";
						for(int c = firstIndex; c <= lastIndex; c++){
							temp = temp + inputArray[c];
							inputArray[c] = ' ';
						}
						temp = temp.replace(")", "");
						temp = temp.replace("(", "");
						if(containsDigit(temp)==true){
							sortArray[counter] = temp;
							magic = magic.replace("("+temp+")", splitter(temp) + "");
							counter++;
							a = 0;
						}
						break;
					}
				}
			}
		}
		
		System.out.println("Second magic: " + magic);
		
		String retrievedLine = "";
		String previousSum = "";
		String totalSum = "";
			
		for(int i = 0; i < counter; i++){
			retrievedLine = sortArray[i];
			totalSum = splitter(previousSum + retrievedLine) + "";
			previousSum = totalSum;
		}
			
		if(totalSum.isEmpty()){
			totalSum = splitter(cleaner(input, true).replace("(", "").replace(")", "")) + "";
		}
		
		return totalSum;
	}
	
	public static boolean containsDigit(String input) {  
	    boolean containsDigit = false;

	    if(input != null){
	        for(char c : input.toCharArray()){
	            if(Character.isDigit(c)){
	                containsDigit = true;
	                break;
	            }
	        }
	    }

	    return containsDigit;
	}
}
