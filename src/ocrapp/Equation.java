package ocrapp;

/**
 * @author David Chen
 * @author John Fish
 * @author Ryan Mandur
 */
public final class Equation {
    public double a,b,c;
    public int degree;
    public double[] roots = new double[2]; 
    public String equation;
    public double discriminant = (this.b * this.b) - 4*this.a*this.c; // keep this?
    private char[] newEquation;
    //Constructer
    public Equation(String e){
        this.equation = e;
        newEquation = clean(this.equation);
        getDegrees();
        getCoefficients();
        getRoots();
    }
    //handles quad formula for finding roots
    static double[] returnQuadraticFormula( double a, double b , double c){
        double positive, negative;
        double[] both = new double[2];
        //values for subtracting root and addition root
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
        else
            roots = returnQuadraticFormula(this.a, this.b, this.c); // uh you can fix this if you want   
    }
    private char[] clean(String e){ //method for converting string to a workable form
        String[] t = e.split(" ");
        String full = " "; //space here on purpose( makes it easier to detect first co-E )
        for (String s:t) { full += s; }
        full += " ";
        char[] newString = full.toCharArray();
        return newString;
    }
    //finds coefficients and sets a, b ,c values accordingly 
    void getCoefficients(){
        char sign = '+';
        String n;
        int j;
        for (int i = 0; i < newEquation.length; i++) {
            n = ""; //resets n
            if( newEquation[i] == '-' || newEquation[i] == '+') //checks for sign
                sign = newEquation[i];
            if( newEquation[i] == 'x' ){ //checks for values next to x
                j = i - 1;
                while( Character.isDigit( newEquation[j] ) ){ //while loop neccesary for 2+ digit numbers
                    n += String.valueOf( newEquation[j] ); //creates a string so it can easily be converted to a double
                    j--;
                        }
                StringBuffer number = new StringBuffer(n).reverse(); //reverses string because it always comes backwards
                if ( newEquation[i + 1] == '2') { //handles for x^2
                    try{ this.a = Double.parseDouble( sign + String.valueOf( number ) ); } //handles reg numbers
                    catch( Exception e){ //handles case for when there is no co-efficient of x
                        this.a = 1;
                        if ( sign == '-')
                            this.a = -1;
                    }
                }
                else{ //for quadratic w two x's
                    try{ this.b = Double.parseDouble( sign + String.valueOf( number ) ); }
                    catch( Exception e ){
                        this.b = 1;
                        if ( sign == '-')
                            this.b = -1;
                    }
                }
            }
            else if( Character.isDigit( newEquation[i] ) &&  newEquation[i] != '0' && this.c == 0){
                j = i;
                try{ if( newEquation[i - 1] == 'x'){continue;} } //change later with "^"
                catch( Exception e ) { continue; }
                while( Character.isDigit( newEquation[j] )){
                    if ( newEquation[j + 1] == 'x'){
                        n = "0";
                        break;
                    }
                    n += String.valueOf( newEquation[j] );
                    j++;            
                }
                try{ this.c = Double.parseDouble( sign + String.valueOf( n ) ); }
                catch( Exception e) {}
                }
            }
    }
    //sets a degree of either 1 or 2
    void getDegrees(){
        this.degree = 1;
        for (int i = 0; i < newEquation.length; i++) {
            if( newEquation[i] == 'x' && newEquation[i + 1] == '2')
                this.degree = 2;
        }
    }
}
