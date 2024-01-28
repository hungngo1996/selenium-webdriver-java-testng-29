package selenium.testng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.Random;

public class Topic_10_Invocation {

    WebDriver driver;
    @BeforeClass
    public void beforeClass () {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test(invocationCount = 3)
    public void Register() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class = 'footer']//a[@title='My Account']")).click();

        // Đăng kí 1 tài khoản trước
        String firstName = "Automation", lastName = "FC", passWord = "123456", emailAddress = getEmailAddress();
        String fullName = firstName + " " + lastName;
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys(firstName);
        driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys(lastName);
        driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys(emailAddress);
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys(passWord);
        driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys(passWord);
        driver.findElement(By.xpath("//button[@title='Register']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span"))
                .getText(),"Thank you for registering with Main Website Store.");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='welcome-msg']//strong")).getText(),"Hello, " + fullName + "!");
        String contactInfo = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
        Assert.assertTrue(contactInfo.contains(fullName));
        Assert.assertTrue(contactInfo.contains(emailAddress));
        // Login
        driver.findElement(By.xpath("//a[@class='skip-link skip-account']")).click();
        driver.findElement(By.xpath("//a[@title='Log Out']")).click();
        driver.findElement(By.xpath("//div[@class = 'footer']//a[@title='My Account']")).click();
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys(emailAddress);
        driver.findElement(By.xpath("//input[@id='pass']")).sendKeys(passWord);
        driver.findElement(By.xpath("//button[@id='send2']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='welcome-msg']//strong")).getText(),"Hello, " + fullName + "!");
        contactInfo = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
        Assert.assertTrue(contactInfo.contains(fullName));
        Assert.assertTrue(contactInfo.contains(emailAddress));

        // Verify account
        driver.findElement(By.xpath("//a[text()='Account Information']")).click();
        driver.findElement(By.xpath("//input[@id='firstname']")).getAttribute("value");
        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='firstname']")).getAttribute("value"),firstName);
        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='lastname']")).getAttribute("value"),lastName);
        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='email']")).getAttribute("value"),emailAddress);

        driver.findElement(By.xpath("//a[@class='skip-link skip-account']")).click();
        driver.findElement(By.xpath("//a[@title='Log Out']")).click();

        System.out.println("Email Address/Password " + emailAddress + " - " + passWord);

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
    public String getEmailAddress(){
        Random rand = new Random();
        return "automation" + rand.nextInt(99999) + "@gmail.net";
    }
}
