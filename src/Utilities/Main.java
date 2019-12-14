package Utilities;

import classtemplates.APLAC;
import classtemplates.PhysicsAP;

import java.util.HashMap;

public class Main {

    public static final String USERNAME = "USERNAME";
    public static final String PASSWORD = "PASSWORD";

    // example usage, API purposed for providing data to create more methods
    public static void main(String[] args) throws InterruptedException {

        //Scrapes data from Schoolloop to GRADES, fix data collection for apush
//        Scraper.writeMap(Scraper.scrapeGradeData(USERNAME, PASSWORD), "GRADES.txt");

        //This HashMap contains all the data from GRADES in the form of <Block name, Block Object>
        //The names contained in this map are exactly as they appear on the school loop dashboard
        //Ignore empty map entry, soley used for comparison
        HashMap<String, Block> blocks = Scraper.readGradeData("GRADES.txt");

        PhysicsAP physics = new PhysicsAP(blocks.get(getFromBlocks("Physics", blocks)));
        System.out.println(physics.getGradeOnSchoolloop());
        physics.analyze();

        Entry finalExam = new Entry();
        finalExam.put("Assessment", "Tests Quizzes Final Exam");
        finalExam.put("Score", new Score(450,600));

        physics.add(finalExam);
        physics.analyze();

//        APLAC aplac = new APLAC(blocks.get("AP Lang/Comp"));
//        aplac.analyze();
    }

    public static String getFromBlocks(String key, HashMap<String, Block> map) {
        for (String blockTitle : map.keySet()) {
            if (blockTitle.contains(key)) return blockTitle;
        }
        return null;
    }

}
