package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Topic_000_Demo {
    WebDriver driver;
    WebDriverWait explicitWait;
    String projectPath = System.getProperty("user.dir");
    String hcmBird = "ChaoMaoHCM.jfif";
    String hnBird = "ChaoMaoHN.jfif";
    String hueBird = "ChaoMaoHue.jpg";
    String hcmFilePath = projectPath + File.separator + "uploadFiles" + File.separator + hcmBird;
    String hnFilePath = projectPath + File.separator + "uploadFiles" + File.separator + hnBird;
    String hueFilePath = projectPath + File.separator + "uploadFiles" + File.separator + hueBird;
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Url() {
        driver.get("https://gofile.io/?t=uploadFiles");
        driver.findElement(By.cssSelector("a.ajaxLink>button")).click();
        driver.findElement(By.cssSelector("input[type='file']")).sendKeys(hcmFilePath + "\n" + hnFilePath + "\n" + hueFilePath);
        //Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.progress")))));
        driver.findElement(By.cssSelector("div.mainUploadSuccessLink a.ajaxLink")).click();

    }

    @Test
    public void TC_02_Url() {

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
        //driver.quit();
    }
}
