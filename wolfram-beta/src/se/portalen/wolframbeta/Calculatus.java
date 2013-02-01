package se.portalen.wolframbeta;

public class Calculatus {

	public static void main(String[]args){
		System.out.println(trimmer("(((  (  2.  5    *pi*e*e  )* 5.  0  ) *2))))))"));
	}
	
	public static String trimmer(String input){
		
		input = input.replace(" ", "");
		input = input.replace("pi", Math.PI+"");
		input = input.replace("e", Math.E+"");
		
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
