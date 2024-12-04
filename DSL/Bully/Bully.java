import java.util.Scanner;

public class Bully {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        boolean[] active = new boolean[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Is Process " + i + " active? (true/false): ");
            active[i] = sc.nextBoolean();
        }

        System.out.print("Enter initiating process ID: ");
        int initiator = sc.nextInt();

        int coordinator = bullyElection(n, initiator, active);
        System.out.println("Process " + coordinator + " is elected as the Coordinator.");
    }

    static int bullyElection(int n, int initiator, boolean[] active) {
        int maxId = initiator;
        System.out.println("Election initiated by Process " + initiator);

        for (int i = initiator + 1; i < n; i++) {
            if (active[i]) {
                System.out.println("Message sent from Process " + initiator + " to Process " + i);
                maxId = i;
            }
        }
        return maxId;
    }
}

/*
   javac Bully.java
   java Bully
   
   >> 
   Enter number of processes: 5
   Is Process 0 active? (true/false): true
   Is Process 1 active? (true/false): false
   Is Process 2 active? (true/false): true
   Is Process 3 active? (true/false): true
   Is Process 4 active? (true/false): false
   Enter initiating process ID: 1
   Election initiated by Process 1
   Message sent from Process 1 to Process 2
   Message sent from Process 1 to Process 3
   Process 3 is elected as the Coordinator.
*/
