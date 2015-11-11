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

public class Graph {
    
    // Variables declaration
    public BufferedImage image;
    public double[] roots;
    public double zoom = 1;
    public Equation e;
    public String equation;
    private final int xShift, yShift;
    private final int width,height;
    // End of variables declaration
    
    /**
     * Constructor of Graph that takes an Equation object as the argument.
     * @param e equation object
     */
    public Graph(Equation e, int w, int h) {
        this.e = e;
        this.width = w;
        this.height = h;
        this.xShift = this.width/2;
        this.yShift = this.height/2;
        this.construct();
    }

    
    /**
     * Constructs the BufferedImage using setRGB().
     */
    public void construct() {        
        image = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);
        
        // Draws the x-axis and y-axis, and sets the rest of the image to white
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
}
