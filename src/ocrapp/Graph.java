// Package declaration
package ocrapp;

// Imports declaration
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
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
        this.graphEquation();
        this.showRoots();
    }

    /**
     * Constructs the BufferedImage by drawing and labeling the axes, 
     * and setting the background to white.
     */
    public void drawAxes() {                
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                if (x == xShift/zoom || y == yShift/zoom) {
                    image.setRGB(x, y, Color.LIGHT_GRAY.getRGB());
                } else {
                    image.setRGB(x, y, Color.WHITE.getRGB());
                }
            }
        }
        
        Graphics2D g = image.createGraphics();     
        
        g.setColor(Color.GRAY);
        g.setFont(new Font("Helvetica", Font.PLAIN, 10));
        for (int x = 0; x < image.getWidth(); x++) {
            if (x % 25 == 0) {
                String xLabel = String.valueOf(x - xShift);
                g.drawString(xLabel, x-10, yShift+12);
            }
        }
        for (int y = 0; y < image.getWidth(); y++) {
            if (y % 25 == 0 && y != yShift) {
                String yLabel = String.valueOf(-(y -yShift));
                g.drawString(yLabel, xShift-25, y+10);
            }
        }
    }
    
    /**
     * Displays the function on the BufferedImage.
     */
    public void graphEquation() {
        int xPrev = -xShift;
        int yPrev = (int) -(equation.a * xPrev * xPrev + equation.b * xPrev + equation.c);
        
        Graphics2D g = image.createGraphics();
        
        g.setColor(Color.RED);
        if (!equation.isVertical) {
            for (int x = -xShift; x < xShift; x+=1) {
                int y = (int) -(equation.a * x * x + equation.b * x + equation.c);
                g.drawLine(xPrev+xShift, yPrev+yShift, x+xShift, y+yShift);
                xPrev = x;
                yPrev = y;
            }
        } else {
            g.drawLine((int) equation.c + xShift, 0, (int) equation.c+xShift, image.getHeight());
        }
    }
    
    /**
     * Shows the roots on the BufferedImage.
     */
    public void showRoots() {
        Graphics2D g = image.createGraphics();
        
        g.setColor(Color.DARK_GRAY);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString("ROOT(S):", 25, image.getHeight()-75);
        g.setFont(new Font("Arial", Font.PLAIN, 15));
        
        if (equation.roots[0] == equation.roots[1]) {
            g.drawString(String.valueOf(equation.roots[1]), 25, image.getHeight()-50);
        } else {
            if (!Double.isNaN(equation.roots[0])) {
                g.drawString(String.valueOf(equation.roots[0]), 25, image.getHeight()-50);
            }
            if (!Double.isNaN(equation.roots[1])) {
                g.drawString(String.valueOf(equation.roots[1]), 25, image.getHeight()-25);
            }
        }
        if (Double.isNaN(equation.roots[0]) && Double.isNaN(equation.roots[1])) {
            g.drawString("No roots", 25, image.getHeight()-50);
        }
    }
}
