package com.reservationapp.user;

import java.util.Map;

public class UserService {
    private final UsersMap users = new UsersMapWithStateSaving();

    public User signup(String username, boolean isAdmin) {
        users.add(new User(username, isAdmin));
        return login(username);
    }

    public User login(String username) {
        return users.get(username);
    }

    public boolean userExists(String username) {
        return users.isExist(username);
    }

    public Map<String, User> getAllUsers() {
        return users.getAll();
    }
}