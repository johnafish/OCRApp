package ocrapp;

/**
 *
 * @author John Fish <john@johnafish.ca>, Ryan Mandur
 */
public class Equation {
    public double a,b,c;
    public int degree;
    public double[]roots = new double[2]; 
    public String equation;
    public double discriminant = (this.b * this.b) - 4*this.a*this.c; // keep this?
    
    public Equation(String e){
        this.equation = e;
        getDegrees();
        getCoefficients();
        getRoots();
    }
    //handles quad formula for finding roots
    double[] returnQuadraticFormula( double a, double b , double c){
        double positive, negative;
        double[] both = new double[2];
        //pretty sure this works
        positive = (double) ( (-b + Math.sqrt( (b*b) - 4*a*c) ) / 2*a );
        negative = (double) ( (-b - Math.sqrt( (b*b) - 4*a*c) ) / 2*a );
        
        both[0] = negative;
        both[1] = positive;
        
        return both;
    }
    //solves for the roots of this equation
    void getRoots(){
        double x;
        if( this.degree == 1){
            x = (double)( -this.c / this.a ); //assuming b is 0
            roots[0] = x;
            roots[1] = Double.NaN;
        }
        //checks for real roots
        else if ( discriminant < 0 ){ // ( this.b * this.b ) - 4*this.a*this.c
            roots[0] = Double.NaN;
            roots[1] = Double.NaN;
        }
        //fills roots array with returnQF call
        else{
            roots = returnQuadraticFormula(this.a, this.b, this.c); // changing this later   
        }
    }
    
    void getCoefficients(){
        //finds coefficients and sets a, b ,c values accordingly 
    }
    
    void getDegrees(){
//        int index = this.equation.indexOf("x");
//        
//        if( this.equation.indexOf( index + 1) == "2"){
//            
//        }
        //returns a degree of either 1 or 2
    }
}
