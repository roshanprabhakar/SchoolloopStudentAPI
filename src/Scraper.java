import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.*;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Scraper {

    private static final String USERNAME = "";
    private static final String PASSWORD = "";

    private static final int FLEX_INDEX = 2;
    private static final int BLOCK_COUNT = 6;

    public static void main(String[] args) throws InterruptedException {

//        System.exit(0);
        ArrayList<Block> blocks = scrapeAssignmentData();

        for (Block block : blocks) {
            System.out.println("BLOCK: " + block.getClassName());
            for (Entry entry : block.getGrades()) {
                System.out.println(entry);
            }
        }
    }

    public static ArrayList<Block> scrapeAssignmentData() throws InterruptedException {

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
        ArrayList<Block> blocks = new ArrayList<>();
        for (int block = 1; block <= BLOCK_COUNT; block++) {
            if (block != FLEX_INDEX) {
                blockName = driver.findElement(new By.ByXPath("//*[@id=\"container_content\"]/div/div[1]/div[1]/div[2]/div/div[6]/div[" + block + "]/table/tbody/tr/td[2]/a")).getText();

                progressReportLink = driver.findElement(new By.ByXPath(
                        "//*[@id=\"container_content\"]/div/div[1]/div[1]/div[2]/div/div[6]/div[" + block + "]/table/tbody/tr/td[4]/a"
                ));
                progressReportLink.click();

//                System.out.println("here");
                //Raw text data for title row
                WebElement tableHead = driver.findElement(By.cssSelector("[class='general_head'] tr"));

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
                                    )).getText().replace("\n", " ");

//                            System.out.println("here");
                            String cellValue =
                                    driver.findElement(new By.ByXPath(
                                            "//*[@id=\"container_content\"]/div/table[2]/tbody/tr/td[1]/table/tbody/tr[" + row + "]/td[" + col +  "]"
                                    )).getText().replace("\n", " ");

//                            System.out.println("header: " + header);
//                            System.out.println("value: " + cellValue);

                            if (header.contains("Score:")) {
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
                }
                blocks.add(gradeCollection);
                driver.navigate().back();
            }
        }
        driver.quit();

        return blocks;
    }

    //UTIL
    public static void highLighterMethod(WebDriver driver, WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', 'background: white; border: 2px solid red;');", element);
    }

    /*  XPATH PLAYGROUND
        //*[@id="container_content"]/div/div[1]/div[1]/div[2]/div/div[6]/div[1]/table/tbody/tr/td[2]/a
        //*[@id="container_content"]/div/div[1]/div[1]/div[2]/div/div[6]/div[3]/table/tbody/tr/td[2]/a
        //*[@id="container_content"]/div/div[1]/div[1]/div[2]/div/div[6]/div[4]/table/tbody/tr/td[2]/a
        //*[@id="container_content"]/div/div[1]/div[1]/div[2]/div/div[6]/div[6]/table/tbody/tr/td[2]/a









    */
}

