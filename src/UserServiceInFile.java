import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class UserServiceInFile extends UserServiceInMemory implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private static final String FILE_PATH = "users.ser";

    public UserServiceInFile() {
        deserialize();

        // Добавляем shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread(this::serialize));
    }

    private void serialize() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(super.users);
            System.out.println("Объект UserServiceInMemory сохранён перед завершением.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}