import java.util.Scanner;

public class ReservationApp {
    public static void main(String[] args) {

        /* System.out.println("Hello and welcome!");
        for (int i = 1; i <= 5; i++) {
            System.out.println("i = " + i);
        } */

        Scanner scanner = new Scanner(System.in);
        String stopString = "exit";
        String adminModeString = "1";
        String usualModeString = "2";
        String userInput = "";

        while (!userInput.equals(stopString)) {
            System.out.printf("Please, write \"%s\" for admin or \"%s\" for usual login mode (\"%s\" to exit):%n", adminModeString, usualModeString, stopString);
            userInput = scanner.nextLine();
//            System.out.println("Write your username to log in or sign up:");
//            if (userInput.equals(adminModeString)) {
//
//            }
        }

        System.out.println("Exiting.");
        scanner.close();

    }
}