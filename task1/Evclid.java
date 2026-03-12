import java.util.Scanner;

public class Evclid {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        System.out.println(nod(a,b));
    }

    public static int nod(int a, int b) {
        if (a == 0) {
            return 0;
        }
        if (b == 0) {
            return a;
        }
        if (a < b) {
            int c = a;
            a = b;
            b = c;
        }
        int delit;
        while (b != 0) {
            delit = b;
            b = a % b;
            a = delit;
        }
        return a;
    }
}
