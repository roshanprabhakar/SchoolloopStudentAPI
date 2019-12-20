package classtemplates;

import Utilities.Block;
import Utilities.Entry;
import Utilities.WeightedBlock;

import java.util.ArrayList;

public class APUSH extends WeightedBlock {

    public APUSH(Block blockData) {
        super(blockData.getEntries(), blockData.getClassName(), blockData.getGradeOnSchoolloop());
        defineCategories();
    }

    public APUSH(Block blockData, boolean condition) {
        super(blockData.getEntries(), blockData.getClassName(), blockData.getGradeOnSchoolloop());
        defineCategories2();
    }

    public void defineCategories2() {
        categories.put("groupwork", 1.0);
        categories.put("Quiz", 0.92);
        categories.put("Homework", 1.0);
        categories.put("Test", 0.8718);
        categories.put("essay", 0.824);
        categories.put("Discussion", 1.0909);
    }

    @Override
    public void defineCategories() {
//        categories.put("groupwork", 1.0);
//        categories.put("Quiz", 0.8894);
//        categories.put("Homework", 1.0);
//        categories.put("Test", 0.8694);
//        categories.put("essay", 0.8);
//        categories.put("Discussion", 0.9091);

        categories.put("groupwork", 0.18);
        categories.put("Quiz", 0.11);
        categories.put("Homework", 0.10);
        categories.put("Test", 0.40);
        categories.put("essay", 0.10);
        categories.put("Discussion", 0.09);
    }

    public ArrayList<int[]> findWeights() {
        ArrayList<int[]> weight_collection = new ArrayList<>();
        for (int grp = 5; grp < 100; grp++) {
            for (int qz = 10; qz < 100 - grp; qz++) {
                for (int hw = 5; hw < 100 - grp - qz; hw++) {
                    for (int tst = 10; tst < 100 - grp - qz - hw; tst++) {
                        for (int esy = 5; esy < 100 - grp - qz - hw - tst; esy++) {
                            for (int dscn = 5; dscn < 100 - grp - qz - hw - tst - esy; dscn++) {

                                if (tst > grp && tst > qz && tst > hw && tst > esy && tst > dscn) {
//                                    if (qz > grp && qz > hw && qz > dscn) {
//                                        if (esy > hw && esy > grp) {
//                                            if (dscn > hw && grp > hw) {

                                                double total = 0;

                                                double grpGrade = grp * categories.get("groupwork");
                                                total += grpGrade;
                                                double qzGrade = qz * categories.get("Quiz");
                                                total += qzGrade;
                                                double hwGrade = hw * categories.get("Homework");
                                                total += hwGrade;
                                                double tstGrade = tst * categories.get("Test");
                                                total += tstGrade;
                                                double esyGrade = esy * categories.get("essay");
                                                total += esyGrade;
                                                double dscnGrade = dscn * categories.get("Discussion");
                                                total += dscnGrade;

//                                              System.out.println((int) (total * 100));
//                                              System.out.println((int) (getGradeOnSchoolloop() * 100));

                                                if ((int) (total * 100) == (int) (getGradeOnSchoolloop() * 100)) {
                                                    if (true) {

                                                        int[] weights = new int[6];

//                                                        System.out.println();
//                                                        System.out.println();
//                                                        System.out.println("Possible category weightages: ");
//                                                        System.out.println("grp: " + grp);
//                                                        System.out.println("qz: " + qz);
//                                                        System.out.println("hw: " + hw);
//                                                        System.out.println("tst: " + tst);
//                                                        System.out.println("esy: " + esy);
//                                                        System.out.println("dscn: " + dscn);

                                                        weights[0] = grp; weights[1] = qz; weights[2] = hw; weights[3] = tst; weights[4] = esy; weights[5] = dscn;

                                                        weight_collection.add(weights);
                                                    }
                                                }
                                            }
                                        }
//                                    }
//                                }
                            }
                        }
                    }
                }
            }
//        }
        return weight_collection;
    }
}