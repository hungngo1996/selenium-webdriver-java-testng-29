package selenium.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_04_Relative_Locator {
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

    }

    @Test
    public void TC_01_Relative() {
        driver.get("https://demo.nopcommerce.com/login?returnUrl=%2Fregister");
        //Login button
        By loginbuttonBy = By.cssSelector("button.login-button");
        //Remember Me checkbox
        By rememberMeCheckboxBy = By.id("RememberMe");
        //Forgot password link
        WebElement forgotPasswordElement = driver.findElement(By.cssSelector("span.forgot-password"));
        // Password textbox
        By pwtextBox = By.cssSelector("input.password");
        WebElement rememberMeTextElement = driver.findElement(RelativeLocator.with(By.tagName("label"))
                .above(loginbuttonBy)
                .toRightOf(rememberMeCheckboxBy)
                .toLeftOf(forgotPasswordElement)
                .below(pwtextBox)
                .near(forgotPasswordElement));
        System.out.println(rememberMeTextElement.getText());
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
