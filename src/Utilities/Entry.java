package Utilities;

import java.util.ArrayList;
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
        StringBuilder out = new StringBuilder("{");
        for (String key : values.keySet()) {
            out.append("\"").append(key).append("\"=\"").append(values.get(key)).append("\", ");
        }
        out = new StringBuilder(out.substring(0, out.length() - 2));
        out.append("}");
        return out.toString();
    }

    public static Entry parseEntry(String line) {
        line = line.substring(1, line.length() - 1);
        ArrayList<String> collection = new ArrayList<>();
        int quoteCount = 0;
        String append = "";
        boolean added = false;
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '\"') quoteCount++;
            if (quoteCount % 2 == 1) {
                append += line.charAt(i);
                added = false;
            } else if (quoteCount % 2 == 0 && !added) {
                collection.add(append.replace("\"", ""));
                append = "";
                added = true;
            }
        }
        return parseEntry(collection);
    }

    private static Entry parseEntry(ArrayList<String> collection) {
        Entry entry = new Entry();
        for (int i = 0; i < collection.size() - 1; i += 2) {
            entry.put(collection.get(i), collection.get(i + 1));
        }
        return entry;
    }

//    // Implemented for keys not surrounded by quotes
//    public static Entry parseEntry(String line) {
//        Entry out = new Entry();
//        line = line.substring(1, line.length() - 1);
//        String[] elements = line.split(", ");
////        System.out.println(Arrays.toString(elements));
//        for (String element : elements) {
//            String[] broken = element.split("=");
//            if (broken.length == 1) broken = new String[]{broken[0], ""};
////            System.out.println(Arrays.toString(broken));
//            if (broken[0].equals("Score")) {
////                System.out.println("-------------");
////                System.out.println(broken[1]);
//                out.put("Score", Score.readScore(broken[1]));
//            } else out.put(broken[0], broken[1]);
////            System.out.println("key: " + broken[0]);
////            System.out.println("val: " + out.get(broken[0]));
//        }
//        return out;
//    }
}