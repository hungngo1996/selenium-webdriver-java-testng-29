package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_09_TextBox_TextArea {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void Login_01_Empty_Email_And_Password() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class = 'footer']//a[@title='My Account']")).click();
        sleepInSeconds(1);
        driver.findElement(By.xpath("//button[@id='send2']")).click();
//        driver.findElement(By.xpath("//input[@id='email']")).sendKeys();
//        driver.findElement(By.xpath("//input[@id='pass']")).sendKeys();
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText(),"This is a required field.");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText(),"This is a required field.");
    }

    @Test
    public void Login_02_Invalid_Email() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class = 'footer']//a[@title='My Account']")).click();
        sleepInSeconds(1);

        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("12323@223.3434");
        driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("1232323");
        driver.findElement(By.xpath("//button[@id='send2']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-validate-email-email']"))
                .getText(),"Please enter a valid email address. For example johndoe@domain.com.");
    }
    @Test
    public void Login_03_Invalid_Password() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class = 'footer']//a[@title='My Account']")).click();
        //sleepInSeconds(1);

        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("hung@gmail.com");
        driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123");
        driver.findElement(By.xpath("//button[@id='send2']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']"))
                .getText(),"Please enter 6 or more characters without leading or trailing spaces.");
    }
    @Test
    public void Login_04_Incorrect_Password_or_Email() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class = 'footer']//a[@title='My Account']")).click();
        sleepInSeconds(1);

        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");
        driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123123123");
        driver.findElement(By.xpath("//button[@id='send2']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//span"))
                .getText(),"Invalid login or password.");
        driver.findElement(By.xpath("//input[@id='email']")).clear();
        driver.findElement(By.xpath("//input[@id='pass']")).clear();
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automationfc@gmail.com");
        driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123123123");
        driver.findElement(By.xpath("//button[@id='send2']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//span"))
                .getText(),"Invalid login or password.");

    }


    @Test
    public void Login_05_Success() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class = 'footer']//a[@title='My Account']")).click();
        sleepInSeconds(1);

        //1- Đăng kí trước (manual) và dùng để login
        // Khi hệ thống reset dữ liệu là phải đăng kí lại
        // Qua môi trường mới (Dev/Release/Prod...)

        //2- Sẽ dùng tính năng Register trước - email cố định ko thay đổi
        // Chức năng Register cũng automation
        // Email khi dùng đăng kí lại fix cứng ( chỉ chạy được 1 lần) - Hardcode

        //3- Sẽ dùng tính năng Register trước - email ko cố định (random)
        // Chạy luôn luôn cho tất cả case

        // Đăng kí 1 tài khoản trước
        String firstName = "Automation", lastName = "FC", passWord = "123456", emailAddress = getEmailAddress();
        String fullName = firstName + " " + lastName;
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        sleepInSeconds(2);
        driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys(firstName);
        driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys(lastName);
        driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys(emailAddress);
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys(passWord);
        driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys(passWord);
        driver.findElement(By.xpath("//button[@title='Register']")).click();
        sleepInSeconds(2);

        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span"))
                .getText(),"Thank you for registering with Main Website Store.");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='welcome-msg']//strong")).getText(),"Hello, " + fullName + "!");
        String contactInfo = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
               Assert.assertTrue(contactInfo.contains(fullName));
               Assert.assertTrue(contactInfo.contains(emailAddress));
        // Login
        driver.findElement(By.xpath("//a[@class='skip-link skip-account']")).click();
        sleepInSeconds(2);
        driver.findElement(By.xpath("//a[@title='Log Out']")).click();
        sleepInSeconds(5);
        driver.findElement(By.xpath("//div[@class = 'footer']//a[@title='My Account']")).click();
        sleepInSeconds(1);
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys(emailAddress);
        driver.findElement(By.xpath("//input[@id='pass']")).sendKeys(passWord);
        driver.findElement(By.xpath("//button[@id='send2']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='welcome-msg']//strong")).getText(),"Hello, " + fullName + "!");
        contactInfo = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
        Assert.assertTrue(contactInfo.contains(fullName));
        Assert.assertTrue(contactInfo.contains(emailAddress));

        // Verify account
        driver.findElement(By.xpath("//a[text()='Account Information']")).click();
        sleepInSeconds(2);
        driver.findElement(By.xpath("//input[@id='firstname']")).getAttribute("value");
        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='firstname']")).getAttribute("value"),firstName);
        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='lastname']")).getAttribute("value"),lastName);
        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='email']")).getAttribute("value"),emailAddress);

    }

    public void sleepInSeconds(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public String getEmailAddress(){
        Random rand = new Random();
        return "automation" + rand.nextInt(99999) + "@gmail.net";
    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
