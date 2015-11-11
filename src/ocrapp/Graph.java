// Package declaration
package ocrapp;

// Imports declaration
import java.awt.Color;
import java.awt.image.BufferedImage;
// End of imports declaration

/**
 * @author David Chen
 * @author John Fish
 * @author Ryan Mandur
 */
public final class Graph {
    
    // Variables declaration
    public BufferedImage image;
    public double zoom;
    public Equation equation;
    private final int xShift, yShift;
    private final int width, height;
    // End of variables declaration
    
    /**
     * Constructor of Graph that takes an Equation object as the argument.
     * @param equation equation object
     * @param width width of BufferedImage
     * @param height height of BufferedImage
     */
    public Graph(Equation equation, int width, int height) {
        this.equation = equation;
        this.zoom = 1;
        this.width = width;
        this.height = height;
        this.image = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);
        this.xShift = this.width/2;
        this.yShift = this.height/2;
        
        this.drawAxes();
        //this.graphEquation();
    }

    /**
     * Constructs the BufferedImage by drawing the axes, 
     * and setting the background to white.
     */
    public void drawAxes() {                
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                if (x == xShift/zoom || y == yShift/zoom) {
                    image.setRGB(x, y, Color.GRAY.getRGB());
                } else {
                    image.setRGB(x, y, Color.WHITE.getRGB());
                }
            }
        }
    }
    
    /**
     * Displays the function on the BufferedImage.
     */
    public void graphEquation() {
        if (equation.degree == 1) {
            for (int x = 0; x < this.width; x++) {
                int y = (int) (equation.b * x + equation.c);
                image.setRGB(x, y, Color.GREEN.getRGB());
            }
        }
        if (equation.degree == 2) {
            
        }
    }
}
