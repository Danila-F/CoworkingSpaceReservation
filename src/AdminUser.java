public class AdminUser extends User{

    public AdminUser(String username) {
        super(username);
    }

    @Override
    public UserRole getRole() {
        return UserRole.ADMIN;
    }

}
