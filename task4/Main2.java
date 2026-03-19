public class Main2 {

    public static int findMissingNumber(int[] arr) {
        int n = arr.length;
        int allSum = n * (n + 1) / 2;
        int actualSum = 0;
        for (int i = 0; i < n; i++) {
            actualSum = actualSum + arr[i];
        }
        return allSum - actualSum;
    }

    public static void main(String[] args) {
        int[] arr = {0, 1, 2, 4, 5};
        int missing = findMissingNumber(arr);
        System.out.println("Пропущенное число: " + missing);
    }
}
