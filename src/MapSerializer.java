import java.io.*;
import java.util.Map;

public class MapSerializer {
    public static <K, V> void serializeMap(Map<K, V> map, String fileName) throws IOException {
        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            stream.writeObject(map);
        }
    }
}
