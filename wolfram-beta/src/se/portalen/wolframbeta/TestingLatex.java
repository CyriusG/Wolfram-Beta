package se.portalen.wolframbeta;

public class TestingLatex {
	public static void main(String[] args) {
		TeXMaker texPareser = new TeXMaker();
		
		
		
		System.out.println(texPareser.parseTex("(2 * (2/3)) + 5x"));
	}
}