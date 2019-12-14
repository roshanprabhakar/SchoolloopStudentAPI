package Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Scraper {

    private String USERNAME;
    private String PASSWORD;

    private static final int FLEX_INDEX = 2;
    private static final int BLOCK_COUNT = 6;

    public static HashMap<String, Block> scrapeGradeData(String USERNAME, String PASSWORD) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "chromedriver78");
        WebDriver driver = new ChromeDriver();
        driver.get("https://fremonths.schoolloop.com/");

        //fill out username
        WebElement username = driver.findElement(new By.ByXPath("//*[@id=\"login_name\"]"));
        username.sendKeys(USERNAME);

        //fill out password
        WebElement password = driver.findElement(new By.ByXPath("//*[@id=\"password\"]"));
        password.sendKeys(PASSWORD);

        //submit
        WebElement submit = driver.findElement(new By.ByXPath("//*[@id=\"login_form\"]/a[1]"));
        submit.click();

//        //Query all progress reports
//        List<WebElement> reports = driver.findElements(By.className("ajax_accordion"));
//        for (WebElement report : reports) {
//            report.click();
//        }

        WebElement progressReportLink;
        String blockName;
//        ArrayList<Block> blocks = new ArrayList<>();
        HashMap<String, Block> blocks = new HashMap<>();
        for (int block = 1; block <= BLOCK_COUNT; block++) {
            if (block != FLEX_INDEX) {
                blockName = driver.findElement(new By.ByXPath("//*[@id=\"container_content\"]/div/div[1]/div[1]/div[2]/div/div[6]/div[" + block + "]/table/tbody/tr/td[2]/a")).getText();

                progressReportLink = driver.findElement(new By.ByXPath(
                        "//*[@id=\"container_content\"]/div/div[1]/div[1]/div[2]/div/div[6]/div[" + block + "]/table/tbody/tr/td[4]/a"
                ));
                progressReportLink.click();

//                System.out.println("here");
                //Raw text data for title row
//                WebElement tableHead = driver.findElement(By.cssSelector("[class='general_head'] tr"));

//                System.out.println("here");
                //Raw text data for rows
                List<WebElement> table = driver.findElements(By.cssSelector("[class='general_body'] tr"));

//                System.out.println("here");
                Entry entry;
                Block gradeCollection = new Block(blockName);
                for (int row = 1; row <= table.size(); row++) {

                    int col = 1;
                    boolean moreCol = true;
                    entry = new Entry();
                    while (moreCol) {
                        try {
//                            System.out.println("here");
                            String header =
                                    driver.findElement(new By.ByXPath(
                                        "//*[@id=\"container_content\"]/div/table[2]/tbody/tr/td[1]/table/thead/tr/th[" + col + "]"
                                    )).getText().replace("\n", " ").replace(":", "");

//                            System.out.println("here");
                            String cellValue =
                                    driver.findElement(new By.ByXPath(
                                            "//*[@id=\"container_content\"]/div/table[2]/tbody/tr/td[1]/table/tbody/tr[" + row + "]/td[" + col +  "]"
                                    )).getText().replace("\n", " ");

//                            System.out.println("header: " + header);
//                            System.out.println("value: " + cellValue);

                            if (header.contains("Score")) {
                                entry.put(header.replace("\n", ""), Score.parseScore(cellValue));
//                                System.out.println("cell value: " + cellValue);
//                                System.out.println("parsed value: " + entry.get(header.replace("\n", "")));
                            } else entry.put(header.replace("\n", ""), cellValue);

                            col++;
                        } catch (Exception e) {
                            moreCol = false;
                        }
                    }
                    gradeCollection.add(entry);
//                    return null;
                }

                WebElement gradeOnSchooLoop = driver.findElement(new By.ByXPath("//*[@id=\"container_content\"]/div/table[1]/tbody/tr[2]/td[1]/b[2]"));
                gradeCollection.setGradeOnSchoolloop(Double.parseDouble(
                        gradeOnSchooLoop.getText().replace("\n", "").replace(" ", "").replace("%", ""))
                );

                blocks.put(blockName, gradeCollection);
//                blocks.get(blockName).display();
                driver.navigate().back();
//                REMOVE
//                return blocks;
            }
        }
        driver.quit();
        return blocks;
    }

    /*
    XPATH PLAYGROUND
    //*[@id="container_content"]/div/table[1]/tbody/tr[2]/td[1]/b[2]
    //*[@id="container_content"]/div/table[1]/tbody/tr[2]/td[1]/b[2]
    //*[@id="container_content"]/div/table[1]/tbody/tr[2]/td[1]/b[2]
    //*[@id="container_content"]/div/table[1]/tbody/tr[2]/td[1]/b[2]
     */

    public static HashMap<String, Block> readGradeData(String filepath) {

        HashMap<String, Block> map = new HashMap<>();
        ArrayList<String> lines = Utils.readLines(filepath); assert lines != null;

        String previousBlock = null;
        for (int i = 0; i < lines.size(); i++) {
            if (!lines.get(i).contains("{")) {
                map.put(lines.get(i).replace("\n", ""),
                        new Block(lines.get(i).replace("\n", "")));
                previousBlock = lines.get(i).replace("\n", "");
                String[] options = lines.get(i).trim().replace("\n", "").split(" ");
                if (options.length > 1) map.get(previousBlock).setGradeOnSchoolloop(Double.parseDouble(options[options.length - 1]));
            } else map.get(previousBlock).add(Entry.parseEntry(lines.get(i).replace("\n", "")));
        }

        return map;
    }

    public static void writeMap(HashMap<String, Block> blocks, String outfile) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(outfile)));
            Block block;
            for (String key : blocks.keySet()) {
                block = blocks.get(key);
                writer.write(block.getClassName() + " " + block.getGradeOnSchoolloop() + "\n");
                for (Entry entry : block.getEntries()) {
                    writer.write(entry.toString() + "\n");
                }
                writer.write("\n");
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("Could not write map data to disk");
        }
    }
}

