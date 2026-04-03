public class Benchmark {
    public static void main(String[] args) {
        System.out.println("Size\tInsertMs\tExtractMs\tFindMinNs");
        int runs = 5;  // кол-во замеров
        for (int size = 100; size <= 10000; size += 200) {
            double totalInsert = 0;
            double totalExtract = 0;
            double totalFind = 0;
            for (int run = 0; run < runs; run++) {
                int[] data = new int[size];
                for (int i = 0; i < size; i++) {
                    data[i] = (int) (Math.random() * 1_000_000);
                }

                // Вставка
                BinomialHeap heap = new BinomialHeap();
                long startInsert = System.nanoTime();
                for (int val : data) heap.insert(val);
                long endInsert = System.nanoTime();
                totalInsert += (endInsert - startInsert) / 1_000_000.0;

                // Поиск минимума
                long startFind = System.nanoTime();
                heap.findMin();
                long endFind = System.nanoTime();
                totalFind += (endFind - startFind);  // наносекунды

                // Удаление всех
                long startExtract = System.nanoTime();
                while (!heap.isEmpty()) heap.extractMin();
                long endExtract = System.nanoTime();
                totalExtract += (endExtract - startExtract) / 1_000_000.0;
            }

            double avgInsertMs = totalInsert / runs;
            double avgExtractMs = totalExtract / runs;
            double avgFindNs = totalFind / runs;

            System.out.printf("%d\t%.3f\t%.3f\t%.1f\n", size, avgInsertMs, avgExtractMs, avgFindNs);
        }
    }
}