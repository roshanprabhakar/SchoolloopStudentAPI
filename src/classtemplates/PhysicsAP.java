package classtemplates;

import Utilities.*;

public class PhysicsAP extends WeightedBlock {

    public PhysicsAP(Block blockData) {
        super(blockData.getEntries(), blockData.getClassName());
        defineCategories();
    }

    @Override
    public void defineCategories() {
        categories.put("Tests Quizzes", 0.7);
        categories.put("Labs Activities", 0.15);
        categories.put("Assignment Notebook", 0.1);
        categories.put("Problem Solving Grp", 0.05);
    }
}
