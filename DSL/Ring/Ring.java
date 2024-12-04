import java.util.*;

public class Ring {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter number of processes: ");
        int n = in.nextInt();
        int[] proc = new int[n];

        System.out.println("Enter process IDs:");
        for (int i = 0; i < n; i++) proc[i] = in.nextInt();

        Arrays.sort(proc);
        System.out.println("Processes: " + Arrays.toString(proc));
        System.out.println("Coordinator: " + proc[n - 1]);

        while (true) {
            System.out.println("\n1. Start Election\n2. Quit");
            int choice = in.nextInt();
            if (choice == 2) break;

            if (choice == 1) {
                System.out.print("Enter initiating process number: ");
                int start = in.nextInt() - 1;
                int max = proc[start];
                System.out.println("Election Messages:");

                for (int i = 0; i < n; i++) {
                    int curr = proc[(start + i) % n];
                    System.out.println("Process " + proc[start] + " sends message to " + curr);
                    max = Math.max(max, curr);
                }
                System.out.println("Coordinator: " + max);
            } else {
                System.out.println("Invalid choice.");
            }
        }
        System.out.println("Program terminated.");
    }
}

/*
   java Ring

   Enter number of processes: 5
   Enter process IDs:
   1 2 3 4 5
   Processes: [1, 2, 3, 4, 5]
   Coordinator: 5

   1. Start Election
   2. Quit
   1
   Enter initiating process number: 3
   Election Messages:
   Process 3 sends message to 3
   Process 3 sends message to 4
   Process 3 sends message to 5
   Process 3 sends message to 1
   Process 3 sends message to 2
   Coordinator: 5

   1. Start Election
   2. Quit
*/
