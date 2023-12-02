package Checkout;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
public class CheckoutCus {
    WebDriver driver;
    WebDriverWait expliciWait;
    String emailAddress = this.getEmailAddress();
    String Phone = this.getPhoneNumber();
    Actions action;
    String Address = this.getAddress();

    @BeforeClass
    public void beforeClass() {
        this.driver = new FirefoxDriver();
        this.driver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
        this.driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Url() {
        this.action = new Actions(this.driver);
        this.driver.get("https://dev-shop-poc.drberg.com/");
        this.sleepInSeconds(3);
        this.driver.findElement(By.xpath("//button[@aria-label='profile']")).click();
        this.driver.findElement(By.xpath("//input[@name='popup-email']")).sendKeys("hung.ngo@mgisolutions.com");
        this.driver.findElement(By.xpath("//button[text()=' CONTINUE WITH EMAIL ']")).click();
        this.sleepInSeconds(3);
        this.action.click(this.driver.findElement(By.xpath("//input[@class='input-password']"))).perform();
        this.sleepInSeconds(1);
        this.driver.findElement(By.xpath("//input[@class='input-password']")).sendKeys("Admin$123");
        this.sleepInSeconds(3);
        this.driver.findElement(By.xpath("//button[text()='SIGN IN']")).click();
        this.sleepInSeconds(10);
        By addToCart = By.xpath("//a[text()='Chewable Vitamin C Complex']/parent::div/parent::div/following-sibling::div//button");
        JavascriptExecutor jsExcutor = (JavascriptExecutor)this.driver;
        jsExcutor.executeScript("arguments[0].click();", driver.findElement(addToCart));
        this.sleepInSeconds(5);
        this.driver.findElement(By.xpath("//button[@aria-label='cart']")).click();
        this.sleepInSeconds(3);
        this.driver.findElement(By.xpath("//a[text() = 'View cart']")).click();
        this.sleepInSeconds(5);
        this.driver.findElement(By.xpath("//span[contains(text(),'Checkout with credit card')]")).click();
        this.sleepInSeconds(15);
        this.driver.findElement(By.xpath("//span[text()='USPS Ground Advantage (2-5 days) TN']")).click();
        this.driver.switchTo().frame("bluesnap-hosted-iframe-ccn");
        this.driver.findElement(By.xpath("//input[@id='ccn']")).sendKeys("4111111111111111");
        this.driver.switchTo().defaultContent();
        this.driver.findElement(By.xpath("//input[@id='cardholder-name']")).sendKeys("MGI");
        this.driver.switchTo().frame("bluesnap-hosted-iframe-exp");
        this.driver.findElement(By.xpath("//input[@id='exp']")).sendKeys("06/26");
        this.driver.switchTo().defaultContent();
        this.driver.switchTo().frame("bluesnap-hosted-iframe-cvv");
        this.driver.findElement(By.xpath("//input[@id='cvv']")).sendKeys("315");
        this.driver.switchTo().defaultContent();
        this.driver.findElement(By.xpath("//label[text()='Save card to my wallet for future purchases']//preceding-sibling::div//input")).click();
        this.sleepInSeconds(3);
        By Checkoutbutton = By.xpath("//div[@class='payment_container flex flex-col']//following-sibling::button");
        jsExcutor.executeScript("arguments[0].click();", driver.findElement(Checkoutbutton));
        this.sleepInSeconds(20);
    }

    @Test
    public void TC_02_Url() {
    }

    public void sleepInSeconds(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000L);
        } catch (InterruptedException var4) {
            throw new RuntimeException(var4);
        }
    }

    public String getEmailAddress() {
        Random rand = new Random();
        return "automation" + rand.nextInt(99999) + "@gmail.net";
    }

    public String getPhoneNumber() {
        Random rand = new Random();
        int var10000 = rand.nextInt(999);
        return "" + var10000 + rand.nextInt(999) + rand.nextInt(9999);
    }

    public String getAddress() {
        Random rand = new Random();
        return rand.nextInt(99) + " Denali Highway";
    }


    @AfterClass
    public void afterClass() {
    }
}
