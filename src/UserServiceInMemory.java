import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserServiceInMemory implements UserService {
    private Map<String, User> users = new HashMap<>();

    @Override
    public User signup(String username) {
        Scanner scanner = new Scanner(System.in);
        int userInput = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Signing up...");
        System.out.println("1. Regular user");
        System.out.println("2. Admin user");
        System.out.println("0. Back");

        switch (userInput) {
            case 0:
                System.out.println("Canceling creation of new user...");
                return null;

            case 1:
                return new RegularUser(username);

            case 2:
                return new AdminUser(username);

            default:
                System.out.println("Invalid input, try again please.");
                break;
        }
        return null;
    }

    @Override
    public User login(String username) {
        return null;
    }
}
