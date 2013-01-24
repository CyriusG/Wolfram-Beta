package se.portalen.wolframbeta;

import org.scilab.forge.jlatexmath.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.swing.JLabel;

public class EquationGen extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Generate the equation image.
	 */
	public void generateEquation(String input, String name) {
		
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
	    // Set the background colour to white.
	    g2.setColor(Color.white);
	    g2.fillRect(0, 0, icon.getIconWidth(), icon.getIconHeight());
	    // Create a JLabel which will act as the foreground and make it black.
	    JLabel jl = new JLabel();
	    jl.setForeground(new Color(0, 0, 0));
	    // Paint the equation using the graphics and JLabel.
	    icon.paintIcon(jl, g2, 0, 0);
	    // Create a new file with the specified name so that it can be found later. 
	    File file = new File("C:/Users/Elev 3C/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/wolfram-beta/temp/equations/" + name + ".png");
	    // Write it to disk.
	    try {
	    	// Make first sure that the folders exists.
	    	checkFolder("C:/Users/Elev 3C/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/wolfram-beta/temp/equations/");
	    	// Write to disk only if there's no file with the same name.
	    	//if(!checkFileExists(file)) {
	    		ImageIO.write(image, "png", file);
	    	//}
	    } catch (IOException ex) {
	        System.out.println(ex.getMessage());
	    }
	    
	    cleanFolder(file);
	}
	
	private void cleanFolder(File keepFile) {
		File directory = new File("C:/Users/Elev 3C/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/wolfram-beta/temp/equations/");
		
		if(folderSize(directory) > 524288000) {
			for(File file : directory.listFiles()) {
				System.out.println("Keep this: " + keepFile);
				System.out.println("Regular file " + file);
				if(file.isFile() && file != keepFile)  {
					file.delete();
				}
			}
		}
	}
	
	private long folderSize(File directory) {
		long length = 0;
		
		for(File file : directory.listFiles()) {
			if(file.isFile()) 
				length += file.length();
			else
				length += folderSize(file);
		}
		
		return length;
	}
	
	/**
	 * Makes sure the folder where the file is being created exists.
	 * @param folder
	 */
	private void checkFolder(String folder) {
		File f = new File(folder).getAbsoluteFile();
		
		// If the folders doesn't exists create them.
		if(!f.exists()) {
			f.mkdirs();
		}
	}
	
	/**
	 * Returns true if the file exists.
	 * @param file
	 */
	private static boolean checkFileExists(File file) {
		if(file.exists())
			return true;
		else
			return false;
	}
}
