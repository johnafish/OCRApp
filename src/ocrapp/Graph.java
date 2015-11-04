package ocrapp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import static javax.swing.JFrame.DISPOSE_ON_CLOSE;

/**
 * @author David Chen <Darksteal132@gmail.com>
 * @author John Fish <john@johnafish.ca>
 */

public class Graph extends JFrame {
    
    public BufferedImage image;
    public double[] roots;
    public double zoom = 1;
    public Equation e;
    public String equation;
    
    /**
     * Constructor of Graph that takes an Equation object as the argument
     * @param e equation object
     */
    public Graph(Equation e) {
        super("Graph");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        this.e = e;
    }
    
    /**
     * Constructor of Graph that takes an equation in the form of a String
     * as the argument. Use this constructor only if the Equation class 
     * is not completed yet.
     * @param equation equation in the form of a String
     */
    public Graph(String equation) {
        super("Graph");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        this.equation = equation;
    }
    
    /**
     * Constructs the BufferedImage using setRGB().
     */
    private void construct() {
        image = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                if (x == getWidth()/2/zoom || y == getHeight()/2/zoom) {
                    image.setRGB(x, y, Color.GRAY.getRGB());
                } else {
                    image.setRGB(x, y, Color.WHITE.getRGB());
                }
            }
        }
        /*
        if (e.degree == 1) {
            
        }
        if (e.degree == 2) {
            
        }
        */
    }
    
    /**
     * Displays the BufferedImage, roots, title, and buttons
     * @param g graphics object
     */
    @Override
    public void paint(Graphics g) {
        this.construct();
        g.drawImage(image, 0, 0, this);
    }
    
}
