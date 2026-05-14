import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long a = sc.nextInt();
        long f0 = 0;
        long f1 = 1;

        while (f1 < a) {
            long next = f0 + f1;
            f0 = f1;
            f1 = next;
        }
        if (f1 == a) {
            System.out.println("веригут");
        } else {
            System.out.println("анлак");
        }
    }
}
