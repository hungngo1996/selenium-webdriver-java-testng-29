package selenium.testng;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Topic_04_alwayRun {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        System.out.println("run before class");
        Assert.assertTrue(false);
        // Nếu bị fail ở before class thì các testcases/after sẽ bị skip
    }

    @Test
    public void TC_01() {
        System.out.println("Run test cases 01");
    }

    @Test
    public void TC_02() {
        System.out.println("Run test cases 02");
    }

    @Test
    public void TC_03() {
        System.out.println("Run test cases 03");
    }
    @AfterClass(alwaysRun = true)
    public void afterClass() {
        driver.quit();
        System.out.println("Run after class");
    }
}