package Utilities;

import classtemplates.PhysicsAP;

import java.util.HashMap;

public class Main {

    public static final String USERNAME = "Your username";
    public static final String PASSWORD = "Your password";

    public static void main(String[] args) throws InterruptedException {

        //Scrapes data from schoolloop to GRADES
//        Scraper.writeMap(Scraper.scrapeGradeData(USERNAME, PASSWORD), "GRADES.txt");

        HashMap<String, Block> blocks = Scraper.readGradeData("GRADES.txt"); //This HashMap contains all the data from GRADES in the form of <Block name, Block Object>

        // example usage, API purposed for providing data to create more methods
        PhysicsAP physics = new PhysicsAP(blocks.get("Physics AP"));
        physics.analyze();
    }
}
