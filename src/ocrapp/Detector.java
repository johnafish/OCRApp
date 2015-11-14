package ocrapp;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.imageio.ImageIO;

/**
 * @author David Chen
 * @author John Fish
 * @author Ryan Mandur
 *
 */

public final class Detector {
    //Field initialization
    String content = ""; //The eventual machine-readable content of the image
    BufferedImage image; //The total image used, usually with a filter applied (binarization)
    List<BufferedImage> letterImages; //Isolated images of characters
    List<double[]> minimizedLetters; //Arrays of minimized letters
    //IF YOU CHANGE RESOLUTION YOUR DATABASE WILL BREAK!
    public static int resolution = 5; //The resolution of the minimized letters.
    
    /**
     * Constructor of Detector
     * @param i BufferedImage to be read from
     */
    public Detector(BufferedImage i){
        this.image = i;
        this.letterImages = new ArrayList<>();
        this.minimizedLetters = new ArrayList<>();
        this.binarize();
        this.isolateLetters();
        this.minimizeLetters();
        this.guessLetters();
    }
    
    /**
     * Populates the letterImages array
     */
    public void isolateLetters(){
        boolean onLetter = false; //Tracks whether the scanner is on a letter or not
        
        //Lists of the bounding box coordinates of letters
        List<Integer> leftX = new ArrayList<>();
        List<Integer> rightX = new ArrayList<>();
        List<Integer> topY = new ArrayList<>();
        List<Integer> bottomY = new ArrayList<>();
        
        //Generate leftX and rightX
        for (int i = 0; i < this.image.getWidth(); i++) {
            //Sum the columns
            int columnSum = 0;
            for (int j = 0; j < this.image.getHeight(); j++) {
                Color pixelValue = new Color(this.image.getRGB(i, j));
                int sum = pixelValue.getRed();
                columnSum+=sum;
            }
            //Determine if at the edge of a character
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
        
        //Generate topY and bottomY
        for (int i = 0; i < leftX.size(); i++) {
            //Scanner from top to bottom, summing rows
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
            //Scanner from bottom to top, summing rows
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
        //Add all the images slices that are greater than 10px by 10px
        for (int i = 0; i < leftX.size(); i++) {
            int width = rightX.get(i)-leftX.get(i);
            int height = bottomY.get(i)-topY.get(i);
            if(width*height>100){
                letterImages.add(this.image.getSubimage(leftX.get(i), topY.get(i), width, height));
            }
        }
    }
    
    /**
     * Populates the minimizedLetters array
     */
    public void minimizeLetters(){
        //Reduce all the letterImages and add to minimizedLetters
        for (int i = 0; i < letterImages.size(); i++) {
            minimizedLetters.add(reduceImage(letterImages.get(i)));
        }
    }
    
    /**
     * Guesses the most likely letter for an array
     * @param reduced double array from minimizedLetters
     * @return single character guessed from database
     */
    public String guessLetter(double[] reduced) {
        try {
            //Open database and scan
            File database = new File("database.txt");
            Scanner s = new Scanner(database);
            String guess = "";
            double close = Double.POSITIVE_INFINITY;
            while(s.hasNext()){
                String letter = s.next();
                double diff = 0;
                //Matrix subtraction
                for (int i = 0; i < resolution*resolution; i++) {
                    diff += Math.abs(s.nextDouble()-reduced[i]);
                }
                //Solve for lowest delta
                if(diff<close){
                    guess = letter;
                    close = diff;
                }
            }
            return guess;
        } catch (IOException e){
            System.out.println("Error opening database.");
            return "-1";
        }
    }
    
    
    /**
     * Populates the content string with guesses
     */
    public void guessLetters(){
        //Guess each letter content from minimizedLetters
        for (int i = 0; i < minimizedLetters.size(); i++) {
            this.content+=guessLetter(minimizedLetters.get(i));
        }
    }
    
    /**
     * Populates database.txt based on the reduced values of an image and a correct string
     * @param correct 
     * Populates the database.txt file
     */
    public void populateDB(String correct){
        for (int i = 0; i < correct.length(); i++) {
            //Write character followed by reduced data points to file
            try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("database.txt", true)))) {
                out.print(correct.charAt(i)+" ");
                double[] reduced = minimizedLetters.get(i);
                for (int j = 0; j < reduced.length; j++) {
                    out.print(reduced[j]+" ");
                }
                out.println();
            }catch (IOException e) {
                System.out.println("Error populating DB.");
            }
        }
    }
    
    /**
     * Binarizes image (RGB -> true black or true white)
     */
    public void binarize(){
        for (int i = 0; i < this.image.getWidth(); i++) {
            for (int j = 0; j < this.image.getHeight(); j++) {
                //Sums R,G,B components and checks if it should be black or white
                Color pixelValue = new Color(this.image.getRGB(i, j));
                int sum = pixelValue.getRed()+pixelValue.getBlue()+pixelValue.getGreen();
                if (sum>320){
                    this.image.setRGB(i, j, Color.black.getRGB());
                } else {
                    this.image.setRGB(i, j, Color.white.getRGB());
                }
            }
        }
    }
    
    /**
     * Converts image to a reduced array of doubles
     * @param img
     * @return reduced double array of image
     */
    public static double[] reduceImage(BufferedImage img){
        double[] reduced = new double[resolution*resolution];
        int reducedWidth = img.getWidth()/resolution;
        int reducedHeight = img.getHeight()/resolution;
        for (int i = 0; i < resolution*resolution; i++) {
            int sum = 0;
            int numPixels = 0;
            for (int j = 0; j < reducedWidth; j++) {
                for (int k = 0; k < reducedHeight; k++) {
                    sum+=new Color(img.getRGB(reducedWidth*(i%resolution)+j, reducedHeight*(i/resolution)+k)).getRed();
                }
            }
            reduced[i]= (double) sum/(reducedWidth*reducedHeight);
        }
        return reduced;
    }
}
