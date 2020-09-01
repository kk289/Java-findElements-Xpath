<h2> Extract Table Data and Verify Name Using Selenium WebDriver </h2>

Platform supporting Maven: [IntelliJ IDEA CE](https://www.jetbrains.com/idea/download/download-thanks.html?platform=mac&code=IIC)

1. Create a new Project

2. Choose [Maven](https://en.wikipedia.org/wiki/Apache_Maven#:~:text=Maven%20is%20a%20build%20automation,%2C%20Scala%2C%20and%20other%20languages.&text=Maven%20is%20built%20using%20a,application%20controllable%20through%20standard%20input.)

3. In Project SDK box, make sure you have latest version of java "JDK"

<p align="center">
	<img width="700px" src="https://github.com/kk289/Java-Automation-OpenBrowser/blob/master/OpenBrowser/Image/_1CreateProject.png" align="center"/>
</p>

4. Click Next, and give a name to your project, like "verifyDataProj"

5. Now create a package inside src/test/java/ and name it "verifyDataPack" then create a class inside it, name it "verifyName".


<h4> What is Maven? </h4>

"Maven is a build automation tool used primarily for Java projects. Maven can also be used to build and manage projects written in C#, Ruby, Scala, and other languages.

Maven addresses two aspects of building software: how software is built, and its dependencies. An XML file describes the software project being built, its dependencies on other external modules and components, the build order, directories, and required plug-ins. It comes with pre-defined targets for performing certain well-defined tasks such as compilation of code and its packaging. 

Maven dynamically downloads Java libraries and Maven plug-ins from one or more repositories such as the Maven 2 Central Repository, and stores them in a local cache. Maven projects are configured using a Project Object Model, which is stored in a [pom.xml](https://github.com/kk289/Java-sendEmail-with-Attachment/blob/master/pom.xml) file."

An example of [pom.xml](https://github.com/kk289/Java-sendEmail-with-Attachment/blob/master/pom.xml) file looks like: 

```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>org.example</groupId>
    <artifactId>OpenBrowser</artifactId>
    <version>1.0-SNAPSHOT</version>
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <configuration>
                    <source>8</source>
                    <target>8</target>
                </configuration>
            </plugin>
        </plugins>
    </build>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.source>1.7</maven.compiler.source>
        <maven.compiler.target>1.7</maven.compiler.target>
    </properties>

    <dependencies>

    	<dependency>
      		<groupId>junit</groupId>
      		<artifactId>junit</artifactId>
      		<version>4.11</version>
      		<scope>test</scope>
    	</dependency>

        <!-- https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java-->
    	<dependency>
      		<groupId>org.seleniumhq.selenium</groupId>
      		<artifactId>selenium-java</artifactId>
      		<version>3.141.59</version>
      		<scope>test</scope>
    	</dependency>

    	<!-- https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-api -->
    	<dependency>
      		<groupId>org.seleniumhq.selenium</groupId>
      		<artifactId>selenium-api</artifactId>
     		<version>3.141.59</version>
      		<scope>test</scope>
    	</dependency>

    </dependencies>

</project>
```

9. Make sure to setup your "pom.xml" file like above. Just replace your pom.xml file with this [pom.xml](https://github.com/kk289/Java-sendEmail-with-Attachment/blob/master/pom.xml)

10. Let's look at "mailAttachment" class:

## mailAttachment

```
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
```

Let's run the "mailAttachment" class. We get following result: 

<p align="center">
	<img width="900px" src="Image/attach2.png" align="center"/>
	<br>
	<br>
	<img width="900px" src="Image/attach1.png" align="center"/>
</p>

The program run successfully. Make sure you check the inbox whether receiver got any email or not.

<b>Thank you. Let me know if you have any questions.</b>
