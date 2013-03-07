package se.portalen.wolframbeta;

import java.util.ArrayList;

public class Calculatus {
	
	/**
	 * Denna metod innehåller logik som sätter in gångertecken 
	 * det behövs i inputen. I och med den grundläggande matematiska
	 * regeln att t.ex. en siffra som står jämte en variabel ska gångras med denna.
	 * 
	 * Returnerar en sträng som är i princip likadan som den som skickades in, fast
	 * med instatta gångertecken där det behövs
	 */
	private static String parMultiplyLogic(String input) {
		
		//Här sker simpel ersättning av variabler till enklare
		//att arbeta med självständiga bokstäver.
		input = input.replace("pi", "p");
		input = input.replace("Pi", "p");
		input = input.replace("pI", "p");
		input = input.replace("PI", "p");
		input = input.replace("e", "e");
		input = input.replace("E", "e");
		
		//Listan som kommer att hålla våran input i Characterformat
		ArrayList<Character> list = new ArrayList<Character>();
		
		//Här omvandlas strängen input till en char array och sedan 
		//läggs varje char in i listan på enskilda index.
		for(char c : input.toCharArray()) {
		    list.add(c);
		}
		
		//Loopen letar efter konstanter (i detta fall kan den dock bara finna e eller p)
		//på varje index. Ifall den väl hittar en konstant, kollar just denna loopen ifall det finns
		//något bakom konstanten som den kan multipliceras med.
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
		
		//Samma som ovan fast kollar framför konstanten istället.
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
		//titta på indexen bakom indexen den hittar siffran på. Ifall det nu finns en parentes vänd
		//åt rätt håll där så sätts ett mulitplikationstecken in.
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
		
		//Samma som ovan, fast kollar åt andra hållet. 
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
		
		//Kollar efter ifall två parenteser som ligger jämte varandra är vända åt olika håll
		//, ifall så, sätts det in ett multiplikationstecken mellan dem.
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
		
		//Här fylls strängen med varje char från den nu-modifierade listan. 
		for(char c : list){
			reString = reString + c;
		}
		
		return reString;
	}
	
	/**
	 * Denna metoden tar bort oönskade parenteser. 
	 */
	private static String parCleaner(String input) {
		
		//Här tas onödiga mellanslag bort från våran input.
		//Därefter gör vi en char array som håller varje char i strängen.
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
		
		//Den här loopen tittar på varje index av våran array. Ifall den hittar en parentes, så en if-sats
		//kolla på ifall counter är falsk eller inte. Om den är falsk, gör vi den till true, för att signalera att ytterligare
		//parenteser efter denna är oönskade. Så länge som counter förblir true kommer parenteser på indexen som tittas
		//på att tas bort. Om counter är true och det inte finns en parentes på indexen, gör vi counter till false.
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
		
		//Sen tar vi alla chars i arrayen och sparar dem i våran retursträng.
		for(char a : cArray) {
		    reString = reString + a;
		}

		return reString.replace(" ", "");
	}
	
	/**
	 * Denna metod är ett supplement till parCleaner. I och med hur parCleaner fungerar
	 * så måste det läggas till parenteser på någon av sidorna så att parentesbalansen återställs.
	 */
	private static String parSupplement(String input) {
		
		//Initialisering av variabler.
		char[] cArray = input.toCharArray();
		int length = cArray.length;
		int leftCount = 0;
		int rightCount = 0;
		String reString = "";
		
		//Simpel loop som räknar alla parenteser och sparar vardera mängd
		//av dem i varsin integer.
		for(int i = 0; i < length; i++){
			if(String.valueOf(input.charAt(i)).equals("(")){
				leftCount++;
			} else if(String.valueOf(input.charAt(i)).equals(")")){
				rightCount++;
			}
		}
		
		int difference = leftCount-rightCount;
		
		//Sedan tar denna if-sats en titt på skillnaden mellan vänster och högerparenteser.
		//Ifall det finns mer av någon av dem, sätts nödvändiga parenteser till för att 
		//återskapa balansen. Ifall de redan är i balans görs inget.
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
	 * Denna metod tar parentes för parentes, efter att den råa inputen har processerats via
	 * andra metoder, och skickar in den högsta prioriterings parentesen först för att räknas ut,
	 * sen den näst högsta prioriteringsparentesen etc.
	 */
	public static String parStructurer(String input){
		
		try {
			input = parCleaner(input);
			input = parSupplement(input);
			input = parMultiplyLogic(input);
			input = "(" + input + ")";
			
			//Här sätts det in ordentliga värden på konstanterna.
			input = input.replace("p", Math.PI + "");
			input = input.replace("e", Math.E + "");
			
			String trim = ""; 
			
			//Letar efter den första slutparentesen i strängen den går igenom. När den hittar den
			//så går den baklänges tills den hittar närmsta begynnelseparentes.
			//Sedan skapas en substräng av parentesens innehåll som skickas in till uträkningsmetoden.
			//Slutligen byts parenteserna och dess innehåll ut mot det uträknade värdet, och loopen tittar vidare.
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
			
			//Några simpla catches för eventuella fel som kan upptstå ifall
			//användaren skickar in bedrövliga inputs.
		} catch (NullPointerException e) {
			return "Bad input";
		} catch (NumberFormatException e) {
			return "Bad input";
		} catch (ArrayIndexOutOfBoundsException e) {
			return "Bad input";
		}
	}
}