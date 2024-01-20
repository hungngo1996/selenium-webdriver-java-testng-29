package selenium.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_18_Frame_iFrame {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Url() {
        driver.get("https://skills.kynaenglish.vn/");
        sleepInSeconds(2);
        WebElement iframe = driver.findElement(By.xpath("//div[@class='fanpage ']//iframe"));
        Assert.assertTrue(iframe.isDisplayed());
        driver.switchTo().frame(iframe);
        Assert.assertEquals(driver.findElement(By.xpath("//a[text()='Kyna.vn']/parent::div//following-sibling::div")).getText(),"169K followers");
        driver.switchTo().defaultContent();
        driver.switchTo().frame("cs_chat_iframe");
        driver.findElement(By.cssSelector("div.button_bar")).click();
        sleepInSeconds(2);
        driver.findElement(By.cssSelector("input.input_name")).sendKeys("Hung Ngo");
        driver.findElement(By.cssSelector("input.input_phone")).sendKeys("098987526");
        new Select(driver.findElement(By.cssSelector("select#serviceSelect"))).selectByVisibleText("TƯ VẤN TUYỂN SINH");
        driver.findElement(By.cssSelector("textarea[name='message']")).sendKeys("Java");
        driver.switchTo().defaultContent();
        driver.findElement(By.xpath("//input[@id='live-search-bar']")).sendKeys("Excel");
        sleepInSeconds(2);
        driver.findElement(By.xpath("//button[@class='search-button']")).click();
        sleepInSeconds(3);
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='content']//h4[contains(text(),'Excel')]")).isDisplayed());
    }
    @Test
    public void TC_02_Url() {
        driver.get("https://www.formsite.com/templates/education/campus-safety-survey/");

        driver.findElement(By.xpath("//div[@id='imageTemplateContainer']")).click();
        sleepInSeconds(5);
        WebElement iframe = driver.findElement(By.xpath("//div[@id='formTemplateContainer']/iframe"));
        driver.switchTo().frame(iframe);
        new Select(driver.findElement(By.xpath("//select[@id='RESULT_RadioButton-2']"))).selectByVisibleText("Senior");
        new Select(driver.findElement(By.xpath("//select[@id='RESULT_RadioButton-3']"))).selectByVisibleText("East Dorm");
        driver.findElement(By.xpath("//label[text()='Male']")).click();
        driver.switchTo().defaultContent();
        driver.findElement(By.cssSelector("nav.header--desktop-floater a[title='Log in']")).click();
        sleepInSeconds(3);
        driver.findElement(By.xpath("//button[@id='login']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='message-error']")).getText(),"Username and password are both required.");
    }
    @Test
    public void TC_03_Url() {
        driver.get("https://netbanking.hdfcbank.com/netbanking/");
        driver.switchTo().frame("login_page");
        driver.findElement(By.cssSelector("div.loginData>input")).sendKeys("85597578");
        driver.findElement(By.cssSelector("a.login-btn")).click();
        sleepInSeconds(3);
        driver.findElement(By.cssSelector("input#keyboard")).sendKeys("12324763");
        sleepInSeconds(2);
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
