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
        if( this.degree == 1){
            roots[0] = (double)( -this.c / this.a ); //assuming b is 0
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
        char[] newEquation = this.equation.toCharArray();
        char sign = '+';
        for (int i = 0; i < newEquation.length; i++) {
           
            if( newEquation[i] == '-' || newEquation[i] == '+') //checks for sign
                sign = newEquation[i];
            
            if( newEquation[i] == 'x' ){
                if( this.a > 0){ //for quadratic w two x's
                    try{
                        this.b = Double.parseDouble( sign + String.valueOf(newEquation[i - 1]) );
                    }
                    catch( Exception e ){
                        this.b = 1;
                        if ( sign == '-')
                            this.b *= -1;
                    }
                }
                else{
                    try{
                        this.a = Double.parseDouble( sign + String.valueOf(newEquation[i - 1]) );
                    }
                    catch( Exception e){
                        this.a = 1;
                        if ( sign == '-')
                            this.a *= -1;
                    }
                }
            }
            else if( Character.isDigit( newEquation[i] ) &&  newEquation[i] != '0' ){
                if( newEquation[i + 1] == 'x'){}
                else if( newEquation[i - 1] == 'x'){}
                
                else
                    this.c = Double.parseDouble( sign + String.valueOf(newEquation[i]) );
            }
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
