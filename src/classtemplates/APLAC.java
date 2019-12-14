package classtemplates;

import Utilities.Block;
import Utilities.WeightedBlock;

public class APLAC extends WeightedBlock {

    public APLAC(Block blockData) {
        super(blockData.getEntries(), blockData.getClassName(), blockData.getGradeOnSchoolloop());
        defineCategories();
    }

    @Override
    public void defineCategories() {
        categories.put("Mastery", 0.8);
        categories.put("Practice", 0.2);
    }
}
