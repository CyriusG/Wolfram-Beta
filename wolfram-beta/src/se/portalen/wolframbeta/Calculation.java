package se.portalen.wolframbeta;


import java.util.*;

public class Calculation {
	//Metoden calcAnalyzer jämför tecken som inte är siffror ger dom ett värde i arrayn
	public static double calcAnalyzer (char temp){
		double x = 0;
		
		if (temp == '/'){
			x = 1;
			
		}
		
		if (temp == '*'){
			x = 2;
			
		}
		
		if (temp == '+'){
			x = 3;
			
		}
		
		if (temp == '-'){
			x = 4;
		}
		
		if (temp == '.' || temp == '.'){
			x = 5;
		}
		//returner värde som ska sättas in i arrayn
		return x;

	}
	
	public static ArrayList<String> calcCalculator(String input,double raw[][], ArrayList<String> refined){
		double i = 0;

		//calcCalculator skapar arrayn reined genom att räkna ut vad som är nummer och operatörer i raw arrayn
		while(input.length() > i){
			
			//anroper metoden calc backwards och sedan lägger till ett nummer i arrayrlistan refined
			if (raw[(int) i][1] == 0){
				refined.add( input.substring((int) i, calcBackwards(raw, i) ));
				i = calcBackwards(raw, i)-1;
				
			}
			// lägger till en operator
			if (raw[(int) i][1] > 0 && raw[(int) i][1] < 5){
				refined.add("" +  input.charAt((int) i));
			}
			i++;
		}
		
		//retunerar Arraylistan refined
		
		return refined;
	}
	
	public static int calcBackwards(double raw[][], double i){
		//räknar ut när nästa operator kommer finnas och retunerar dess position
		boolean fixed = false;
		
		 while (i < raw.length && fixed == false){
			 
			 if (raw[(int)i][1] > 0 && raw[(int)i][1]<5){
				 
				 fixed = true;
				 break;
			 }
			 i++;
			 
		 }
		
		return (int)i;
	}
	
	public static double calcSplitter(String input){
		//Variablar deklareras

		System.out.println(input);
		double i = 0;
		double[][] raw;
		raw = new double[input.length()][2];
		char temp;
		
		
		//skapar raw arrayn
		while (i < input.length()){
			
			temp = input.charAt((int) i);
			
			if (Character.isDigit(temp) == true){
				
				raw[(int) i][0] = Character.getNumericValue(temp);
				raw[(int) i][1] = 0;
			}
			
			else {
				raw[(int)i][1] = calcAnalyzer(temp);
				raw[(int)i][0] = 0;
			}
			i++;
		}
		
		
		ArrayList<String> refined = new ArrayList<String>();
		
		//Kallar på en metod som gör om raw arrayn till refined arraylistan
		refined = calcCalculator(input, raw, refined);
		//reyturnerar det färdigberäknade resultatet
		return calcFinal(refined);
		
		
	}
	//Gör om arraylistan till ett double värde
	public static double calcFinal (ArrayList<String> refined){
		double answer = 0;
		int i = 0;
		int x = 0;
		while(refined.size() > x ){
			
			System.out.println(refined.get(x));
						
			x++;
		}
		
		//går igenom arrayn och letar efter operatörer, hittar den en så gör den en matematisk opereration med dom tecken so ligger nära den
		
		while (refined.size() > i){
			
			if (refined.get(i).compareTo("/") == 0){
				refined.remove(i);
				refined.add(i, ("" + (Double.parseDouble(refined.get(i-1))) / (Double.parseDouble(refined.get(i)))));
				refined.remove(i+1);
				refined.remove(i-1);
				i = 0;
			}
			
			if (refined.get(i).compareTo("*") == 0){
				refined.remove(i);
				refined.add(i, ("" + (Double.parseDouble(refined.get(i-1))) * (Double.parseDouble(refined.get(i)))));
				refined.remove(i+1);
				refined.remove(i-1);
				i = 0;
			}		
			

			i++;
		}
		
		i = 0;
		
		while (refined.size() > i){
			
			
			if (refined.get(i).compareTo("+") == 0){
				refined.remove(i);
				refined.add(i, "" + ((Double.parseDouble(refined.get(i-1))) + (Double.parseDouble(refined.get(i)))));
				refined.remove(i+1);
				refined.remove(i-1);
				i = 0;

			}
			
			if (refined.get(i).compareTo("-") == 0){
				refined.remove(i);
				refined.add(i, "" +((Double.parseDouble(refined.get(i-1))) - (Double.parseDouble(refined.get(i)))));
				refined.remove(i+1);
				refined.remove(i-1);
				i = 0;

							
			}
			i++;
		}
		
		answer = Double.parseDouble(refined.get(0));
		
		//returnerar en double med det färdiga svaret
		return answer;
	}

}
