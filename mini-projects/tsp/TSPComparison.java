import java.util.Arrays;

// TSP using an exact algorithm (brute force) and the Nearest Neighbour heuristic
public class TSPComparison {
    static int[][] cost = {
        {0, 10, 15, 20},
        {10, 0, 35, 25},
        {15, 35, 0, 30},
        {20, 25, 30, 0}
    };

    static int n = cost.length;
    static int bestCost = Integer.MAX_VALUE;
    static int[] bestTour;

    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Exact algorithm: try every permutation of cities 1..n-1 (city 0 is fixed)
    static void permute(int[] tour, int k) {
        if (k == n) {
            int total = 0;
            for (int i = 0; i < n - 1; i++) {
                total += cost[tour[i]][tour[i + 1]];
            }
            total += cost[tour[n - 1]][tour[0]]; // return to starting city

            if (total < bestCost) {
                bestCost = total;
                bestTour = tour.clone();
            }
            return;
        }

        for (int i = k; i < n; i++) {
            swap(tour, k, i);
            permute(tour, k + 1);
            swap(tour, k, i); // backtrack
        }
    }

    // Nearest Neighbour heuristic: choose the nearest unvisited city each time
    static int nearestNeighbour() {
        boolean[] visited = new boolean[n];
        int[] tour = new int[n];
        int total = 0;
        int current = 0;

        visited[0] = true;
        tour[0] = 0;

        for (int i = 1; i < n; i++) {
            int next = -1;
            int minDistance = Integer.MAX_VALUE;

            for (int j = 0; j < n; j++) {
                if (!visited[j] && cost[current][j] < minDistance) {
                    minDistance = cost[current][j];
                    next = j;
                }
            }

            visited[next] = true;
            tour[i] = next;
            total += cost[current][next];
            current = next;
        }

        total += cost[current][0];
        System.out.println("Nearest Neighbour tour: " + Arrays.toString(tour));
        System.out.println("Nearest Neighbour cost: " + total);
        return total;
    }

    public static void main(String[] args) {
        int[] tour = {0, 1, 2, 3};

        System.out.println("Exact TSP (Brute Force):");
        permute(tour, 1);
        System.out.println("Best tour: " + Arrays.toString(bestTour));
        System.out.println("Best cost: " + bestCost);

        System.out.println();
        System.out.println("Nearest Neighbour Heuristic:");
        nearestNeighbour();
    }
}
