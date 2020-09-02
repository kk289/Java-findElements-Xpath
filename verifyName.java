package verifyDataPack;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;

public class verifyName {

    WebDriver wd;

    @Before
    // Open ChromeBrowser
    public void openBrowser() {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver"); // chrome browser
        wd = new ChromeDriver();
        wd.get("https://www.w3schools.com/html/html_tables.asp");
    }

    @Test
    public void checkName() {
        // divided Xpath In six parts to pass Row_count and Col_count values.
        String first_part = "//*[@id='footer']/div[5]/div[";
        String second_part = "]/div";
        String third_part = "/a";    // to get each listed name inside Header
        String forth_part = "[";
        String fifth_part = "]";
        String sixth_part = "/h4";  // to get Header name

        // Get number of rows from the selected Header
        // NOTE: To select the header, input the number range from 1 to 4
        // 1 = "Top Tutorial"
        // 2 = "Top References"
        // 3 = "Top Examples"
        // 4 = "Web Certificates"
        String rowPath = first_part + 1 + second_part + third_part;
        List<WebElement> Row_count = wd.findElements(By.xpath(rowPath));
        System.out.println("Number of Rows= " + Row_count.size());

        // Actually we don't need column here, but may needed for other tables having more than 1 column
        // Get number of columns from the selected Header
        // NOTE: To select the header, input the number range from 1 to 4
        // 1 = "Top Tutorial"
        // 2 = "Top References"
        // 3 = "Top Examples"
        // 4 = "Web Certificates"
        String columnPath = first_part + 1 + second_part;
        List<WebElement> Col_count = wd.findElements(By.xpath(columnPath));
        System.out.println("Number of Columns = " + Col_count.size() + "\n");

        // Print out the selected Header name
        // NOTE: To select the header, input the number range from 1 to 4
        // 1 = "Top Tutorial"
        // 2 = "Top References"
        // 3 = "Top Examples"
        // 4 = "Web Certificates"
        String referenceXpath = first_part + 1 + second_part + sixth_part;
        String headerTitle = wd.findElement(By.xpath(referenceXpath)).getText();
        for (String data: headerTitle.split(" ")) {
            System.out.print(String.format("%-5s ",  data));
        }
        System.out.println();

        // Print out dash line under Header name
        for (int i = 0; i < 5*3; i++) {
            System.out.print("-");
        }
        System.out.println();

        // Loop to extract all listed names inside the selected header using xpath
        // NOTE: To select the header, input the number range from 1 to 4
        // 1 = "Top Tutorial"
        // 2 = "Top References"
        // 3 = "Top Examples"
        // 4 = "Web Certificates"
        for (int i = 1; i <= Row_count.size(); i++) {
            String final_xpath = first_part + 1 + second_part + third_part + forth_part + i + fifth_part;
            String Table_data = wd.findElement(By.xpath(final_xpath)).getText();
            System.out.print(String.format("%-10s ", Table_data));
            System.out.println("");
        }

        // Testing
        String headerName = "Top Tutorial";    // change it according to header name
        String tutorialName = "nepal Tutorial";
        boolean isTutorialFound = false;  // change string name according to header name

        // Loop to check if given name is listed inside the selected header
        // NOTE: To select the header, input the number range from 1 to 4
        // 1 = "Top Tutorial"
        // 2 = "Top References"
        // 3 = "Top Examples"
        // 4 = "Web Certificates"
        for (int i = 1; i <= Row_count.size(); i++) {
            String final_xpath = first_part + 1 + second_part + third_part + forth_part + i + fifth_part;
            String Table_data = wd.findElement(By.xpath(final_xpath)).getText();
            if (Table_data.contains(tutorialName)) {
                isTutorialFound = true;
                break;
            }
        }

        // Print out the test result
        if(isTutorialFound){
            System.out.println("\n" + tutorialName + " Detected in " + headerName + " List.\n");
        }else{
            System.out.println("\n" + tutorialName + " Not Detected in " + headerName + " List.\n");
        }
    }

    @After
    // Close the browser
    public void Close() throws InterruptedException {
        wd.quit();
    }
}