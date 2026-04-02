import java.util.Arrays;
import java.util.Comparator;

public class task2 {
    public static void main(String[] args) {
        String[] arr = {"бобаити", "мопс", "миньон", "кулебяка", "пэпэ"};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(String[] arr) {
        Arrays.sort(arr, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return a.compareTo(b);
            }
        });
    }
}
