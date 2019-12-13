import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.*;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class Scraper {

    private static final String USERNAME = "rprabhakar942";
    private static final String PASSWORD = "TyKoRoshan21@0g";

    private static final int FLEX_INDEX = 2;
    private static final int BLOCK_COUNT = 6;

    public static void main(String[] args) throws InterruptedException {
        ArrayList<Block> blocks = scrapeAssignmentData();

        for (Block block : blocks) {
            System.out.println("BLOCK -----------");
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
        ArrayList<Block> blocks = new ArrayList<>();
        for (int block = 1; block <= BLOCK_COUNT; block++) {
            if (block != FLEX_INDEX) {
                progressReportLink = driver.findElement(new By.ByXPath(
                        "//*[@id=\"container_content\"]/div/div[1]/div[1]/div[2]/div/div[6]/div[" + block + "]/table/tbody/tr/td[4]/a"
                ));
                progressReportLink.click();

                //Raw text data for title row
                WebElement tableHead = driver.findElement(By.cssSelector("[class='general_head'] tr"));

                //Raw text data for rows
                List<WebElement> table = driver.findElements(By.cssSelector("[class='general_body'] tr"));

                Entry entry;
                Block gradeCollection = new Block();
                for (int row = 1; row <= table.size(); row++) {

                    int col = 1;
                    boolean moreCol = true;
                    entry = new Entry();
                    while (moreCol) {
                        try {
                            String header =
                                    driver.findElement(new By.ByXPath(
                                        "//*[@id=\"container_content\"]/div/table[2]/tbody/tr/td[1]/table/thead/tr/th[" + col + "]"
                                    )).getText().replace("\n", " ");

                            String cellValue =
                                    driver.findElement(new By.ByXPath(
                                            "//*[@id=\"container_content\"]/div/table[2]/tbody/tr/td[1]/table/tbody/tr[" + row + "]/td[" + col +  "]"
                                    )).getText().replace("\n", " ");

//                            System.out.println("header: " + header);
//                            System.out.println("value: " + cellValue);

                            if (header.contains("Score:")) {
//                            if (false) {
                                entry.put(header.replace("\n", ""), Score.parseScore(cellValue));
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

        Corresponding header paths:
        //*[@id="container_content"]/div/table[2]/tbody/tr/td[1]/table/thead/tr/th[1]
        //*[@id="container_content"]/div/table[2]/tbody/tr/td[1]/table/thead/tr/th[2]
        //*[@id="container_content"]/div/table[2]/tbody/tr/td[1]/table/thead/tr/th[3]
        //*[@id="container_content"]/div/table[2]/tbody/tr/td[1]/table/thead/tr/th[4]

        Row 1 Cells:
        //*[@id="container_content"]/div/table[2]/tbody/tr/td[1]/table/tbody/tr[1]/td[1]
        //*[@id="container_content"]/div/table[2]/tbody/tr/td[1]/table/tbody/tr[1]/td[2]
        //*[@id="container_content"]/div/table[2]/tbody/tr/td[1]/table/tbody/tr[1]/td[3]

        Row 2 Cells:
        //*[@id="container_content"]/div/table[2]/tbody/tr/td[1]/table/tbody/tr[2]/td[1]
        //*[@id="container_content"]/div/table[2]/tbody/tr/td[1]/table/tbody/tr[2]/td[2]
        //*[@id="container_content"]/div/table[2]/tbody/tr/td[1]/table/tbody/tr[2]/td[3]














    */
}

