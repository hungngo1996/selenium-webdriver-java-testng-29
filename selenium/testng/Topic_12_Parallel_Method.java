package selenium.testng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.Random;

public class Topic_12_Parallel_Method {

    WebDriver driver;
    @BeforeMethod
    public void beforeClass () {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void TC_01() {
        System.out.println("Run TC 01");
    }

    @Test
    public void TC_02() {
        System.out.println("Run TC 02");
    }

    @Test
    public void TC_03() {
        System.out.println("Run TC 03");
    }

    @AfterMethod
    public void afterClass() {
        driver.quit();
    }
    public String getEmailAddress(){
        Random rand = new Random();
        return "automation" + rand.nextInt(99999) + "@gmail.net";
    }
}
