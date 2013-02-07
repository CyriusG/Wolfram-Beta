package se.portalen.wolframbeta;

public class Calculatus {

	public static void main(String[]args){
		System.out.println(structurer("((((5*3)/6)*9)+95)-7"));
	}
	
	public static String structurer(String input){
		
		String trim = ""; 
		int leftCount = 0;
		int rightCount = 0;
		input = "("+input+")";
		
		input = input.replace(" ", "");
		
		input = input.replace("pi", Math.PI+"");
		input = input.replace("Pi", Math.PI+"");
		input = input.replace("pI", Math.PI+"");
		input = input.replace("PI", Math.PI+"");
		
		input = input.replace("e", Math.E+"");
		input = input.replace("E", Math.E+"");
		
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
		
		for (int i = 0; i < input.length(); i++) {
			if (String.valueOf(input.charAt(i)).equals(")")){
				rightCount++;
			}
		}
		
		for (int i = 0; i < input.length(); i++) {
			if (String.valueOf(input.charAt(i)).equals("(")){
				leftCount++;
			}
		}
		
		int numPar = rightCount-leftCount;
		
		if(numPar!=0){
			if((numPar)>0){
				for(int i=0; i<numPar; i++){
					input = input.substring(0, input.length()-1);
				}
			} else if((numPar)<0){
				for(int i=0; i>numPar; i--){
					input = input.substring(1, input.length());
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
