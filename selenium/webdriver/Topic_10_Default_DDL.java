package selenium.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_10_Default_DDL {
    WebDriver driver;
    String firstName = "Hung";
    String lastName = "Ngo";
    String email = getEmailAddress();
    String passWord = "123456789";
    String day="21";
    String month="November";
    String year="1996";
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/");
    }

    @Test
    public void TC_01_Register() {
        driver.findElement(By.xpath("//a[@class='ico-register']")).click();
        //sleepInSeconds(2);
        driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys(firstName);
        driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys(lastName);

        Select day = new Select(driver.findElement(By.name("DateOfBirthDay")));
        day.selectByVisibleText(this.day);
        Select month = new Select(driver.findElement(By.name("DateOfBirthMonth")));
        month.selectByVisibleText(this.month);
        Select year = new Select(driver.findElement(By.name("DateOfBirthYear")));
        year.selectByVisibleText(this.year);

        Assert.assertFalse(day.isMultiple());
        Assert.assertFalse(month.isMultiple());
        Assert.assertFalse(year.isMultiple());
        Assert.assertEquals(day.getOptions().size(),32);
        Assert.assertEquals(month.getOptions().size(),13);
        Assert.assertEquals(year.getOptions().size(),112);

        driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@id='Password']")).sendKeys(passWord);
        driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys(passWord);

        driver.findElement(By.xpath("//button[@id='register-button']")).click();
        sleepInSeconds(2);
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result']")).getText(),"Your registration completed");
    }

    @Test
    public void TC_02_Login() {
        driver.get("https://demo.nopcommerce.com/");

        // login
        driver.findElement(By.cssSelector("a.ico-login")).click();
        //sleepInSeconds(1);
        driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@id='Password']")).sendKeys(passWord);
        driver.findElement(By.cssSelector("button.login-button")).click();
        //sleepInSeconds(2);
        //Verify
        driver.findElement(By.cssSelector("a.ico-account")).click();

        //sleepInSeconds(2);
        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='FirstName']")).getAttribute("value"),firstName);
        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='LastName']")).getAttribute("value"),lastName);
        Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthDay"))).getFirstSelectedOption().getText(),day);
        Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthMonth"))).getFirstSelectedOption().getText(),month);
        Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthYear"))).getFirstSelectedOption().getText(),year);
        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='Email']")).getAttribute("value"),email);
    }
    public String getEmailAddress(){
        Random rand = new Random();
        return "kenvin" + rand.nextInt(99999) + "@gmail.net";
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
