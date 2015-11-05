package test;

public class Test {
    public static void main(String[] args) {
        double temp = Math.sqrt(-1);
        if (Double.isNaN(temp)) {
            System.out.println("NaN");
        } else {
            System.out.println("Not NaN");
        }
    }
}
