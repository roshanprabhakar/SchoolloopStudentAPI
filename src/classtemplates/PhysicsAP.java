package classtemplates;

import Utilities.*;

public class PhysicsAP extends WeightedBlock {

    public PhysicsAP(Block blockData) {
        super(blockData.getEntries(), blockData.getClassName(), blockData.getGradeOnSchoolloop());
        defineCategories();
    }

    @Override
    public void defineCategories() {
        categories.put("Tests Quizzes", 0.6);
        categories.put("Labs Activities", 0.2);
        categories.put("Assignment Notebook", 0.15);
        categories.put("Problem Solving Grp", 0.05);
    }
}
