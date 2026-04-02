import java.util.Arrays;
import java.util.Comparator;

public class task4 {
    public static void main(String[] args) {
        int[] arr1 = {1, 4, 7, 2};
        System.out.println("Максимальное число: " + large(arr1));
    }

    public static String large(int[] nums) {
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strs[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(strs, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                String order1 = a + b;
                String order2 = b + a;
                return order2.compareTo(order1);
            }
        });
        if (strs[0].equals("0")) {
            return "0";
        }
        StringBuilder result = new StringBuilder();
        for (String s : strs) {
            result.append(s);
        }
        return result.toString();
    }
}