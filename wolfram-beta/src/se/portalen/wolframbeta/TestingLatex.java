package se.portalen.wolframbeta;

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
		 String math =  "\\mathbf{V}_1 \\times \\mathbf{V}_2 =  \\begin{vmatrix}" +
		 		"\\mathbf{i} & \\mathbf{j} & \\mathbf{k} \\" +
		 		"\\frac{\\partial X}{\\partial u} &  \\frac{\\partial Y}{\\partial u} & 0 \\" +
		 		"\\frac{\\partial X}{\\partial v} &  \\frac{\\partial Y}{\\partial v} & 0" +
		 		"\\end{vmatrix}";

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
