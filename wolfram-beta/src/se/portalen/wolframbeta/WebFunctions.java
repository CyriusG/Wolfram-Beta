package se.portalen.wolframbeta;

import java.io.File;

import org.apache.catalina.connector.Request;

public class WebFunctions {
	static final String siteTitle = "WolframBeta";
	
	
	public static String getTitle() {
		return siteTitle;
	}
	
	public static String generateEqName(String equation) {
		String name = "eq_";
		name.replace(" ", "");
		
		for (int i = 0; i < equation.length(); i++) {
			name = name + ((int) equation.charAt(i));
		}
		
		return name;
	}
	
	public static boolean checkIfEqExists(String name) {
		File file = new File("temp/equations/" + name + ".png");
		
		if(file.exists()) {
			return true;
		}
		else
			return false;
	}
}
