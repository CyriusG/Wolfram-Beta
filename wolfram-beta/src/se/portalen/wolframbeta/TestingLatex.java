package se.portalen.wolframbeta;

public class TestingLatex {
	public static void main(String[] args) {
		EquationGen equationGen = new EquationGen();
		TeXMaker texPareser = new TeXMaker();
		
		equationGen.generateEquation(texPareser.parseTex("5pix^5) / (7 + 5x - 4x^2)"), "Fancy");
	}
}