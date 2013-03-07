package se.portalen.wolframbeta;

import java.util.ArrayList;

public class Calculatus {
	
	/**
	 * Denna metod inneh�ller logik som s�tter in g�ngertecken 
	 * det beh�vs i inputen. I och med den grundl�ggande matematiska
	 * regeln att t.ex. en siffra som st�r j�mte en variabel ska g�ngras med denna.
	 * 
	 * Returnerar en str�ng som �r i princip likadan som den som skickades in, fast
	 * med instatta g�ngertecken d�r det beh�vs
	 */
	private static String parMultiplyLogic(String input) {
		
		//H�r sker simpel ers�ttning av variabler till enklare
		//att arbeta med sj�lvst�ndiga bokst�ver.
		input = input.replace("pi", "p");
		input = input.replace("Pi", "p");
		input = input.replace("pI", "p");
		input = input.replace("PI", "p");
		input = input.replace("e", "e");
		input = input.replace("E", "e");
		
		//Listan som kommer att h�lla v�ran input i Characterformat
		ArrayList<Character> list = new ArrayList<Character>();
		
		//H�r omvandlas str�ngen input till en char array och sedan 
		//l�ggs varje char in i listan p� enskilda index.
		for(char c : input.toCharArray()) {
		    list.add(c);
		}
		
		//Loopen letar efter konstanter (i detta fall kan den dock bara finna e eller p)
		//p� varje index. Ifall den v�l hittar en konstant, kollar just denna loopen ifall det finns
		//n�got bakom konstanten som den kan multipliceras med.
		for(int i = 0; i < list.size(); i++){
			if(String.valueOf(list.get(i)).matches("[a-z]")){
				try {
					if(String.valueOf(list.get(i-1)).matches("[0-9]") || String.valueOf(list.get(i-1)).matches("\\)")
						|| String.valueOf(list.get(i-1)).matches("[a-z]")){
							list.add(i, '*');
							i = 0;
					}
					
				} catch (IndexOutOfBoundsException e){
				}
			}
		}
		
		//Samma som ovan fast kollar framf�r konstanten ist�llet.
		for(int i = 0; i < list.size(); i++){
			if(String.valueOf(list.get(i)).matches("[a-z]")){
				try {
					if(String.valueOf(list.get(i+1)).matches("[0-9]") || String.valueOf(list.get(i+1)).matches("\\(")
						|| String.valueOf(list.get(i+1)).matches("[a-z]")){
							list.add(i+1, '*');
							i = 0;
					}
					
				} catch (IndexOutOfBoundsException e){
				}
			}
		}
		
		//Denna loopen letar efter siffror som ligger intill parenteser. Just denna kommer att
		//titta p� indexen bakom indexen den hittar siffran p�. Ifall det nu finns en parentes v�nd
		//�t r�tt h�ll d�r s� s�tts ett mulitplikationstecken in.
		for(int i = 0; i < list.size(); i++){
			if(String.valueOf(list.get(i)).matches("[0-9]")){
				try {
					if(String.valueOf(list.get(i-1)).matches("\\)")){
							list.add(i, '*');
							i = 0;
					}
					
				} catch (IndexOutOfBoundsException e){
				}
			}
		}
		
		//Samma som ovan, fast kollar �t andra h�llet. 
		for(int i = 0; i < list.size(); i++){
			if(String.valueOf(list.get(i)).matches("[0-9]")){
				try {
					if(String.valueOf(list.get(i+1)).matches("\\(")){
							list.add(i+1, '*');
							i = 0;
					}
					
				} catch (IndexOutOfBoundsException e){
				}
			}
		}
		
		//Kollar efter ifall tv� parenteser som ligger j�mte varandra �r v�nda �t olika h�ll
		//, ifall s�, s�tts det in ett multiplikationstecken mellan dem.
		for(int i = 0; i < list.size(); i++){
			if(String.valueOf(list.get(i)).matches("\\)")){
				try {
					if(String.valueOf(list.get(i+1)).matches("\\(")){
							list.add(i+1, '*');
							i = 0;
					}
					
				} catch (IndexOutOfBoundsException e){
				}
			}
		}
		
		String reString = "";
		
		//H�r fylls str�ngen med varje char fr�n den nu-modifierade listan. 
		for(char c : list){
			reString = reString + c;
		}
		
		return reString;
	}
	
	/**
	 * Denna metoden tar bort o�nskade parenteser. 
	 */
	private static String parCleaner(String input) {
		
		//H�r tas on�diga mellanslag bort fr�n v�ran input.
		//D�refter g�r vi en char array som h�ller varje char i str�ngen.
		input = input.replace(" ", "");
		boolean counter = false;
		char[] cArray = input.toCharArray();
		
		//Denna loop letar efter tomma parenteser och tar bort dessa.
		for(int i = 0; i < cArray.length; i++){
			if(String.valueOf(input.charAt(i)).equals("(")){
				if(String.valueOf(input.charAt(i+1)).equals(")")){
					cArray[i] = ' ';
					cArray[i+1] = ' ';
				}
			}
		}
		
		//Den h�r loopen tittar p� varje index av v�ran array. Ifall den hittar en parentes, s� en if-sats
		//kolla p� ifall counter �r falsk eller inte. Om den �r falsk, g�r vi den till true, f�r att signalera att ytterligare
		//parenteser efter denna �r o�nskade. S� l�nge som counter f�rblir true kommer parenteser p� indexen som tittas
		//p� att tas bort. Om counter �r true och det inte finns en parentes p� indexen, g�r vi counter till false.
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
		
		String reString = "";
		
		//Sen tar vi alla chars i arrayen och sparar dem i v�ran returstr�ng.
		for(char a : cArray) {
		    reString = reString + a;
		}

		return reString.replace(" ", "");
	}
	
	/**
	 * Denna metod �r ett supplement till parCleaner. I och med hur parCleaner fungerar
	 * s� m�ste det l�ggas till parenteser p� n�gon av sidorna s� att parentesbalansen �terst�lls.
	 */
	private static String parSupplement(String input) {
		
		//Initialisering av variabler.
		char[] cArray = input.toCharArray();
		int length = cArray.length;
		int leftCount = 0;
		int rightCount = 0;
		String reString = "";
		
		//Simpel loop som r�knar alla parenteser och sparar vardera m�ngd
		//av dem i varsin integer.
		for(int i = 0; i < length; i++){
			if(String.valueOf(input.charAt(i)).equals("(")){
				leftCount++;
			} else if(String.valueOf(input.charAt(i)).equals(")")){
				rightCount++;
			}
		}
		
		int difference = leftCount-rightCount;
		
		//Sedan tar denna if-sats en titt p� skillnaden mellan v�nster och h�gerparenteser.
		//Ifall det finns mer av n�gon av dem, s�tts n�dv�ndiga parenteser till f�r att 
		//�terskapa balansen. Ifall de redan �r i balans g�rs inget.
		if(difference>0){
			char[] tempArray = new char[length+difference];
			
			for(int i = 0; i < length; i++){
				tempArray[i] = cArray[i];
			}
			for(int j = 0; j < difference; j++){
				tempArray[length+j] = ')';
			}
			for(char a : tempArray){
				reString = reString + a;
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
				reString = reString + a;
			}
		} else {
			reString = input;
		}

		return reString;
	}
	
	/**
	 * Denna metod tar parentes f�r parentes, efter att den r�a inputen har processerats via
	 * andra metoder, och skickar in den h�gsta prioriterings parentesen f�rst f�r att r�knas ut,
	 * sen den n�st h�gsta prioriteringsparentesen etc.
	 */
	public static String parStructurer(String input){
		
		try {
			input = parCleaner(input);
			input = parSupplement(input);
			input = parMultiplyLogic(input);
			input = "(" + input + ")";
			
			//H�r s�tts det in ordentliga v�rden p� konstanterna.
			input = input.replace("p", Math.PI + "");
			input = input.replace("e", Math.E + "");
			
			String trim = ""; 
			
			//Letar efter den f�rsta slutparentesen i str�ngen den g�r igenom. N�r den hittar den
			//s� g�r den bakl�nges tills den hittar n�rmsta begynnelseparentes.
			//Sedan skapas en substr�ng av parentesens inneh�ll som skickas in till utr�kningsmetoden.
			//Slutligen byts parenteserna och dess inneh�ll ut mot det utr�knade v�rdet, och loopen tittar vidare.
			for (int i = 0; i < input.length(); i++) {	
				if (String.valueOf(input.charAt(i)).equals(")")){
					for (int j = i; j >= 0; j--) {
						if (String.valueOf(input.charAt(j)).equals("(")){
							trim = input.substring(j+1, i);
							input = input.replace("("+trim+")", Calculation.calcSplitter(trim)+"");
							i = 0;
							break;
						} 
					}
				}	
			}
		
			return input;
			
			//N�gra simpla catches f�r eventuella fel som kan upptst� ifall
			//anv�ndaren skickar in bedr�vliga inputs.
		} catch (NullPointerException e) {
			return "Bad input";
		} catch (NumberFormatException e) {
			return "Bad input";
		} catch (ArrayIndexOutOfBoundsException e) {
			return "Bad input";
		}
	}
}