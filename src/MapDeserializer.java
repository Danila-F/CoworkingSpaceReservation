import java.io.*;
import java.util.Map;

public class MapDeserializer {
    @SuppressWarnings("unchecked")
    public static <K, V> Map<K, V> deserializeMap(String fileName) throws IOException, ClassNotFoundException {
        try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(fileName))) {
            return (Map<K, V>) stream.readObject();
        }
    }
}