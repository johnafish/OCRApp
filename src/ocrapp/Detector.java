package ocrapp;

import java.awt.image.BufferedImage;

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
}
