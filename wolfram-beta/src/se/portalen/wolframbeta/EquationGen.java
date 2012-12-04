package se.portalen.wolframbeta;

import org.omg.CORBA.INTERNAL;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

import org.eclipse.jdt.internal.compiler.util.GenericXMLWriter;
import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

public class EquationGen {
	static Random rand = new Random();
	
	public static void main(String[] args) {
		generateEquation("\\frac{5$\\pi$x^5}{7 +5x - 4x^2}", "eq_833809");
	}
	
	public EquationGen() {}
	
	/**
	 * Generate the equation image.
	 */
	public static void generateEquation(String input, String name) {
		// Parse the text that the user inputs.
		TeXFormula fomule = new TeXFormula(input);
		// After that make the parsed text into a formula which can later be rendered.
	    TeXFormula formula = new TeXFormula(fomule);
	    TeXIcon icon = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, 20);
	    
	    // Start rendering the image.
	    icon.setInsets(new Insets(5, 5, 5, 5));
	    // Create a image the same size as the equation.
	    BufferedImage image = new BufferedImage(icon.getIconWidth(),
	    icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
	    // Make it into graphics which will act as the background..
	    Graphics2D g2 = image.createGraphics();
	    // Set the backgroundc olour to white.
	    g2.setColor(Color.white);
	    g2.fillRect(0, 0, icon.getIconWidth(), icon.getIconHeight());
	    // Create a JLabel which will act as the foreground and make it black.
	    JLabel jl = new JLabel();
	    jl.setForeground(new Color(0, 0, 0));
	    // Paint the equation using the graphics and JLabel.
	    icon.paintIcon(jl, g2, 0, 0);
	    // Create a new file with the specified name so that it can be found later.
	    File file = new File("WebContent/temp/equations/" + name + ".png");
	    // Write it to disk.
	    try {
	    	checkFolder("WebContent/temp/equations/");
	    	checkFileExists(file);
	        ImageIO.write(image, "png", file.getAbsoluteFile());
	    } catch (IOException ex) {
	        ex.getMessage();
	    }
	}
	
	/**
	 * Makes sure the folder where the file is being created exists.
	 * @param folder
	 */
	private static void checkFolder(String folder) {
		File f = new File(folder).getAbsoluteFile();
		
		if(!f.exists()) {
			f.mkdirs();
		}
	}
	
	private static void checkFileExists(File file) {
		if(file.exists()) {
			file.delete();
		}
	}
}
