package Utilities;

import classtemplates.APLAC;
import classtemplates.PhysicsAP;

import java.util.HashMap;

public class Main {

    public static final String USERNAME = "Your username";
    public static final String PASSWORD = "Your password";

    // example usage, API purposed for providing data to create more methods
    public static void main(String[] args) throws InterruptedException {

        //Scrapes data from schoolloop to GRADES
//        Scraper.writeMap(Scraper.scrapeGradeData(USERNAME, PASSWORD), "GRADES.txt");

        HashMap<String, Block> blocks = Scraper.readGradeData("GRADES.txt"); //This HashMap contains all the data from GRADES in the form of <Block name, Block Object>
        //The names contained in this map are exactly as they appear on the school loop dashboard

        PhysicsAP physics = new PhysicsAP(blocks.get("Physics AP"));
//        physics.analyze();

        APLAC aplac = new APLAC(blocks.get("AP Lang/Comp"));
        aplac.analyze();
    }
}
