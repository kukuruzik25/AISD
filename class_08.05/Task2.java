import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        if (n == 0) {
            System.out.println("анлак");
            return;
        }
        if (n == 1) {
            System.out.println(1);
            return;
        }

        int a = 0;
        int b = 1;
        for (int i = 2; i <= n; i++) {
            int c = (a + b) % 10;
            a = b;
            b = c;
        }
        System.out.println(b);
    }
}
