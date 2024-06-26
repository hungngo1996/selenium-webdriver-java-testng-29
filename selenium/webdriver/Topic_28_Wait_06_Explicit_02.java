package selenium.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_28_Wait_06_Explicit_02 {
    WebDriver driver;

    WebDriverWait explicitWait; //khai báo chưa khởi tạo


    @BeforeClass //precondition - khởi tạo dữ liệu/data test/page class/variable
    public void beforeClass() {
        driver = new FirefoxDriver();

        //Khởi tạo 1 explicitWait có tổng thời gian là 10s - polling là 0.5s mặc định
        //500 milisecond = 0.5 second
        //explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //Khởi tạo 1 explicitWait có tổng thời gian là 10s - polling là 0.3s mặc định
        //explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofMillis(300));
    }

    @Test
    public void TC_01_Equal_5_Second() {
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://automationfc.github.io/dynamic-loading/");

        driver.findElement(By.cssSelector("div#start>button")).click();
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),"Hello World!");
    }

    @Test
    public void TC_02_Less_Than_5_Second() {
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(3));

        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.cssSelector("div#start>button")).click();

        //wait cho loading icon bien mat
        //explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));
        //wait cho hello wolrd text hien thi
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),"Hello World!");
    }
    @Test
    public void TC_03_Greater_Than_5_Second() {
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://automationfc.github.io/dynamic-loading/");

        driver.findElement(By.cssSelector("div#start>button")).click();
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),"Hello World!");
    }

    @Test
    public void TC_04_Equal_5_Second() {
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://automationfc.github.io/dynamic-loading/");

        driver.findElement(By.cssSelector("div#start>button")).click();
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),"Hello World!");
    }

    @Test
    public void TC_05_Less_Than_5_Second() {
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(3));

        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.cssSelector("div#start>button")).click();

        //wait cho loading icon bien mat
        //explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));
        //wait cho hello wolrd text hien thi
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),"Hello World!");
    }
    @Test
    public void TC_06_Greater_Than_5_Second() {
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://automationfc.github.io/dynamic-loading/");

        driver.findElement(By.cssSelector("div#start>button")).click();
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),"Hello World!");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
