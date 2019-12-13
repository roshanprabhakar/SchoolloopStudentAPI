import java.util.ArrayList;

public class Block {

    private ArrayList<Entry> grades;

    public Block() {
        grades = new ArrayList<>();
    }

    public ArrayList<Entry> getGrades() {
        return grades;
    }

    public void add(Entry entry) {
        grades.add(entry);
    }

    public String toString() {
        return grades.toString();
    }

//    public abstract void applyWeightCategories(Weights weights);
}
