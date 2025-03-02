public class RegularUser extends User {

    public RegularUser(String username) {
        super(username);
    }

    @Override
    public UserRole getRole() {
        return UserRole.REGULAR;
    }
}
