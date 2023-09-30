package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_06_WebBrowser_Commands {

    // Các câu lệnh thao tác với browser
    // driver.
    WebDriver driver;
    // Các câu lệnh thao tác với element
    // element.
    WebElement element;

    FirefoxDriver FDriver;
    ChromeDriver ChDriver;
    EdgeDriver EdDriver;
    @BeforeClass
    public void beforeClass() {
        // Muốn dùng được phải khởi tạo
        // Nếu kh khởi tạo sẽ gặp lỗi:NullPointerException
        driver = new FirefoxDriver();
        driver = new EdgeDriver();
        driver = new ChromeDriver();
        driver = new SafariDriver();

//        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void TC_01_Url() throws MalformedURLException {
        // Mở ra 1 page URL bất kì
        driver.get("https://www.facebook.com/");
        // Đóng tab đang thao tác
        driver.close();
        // Đóng Browser
        driver.quit();
        // Nó sẽ đi tìm vs loại By nào và trả về element nếu như tìm thấy
        // Ko tìm thấy fail tại step này - throw exception: NoSuchElementException
        // Trả về element - Nếu tìm thấy nhiều hơn 1 element thì cũng chỉ lấy 1 (thao tác với cái đầu tien)
        WebElement element = driver.findElement(By.id("email"));
        // Nó sẽ đi tìm với loại By nào và trả về 1 danh sách element nếu như được tìm thấy (List WebElement)
        // Không được tìm thấy - không bị fail - Trả về 1 List rỗng (0 Element)
        List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@class='checkbox']"));

        // khai báo biến
        String currentUrl = driver.getCurrentUrl();

        // Tại sao cần phải lấy dữ liệu ra để làm gì?
        // Dùng để lấy ra Url của màn hình/ page hiện tại đang đứng
        driver.getCurrentUrl();

        // Lấy ra page source HTML/CSS/JS của page hiện tại
        // Verify một cách tương đối
        driver.getPageSource();
        Assert.assertTrue(driver.getPageSource().contains("The Apple"));

        // Lấy ra title của page hiện tại
        driver.getTitle();

        // Lấy ra Id của tab hiện tại
        // Handle Window/Tab
        driver.getWindowHandle();
        driver.getWindowHandles();

        //Cookies - Framework
        driver.manage().getCookies();
        //Get ra nhưng log ở Devtools
        driver.manage().logs().get(LogType.DRIVER);
        // Apply cho việc tìm element (findElement/findElements)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        // Chờ cho page được load xong
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        // Set trước khi dùng vs thư viện JavascriptExcutor
        // Inject 1 đoạn code JS vào trong Browser/Element
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
        // Selenium 4 mới có
        driver.manage().timeouts().getImplicitWaitTimeout();
        driver.manage().timeouts().getPageLoadTimeout();
        driver.manage().timeouts().getScriptTimeout();
        // Chạy full màn hình
        driver.manage().window().fullscreen();
        driver.manage().window().maximize();
        driver.manage().window().minimize();
        // Test Responsive (Resolution)
        driver.manage().window().setSize(new Dimension(1366, 768));
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.manage().window().setSize(new Dimension(2568, 1440));

        driver.manage().window().getSize();

        // set tọa độ hiển thị cho browser (run trên màn hình có kích thước bao nhiêu)
        driver.manage().window().setPosition(new Point(0,0));
        driver.manage().window().getPosition();

        // Điều hướng trang web
        driver.navigate().back();
        driver.navigate().refresh();
        driver.navigate().forward();

        //Thao tác với history của web page (back/forward)
        driver.navigate().to("https://www.facebook.com/");
        driver.navigate().to(new URL("https://www.facebook.com/"));

        // Lấy ra ID của cửa sổ/ tab hiện tại
        // Handle WIndow/tab
        String homePageWindowID = driver.getWindowHandle();
        driver.switchTo().window(homePageWindowID);

        driver.switchTo();

        // Nếu chỉ dùng một lần không cần phải khai báo biến
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.facebook.com/");
        // Dùng lại nhiều lần (từ 2 lần trở lên)
        Assert.assertEquals(currentUrl,"https://www.facebook.com/");


    }

    @Test
    public void TC_02_Url() {

    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
