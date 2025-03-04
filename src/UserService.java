public interface UserService {
    User signup(String username, boolean isAdmin);
    User login(String username);
}
