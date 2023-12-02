package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_16_Popup_01 {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Fixed_Popup_InDom_01() {
        driver.get("https://ngoaingu24h.vn/");
        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
        sleepInSeconds(3);
        Assert.assertTrue(driver.findElement(By.xpath("//div[@id='modal-login-v1' and @style]/div")).isDisplayed());
        driver.findElement(By.xpath("//div[@id='modal-login-v1' and @style]/div//input[@id='account-input']")).sendKeys("automationfc");
        driver.findElement(By.xpath("//div[@id='modal-login-v1' and @style]/div//input[@id='password-input']")).sendKeys("automationfc");
        driver.findElement(By.xpath("//div[@id='modal-login-v1' and @style]/div//button[text()='Đăng nhập']")).click();
        sleepInSeconds(2);
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='modal-login-v1' and @style]/div//div[@class='row error-login-panel']")).getText(),"Tài khoản không tồn tại!");
        sleepInSeconds(2);
        driver.findElement(By.xpath("//div[@id='modal-login-v1' and @style]/div//button[@class='close']")).click();
        sleepInSeconds(2);
        Assert.assertFalse(driver.findElement(By.xpath("//div[@id='modal-login-v1' and @style]/div")).isDisplayed());
    }
    @Test
    public void TC_02_Fixed_Popup_InDom_02() {
        driver.get("https://skills.kynaenglish.vn/");
        driver.findElement(By.xpath("//a[@class='login-btn']")).click();
        sleepInSeconds(3);
        Assert.assertTrue(driver.findElement(By.xpath("//div[@id='k-popup-account-login-mb']//div[@class='modal-content']")).isDisplayed());
        driver.findElement(By.xpath("//div[@id='k-popup-account-login-mb']//div[@class='modal-content']//input[@id='user-login']")).sendKeys("automation@gmail.com");
        driver.findElement(By.xpath("//div[@id='k-popup-account-login-mb']//div[@class='modal-content']//input[@id='user-password']")).sendKeys("123456");
        driver.findElement(By.xpath("//button[@id='btn-submit-login']")).click();
        sleepInSeconds(3);
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='k-popup-account-login-mb']//div[@class='modal-content']//div[@id='password-form-login-message']")).getText(),"Sai tên đăng nhập hoặc mật khẩu");
        driver.findElement(By.xpath("//div[@id='k-popup-account-login-mb']//div[@class='modal-content']//button[@class='k-popup-account-close close']")).click();
        Assert.assertFalse(driver.findElement(By.xpath("//div[@id='k-popup-account-login-mb']//div[@class='modal-content']")).isDisplayed());
    }
    @Test
    public void TC_03_Fixed_Popup_Not_InDom_01() {
        driver.get("https://tiki.vn/");
        driver.findElement(By.xpath("//div[@data-view-id='header_header_account_container']")).click();
        sleepInSeconds(3);
        Assert.assertTrue(driver.findElement(By.xpath("//div[contains(@class,'ReactModal__Content')]/div")).isDisplayed());
        driver.findElement(By.xpath("//p[@class='login-with-email']")).click();
        sleepInSeconds(2);
        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
        sleepInSeconds(2);
        Assert.assertEquals(driver.findElement(By.xpath("//span[text()='Email không được để trống']")).getText(),"Email không được để trống");
        Assert.assertEquals(driver.findElement(By.xpath("//span[text()='Mật khẩu không được để trống']")).getText(),"Mật khẩu không được để trống");
        driver.findElement(By.xpath("//img[@class='close-img']")).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Assert.assertEquals(driver.findElements(By.xpath("//div[contains(@class,'ReactModal__Content')]/div")).size(),0);
    }
    @Test
    public void TC_04_Fixed_Popup_Not_InDom_02  () {
        driver.get("https://www.facebook.com/");
        driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
        sleepInSeconds(2);
        Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/parent::div")).isDisplayed());
        driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/parent::div/img")).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Assert.assertEquals(driver.findElements(By.xpath("//div[text()='Sign Up']/parent::div/parent::div")).size(),0);
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
