import java.util.Locale; // ← добавь этот импорт в начало файла

public class PrimTest {
    public static void main(String[] args) {
        GraphGenerator gen = new GraphGenerator();
        StringBuilder csv = new StringBuilder("V,avgTimeMs,avgSteps\n");

        int runs = 5;

        for (int V = 100; V <= 10000; V += 200) {
            int[][] adj = gen.generateConnectedGraph(V, 0.2);

            long totalTime = 0;
            long totalSteps = 0;

            for (int r = 0; r < runs; r++) {
                PrimAlgorithm prim = new PrimAlgorithm(V);
                setupWeights(prim, adj, V);

                long start = System.nanoTime();
                prim.run();
                long end = System.nanoTime();

                totalTime += (end - start);
                totalSteps += prim.iterations;
            }

            double avgTimeMs = (totalTime / (double) runs) / 1_000_000.0;
            long avgSteps = totalSteps / runs;

            // Locale.US гарантирует использование точки вместо запятой
            csv.append(V).append(",")
                    .append(String.format(Locale.US, "%.3f", avgTimeMs)).append(",")
                    .append(avgSteps).append("\n");
        }
        System.out.print(csv);
    }

    private static void setupWeights(PrimAlgorithm prim, int[][] adj, int V) {
        for (int i = 0; i < V; i++) {
            for (int j = i + 1; j < V; j++) {
                if (adj[i][j] > 0) {
                    prim.setWeight(i, j, adj[i][j]);
                }
            }
        }
    }
}