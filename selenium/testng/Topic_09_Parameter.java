package selenium.testng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Topic_09_Parameter {

    WebDriver driver;
    @Parameters({"browser","version"})
    @BeforeClass
    public void beforeClass (String browserName, String browserVersion) {
        driver = getBrowserDriver(browserName);
        System.out.println("browserName = " + browserName + ", browserVersion = " + browserVersion);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Parameters("environments")
    @Test
    public void TC_01_LoginToSystem(@Optional("live") String environmentName)  {
        driver.get(getEnvironment(environmentName) + "/index.php/customer/account/login/");
        driver.findElement(By.xpath("//*[@id='email']")).sendKeys("selenium_11_01@gmail.com");
        driver.findElement(By.xpath("//*[@id='pass']")).sendKeys("111111");
        driver.findElement(By.xpath("//*[@id='send2']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//p")).getText().contains("selenium_11_01@gmail.com"));

        // ....

//        driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
//        driver.findElement(By.xpath("//a[text()='Log Out']")).click();
    }

    public WebDriver getBrowserDriver(String browserName){
        WebDriver driver;
        if (browserName.equals("firefox")) {
            driver = new FirefoxDriver();
        } else if (browserName.equals("chrome"))
        {
            driver = new ChromeDriver();
        } else if (browserName.equals("edge")){
            driver = new EdgeDriver();
        } else
        {
            throw  new RuntimeException("Browser name is not valid.");
        }
        return driver;
    }
    public String getEnvironment(String environmentName){
        String Url;
        if (environmentName.equals("dev")) {
            Url = "http://dev.techpanda.org";
        } else if (environmentName.equals("release"))
        {
            Url = "http://release.techpanda.org";
        } else if (environmentName.equals("live")){
            Url = "http://live.techpanda.org";
        } else
        {
            throw  new RuntimeException("Environment Name is not valid.");
        }
        return Url;
    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
