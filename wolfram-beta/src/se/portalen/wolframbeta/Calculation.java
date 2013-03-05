package se.portalen.wolframbeta;


import java.util.*;

public class Calculation {
	
	public static double Analyzer (char temp){
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
		
		return x;

	}
	
	public static ArrayList<String> Calculator(String Test,double raw[][], ArrayList<String> refined){
		double i = 0;

		
		while(Test.length() > i){
			
			if (raw[(int) i][0]>0){
				refined.add( Test.substring((int) i, Backwards(raw, i) ));
				i = Backwards(raw, i)-1;
				
			}
			if (raw[(int) i][1] > 0 && raw[(int) i][1] < 5){
				refined.add("" +  Test.charAt((int) i));
			}
			i++;
		}		
		
		return refined;
	}
	
	public static int Backwards(double raw[][], double i){
		
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
	
	public static void main(String[]args){
	
		String Test = "5*5*5-7/255+9-25*77/3";
		double i = 0;
		double[][] raw;
		raw = new double[Test.length()][2];
		char temp;
		
		
		while (i < Test.length()){
			
			temp = Test.charAt((int) i);
			
			if (Character.isDigit(temp) == true){
				
				raw[(int) i][0] = Character.getNumericValue(temp);
				raw[(int) i][1] = 0;
			}
			
			else {
				raw[(int)i][1] = Analyzer(temp);
				raw[(int)i][0] = 0;
			}
			i++;
		}
		
		
		ArrayList<String> refined = new ArrayList<String>();
		refined = Calculator(Test, raw, refined);
		System.out.println(Final(refined));
		
		
	}
	
	public static double Final (ArrayList<String> refined){
		double answer = 0;
		int i = 0;
		int x = 0;
		while(refined.size() > x ){
			
			System.out.println(refined.get(x));
						
			x++;
		}
		
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
		
		System.out.println(refined.get(0));
		
		return answer;
	}

}
