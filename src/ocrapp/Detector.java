package ocrapp;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

/**
 *
 * @author John Fish <john@johnafish.ca>
 */
public class Detector {
    String content;
    BufferedImage image;
    List<BufferedImage> letterImages;
    double[][][] minimizedLetters;
    
    public Detector(BufferedImage i){
        this.image = i;
        this.letterImages = new ArrayList<BufferedImage>();
        this.binarize();
        this.isolateLetters();
        this.saveCharacters();
    }
    
    public void isolateLetters(){
        //Isolate letters here and populate in letterImages
        boolean onLetter = false;
        List<Integer> leftX = new ArrayList<Integer>();
        List<Integer> rightX = new ArrayList<Integer>();
        List<Integer> topY = new ArrayList<Integer>();
        List<Integer> bottomY = new ArrayList<Integer>();
        
        for (int i = 0; i < this.image.getWidth(); i++) {
            int columnSum = 0;
            for (int j = 0; j < this.image.getHeight(); j++) {
                Color pixelValue = new Color(this.image.getRGB(i, j));
                int sum = pixelValue.getRed();
                columnSum+=sum;
            }
            if(columnSum!=0){
                if (onLetter == false){
                    onLetter = true;
                    leftX.add(i);
                }
            } else {
                if (onLetter==true){
                    rightX.add(i);
                    onLetter = false;
                }
            }
        }
        
        for (int i = 0; i < leftX.size(); i++) {
            
            for (int j = 0; j < this.image.getHeight(); j++) {
                int rowSum = 0;
                for (int k = leftX.get(i); k < rightX.get(i); k++) {
                    Color pixelValue = new Color(this.image.getRGB(k,j));
                    rowSum += pixelValue.getRed();
                }
                if(rowSum!=0){
                    topY.add(j);
                    break;
                }
            }
            
            for (int j = this.image.getHeight()-1; j >= 0; j--) {
                int rowSum = 0;
                for (int k = leftX.get(i); k < rightX.get(i); k++) {
                    Color pixelValue = new Color(this.image.getRGB(k, j));
                    rowSum += pixelValue.getRed();
                }
                if(rowSum!=0){
                    bottomY.add(j);
                    break;
                }
            }
        }
        
        for (int i = 0; i < leftX.size(); i++) {
            letterImages.add(this.image.getSubimage(leftX.get(i), topY.get(i), rightX.get(i)-leftX.get(i), bottomY.get(i)-topY.get(i)));
        }
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
                    this.image.setRGB(i, j, Color.black.getRGB());
                } else {
                    this.image.setRGB(i, j, Color.white.getRGB());
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
    
    public  void saveCharacters(){
        for (int i = 0; i < 10; i++) {
            this.saveImage(this.letterImages.get(i), "img"+i);
        }
    }
}
