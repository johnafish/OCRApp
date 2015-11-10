package ocrapp;

/**
 * @author David Chen
 * @author John Fish
 * @author Ryan Mandur
 */
public final class Equation {
    public double a,b,c;
    public int degree;
    //public List<Double[]> roots = new ArrayList<Double[]>();
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
    private char[] clean(String e){
        String[] t = e.split(" ");
        String full = " ";
        for (String s:t) { full += s; }
        full += " ";
        char[] newString = full.toCharArray();
        return newString;
    }
    
    //finds coefficients and sets a, b ,c values accordingly 
    void getCoefficients(){
        char sign = '+';
        for (int i = 0; i < newEquation.length; i++) {      
            if( newEquation[i] == '-' || newEquation[i] == '+') //checks for sign
                sign = newEquation[i];
            if( newEquation[i] == 'x' ){
                int j = i - 1;
                String n = "";
                while( Character.isDigit( newEquation[j] ) ){ 
                    n += String.valueOf( newEquation[j] );
                    if( j - 1 < newEquation.length)
                        break;
                    else
                        j--;
                        }
                StringBuffer number = new StringBuffer(n).reverse();
                if ( newEquation[i + 1] == '2') {
                    try{
                        this.a = Double.parseDouble( sign + String.valueOf( number ) );
                    }
                    catch( Exception e){
                        this.a = 1;
                        if ( sign == '-')
                            this.a *= -1;
                    }
                }
                else{ //for quadratic w two x's
                    try{
                        this.b = Double.parseDouble( sign + String.valueOf( number ) );
                    }
                    catch( Exception e ){
                        this.b = 1;
                        if ( sign == '-')
                            this.b *= -1;
                    }
                }
            }
            else if( Character.isDigit( newEquation[i] ) &&  newEquation[i] != '0' && this.c == 0){
                int u = i;
                String num ="";
                try{ 
                    if( newEquation[i + 1] == 'x'){continue;} 
                    else if( newEquation[i - 1] == 'x'){continue;}
                    }
                catch( Exception e) { continue; }
                while( Character.isDigit( newEquation[u] )){
                    num += String.valueOf( newEquation[u] );
                    if( u + 1 > ( newEquation.length - 1 ) )
                      break;
                    else{
                      u++;
                    }
                }
                try{
                this.c = Double.parseDouble( sign + String.valueOf( num ) ); 
                } catch( Exception e){ System.out.println("there is no c value"); }
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
