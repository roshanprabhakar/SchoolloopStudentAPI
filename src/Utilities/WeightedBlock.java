package Utilities;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class WeightedBlock extends Block {

    protected HashMap<String, Double> categories;

    public WeightedBlock(String className) {
        super(className);
        this.categories = new HashMap<>();
    }

    public WeightedBlock(ArrayList<Entry> entries, String className) {
        super(entries, className);
        this.categories = new HashMap<>();
    }

    public void addCategory(String category, double weight) {
        categories.put(category, weight);
    }

    private Score getScore(String category) {
        double earned = 0;
        double total = 0;
        for (Entry entry : entries) {
            if (Utils.belongsTo(category, entry.get("Assessment").toString())) {
                Score score = Score.readScore(entry.get("Score").toString());
                earned += score.getEarned();
                total += score.getPossible();
            }
        }
        return new Score(earned, total);
    }

    public double calculateGrade() {
        double grade = 0;
        for (String category : categories.keySet()) {
            Score categoryScore = getScore(category);
            grade += (categoryScore.getEarned() / categoryScore.getPossible()) * categories.get(category);
        }
        return grade;
    }

    public void analyze() {
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("BLOCK: " + className + " ------------------");
        for (String category : categories.keySet()) {
            System.out.println("-----------------------------------------");
            System.out.println("CATEGORY: " + category);
            Score categoryScore = getScore(category);
            System.out.println("EARNED POINTS: " + categoryScore.getEarned());
            System.out.println("TOTAL POINTS: " + categoryScore.getPossible());
            System.out.println("CATEGORY PERCENTAGES: " + (int) (categoryScore.percentage() * 100) + "%");
            System.out.println("-----------------------------------------");
            System.out.println();
        }
        System.out.println("BLOCK: " + className + " ------------------");
        System.out.println("TOTAL GRADE: " + calculateGrade());
        System.out.println();
    }

    public abstract void defineCategories();
}
