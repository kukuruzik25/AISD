public class Main {
    public static void main(String[] args) {
        String expr1 = "A B +";
        System.out.println("A B L + = " + PostfixCalculator.evaluate(expr1, 5, 3)); // 10


        String expr2 = "A B *";
        System.out.println("A B * = " + PostfixCalculator.evaluate(expr2, 5, 3)); // 13


        String expr3 = "A B -";
        System.out.println("A B - = " + PostfixCalculator.evaluate(expr3, 5, 3)); // 4


        String expr4 = "A B * A -";
        System.out.println("A B * A - = " + PostfixCalculator.evaluate(expr4, 5, 3)); // 12
    }
}