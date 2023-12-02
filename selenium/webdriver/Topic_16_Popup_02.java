package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_16_Popup_02 {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        //driver.manage().window().setSize(new Dimension(1279,980));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Random_Not_In_Dom() {
        driver.get("https://www.javacodegeeks.com/");
        sleepInSeconds(15);
        By newletterpopup = By.xpath("//div[@class = 'lepopup-popup-container']/div[not(starts-with(@style, 'display:none'))]");

        if (driver.findElements(newletterpopup).size() > 0 && driver.findElements(newletterpopup).get(0).isDisplayed())
        {
            driver.findElement(By.xpath("//div[@class = 'lepopup-popup-container']/div[not(starts-with(@style, 'display:none'))]//a[text()='×']")).click();
            sleepInSeconds(3);
        }
        driver.findElement(By.xpath("//input[@id='search-input']")).sendKeys("Agile Testing Explained");
        sleepInSeconds(2);
        driver.findElement(By.xpath("//button[@id='search-submit']/span[@class='tie-icon-search tie-search-icon']")).click();
        sleepInSeconds(3);
        Assert.assertEquals(driver.findElement(By.xpath("//a[text()='Agile Testing Explained']")).getText(),"Agile Testing Explained");
    }
    @Test
    public void TC_02_Random_In_Dom() {
        driver.get("https://vnk.edu.vn/");
        sleepInSeconds(30);
        By marketingpopup = By.xpath("//div[@class='tve_p_lb_inner']");
        if (driver.findElement(marketingpopup).isDisplayed())
        {
            driver.findElement(By.xpath("//div[contains(@class,'tve_ea_thrive_leads_form_close')]")).click();
            sleepInSeconds(3);
        }
        driver.findElement(By.xpath("//button[@class='btn btn-danger']")).click();
        sleepInSeconds(3);
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='title-content']/h1")).getText(),"Lịch Khai Giảng");
    }
    @Test
    public void TC_02_Override_find_element_Random_In_Dom() {
        driver.get("https://vnk.edu.vn/");
        sleepInSeconds(40);
        findElement(By.xpath("//button[@class='btn btn-danger']")).click();
        sleepInSeconds(3);
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='title-content']/h1")).getText(),"Lịch Khai Giảng");
    }
    public WebElement findElement(By locator){
        //Nếu pop-up xuất hiện thì sẽ close nó đi
        By popup= By.xpath("//div[@class='tve_p_lb_inner']");
        if (driver.findElements(popup).size() > 0 && driver.findElements(popup).get(0).isDisplayed()){
            driver.findElement(By.xpath("//div[contains(@class,'tve_ea_thrive_leads_form_close')]")).click();
            sleepInSeconds(3);
        }
        return driver.findElement(locator);
    }
    @Test
    public void TC_03_Fixed_Popup_Not_InDom() {
        driver.get("https://dehieu.vn/");
        sleepInSeconds(10);
        By marketingpopup = By.xpath("//div[@class='popup-content']");
        if (driver.findElements(marketingpopup).size() > 0 && driver.findElements(marketingpopup).get(0).isDisplayed())
        {
            int Width = driver.manage().window().getSize().getWidth();
            if (Width < 1280){
                System.out.println("Width: " + Width);
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(By.xpath("//button[@id='close-popup']")));
            } else {
                driver.findElement(By.xpath("//button[@id='close-popup']")).click();
            }
            sleepInSeconds(3);
        }
    }
    @Test
    public void TC_04_Fixed_Popup_Not_InDom_02  () {
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
