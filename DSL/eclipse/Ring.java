package Distributed_system;

import java.util.Scanner;

public class Ring {
    public static void main(String[] args) {
        int temp, i, j;
        int arr[] = new int[10];
        Rr proc[] = new Rr[10];
        // Object initialization
        for (i = 0; i < proc.length; i++) {
            proc[i] = new Rr();
        }

        Scanner in = new Scanner(System.in);
        System.out.println("Enter the number of processes: ");
        int num = in.nextInt();

        // Getting input from users
        for (i = 0; i < num; i++) {
            proc[i].index = i;
            System.out.println("Enter the ID of process: ");
            proc[i].id = in.nextInt();
            proc[i].state = "active";
            proc[i].f = 0;
        }

        // Sorting the processes based on ID
        for (i = 0; i < num - 1; i++) {
            for (j = 0; j < num - 1; j++) {
                if (proc[j].id > proc[j + 1].id) {
                    temp = proc[j].id;
                    proc[j].id = proc[j + 1].id;
                    proc[j + 1].id = temp;
                }
            }
        }

        // Displaying sorted processes
        for (i = 0; i < num; i++) {
            System.out.print(" [" + i + "] " + proc[i].id);
        }
        System.out.println();

        proc[num - 1].state = "inactive";
        System.out.println("\nProcess " + proc[num - 1].id + " selected as coordinator");

        while (true) {
            System.out.println("\n1. Election 2. Quit");
            int ch = in.nextInt();

            // Reset the flags for all processes
            for (i = 0; i < num; i++) {
                proc[i].f = 0;
            }

            switch (ch) {
                case 1:
                    System.out.println("Enter the process number who initiated the election: ");
                    int init = in.nextInt();
                    init--;
                    int temp2 = init;
                    int temp1 = (init + 1) % num;
                    i = 0;

                    while (temp2 != temp1) {
                        if ("active".equals(proc[temp1].state) && proc[temp1].f == 0) {
                            System.out.println("Process " + proc[init].id + " sends message to " + proc[temp1].id);
                            proc[temp1].f = 1;
                            init = temp1;
                            arr[i] = proc[temp1].id;
                            i++;
                        }
                        temp1 = (temp1 + 1) % num;
                    }

                    System.out.println("Process " + proc[init].id + " sends message to " + proc[temp2].id);
                    arr[i] = proc[temp2].id;
                    i++;

                    // Finding the maximum ID for coordinator selection
                    int max = -1;
                    for (j = 0; j < i; j++) {
                        if (max < arr[j]) {
                            max = arr[j];
                        }
                    }

                    // Printing the new coordinator
                    System.out.println("Process " + max + " selected as coordinator");
                    for (i = 0; i < num; i++) {
                        if (proc[i].id == max) {
                            proc[i].state = "inactive";
                        }
                    }
                    break;

                case 2:
                    System.out.println("Program terminated...");
                    in.close();
                    return;

                default:
                    System.out.println("Invalid response");
                    break;
            }
        }
    }
}

class Rr {
    public int index; // To store the index of process
    public int id; // Process ID
    public int f; // Flag to check message passing
    String state; // Process state: active or inactive
}


/*Enter the number of processes: 
3
Enter the ID of process: 
3
Enter the ID of process: 
5
Enter the ID of process: 
7
 [0] 3 [1] 5 [2] 7

Process 7 selected as coordinator

1. Election 2. Quit
1
Enter the process number who initiated the election: 
1
Process 3 sends message to 5
Process 5 sends message to 3
Process 5 selected as coordinator

1. Election 2. Quit
*/