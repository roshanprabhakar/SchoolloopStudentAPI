package Utilities;

import java.util.ArrayList;
import java.util.HashMap;

public class Block {

    protected ArrayList<Entry> entries;
    protected String className;

    public Block(String className) {
        entries = new ArrayList<>();
        this.className = className;
    }

    public Block(ArrayList<Entry> entries, String className) {
        this(className);
        this.entries = entries;
    }

    public ArrayList<Entry> getEntries() {
        return entries;
    }

    public String getClassName() {
        return className;
    }

    public void add(Entry entry) {
        entries.add(entry);
    }

    public String toString() {
        return entries.toString();
    }

    public void display() {
        System.out.println();
        System.out.println("BLOCK: " + getClassName());
        for (Entry entry : entries) {
            System.out.println(entry);
        }
    }
}
