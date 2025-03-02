import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class User {
    private String username;
    private Map<Operation, Set<User>> accessMap;

    public User(String username) {
        this.username = username;
        this.accessMap = new HashMap<>();
    }

    public String getUsername() {
        return username;
    }

    public void setAccess(Operation operation, Set<User> users) {
        accessMap.put(operation, users);
    }

    public Set<User> getAccessibleUsers(Operation operation) {
        return accessMap.getOrDefault(operation, Collections.emptySet());
    }

    public boolean canAccess(Operation operation, User targetUser) {
        return getAccessibleUsers(operation).contains(targetUser);
    }
}
