import java.util.HashSet;

public class task1 {
    public static void main(String[] args) {
        int[] arr = {1, 4, 2, 7, 5, 3, 6};
        int target = 9;

        findPair(arr, target);
    }

    public static void findPair(int[] arr, int target) {
        HashSet<Integer> seen = new HashSet<>();
        for (int num : arr) {
            int needed = target - num;
            if (seen.contains(needed)) {
                System.out.println("Найдена пара: " + needed + " + " + num + " = " + target);
                return;
            }
            seen.add(num);
        }
        System.out.println("Пара не найдена");
    }
}