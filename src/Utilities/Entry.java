package Utilities;

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

    public static Entry parseEntry(String line) {
        Entry out = new Entry();
        line = line.substring(1, line.length() - 1);
        String[] elements = line.split(", ");
//        System.out.println(Arrays.toString(elements));
        for (String element : elements) {
            String[] broken = element.split("=");
            if (broken.length == 1) broken = new String[]{broken[0], ""};
//            System.out.println(Arrays.toString(broken));
            if (broken[0].equals("Score")) {
//                System.out.println("-------------");
//                System.out.println(broken[1]);
                out.put("Score", Score.readScore(broken[1]));
            } else out.put(broken[0], broken[1]);
//            System.out.println("key: " + broken[0]);
//            System.out.println("val: " + out.get(broken[0]));
        }
        return out;
    }
}