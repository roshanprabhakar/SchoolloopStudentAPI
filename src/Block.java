import java.util.ArrayList;
import java.util.HashMap;

public class Block {

    private ArrayList<Entry> grades;
    private HashMap<String, Double> weightages;
    private String className;

    public Block(String className) {
        grades = new ArrayList<>();
        this.className = className;
    }

    public void loadWeightages(HashMap<String, Double> weightages) {
        this.weightages = weightages;
    }

    public ArrayList<Entry> getGrades() {
        return grades;
    }

    public String getClassName() {
        return className;
    }

    public void add(Entry entry) {
        grades.add(entry);
    }

    public String toString() {
        return grades.toString();
    }

//    public abstract void applyWeightCategories(Weights weights);
}
