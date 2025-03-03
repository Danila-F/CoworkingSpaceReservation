import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserServiceInMemory implements UserService {
    private final Map<String, User> users = new HashMap<>();

    private User addUser(User user) {
        users.put(user.getUsername(), user);
        return user;
    }

    private User signup(String username, Scanner scanner) {
        System.out.println("Signing up...");
        System.out.println("1. Regular user");
        System.out.println("2. Admin user");
        System.out.println("0. Back");

        User user;
        String userInput = scanner.nextLine();

        switch (userInput) {
            case "0":
                System.out.println("Canceling creation of new user...");
                user = null;
                break;

            case "1":
                System.out.println("Welcome, " + username);
                user = addUser(new User(username, false));
                break;

            case "2":
                System.out.println("Welcome, " + username);
                user = addUser(new User(username, true));
                break;

            default:
                System.out.println("Invalid input, canceling creation of new user...");
                user = null;
                break;
        }
        return user;
    }

    @Override
    public User login(String username, Scanner scanner) {
        if (users.containsKey(username)) {
            return users.get(username);
        } else {
            return signup(username, scanner);
        }
    }
}
