package selenium.webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.function.Function;

public class Topic_30_Wait_08_Fluent {
    WebDriver driver;

    WebDriverWait explicitWait; //khai báo chưa khởi tạo

    FluentWait<WebDriver> fluentWebdriver;

    FluentWait<WebElement> fluentElement;

    FluentWait<String> fluentString;
    @BeforeClass //precondition - khởi tạo dữ liệu/data test/page class/variable
    public void beforeClass() {
        driver = new FirefoxDriver();
        fluentWebdriver = new FluentWait<WebDriver>(driver);
    }

    @Test
    public void TC_01_Demo() {
        fluentWebdriver = new FluentWait<WebDriver>(driver);

        fluentElement = new FluentWait<WebElement>(driver.findElement(By.cssSelector("")));

        fluentString = new FluentWait<String>("");

        //Setting Tong time
        fluentWebdriver.withTimeout(Duration.ofSeconds(10));
        //Polling time
        fluentWebdriver.pollingEvery(Duration.ofMillis(300));
        // Ignore NosuchElement exceptions
        fluentWebdriver.ignoring(NoSuchElementException.class);
        // Ignore TimeoutException
        fluentWebdriver.ignoring(TimeoutException.class);
        // Condition
        fluentWebdriver.until(new Function<WebDriver, Object>() {
            @Override
            public Object apply(WebDriver webDriver) {
                return null;
            }
        });

        fluentWebdriver.withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(300))
                .ignoring(NoSuchElementException.class)
                .ignoring(TimeoutException.class)
                .until(new Function<WebDriver, Object>() {
                    @Override
                    public Boolean apply(WebDriver webDriver) {
                        return webDriver.findElement(By.cssSelector("")).isDisplayed();
                    }
                });
    }

    @Test
    public void TC_02_() {
        driver.get("https://automationfc.github.io/dynamic-loading/");

        waitAndFindElement(By.cssSelector("div#start>button")).click();

        // Chờ cho HelloWorld Text hiển thị trong vòng 10s
       /* fluentWebdriver.withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(300))
                .ignoring(NoSuchElementException.class)
                .ignoring(TimeoutException.class)
                .until(new Function<WebDriver, Boolean>() {
                    @Override
                    public Boolean apply(WebDriver webDriver) {
                        return webDriver.findElement(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")).isDisplayed();
                    }
                });*/
        waitAndFindElement(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")).isDisplayed();
    }
    @Test
    public void TC_03_() {
        driver.get("https://automationfc.github.io/fluent-wait/");

        WebElement countDownTime = driver.findElement(By.cssSelector("div#javascript_countdown_time"));

        fluentElement = new FluentWait<WebElement>(countDownTime);

        fluentElement.withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class);
        fluentElement.until(new Function<WebElement, Boolean>() {
            @Override
            public Boolean apply(WebElement webElement) {
                return countDownTime.getText().endsWith("00");
            }
        });
    }
    public WebElement waitAndFindElement(By locator){
        FluentWait<WebDriver> fluentDriver = new FluentWait<WebDriver>(driver);
        fluentDriver.withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(300))
                .ignoring(NoSuchElementException.class);

        return fluentDriver.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return webDriver.findElement(locator);
            }
        });
    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
