import java.util.HashMap;

public class Entry {

    private HashMap<String, Object> values;

    public Entry() {
        values = new HashMap<>();
    }

    public void put(String key, Object value) {
        values.put(key, value);
    }

    public Object get(String key) {
        return values.get(key);
    }

    public HashMap<String, Object> getValues() {
        return values;
    }

    public String toString() {
        return values.toString();
    }

    public void parseScore() {

    }
}