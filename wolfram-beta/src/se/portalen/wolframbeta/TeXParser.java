package se.portalen.wolframbeta;

public class TeXParser {
	
	public TeXParser() {}
	
	final Texcommands texcommands;
	
	enum Texcommands {
		PI("$\\pi$");
		
		private final String texcommands;
		
		Texcommands(String texcommands) {
			this.texcommands = texcommands;
		}
	}
	
}
