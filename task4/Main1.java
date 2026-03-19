import java.util.Scanner;

public class Main1 {

    public static boolean isCyclicShift(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        if (s.length() == 0) {
            return true;
        }

        String doubleT = t + t;
        return doubleT.contains(s);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите строку s:");
        String s = scanner.nextLine();

        System.out.println("Введите строку t:");
        String t = scanner.nextLine();

        if (isCyclicShift(s, t)) {
            System.out.println("Да, s циклический сдвиг t");
        } else {
            System.out.println("Нет, s не циклический сдвиг t");
        }

    }
}
