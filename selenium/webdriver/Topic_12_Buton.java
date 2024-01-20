package selenium.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_12_Buton {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Egov_Button() {
        driver.get("https://egov.danang.gov.vn/reg");
        Assert.assertFalse(driver.findElement(By.xpath("//input[@class='egov-button']")).isEnabled());
        driver.findElement(By.xpath("//input[@id='chinhSach']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//input[@class='egov-button']")).isEnabled());
        String ButtonBGcolor = driver.findElement(By.xpath("//input[@class='egov-button']")).getCssValue("background-color");
        Assert.assertEquals(Color.fromString(ButtonBGcolor).asHex().toLowerCase(),"#ef5a00");
    }

    @Test
    public void TC_02_Fasa_Button() {
        driver.get("https://www.fahasa.com/customer/account/create");
        driver.findElement(By.xpath("//li[@class ='popup-login-tab-item popup-login-tab-login']")).click();
        Assert.assertFalse(driver.findElement(By.xpath("//button[@class ='fhs-btn-login']")).isEnabled());
        Assert.assertEquals(driver.findElement(By.xpath("//button[@class ='fhs-btn-login']")).getCssValue("background-image"),"linear-gradient(90deg, rgb(224, 224, 224) 0%, rgb(224, 224, 224) 100%)");
        driver.findElement(By.xpath("//input[@id='login_username']")).sendKeys("hung@gmail.com");
        driver.findElement(By.xpath("//input[@id='login_password']")).sendKeys("aaaaaaaaaa");
        Assert.assertTrue(driver.findElement(By.xpath("//button[@class ='fhs-btn-login']")).isEnabled());
        Assert.assertEquals(Color.fromString(driver.findElement(By.xpath("//button[@class ='fhs-btn-login']")).getCssValue("background-color")).asHex().toLowerCase(),"#c92127");
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
