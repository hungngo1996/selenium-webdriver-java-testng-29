package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_08_Exercise {
    WebDriver driver;
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }
    //Browser
    @Test
    public void TC_01_Url() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class = 'footer']//a[@title='My Account']")).click();
        sleepInSeconds(1);
        Assert.assertEquals(driver.getCurrentUrl(), ("http://live.techpanda.org/index.php/customer/account/login/"));
        driver.findElement(By.xpath("//a[@title = 'Create an Account']")).click();
        sleepInSeconds(1);
        Assert.assertEquals(driver.getCurrentUrl(), ("http://live.techpanda.org/index.php/customer/account/create/"));
    }

    @Test
    public void TC_02_Title() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class = 'footer']//a[@title='My Account']")).click();
        sleepInSeconds(1);
        Assert.assertEquals(driver.getTitle(), ("Customer Login"));
        driver.findElement(By.xpath("//a[@title = 'Create an Account']")).click();
        sleepInSeconds(1);
        Assert.assertEquals(driver.getTitle(), ("Create New Customer Account"));

    }

    @Test
    public void TC_03_Navigation() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class = 'footer']//a[@title='My Account']")).click();
        sleepInSeconds(1);
        driver.findElement(By.xpath("//a[@title = 'Create an Account']")).click();
        driver.navigate().back();
        sleepInSeconds(1);
        Assert.assertEquals(driver.getCurrentUrl(), ("http://live.techpanda.org/index.php/customer/account/login/"));
        driver.navigate().forward();
        sleepInSeconds(1);
        Assert.assertEquals(driver.getTitle(), ("Create New Customer Account"));
    }

    @Test
    public void TC_04_PageSource() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class = 'footer']//a[@title='My Account']")).click();
        sleepInSeconds(1);
        driver.findElement(By.xpath("//div[@class='page-title']/h1"));
        Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));
        driver.findElement(By.xpath("//a[@title = 'Create an Account']")).click();
        sleepInSeconds(1);
        driver.findElement(By.xpath("//div[@class='page-title']/h1"));
        Assert.assertTrue(driver.getPageSource().contains("Create an Account"));
    }
    //Element
    @Test
    public void TC_05_Displayed(){
        driver.get("https://automationfc.github.io/basic-form/index.html");
        if (driver.findElement(By.cssSelector("input#email")).isDisplayed()) {
            driver.findElement(By.cssSelector("input#email")).sendKeys("Automation Testing");
            System.out.println("Email input is displayed");
        }else {
            System.out.println("Email input is not displayed");
        }
        if (driver.findElement(By.cssSelector("input#under_18")).isDisplayed()) {
            driver.findElement(By.cssSelector("input#under_18")).click();
            System.out.println("Under 18 radio button is displayed");
        }else {
            System.out.println("Under 18 radio button is not displayed");
        }
        if (driver.findElement(By.cssSelector("textarea#edu")).isDisplayed()) {
            driver.findElement(By.cssSelector("textarea#edu")).sendKeys("Automation Testing");
            System.out.println("Education TextArea is displayed");
        }else {
            System.out.println("Education TextArea not is displayed");
        }
        if (driver.findElement(By.xpath("//h5[text()='Name: User5']")).isDisplayed()) {
            System.out.println("Name user 5 is displayed");
        }else {
            System.out.println("Name user 5 is not displayed");
        }
    }
    @Test
    public void TC_06_Enabled() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        if (driver.findElement(By.cssSelector("input#email")).isEnabled()) {
            System.out.println("Email input is enabled");
        }else {
            System.out.println("Email input is disable");
        }
        if (driver.findElement(By.cssSelector("input#disable_password")).isEnabled()) {
            System.out.println("Password Textbox is enabled");
        }else {
            System.out.println("Password Textbox is disable");
        }
        if (driver.findElement(By.cssSelector("input#check-disbaled")).isEnabled()) {
            System.out.println("Checkbox is enabled");
        }else {
            System.out.println("Checkbox is disabled");
        }
    }
    @Test
    public void TC_07_Selected() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.cssSelector("input#under_18")).click();
        driver.findElement(By.cssSelector("input#java")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector("input#under_18")).isSelected());
        Assert.assertTrue(driver.findElement(By.cssSelector("input#java")).isSelected());

        driver.findElement(By.cssSelector("input#java")).click();
        sleepInSeconds(2);
        Assert.assertTrue(driver.findElement(By.cssSelector("input#under_18")).isSelected());
        Assert.assertFalse(driver.findElement(By.cssSelector("input#java")).isSelected());
    }
    @Test
    public void TC_08_MailChimp() {
        driver.get("https://login.mailchimp.com/signup/");
        //Empty
        driver.findElement(By.xpath("//input[@id='new_password']")).clear();
        driver.findElement(By.xpath("//button[@id='create-account-enabled']")).click();
        sleepInSeconds(2);
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class = 'lowercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class = 'uppercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class = 'number-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class = 'special-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class = '8-char not-completed']")).isDisplayed());
        // Number
        driver.findElement(By.xpath("//input[@id='new_password']")).sendKeys("12345");
        sleepInSeconds(2);
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class = 'lowercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class = 'uppercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class = 'number-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class = 'special-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class = '8-char not-completed']")).isDisplayed());
        // Lower
        driver.findElement(By.xpath("//input[@id='new_password']")).clear();
        driver.findElement(By.xpath("//input[@id='new_password']")).sendKeys("auto");
        sleepInSeconds(2);
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class = 'lowercase-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class = 'uppercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class = 'number-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class = 'special-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class = '8-char not-completed']")).isDisplayed());
        // Upper
        driver.findElement(By.xpath("//input[@id='new_password']")).clear();
        driver.findElement(By.xpath("//input[@id='new_password']")).sendKeys("AUTO");
        sleepInSeconds(2);
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class = 'lowercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class = 'uppercase-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class = 'number-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class = 'special-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class = '8-char not-completed']")).isDisplayed());
        // Special
        driver.findElement(By.xpath("//input[@id='new_password']")).clear();
        driver.findElement(By.xpath("//input[@id='new_password']")).sendKeys("@#$#%");
        sleepInSeconds(2);
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class = 'lowercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class = 'uppercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class = 'number-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class = 'special-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class = '8-char not-completed']")).isDisplayed());
        //Max length
        driver.findElement(By.xpath("//input[@id='new_password']")).clear();
        driver.findElement(By.xpath("//input[@id='new_password']")).sendKeys("12345678");
        sleepInSeconds(2);
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class = 'lowercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class = 'uppercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class = 'number-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class = 'special-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class = '8-char completed']")).isDisplayed());
        //Valid
        driver.findElement(By.xpath("//input[@id='new_password']")).clear();
        driver.findElement(By.xpath("//input[@id='new_password']")).sendKeys("Auto@123");
        sleepInSeconds(2);
        Assert.assertFalse(driver.findElement(By.xpath("//li[@class = 'lowercase-char completed']")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.xpath("//li[@class = 'uppercase-char completed']")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.xpath("//li[@class = 'number-char completed']")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.xpath("//li[@class = 'special-char completed']")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.xpath("//li[@class = '8-char completed']")).isDisplayed());
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
