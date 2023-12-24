package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Topic_23_Wait_01_Element_Status {
    WebDriver driver;
    WebDriverWait explicitWait;
    By reconfirmEmailTxtbox = By.cssSelector("input[name='reg_email_confirmation__']");
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.facebook.com/");
    }

    @Test
    public void TC_01_Visible() {
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        sleepInSeconds(3);

        driver.findElement(By.cssSelector("input[name='reg_email__']")).sendKeys("hung@gmail.com");
        sleepInSeconds(3);

        // Tai step confirm textbox dang hien thi visible
        explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(reconfirmEmailTxtbox));

        // Kiem tra 1 element dang hien thi
        Assert.assertTrue(driver.findElement(reconfirmEmailTxtbox).isDisplayed());

    }

    @Test
    public void TC_02_Invisible_InDom() {
        // DK2
        driver.findElement(By.cssSelector("input[name='reg_email__']")).clear();
        sleepInSeconds(3);

        // Tai step confirm textbox dang hien thi invisible
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(reconfirmEmailTxtbox));
        // Kiem tra 1 element dang ko hien thi
        Assert.assertFalse(driver.findElement(reconfirmEmailTxtbox).isDisplayed());
    }
    @Test
    public void TC_02_Invisible_Not_InDom() {
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        sleepInSeconds(2);
        // Dong popup
        driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
        sleepInSeconds(2);

        // DK3
        // Tai step confirm textbox dang invisible
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(reconfirmEmailTxtbox));
        // Kiem tra 1 element dang ko hien thi
        //Assert.assertFalse(driver.findElement(reconfirmEmailTxtbox).isDisplayed());
    }

    @Test
    public void TC_03_Presence() {
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        sleepInSeconds(3);

        driver.findElement(By.cssSelector("input[name='reg_email__']")).sendKeys("hung@gmail.com");
        sleepInSeconds(3);
        // DK1
        // Tai step confirm textbox dang precense
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(reconfirmEmailTxtbox));

        driver.findElement(By.cssSelector("input[name='reg_email__']")).clear();
        sleepInSeconds(3);
        // DK2
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(reconfirmEmailTxtbox));

    }

    @Test
    public void TC_04_Staleness() {
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        sleepInSeconds(3);
        // Tai thoi diem nay element co xuat hien va minh se findElement
        // Attached to the DOM
        WebElement reconfirmEmail =  driver.findElement(reconfirmEmailTxtbox);

        //Click vao icon de dong popup
        driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
        sleepInSeconds(2);

        //DK3
        //Wait until an element is no longer attached to the DOM
        explicitWait.until(ExpectedConditions.stalenessOf(reconfirmEmail));


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
