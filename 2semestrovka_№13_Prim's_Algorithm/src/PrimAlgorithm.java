import java.util.Arrays;

public class PrimAlgorithm {
    private final int V;
    private final int[][] adj;
    public long iterations = 0; // счётчик итераций самого вложенного цикла

    public PrimAlgorithm(int V) {
        this.V = V;
        // adj[i][j] = вес ребра, 0 означает отсутствия ребра
        this.adj = new int[V][V];
    }

    public void setWeight(int u, int v, int w) {
        adj[u][v] = w;
        adj[v][u] = w; // неориентированный граф
    }

    //Возвращает массив parent[], где parent[i] - родитель вершины i в MST
    // parent[0] = -1 (корень)
    public int[] run() {
        int[] key = new int[V];
        boolean[] inMST = new boolean[V];
        int[] parent = new int[V];

        Arrays.fill(key, Integer.MAX_VALUE);
        key[0] = 0;
        parent[0] = -1;

        iterations = 0; // сброс перед запуском

        // добавляем по одной вершине за шаг
        for (int count = 0; count < V; count++) {
            int u = -1;
            // поиск вершины с минимальным key среди не добавленных
            for (int v = 0; v < V; v++) {
                iterations++; // считаем только его по условию
                if (!inMST[v] && (u == -1 || key[v] < key[u])) {
                    u = v;
                }
            }

            inMST[u] = true; // добавляем вершину u в MST

            // обновляем ключи соседей
            for (int v = 0; v < V; v++) {
                // adj[u][v] > 0 означает наличие ребра
                if (adj[u][v] > 0 && !inMST[v] && adj[u][v] < key[v]) {
                    key[v] = adj[u][v];
                    parent[v] = u;
                }
            }
        }
        return parent;
    }
}