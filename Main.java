import java.util.ArrayList;
import java.util.Arrays;

class BankersAlgorithm {
    private int[][] allocation;
    private int[][] max;
    private int[] available;
    private int[][] need;
    private boolean[] finish;
    private ArrayList<Integer> safeSequence;

    public BankersAlgorithm(int[][] allocation, int[][] max, int[] available) {
        this.allocation = allocation;
        this.max = max;
        this.available = available;

        int processes = allocation.length;
        int resources = allocation[0].length;

        need = new int[processes][resources];
        finish = new boolean[processes];
        safeSequence = new ArrayList<>();

        // Calculate need matrix
        for (int i = 0; i < processes; i++) {
            for (int j = 0; j < resources; j++) {
                need[i][j] = max[i][j] - allocation[i][j];
            }
        }
    }

    public void run() {
        int processes = allocation.length;
        int resources = allocation[0].length;

        int[] work = Arrays.copyOf(available, resources);

        while (true) {
            boolean found = false;

            for (int i = 0; i < processes; i++) {
                if (!finish[i]) {
                    boolean canAllocate = true;
                    for (int j = 0; j < resources; j++) {
                        if (need[i][j] > work[j]) {
                            canAllocate = false;
                            break;
                        }
                    }
                    if (canAllocate) {
                        for (int j = 0; j < resources; j++) {
                            work[j] += allocation[i][j];
                        }
                        finish[i] = true;
                        safeSequence.add(i);
                        found = true;
                    }
                }
            }

            if (!found) {
                break;
            }
        }

        boolean isSafe = safeSequence.size() == processes;

        if (isSafe) {
            System.out.println("Safe Sequence: " + safeSequence);
        } else {
            System.out.println("System is not in a safe state.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        int[][] allocation = {{0, 1, 0}, {2, 0, 0}, {3, 0, 2}, {2, 1, 1}, {0, 0, 2}};
        int[][] max = {{7, 5, 3}, {3, 2, 2}, {9, 0, 2}, {2, 2, 2}, {4, 3, 3}};
        int[] available = {3, 3, 2};

        BankersAlgorithm banker = new BankersAlgorithm(allocation, max, available);
        banker.run();
    }
}
