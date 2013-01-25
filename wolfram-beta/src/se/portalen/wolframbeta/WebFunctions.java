package se.portalen.wolframbeta;

import java.io.File;

public class WebFunctions {
	static final String siteTitle = "WolframBeta";
	
	public static String getTitle() {
		return siteTitle;
	}
	
	public static String generateEqName(String equation) {
		if(equation != null) {
			String name = "eq_";
			equation = equation.replace(" ", "");
		
			for (int i = 0; i < equation.length(); i++) {
				name = name + ((int) equation.charAt(i));
			}
		
			return name;
		}
		else
			return "error no equation.";
	}
	
	/**
	 * Returns 1, 0 or 3.
	 * If it returns 1 it means that the file does exist.
	 * If it returns 0 it means that the file doesn't exist.
	 * If it returns 3 it means that there was a error.
	 * @param name
	 * @return
	 */
	public static int checkIfEqExists(String name) {
		if(!name.contains("error")) {
			File file = new File("temp/equations/" + name + ".png");
			
			if(file.exists()) {
				return 1;
			}
			else
				return 0;
		}
		else
			return 3;
	}
	
	public static void generateEqImage(String equation, String name, String path) {
		
		TeXMaker texMaker = new TeXMaker();
		EquationGen eqGen = new EquationGen();
		
		eqGen.generateEquation(texMaker.parseTex(equation), name, path);
	}
}
