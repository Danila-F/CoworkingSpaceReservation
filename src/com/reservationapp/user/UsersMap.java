package com.reservationapp.user;

import java.util.Map;

interface UsersMap {
    void add(User user);
    User get(String username);
    boolean isExist(String username);
    Map<String, User> getAll();
}
