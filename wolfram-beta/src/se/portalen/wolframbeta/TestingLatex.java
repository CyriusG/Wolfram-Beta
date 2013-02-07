package se.portalen.wolframbeta;

public class TestingLatex {
	public static void main(String[] args) {
		TeXMaker texPareser = new TeXMaker();
		
		
		
		texPareser.parseTex("(5*(2/3)) - 56 + 5x - 46");
	}
}