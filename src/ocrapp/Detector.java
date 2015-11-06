package ocrapp;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author John Fish <john@johnafish.ca>
 */
public class Detector {
    String content;
    BufferedImage image;
    BufferedImage[] letterImages;
    double[][][] minimizedLetters;
    
    public Detector(BufferedImage i){
        this.image = i;
        this.binarize();
    }
    
    public void isolateLetters(){
        //Isolate letters here and populate in letterImages
    }
    
    public void minimizeLetters(){
        //Convert isolated letter images into 2D arrays of 5x5 of binary values
    }
    
    public void guessLetters(){
        //Populate content string with guesses from database
    }
    
    public void populateDB(String correct){
        //Populate the database with the letters and their minimized information
    }
    
    public void binarize(){
        for (int i = 0; i < this.image.getWidth(); i++) {
            for (int j = 0; j < this.image.getHeight(); j++) {
                Color pixelValue = new Color(this.image.getRGB(i, j));
                int sum = pixelValue.getRed()+pixelValue.getBlue()+pixelValue.getGreen();
                if (sum>330){
                    this.image.setRGB(i, j, Color.white.getRGB());
                } else {
                    this.image.setRGB(i, j, Color.black.getRGB());
                }
            }
        }
        saveImage(this.image, "test");
    }
    
    public static void saveImage(BufferedImage img, String name){
        try {
            File outputFile = new File(name+".png");
            ImageIO.write(img, "png", outputFile);
        } catch (IOException e){
            System.out.println("Error saving.");
        }
    }
}
