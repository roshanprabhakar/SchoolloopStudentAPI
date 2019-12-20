package Utilities;

import classtemplates.APLAC;
import classtemplates.APUSH;
import classtemplates.PhysicsAP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Main {

    public static final String USERNAME = "USERNAME";
    public static final String PASSWORD = "PASSWORD";
    public static final int FLEX_INDEX = 2;
    public static final int BLOCK_COUNT = 6;

    // example usage, API purposed for providing data to create more methods
    public static void main(String[] args) throws InterruptedException {

        //Scrapes data from Schoolloop to GRADES, fix data collection for APUSH
//        Scraper.writeMap(Scraper.scrapeGradeData(USERNAME, PASSWORD, FLEX_INDEX, BLOCK_COUNT), "GRADES.txt");

        //This HashMap contains all the data from GRADES in the form of <Block name, Block Object>
        //The names contained in this map are exactly as they appear on the school loop dashboard
        //Ignore empty map entry, soley used for comparison
        HashMap<String, Block> blocks = Scraper.readGradeData("GRADES.txt"); //roshan
        HashMap<String, Block> blocks1 = Scraper.readGradeData("temp.txt"); //neel


//        APLAC aplac = new APLAC(blocks.get(getFromBlocks("AP Lang/Comp", blocks)));
//
//        Entry finalExam = new Entry();
//        finalExam.put("Assessment", "Mastery Final Exam");
//        finalExam.put("Score", new Score(26, 30));
//        aplac.add(finalExam);
//
//        Entry timedWrite = new Entry();
//        finalExam.put("Assessment", "Mastery Timed Write");
//        finalExam.put("Score", new Score(16, 18));
//        aplac.add(finalExam);
//
//
//        Entry fullCredit = new Entry();
//        fullCredit.put("Assessment", "Practice compensation");
//        fullCredit.put("Score", new Score(30, 0));
////        aplac.add(fullCredit);
//
//        aplac.analyze();


        APUSH apush = new APUSH(blocks.get(getFromBlocks("US History AP", blocks)));

        Entry finalExam = new Entry();
        finalExam.put("Assessment", "Test Final Exam");
        finalExam.put("Score", new Score(67, 80));

        apush.add(finalExam);
        apush.analyze();

//        //Finding weightages for APUSH
//        System.out.println("here");
//        APUSH apush = new APUSH(blocks.get(getFromBlocks("US History AP", blocks)));
//        System.out.println(apush.getGradeOnSchoolloop());
//        ArrayList<int[]> possibleWeights = apush.findWeights();
//        System.out.println(possibleWeights.size());
//
//        System.out.println("here");
//        APUSH neelApush = new APUSH(blocks1.get(getFromBlocks("US History AP", blocks1)), true);
//        System.out.println(neelApush.getGradeOnSchoolloop());
//        ArrayList<int[]> additionalPossibleWeights = neelApush.findWeights();
//        System.out.println(additionalPossibleWeights.size());
//
//        System.out.println("here");
//        ArrayList<int[]> commonWeightages = getCommonWeights(possibleWeights, additionalPossibleWeights);
//        for (int[] weight : commonWeightages) {
//            System.out.println(Arrays.toString(weight));
//        }
//        System.out.println();
//        System.out.println(commonWeightages.size());

//        apush.analyze();

//        PhysicsAP physics = new PhysicsAP(blocks.get(getFromBlocks("Physics", blocks)));

//        PhysicsAP physics = new PhysicsAP(blocks.get(getFromBlocks("Physics", blocks))); //takes in block to account for downcasting
//        physics.analyze();
//
//        Entry finalExam = new Entry();
//        finalExam.put("Assessment", "Tests Quizzes Final Exam");
//        finalExam.put("Score", new Score(357,525));
//
//        physics.add(finalExam);
//        physics.analyze();

//        APLAC aplac = new APLAC(blocks.get(getFromBlocks("AP Lang/Comp", blocks))); //takes in block to account for downcasting
//        aplac.analyze();
//
//        Entry finalExam = new Entry();
//        finalExam.put("Assessment", "Mastery final exam");
//        finalExam.put("Score", new Score(70,100));
//
//        aplac.add(finalExam);
//        aplac.analyze();

    }

    // Specifically for APUSH
    public static ArrayList<int[]> getCommonWeights(ArrayList<int[]> u, ArrayList<int[]> v) {
        ArrayList<int[]> common = new ArrayList<>();
        for (int[] weight1 : u) {
            for (int[] weight2 : v) {
                if (equals(weight1, weight2)) common.add(weight1);
            }
        }
        return common;
    }

    // Specifically for APUSH
    public static boolean equals(int[] u, int[] v) {
        for (int i = 0; i < u.length; i++) {
            if (u[i] != v[i]) return false;
        }
        return true;
    }

    public static String getFromBlocks(String key, HashMap<String, Block> map) {
        for (String blockTitle : map.keySet()) {
            if (blockTitle.contains(key)) return blockTitle;
        }
        return null;
    }

}