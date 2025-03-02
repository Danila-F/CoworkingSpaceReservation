import java.util.HashMap;
import java.util.Map;

public class UserFactory {
    private static final Map<Map.Entry<UserRole, Operation>, UserGroup> userRolesOperationsGroups = Map.of(
            Map.entry(UserRole.REGULAR, Operation.READ_BOOKINGS), UserGroup.SELF,
            Map.entry(UserRole.REGULAR, Operation.CREATE_BOOKINGS), UserGroup.SELF,
            Map.entry(UserRole.REGULAR, Operation.EDIT_BOOKINGS), UserGroup.SELF,
            Map.entry(UserRole.ADMIN, Operation.READ_BOOKINGS), UserGroup.ALL,
            Map.entry(UserRole.ADMIN, Operation.CREATE_BOOKINGS), UserGroup.ALL,
            Map.entry(UserRole.ADMIN, Operation.EDIT_BOOKINGS), UserGroup.ALL
    );

    public User createUser(String username, UserRole userRole) {
        User user = new User(username);
        Map<Map.Entry<UserRole, Operation>, UserGroup> filtered = new HashMap<>();

        for (Map.Entry<Map.Entry<EnumA, EnumB>, EnumC> entry : permissions.entrySet()) {
            if (entry.getKey().getKey() == userRole) {
                filtered.put(entry.getKey(), entry.getValue());
            }
        }
        user.setAccess();
        return user;
    }
}
