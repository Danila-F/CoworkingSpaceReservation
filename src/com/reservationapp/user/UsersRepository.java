package com.reservationapp.user;

import java.io.*;
import java.util.HashMap;

class UsersRepository implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private static final String FILE_PATH = "users.ser";
    private final UsersMapWithStateSaving usersMap;

    public UsersRepository(UsersMapWithStateSaving usersMap) {
        this.usersMap = usersMap;
    }

    void save() {
        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            stream.writeObject(usersMap.users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    void load() {
        File file = new File(FILE_PATH);
        if (file.exists()) {
            try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
                usersMap.users = (HashMap<String, User>) stream.readObject();
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
    }
}
