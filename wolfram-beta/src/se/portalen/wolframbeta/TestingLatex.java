package se.portalen.wolframbeta;

import org.omg.CORBA.INTERNAL;
import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

import sun.font.LayoutPathImpl.EndType;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JLabel;


public class TestingLatex {
	public static void main(String[] args) {
		
		String input = "3x⁶ / / 5"; 
		String math = constructMath(input);
		
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
	
	public static String constructMath(String input) {
		ArrayList<String> mathConstruction = new ArrayList<String>();
		ArrayList<String> constructedBlocks = new ArrayList<String>();
		String constructBlock = "";
		int lastBlock = -1;
		String result = "";
		String mergedBlock = "";
		
		input = input.replace(" ", "");
		
		for (int i = 0; i < input.length(); i++) {
			mathConstruction.add(String.valueOf(input.charAt(i)));
		}
		
		int startIndex = 0;
		int endIndex = 0;
		for (int i = 0; i < mathConstruction.size(); i++) {
			if(mathConstruction.get(i).equals("/") || mathConstruction.get(i).equals("*") || mathConstruction.get(i).equals("+") || mathConstruction.get(i).equals("-")) {
				for (int j = i; j > 0; j--) {
					if(mathConstruction.get(j).equals("/") || mathConstruction.get(j).equals("*") || mathConstruction.get(j).equals("+") || mathConstruction.get(j).equals("-")) {
						startIndex = lastBlock + 1;
						endIndex = j;
						lastBlock = i;
						break;
					}
				}
				constructBlock = "";
				for (int j = startIndex; j < endIndex; j++) {
					constructBlock = constructBlock + mathConstruction.get(j);
					if(j == endIndex - 1) {
						constructedBlocks.add(constructBlock);
						constructedBlocks.add(mathConstruction.get(endIndex));
					}
				}
				
				
			}
			if(i == mathConstruction.size() - 1) {
				constructBlock = "";
				startIndex = lastBlock + 1;
				endIndex = mathConstruction.size();
				
				for (int j = startIndex; j < endIndex; j++) {
					constructBlock = constructBlock + mathConstruction.get(j);
				}
				
				constructedBlocks.add(constructBlock);
			}
		}
		
		for (int i = 0; i < constructedBlocks.size(); i++) {
			if(i != constructedBlocks.size() - 1) {
				for (int j = 0; j < constructedBlocks.size(); j++) {
					if(constructedBlocks.get(i).startsWith("(") && constructedBlocks.get(j).endsWith(")")) {
						constructedBlocks.set(i, constructedBlocks.get(i).replace("(", ""));
						constructedBlocks.set(j, constructedBlocks.get(j).replace(")", ""));
						
						mergedBlock = constructedBlocks.get(i);
						for (int j2 = i + 1; j2 < j + 1; j2++) {						
							mergedBlock = mergedBlock + constructedBlocks.get(j2);
						}
						constructedBlocks.set(i, mergedBlock); 
						for (int j2 = i + 1; j2 < j + 1; j2++) {
							constructedBlocks.remove(i + 1);
						}
					}
				}
			}
		}
		
		for (int i = 0; i < constructedBlocks.size(); i++) {
			if(constructedBlocks.get(i).equals("/") && i > 0) {
				String firstBlock = constructedBlocks.get(i - 1);
				String secondBlock = constructedBlocks.get(i + 1);
				
				constructedBlocks.set(i - 1, "\\frac {" + firstBlock + "}{" + secondBlock + "}");
				constructedBlocks.remove(i);
				constructedBlocks.remove(i);
			}
		}
		
		for (int i = 0; i < constructedBlocks.size(); i++) {	
			result = result + constructedBlocks.get(i);
		}
		
		return result;
	}
}