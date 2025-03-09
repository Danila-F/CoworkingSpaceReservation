package com.reservationapp.user;

import java.util.HashMap;
import java.util.Map;

public class UserServiceInMemory implements UserService {
    protected Map<String, User> users = new HashMap<>();

    private User addUser(User user) {
        users.put(user.getUsername(), user);
        return user;
    }

    public User signup(String username, boolean isAdmin) {
        return addUser(new User(username, isAdmin));
    }

    @Override
    public User login(String username) {
        return users.get(username);
    }

    public boolean userExists(String username) {
        return users.containsKey(username);
    }

    public Map<String, User> getAllUsers() {
        return users;
    }
}