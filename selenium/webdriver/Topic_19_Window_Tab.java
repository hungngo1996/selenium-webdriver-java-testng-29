package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Topic_19_Window_Tab {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_BasicForm() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        String parentId = driver.getWindowHandle();
        driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
        sleepInSeconds(2);
        switchToWindowsByTitle("Google");
        Assert.assertEquals(driver.getTitle(),"Google");
        driver.findElement(By.xpath("//textarea[@title='Tìm kiếm']")).sendKeys("Automation@gmail.com");
        sleepInSeconds(2);
        switchToWindowsByTitle("Selenium WebDriver");
        driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
        sleepInSeconds(2);
        switchToWindowsByTitle("Facebook – log in or sign up");
        Assert.assertEquals(driver.getTitle(),"Facebook – log in or sign up");
        driver.findElement(By.xpath("//input[@id=\"email\"]")).sendKeys("Automation@gmail.com");
        sleepInSeconds(2);
        switchToWindowsByTitle("Selenium WebDriver");
        driver.findElement(By.xpath("//a[text()='TIKI']")).click();
        sleepInSeconds(2);
        switchToWindowsByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
        Assert.assertEquals(driver.getTitle(),"Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
        driver.findElement(By.xpath("//img[@class='icon-search']/following-sibling::input")).sendKeys("Son");
        sleepInSeconds(2);
        closeAllWindowsWithoutParent(parentId);
        Assert.assertEquals(driver.getTitle(),"Selenium WebDriver");
        driver.findElement(By.xpath("//input[@id='over_18']")).click();
        sleepInSeconds(2);
    }

    @Test
    public void TC_02_Kyna() {
        driver.get("https://skills.kynaenglish.vn/");
        String parentId = driver.getWindowHandle();
        driver.findElement(By.xpath("//div[@class='hotline']//img[@alt='facebook']")).click();
        sleepInSeconds(2);
        switchToWindowsByTitle("Kyna.vn | Ho Chi Minh City | Facebook");
        driver.findElement(By.xpath("//form[@id='login_popup_cta_form']//input[@name='email']")).sendKeys("Hung@gmail.com");
        sleepInSeconds(2);
        switchToWindowsByTitle("Kyna.vn - Học online cùng chuyên gia");
        driver.findElement(By.xpath("//div[@class='hotline']//img[@alt='youtube']")).click();
        sleepInSeconds(2);
        switchToWindowsByTitle("Kyna.vn - YouTube");
        driver.findElement(By.xpath("//input[@id='search']")).sendKeys("Hung@gmail.com");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='inner-header-container']//yt-formatted-string[@id='text']")).getText(),"Kyna.vn");
        sleepInSeconds(2);
        closeAllWindowsWithoutParent(parentId);
        driver.findElement(By.xpath("//input[@id='live-search-bar']")).sendKeys("Hung");
        sleepInSeconds(3);
    }
    @Test
    public void TC_03_TechPanda() {
        driver.get("http://live.techpanda.org/");
        String parentId = driver.getWindowHandle();
        driver.findElement(By.xpath("//a[text()='Mobile']")).click();
        sleepInSeconds(3);
        driver.findElement(By.xpath("//a[@title='IPhone']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
        sleepInSeconds(2);
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(),"The product IPhone has been added to comparison list.");
        driver.findElement(By.xpath("//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
        sleepInSeconds(2);
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(),"The product Samsung Galaxy has been added to comparison list.");
        driver.findElement(By.xpath("//a[@title='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
        sleepInSeconds(2);
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(),"The product Sony Xperia has been added to comparison list.");
        driver.findElement(By.xpath("//button[@title='Compare']")).click();
        sleepInSeconds(2);
        switchToWindowsByTitle("Products Comparison List - Magento Commerce");
        Assert.assertEquals(driver.getTitle(),"Products Comparison List - Magento Commerce");
        closeAllWindowsWithoutParent(parentId);
        driver.findElement(By.xpath("//button[@title='Compare']/following-sibling::a")).click();
        sleepInSeconds(2);
        driver.switchTo().alert().accept();
        sleepInSeconds(2);
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(),"The comparison list was cleared.");
        sleepInSeconds(2);
    }
    public void sleepInSeconds(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void switchToWindowsByTitle(String expectedTitle){
        Set<String> allIDs = driver.getWindowHandles();
        for (String Id : allIDs){
            driver.switchTo().window(Id);
            String actualTitle = driver.getTitle();
            if (actualTitle.equals(expectedTitle))
            {
                break;
            }
        }
    }
    public void closeAllWindowsWithoutParent(String parentId){
        Set<String> allIDs = driver.getWindowHandles();
        for (String Id : allIDs){
            if (!Id.equals(parentId))
            {
                driver.switchTo().window(Id);
                driver.close();
            }
        }
        driver.switchTo().window(parentId);
    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
