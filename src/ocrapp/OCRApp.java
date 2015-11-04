package ocrapp;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;

/**
 * @author John Fish <john@johnafish.ca>
 */

public class OCRApp extends JFrame {

    public OCRApp(){
        this.setSize(800, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("OCR App");
        this.setVisible(true);
    }
    
    @Override
    public void paint(Graphics g){
        //Background colour
        g.setColor(Color.white);
        g.fillRect(0, 0, 800, 600);
    }
    
    public static void main(String[] args) {
        OCRApp app = new OCRApp();
    }

}
