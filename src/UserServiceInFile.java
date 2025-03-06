import java.io.*;
import java.util.HashMap;

public class UserServiceInFile extends UserServiceInMemory implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private static final String FILE_PATH = "users.ser";

    public UserServiceInFile() {
        deserialize();
        Runtime.getRuntime().addShutdownHook(new Thread(this::serialize));
    }

    private void serialize() {
        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            stream.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void deserialize() {
        File file = new File(FILE_PATH);
        if (file.exists()) {
            try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
                users = (HashMap<String, User>) stream.readObject();
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
    }
}