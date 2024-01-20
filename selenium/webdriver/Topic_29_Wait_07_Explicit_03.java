package selenium.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;

public class Topic_29_Wait_07_Explicit_03 {
    WebDriver driver;

    WebDriverWait explicitWait; //khai báo chưa khởi tạo

    String projectPath = System.getProperty("user.dir");
    String hcmBird = "ChaoMaoHCM.jfif";
    String hnBird = "ChaoMaoHN.jfif";
    String hueBird = "ChaoMaoHue.jpg";
    String hcmFilePath = projectPath + File.separator + "uploadFiles" + File.separator + hcmBird;
    String hnFilePath = projectPath + File.separator + "uploadFiles" + File.separator + hnBird;
    String hueFilePath = projectPath + File.separator + "uploadFiles" + File.separator + hueBird;
    @BeforeClass //precondition - khởi tạo dữ liệu/data test/page class/variable
    public void beforeClass() {
        driver = new FirefoxDriver();

        //Khởi tạo 1 explicitWait có tổng thời gian là 10s - polling là 0.5s mặc định
        //500 milisecond = 0.5 second
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        //Khởi tạo 1 explicitWait có tổng thời gian là 10s - polling là 0.3s mặc định
        //explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofMillis(300));
    }

    @Test
    public void TC_01_AjaxLoading() {
        driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");

        By selectedDate = By.cssSelector("span#ctl00_ContentPlaceholder1_Label1");

        Assert.assertEquals(driver.findElement(selectedDate).getText(),"No Selected Dates to display.");

        driver.findElement(By.xpath("//a[text()='12']")).click();
        //wait loading icon bien mat
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id*='RadCalendar1']>div.raDiv")));

        Assert.assertEquals(driver.findElement(selectedDate).getText(),"Friday, January 12, 2024");
    }

    @Test
    public void TC_02_uploadFile() {
        driver.get("https://gofile.io/?t=uploadFiles");

        //Wait + Verify Spinner icon bien mat
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.spinner-border"))));

        //Wait + click
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.ajaxLink>button"))).click();

        //Wait + Verify Spinner icon bien mat
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.spinner-border")))));

        //Upload multi file
        driver.findElement(By.cssSelector("input[type='file']")).sendKeys(hcmFilePath + "\n" + hnFilePath + "\n" + hueFilePath);

        //Wait + Verify Spinner icon bien mat
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.spinner-border")))));

        //Wait progress bar bien mat
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.progress")))));

        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.mainUploadSuccessLink a.ajaxLink"))).click();

        // Wait + Verify button Download
        Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[text()='"+ hcmBird +"']/ancestor::div[contains(@class,'text-md-start')]/following-sibling::div//span[text()='Download']")))
                .isDisplayed());
        Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//span[text()='"+ hnBird +"']/ancestor::div[contains(@class,'text-md-start')]/following-sibling::div//span[text()='Download']")))
                .isDisplayed());
        Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//span[text()='"+ hueBird +"']/ancestor::div[contains(@class,'text-md-start')]/following-sibling::div//span[text()='Download']")))
                .isDisplayed());
        // Wait + Verify button Play
        Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//span[text()='"+ hcmBird +"']/ancestor::div[contains(@class,'text-md-start')]/following-sibling::div//span[text()='Play']")))
                .isDisplayed());
        Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//span[text()='"+ hnBird +"']/ancestor::div[contains(@class,'text-md-start')]/following-sibling::div//span[text()='Play']")))
                .isDisplayed());
        Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//span[text()='"+ hueBird +"']/ancestor::div[contains(@class,'text-md-start')]/following-sibling::div//span[text()='Play']")))
                .isDisplayed());
    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
