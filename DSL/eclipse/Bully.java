package Distributed_system;

import java.util.Scanner;

public class Bully {
    static boolean[] state = new boolean[5];
    int coordinator;

    public static void up(int up) {
        if (state[up - 1]) {
            System.out.println("Process " + up + " is already up.");
        } else {
            state[up - 1] = true;
            System.out.println("Process " + up + " held election.");
            for (int i = up; i < 5; ++i) {
                System.out.println("Election message sent from process " + up + " to process " + (i + 1));
            }
            for (int i = up + 1; i <= 5; ++i) {
                if (!state[i - 1]) continue;
                System.out.println("Alive message sent from process " + i + " to process " + up);
                break;
            }
        }
    }

    public static void down(int down) {
        if (!state[down - 1]) {
            System.out.println("Process " + down + " is already down.");
        } else {
            state[down - 1] = false;
            System.out.println("Process " + down + " is now down.");
        }
    }

    public static void mess(int mess) {
        if (state[mess - 1]) {
            if (state[4]) {
                System.out.println("OK");
            } else {
                System.out.println("Process " + mess + " held an election.");
                for (int i = mess; i < 5; ++i) {
                    System.out.println("Election message sent from process " + mess + " to process " + (i + 1));
                }
                for (int i = 5; i >= mess; --i) {
                    if (!state[i - 1]) continue;
                    System.out.println("Coordinator message sent from process " + i + " to all.");
                    break;
                }
            }
        } else {
            System.out.println("Process " + mess + " is down.");
        }
    }

    public static void main(String[] args) {
        int choice;
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < 5; ++i) {
            state[i] = true;
        }

        System.out.println("5 active processes are:");
        System.out.println("Processes up = P1 P2 P3 P4 P5");
        System.out.println("Process 5 is the coordinator.");

        do {
            System.out.println(".........");
            System.out.println("1. Bring a process up.");
            System.out.println("2. Bring a process down.");
            System.out.println("3. Send a message.");
            System.out.println("4. Exit.");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.println("Bring which process up?");
                    int up = sc.nextInt();
                    if (up == 5) {
                        System.out.println("Process 5 is the coordinator.");
                        state[4] = true;
                    } else {
                        up(up);
                    }
                }
                case 2 -> {
                    System.out.println("Bring which process down?");
                    int down = sc.nextInt();
                    down(down);
                }
                case 3 -> {
                    System.out.println("Which process will send a message?");
                    int mess = sc.nextInt();
                    mess(mess);
                }
                case 4 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 4);

        sc.close();
    }
}

/*
    Output : 
    	5 active processes are:
    		Processes up = P1 P2 P3 P4 P5
    		Process 5 is the coordinator.
    		.........
    		1. Bring a process up.
    		2. Bring a process down.
    		3. Send a message.
    		4. Exit.

    		2
    		Bring which process down?
    		5
    		Process 5 is now down.
    		.........
    		1. Bring a process up.
    		2. Bring a process down.
    		3. Send a message.
    		4. Exit.

    		3
    		Which process will send a message?
    		1
    		Process 1 held an election.
    		Election message sent from process 1 to process 2
    		Election message sent from process 1 to process 3
    		Election message sent from process 1 to process 4
    		Election message sent from process 1 to process 5
    		Coordinator message sent from process 4 to all.
    		.........
    		1. Bring a process up.
    		2. Bring a process down.
    		3. Send a message.
    		4. Exit.

    		1
    		Bring which process up?
    		5
    		Process 5 is the coordinator.
    		.........
    		1. Bring a process up.
    		2. Bring a process down.
    		3. Send a message.
    		4. Exit.
*/