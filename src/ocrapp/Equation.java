package ocrapp;

/**
 *
 * @author John Fish <john@johnafish.ca>, Ryan Mandur
 */
public class Equation {
    double a,b,c;
    int degree;
    double[] roots;
    String equation;
    
    public Equation(String e){
        this.equation = e;
    }
    
    void getRoots(){
        //solves for the roots of this equation
    }
    
    void getCoefficients(){
        //finds coefficients and sets a, b ,c values accordingly 
    }
    
    void getDegrees(){
        //returns a degree of either 1 or 2
    }
}
