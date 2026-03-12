import java.util.Scanner;

public class Evclid_probn {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double a = sc.nextInt();
        double b = sc.nextInt();
        System.out.println(pepe(a,b));
    }

    public static double pepe(double a, double b) {
        if (a != 0.0 && b != 0.0) {
            if (a < b) {
                return nod(b,a);
            } else {
                return nod(b, a % b);
            }
        }
        return 0;
    }

    public static double nod(double a, double b) {
        for (int i = (int)(b); i > 0; i--) {
            if ((a % i == 0) && (b % i == 0)) {
                return i;
            }
        }
        return 1;
    }
}
