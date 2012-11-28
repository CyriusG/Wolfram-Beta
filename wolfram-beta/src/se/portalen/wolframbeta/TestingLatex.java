package se.portalen.wolframbeta;

import org.omg.CORBA.INTERNAL;
import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;


public class TestingLatex {
	public static void main(String[] args) {
		String doMath = parseText("5 + 3 / 5");
		generateImage(doMath);
	}
	
	public static String parseText(String input) {
		
		input.replace(" ", "");
		
		String result = "";
		
		for (int i = 0; i < input.length(); i++) {
			result = result + identiySign(String.valueOf(input.charAt(i))) + " ";
		}
		
		return result;
	}
	
	public static String identiySign(String sign) {
		String[] signs = new String[5];
		String result = "";
		
		System.out.println(sign);
		
		signs[0] = "+";
		signs[1] = "-";
		signs[2] = "/";
		signs[3] = "\\";
		signs[4] = "*";
				
		for (int i = 0; i < signs.length; i++) {
			if(sign.equals(signs[i])) {
				result = signs[i];
				break;
			}
		}
		
		if(signs.equals("")) {
			result = sign;;
		}
		else if(sign.equals(signs[2]) || signs.equals(signs[3])) {
			result = "\\over";
		}
		
		return result;
	}
	
	public static void generateImage(String math) {
		TeXFormula fomule = new TeXFormula(math);
	    TeXFormula formula = new TeXFormula(fomule);
	    TeXIcon icon = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, 20);

	    icon.setInsets(new Insets(5, 5, 5, 5));
	    BufferedImage image = new BufferedImage(icon.getIconWidth(),
	    icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = image.createGraphics();
	    g2.setColor(Color.white);
	    g2.fillRect(0, 0, icon.getIconWidth(), icon.getIconHeight());
	    JLabel jl = new JLabel();
	    jl.setForeground(new Color(0, 0, 0));
	    icon.paintIcon(jl, g2, 0, 0);
	    File file = new File("AlphaBeta.png");
	    try {
	        ImageIO.write(image, "png", file.getAbsoluteFile());
	    } catch (IOException ex) {
	        ex.getMessage();
	    }
	}
}
