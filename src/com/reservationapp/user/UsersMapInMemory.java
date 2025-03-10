package com.reservationapp.user;

import java.util.HashMap;
import java.util.Map;

class UsersMapInMemory implements UsersMap {
    protected Map<String, User> users = new HashMap<>();

    @Override
    public void add(User user) {
        users.put(user.getUsername(), user);
    }

    @Override
    public User get(String username) {
        return users.get(username);
    }

    @Override
    public boolean isExist(String username) {
        return users.containsKey(username);
    }

    @Override
    public Map<String, User> getAll() {
        return users;
    }
}
