package webdriver;

import net.bytebuddy.asm.Advice;
import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v85.network.Network;
import org.openqa.selenium.devtools.v85.network.model.Headers;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class Topic_14_Alert {
    WebDriver driver;
    String username = "admin";
    String password = "admin";
    String projectLocation = System.getProperty("user.dir");
    By result = By.xpath("//p[@id='result']");
    @BeforeClass
    public void beforeClass() {
        driver = new EdgeDriver();
        //Chrome options
        /*ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Program Files\\CocCoc\\Browser\\Application\\browser.exe");
        //options.setBinary("C:\\....browser.exe");
        driver = new ChromeDriver(options);*/
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Accept_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(),"I am a JS Alert");
        alert.accept();
        Assert.assertEquals(driver.findElement(this.result).getText(),"You clicked an alert successfully");
    }

    @Test
    public void TC_02_Confirm_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(),"I am a JS Confirm");
        alert.dismiss();
        Assert.assertEquals(driver.findElement(this.result).getText(),"You clicked: Cancel");
    }

    @Test
    public void TC_03_Promt_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(),"I am a JS prompt");
        alert.sendKeys("HungNgo");
        sleepInSeconds(3);
        alert.accept();
        Assert.assertEquals(driver.findElement(this.result).getText(),"You entered: HungNgo");
    }

    @Test
    public void TC_04_Authentication_ByPass_To_URL() {
        //Cách 1
        driver.get("http://" + username + ":" + password + "@" + "the-internet.herokuapp.com/basic_auth");
        //Assert.assertEquals(driver.findElement(By.xpath("//div[@class='example']/p")).getText(),"Congratulations! You must have the proper credentials.");
        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
        //Cách 2: Từ page A thoa tác lên 1 element nó sẽ qua page B (cần phải thao tác với Authen Alert trước)
        driver.get("http://the-internet.herokuapp.com/");
        String  authenLinkUrl = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
        driver.get(getAuthenAlertByUrl(authenLinkUrl, username, password));
        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
    }
    @Test
    public void TC_05_Authentication_Alert_IO() throws IOException {
        Runtime.getRuntime().exec(new String[] { projectLocation + "\\autoIT\\authen_firefox.exe", "admin", "admin" });
        driver.get("http://the-internet.herokuapp.com/basic_auth");
        sleepInSeconds(5);
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='example']/p")).getText(),"Congratulations! You must have the proper credentials.");
    }
    @Test
    public void TC_06_Authentication_Selenium_4x() throws IOException {
        // Get Devtool object
        DevTools devTools = ((HasDevTools) driver).getDevTools();
        // Start new session
        devTools.createSession();
        // Enalble the Network domain of devtools
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        // Encode user/name
        Map<String, Object> headers = new HashMap<String, Object>();
        String basicAuthen = "Basic " + new String(new Base64().encode(String.format("%s:%s", username, password).getBytes()));
        headers.put("Authorization", basicAuthen);

        //Set to header
        devTools.send(Network.setExtraHTTPHeaders(new Headers(headers)));

        driver.get("http://the-internet.herokuapp.com/basic_auth");

        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
    }
    public void sleepInSeconds(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public String getAuthenAlertByUrl(String url, String username, String password){
        String[] authenAray = url.split("//");
        return authenAray[0] + "//" + username + ":" + password + "@" + authenAray[1];
    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
