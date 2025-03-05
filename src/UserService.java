import java.util.Map;

public interface UserService {
    User signup(String username, boolean isAdmin);
    User login(String username);
    boolean userExists(String username);
    Map<String, User> getAllUsers();
}
