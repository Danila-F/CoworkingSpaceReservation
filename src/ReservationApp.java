import java.util.Scanner;

public class ReservationApp {
    public static void main(String[] args) {

        boolean running = true;
        UserService userService = new UserServiceInMemory();
        WorkspaceService workspaceService = new WorkspaceServiceInMemory();
        User user = null;

        try (Scanner scanner = new Scanner(System.in)) {

            while (running) {

                if (user != null) {
                    if (user.getIsAdmin()) {
                        // Menu for logged admin user
                        System.out.println("\n******Menu******");
                        System.out.println("1. Log out");
                        System.out.println("2. Add a new coworking space");
                        System.out.println("3. Show all coworking spaces");
                        System.out.println("4. Edit a coworking space");
                        System.out.println("0. Exit");

                        String userInput = scanner.nextLine();

                        switch (userInput) {
                            case "0":
                                running = false;
                                System.out.println("Goodbye, enjoy your day!");
                                break;

                            case "1":
                                System.out.println("Goodbye, " + user.getUsername());
                                user = null;
                                break;

                            case "2":
                                System.out.println("Please, write description for a new workspace:");
                                String description = scanner.nextLine();
                                workspaceService.createWorkspace(description, scanner);
                                break;

                            case "3":
                                System.out.println("All workspaces:");
                                workspaceService.showAllWorkspaces();
                                break;

                            case "4":
                                workspaceService.editWorkspace(scanner);
                                break;

                            default:
                                System.out.println("Invalid input, please try again.");
                                break;
                        }
                    } else {
                        // Menu for logged non-admin user
                        System.out.println("\n******Menu******");
                        System.out.println("1. Log out");
                        System.out.println("0. Exit");

                        String userInput = scanner.nextLine();

                        switch (userInput) {
                            case "0":
                                running = false;
                                System.out.println("Goodbye, enjoy your day!");
                                break;

                            case "1":
                                System.out.println("Goodbye, " + user.getUsername());
                                user = null;
                                break;

                            default:
                                System.out.println("Invalid input, please try again.");
                                break;
                    }
                }
                } else {
                    // Start menu
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

                            user = userService.login(username, scanner);
                            if (user != null) {
                                System.out.println("Hello, " + user.getUsername());
                            }
                            break;

                        default:
                            System.out.println("Invalid input, please try again.");
                            break;
                    }
                }
            }

            System.out.println("Exiting.");

        }
    }
}