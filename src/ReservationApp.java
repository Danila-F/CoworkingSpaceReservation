import java.util.Scanner;

public class ReservationApp {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n******Menu******");
            System.out.println("0. Exit");
            System.out.println("1. Log in or sign up");
            int userInput = scanner.nextInt();
            scanner.nextLine();
            switch (userInput) {
                case 0:
                    running = false;
                    System.out.println("Goodbye, enjoy your day!");
                    break;

                case 1:
                    System.out.println("Your username:");
                    String username = scanner.nextLine();

                    // login algorithm

                    break;

                default:
                    System.out.println("Invalid input, try again please.");
                    break;
            }
        }

        System.out.println("Exiting.");
        scanner.close();

    }
}