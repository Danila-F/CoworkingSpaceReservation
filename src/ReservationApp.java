import java.util.Scanner;

public class ReservationApp {
    public static void main(String[] args) {

        boolean running = true;
        UserService userService = new UserServiceInMemory();
        try (Scanner scanner = new Scanner(System.in)) {

            while (running) {
                System.out.println("\n******Menu******");
                System.out.println("1. Log in or sign up");
                System.out.println("0. Exit");

                String userInput = scanner.nextLine();

                switch (userInput) {
                    case "0":
                        running = false;
                        System.out.println("Goodbye, enjoy your day!");
                        break;

                    case "1":
                        System.out.println("Your username:");
                        String username = scanner.nextLine();

                        User user = userService.login(username, scanner);
                        if (user != null) {
                            System.out.println("Hello, " + user.getUsername());
                        }
                        break;

                    default:
                        System.out.println("Invalid input, try again please.");
                        break;
                }
            }

            System.out.println("Exiting.");

        }
    }
}