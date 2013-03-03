package se.portalen.wolframbeta;

public class Calculatus {

	public static void main(String[]args){
		System.out.println(parStructurer("((())))))((())((pi)))(((()*3)))))+3)+5)"));
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
		}

		return returnValue;
	}
	
	public static String parCleaner(String input) {
		char[] cArray = input.toCharArray();
		int counter = 0;
		String returnVal = "";
		
		for(int i = 0; i < cArray.length; i++){
			if(((String.valueOf(input.charAt(i)).equals("(")) || (String.valueOf(input.charAt(i)).equals(")"))) && (counter==0)){
				counter++;
			} else if ((String.valueOf(input.charAt(i)).equals("(")) && (counter!=0)){
				cArray[i] = ' ';
			} else if ((String.valueOf(input.charAt(i)).equals(")")) && (counter!=0)){
				cArray[i] = ' ';
			} else {
				counter = 0;
			}
		}
		
		for(char a : cArray) {
		    returnVal = returnVal + a;
		}
		
		return returnVal.replace(" ", "");
	}
	
	public static String parStructurer(String input){
		input = input.replace(" ", "");
		input = input.replace("pi", Math.PI+"");
		input = input.replace("Pi", Math.PI+"");
		input = input.replace("pI", Math.PI+"");
		input = input.replace("PI", Math.PI+"");
		input = input.replace("e", Math.E+"");
		input = input.replace("E", Math.E+"");
		
		input = parCleaner(input);
		input = parSupplement(input);
		
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
}
