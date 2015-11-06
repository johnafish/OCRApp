package ocrapp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import static javax.swing.JFrame.DISPOSE_ON_CLOSE;

/**
 * @author David Chen <Darksteal132@gmail.com>
 * @author John Fish <john@johnafish.ca>
 */

public class OCRApp extends JFrame {

    public OCRApp(){
        super("OCR App");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
    @Override
    public void paint(Graphics g) {
        //Background colour
        g.setColor(Color.white);
        g.fillRect(0, 0, 800, 600);
    }
    
    public static void main(String[] args) {
        new OCRApp().setVisible(true);
        try {
            BufferedImage img = ImageIO.read(new File("equation.jpg"));
            Detector d = new Detector(img);
        } catch (IOException e){
            System.out.println("yo");
        }
    }
        
        //new Graph("mx+b").setVisible(true);
}