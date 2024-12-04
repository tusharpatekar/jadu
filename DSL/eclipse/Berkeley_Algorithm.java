package distributed_system1;

import java.util.Scanner;

public class Berkeley_Algorithm {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input number of processes
        System.out.print("Enter the number of processes: ");
        int n = sc.nextInt();

        int[] clocks = new int[n];
        System.out.println("Enter the clock times for each process (in seconds):");
        for (int i = 0; i < n; i++) {
            System.out.print("Clock time of process " + (i + 1) + ": ");
            clocks[i] = sc.nextInt();
        }

        // Master process (assume process 0 is master)
        int masterClock = clocks[0];
        System.out.println("\nMaster clock time: " + masterClock);

        // Calculate time differences
        int totalDifference = 0;
        int[] differences = new int[n];
        for (int i = 0; i < n; i++) {
            differences[i] = clocks[i] - masterClock;
            totalDifference += differences[i];
        }

        // Calculate average difference
        int averageDifference = totalDifference / n;

        // Adjust clocks
        System.out.println("\nAdjusting clocks...");
        for (int i = 0; i < n; i++) {
            clocks[i] -= averageDifference;
            System.out.println("Adjusted clock time of process " + (i + 1) + ": " + clocks[i]);
        }

        sc.close();
    }
}



               /*
                {
                	Enter the number of processes: 3
                	Enter the clock times for each process (in seconds):
                	Clock time of process 1: 100
                	Clock time of process 2: 120
                	Clock time of process 3: 90

                	Master clock time: 100

                	Adjusting clocks...
                	Adjusted clock time of process 1: 97
                	Adjusted clock time of process 2: 117
                	Adjusted clock time of process 3: 87

                }
               */