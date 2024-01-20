package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_22_Upload_files {
    WebDriver driver;

    String projectPath = System.getProperty("user.dir");
    String hcmBird = "ChaoMaoHCM.jfif";
    String hnBird = "ChaoMaoHN.jfif";
    String hueBird = "ChaoMaoHue.jpg";

    //String character = Platform.getCurrent().is(Platform.WINDOWS) ? "\\" : "/";
    String hcmFilePath = projectPath + File.separator + "uploadFiles" + File.separator + hcmBird;
    String hnFilePath = projectPath + File.separator + "uploadFiles" + File.separator + hnBird;
    String hueFilePath = projectPath + File.separator + "uploadFiles" + File.separator + hueBird;
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Single_File() {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");
        driver.findElement(By.xpath("//input[@name='files[]']")).sendKeys(hcmFilePath);
        sleepInSeconds(2);
        driver.findElement(By.xpath("//input[@name='files[]']")).sendKeys(hnFilePath);
        sleepInSeconds(2);
        driver.findElement(By.xpath("//input[@name='files[]']")).sendKeys(hueFilePath);
        sleepInSeconds(2);

        List<WebElement> listIMG = driver.findElements(By.xpath("//tr[@class=\"template-upload fade image in\"]//button[@class=\"btn btn-primary start\"]"));
        for (WebElement items : listIMG ){
            items.click();
            sleepInSeconds(2);
        }
        //verify
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class=\"name\"]//a[@title=\"ChaoMaoHCM.jfif\"]")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class=\"name\"]//a[@title=\"ChaoMaoHN.jfif\"]")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class=\"name\"]//a[@title=\"ChaoMaoHue.jpg\"]")).isDisplayed());
    }

    @Test
    public void TC_02_Multiple_File() {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");

        driver.findElement(By.xpath("//input[@name='files[]']")).sendKeys(hcmFilePath + "\n" + hnFilePath + "\n" + hueFilePath);
        sleepInSeconds(2);

        List<WebElement> listIMG = driver.findElements(By.xpath("//tr[@class=\"template-upload fade image in\"]//button[@class=\"btn btn-primary start\"]"));
        for (WebElement items : listIMG ){
            items.click();
            //sleepInSeconds(2);
        }
        //verify
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class=\"name\"]//a[@title=\"ChaoMaoHCM.jfif\"]")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class=\"name\"]//a[@title=\"ChaoMaoHN.jfif\"]")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class=\"name\"]//a[@title=\"ChaoMaoHue.jpg\"]")).isDisplayed());
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
