package selenium.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_02_Selenium_Locator {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    @BeforeClass
    public void beforeClass() {
        if (osName.contains("Windows")) {
            System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        } else {
            System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        }

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/register");
    }

    //TestNG: order testcase theo Alphabet (0-9 A-z)

    @Test
    public void TC_01_ID() {
        driver.findElement(By.id("FirstName")).sendKeys("Hung");
        System.out.print(driver.findElement(By.id("FirstName")));
    }

    @Test
    public void TC_02_Class() {
        driver.findElement(By.className("header-menu"));
    }

    @Test
    public void TC_03_Name() {
        driver.findElement(By.name("customerCurrency"));
    }

    @Test
    public void TC_04_TagName() {
        driver.findElement(By.tagName("div"));
    }

    @Test
    public void TC_05_PartialLinkText() {
        driver.findElement(By.partialLinkText("Shopping cart"));
        driver.findElement(By.partialLinkText("vendor account"));
        driver.findElement(By.partialLinkText("for vendor"));
    }

    @Test
    public void TC_06_LinkText() {
        driver.findElement(By.linkText("Sitemap"));
    }

    @Test
    public void TC_07_Css() {
        // Css vs ID
        driver.findElement(By.cssSelector("input[id='FirstName']"));
        driver.findElement(By.cssSelector("input#FirstName"));
        driver.findElement(By.cssSelector("#FirstName"));
        // Css vs Class
        driver.findElement(By.cssSelector("div[class='page-body']"));
        driver.findElement(By.cssSelector("div.page-body"));
        driver.findElement(By.cssSelector(".page-body"));
        // Css vs Name
        driver.findElement(By.cssSelector("input[name='FirstName']"));
        // Css vs TagName
        driver.findElement(By.cssSelector("input"));
        // Css vs Link
        driver.findElement(By.cssSelector("a[href='/blog']"));
        // Css vs partialLinkText
        driver.findElement(By.cssSelector("a[href*='addresses']"));
    }

    @Test
    public void TC_08_XPath() {
        // Xpath vs ID
        driver.findElement(By.xpath("//input[@id='FirstName']"));
        // Xpath vs Class
        driver.findElement(By.xpath("//div[@class='page-body']"));
        // Xpath vs Name
        driver.findElement(By.xpath("//input[@name='FirstName']"));
        // Xpath vs TagName
        driver.findElement(By.xpath("//input"));
        // Xpath vs Link
        driver.findElement(By.xpath("//a[@href='/blog']"));
        driver.findElement(By.xpath("//a[text()='Blog']"));
        // Xpath vs partialLinkText
        driver.findElement(By.xpath("//a[contains(@href, 'addresses')]"));
        driver.findElement(By.xpath("//a[contains(text(), 'Addresses')]"));
    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
