package ocrapp;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

/**
 * @author David Chen <Darksteal132@gmail.com>
 * @author John Fish <john@johnafish.ca>
 */

public class Graph extends JFrame {
    
    public BufferedImage image;
    public double[] roots;
    public Equation e;
    public String equation;
    
    /**
     * Constructor of Graph
     * @param e 
     */
    public Graph(Equation e) {
        this.e = e;
    }
    
    public Graph(String equation) {
        //this.equation = "3x^2";
    }
    
    /**
     * Constructs the BufferedImage using setRGB()
     */
    public void construct() {
        
    }
    
    /**
     * Displays the BufferedImage, roots, title, and buttons
     * @param g 
     */
    public void display(Graphics g) {
        
    }
    
}
