package selenium.testng;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

//@Listeners(selenium.listeners.ExtentReport.class)
public class Topic_13_Dependencies {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_CreateNewUser() {

    }

    @Test(dependsOnMethods = "TC_01_CreateNewUser")
    public void TC_02_ViewAndSearchUser() {

    }

    @Test(dependsOnMethods = "TC_01_CreateNewUser")
    public void TC_03_UpdateExistingUser() {
        Assert.assertTrue(false);
    }

    @Test(dependsOnMethods = "TC_03_UpdateExistingUser")
    public void TC_04_MoveExistingUserToOtherRole() {

    }

    @Test(dependsOnMethods = "TC_04_MoveExistingUserToOtherRole")
    public void TC_05_DeleteExistingUserToOtherRole() {

    }
    public void sleepInSeconds(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}