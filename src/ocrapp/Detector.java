package ocrapp;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import javax.imageio.ImageIO;

/**
 *
 * @author John Fish <john@johnafish.ca>
 */

public final class Detector {
    String content = "";
    BufferedImage image;
    List<BufferedImage> letterImages;
    List<double[]> minimizedLetters;
    public static int resolution = 5;
    
    public Detector(BufferedImage i){
        this.image = i;
        this.letterImages = new ArrayList<>();
        this.minimizedLetters = new ArrayList<>();
        this.binarize();
        this.isolateLetters();
        this.minimizeLetters();
        this.guessLetters();
//        this.populateDB("y=");
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
            int width = rightX.get(i)-leftX.get(i);
            int height = bottomY.get(i)-topY.get(i);
            if(width>10 && height>10){
                letterImages.add(this.image.getSubimage(leftX.get(i), topY.get(i), width, height));
            }
        }
    }
    
    public void minimizeLetters(){
        for (int i = 0; i < letterImages.size(); i++) {
            minimizedLetters.add(reduceImage(letterImages.get(i)));
            for (int j = 0; j < minimizedLetters.get(i).length; j++) {
            }
        }
    }
    
    public String guessLetter(double[] reduced) {
        try {
            File database = new File("database.txt");
            Scanner s = new Scanner(database);
            String guess = "";
            double close = Double.POSITIVE_INFINITY;
            while(s.hasNext()){
                String letter = s.next();
                double diff = 0;
                for (int i = 0; i < resolution*resolution; i++) {
                    diff += Math.abs(s.nextDouble()-reduced[i]);
                }
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
    
    public void guessLetters(){
        for (int i = 0; i < minimizedLetters.size(); i++) {
            this.content+=guessLetter(minimizedLetters.get(i));
        }
    }
    
    public void populateDB(String correct){
        for (int i = 0; i < correct.length(); i++) {
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
    
    public void binarize(){
        for (int i = 0; i < this.image.getWidth(); i++) {
            for (int j = 0; j < this.image.getHeight(); j++) {
                Color pixelValue = new Color(this.image.getRGB(i, j));
                int sum = pixelValue.getRed()+pixelValue.getBlue()+pixelValue.getGreen();
                if (sum>320){
                    this.image.setRGB(i, j, Color.black.getRGB());
                } else {
                    this.image.setRGB(i, j, Color.white.getRGB());
                }
            }
        }
        saveImage(this.image, "test");
    }
    
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
            reduced[i]=sum/(reducedWidth*reducedHeight);
        }
        return reduced;
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
        for (int i = 0; i < letterImages.size(); i++) {
            this.saveImage(this.letterImages.get(i), "img"+i);
        }
    }
}
