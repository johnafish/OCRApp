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
    //Constructer
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
            roots = returnQuadraticFormula(this.a, this.b, this.c); // uh you can fix this if you want   
        }
    }
    //finds coefficients and sets a, b ,c values accordingly 
    void getCoefficients(){
        String newEquation = this.equation;
        //doing this all assuming the string taken has no spaces and 0=,y= are on the left
        if( newEquation.substring(0, 1).equals("y") ){ //for the form y=mx+b
            this.a = Double.parseDouble( this.equation.substring(2, 3));
            this.b = Double.NaN;
            this.c = Double.parseDouble( this.equation.substring(5));
            
        }
        else{
            newEquation = newEquation.substring(2); // cuts off 0=, y=, etc.
            int indexOfX = newEquation.indexOf("x");

            if( newEquation.substring(0, 1).equals("x") )
                this.a = 1;
            else
                this.a = Double.parseDouble( newEquation.substring(indexOfX - 1, indexOfX) ); //for regular form
            if( newEquation.substring(3, 4).equals("x") )
                this.b = 1;
            else
                this.b = Double.parseDouble( newEquation.substring(indexOfX + 3, indexOfX + 4) );
         
            this.c = Double.parseDouble( newEquation.substring(newEquation.length() - 1) );
        }
    }
    //sets a degree of either 1 or 2
    void getDegrees(){
        int indexOfDegree = this.equation.indexOf("x") + 1;
        
        if( this.equation.substring(indexOfDegree, indexOfDegree + 1).equals("2") )
            this.degree = 2;
        else
            this.degree = 1;
    }
}
