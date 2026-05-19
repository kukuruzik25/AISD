import java.util.Random;
import java.util.Arrays;

public class GraphGenerator {
    private final Random rng = new Random();

    public int[][] generateConnectedGraph(int V, double extraEdgeFactor) {
        int[][] adj = new int[V][V];

        // 1. Строим базовое дерево для связности
        int[] p = new int[V];
        for (int i = 0; i < V; i++) p[i] = i;
        // Перемешиваем массив p (Fisher-Yates)
        for (int i = V - 1; i > 0; i--) {
            int j = rng.nextInt(i + 1);
            int tmp = p[i]; p[i] = p[j]; p[j] = tmp;
        }

        for (int i = 1; i < V; i++) {
            int u = p[i];
            int v = rng.nextInt(i); // соединяем с любой из предыдущих
            int w = rng.nextInt(1000) + 1;
            adj[u][v] = adj[v][u] = w;
        }

        // 2. Добавляем дополнительные рёбра
        int extraEdges = (int) (extraEdgeFactor * V);
        for (int k = 0; k < extraEdges; k++) {
            int u = rng.nextInt(V);
            int v = rng.nextInt(V);
            if (u != v && adj[u][v] == 0) {
                int w = rng.nextInt(1000) + 1;
                adj[u][v] = adj[v][u] = w;
            }
        }
        return adj;
    }
}