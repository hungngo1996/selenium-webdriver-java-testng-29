package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_07_WebElement_Commands {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.facebook.com/");
    }

    @Test
    public void TC_01_Element() {
//        // Tìm và trả về 1 element
//        // HTML element: Button,text,link,image,icon,...
//        driver.findElement(By.id(""));
//        // Tìm và tương tác lên element
//        driver.findElement(By.id("")).click();
//        driver.findElement(By.id("")).sendKeys();
//
//        // Tìm và lưu vào 1 biến WebElement (chưa tương tác)
//        WebElement fullNameTextbox = driver.findElement(By.id(""));
//        fullNameTextbox.clear();
//        fullNameTextbox.sendKeys();
//        fullNameTextbox.getAttribute("value");

        // xóa dữ liệu trong 1 field cho phép nhập
        // Textbox/Textarea/input/...
        // Thường dùng trước hàm sendkeys()
        driver.findElement(By.id("")).clear();

        // Dùng để nhập liệu vào các field
        driver.findElement(By.id("")).sendKeys();

        // Dùng để click lên elements
        driver.findElement(By.id("")).click();

        // Tìm từ node cha tới node con
        driver.findElement(By.id("")).findElement(By.id(""));

        // Trả về nhiều element khớp với điều kiện
        List<WebElement> textboxs = driver.findElements(By.cssSelector(""));

        // Dung để verify 1 checkbox/radio/dropdown đã được chọn hay chưa
        Assert.assertTrue(driver.findElement(By.id("")).isSelected());
        Assert.assertFalse(driver.findElement(By.id("")).isSelected());

        // Dropdown (default/custom)

        // Dùng để verify 1 element có được hiển thị hay không
        Assert.assertTrue(driver.findElement(By.id("")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.id("")).isDisplayed());

        // Dùng để verify
        Assert.assertTrue(driver.findElement(By.id("")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.id("")).isDisplayed());

        // HTML Element
        //
        //
        driver.findElement(By.id("")).getAttribute("title");
        driver.findElement(By.id("")).getAttribute("type");
        driver.findElement(By.id("")).getAttribute("id");

        // Tab Accesibility/ Properties trong element
        driver.findElement(By.id("")).getAccessibleName();
        driver.findElement(By.id("")).getDomAttribute("checked");
        driver.findElement(By.id("")).getDomProperty("validationMessage");
        // Tab Style trong element
        // Font/Size/Color/Font...
        driver.findElement(By.id("")).getCssValue("background-color");
        driver.findElement(By.id("")).getCssValue("font-size");

        // Vị trí của element so vs độ phân giải màn hình
        Point nameTextboxLocation = driver.findElement(By.id("")).getLocation();
        nameTextboxLocation.getX();
        nameTextboxLocation.getY();

        // WidthxHeight
        Dimension sizes = driver.findElement(By.id("")).getSize();
        sizes.getWidth();
        sizes.getHeight();

        // Location + size
        Rectangle nameTextboxRect = driver.findElement(By.id("")).getRect();

        // Location
        Point Location = nameTextboxRect.getPoint();

        // Size
        Dimension Size = nameTextboxRect.getDimension();
        Size.getHeight();
        Size.getWidth();

        //Shadow element
        driver.findElement(By.id("")).getShadowRoot();

        // Từ ID/Class/name/css/Xpath có thể truy ra TagName của element đó
        driver.findElement(By.cssSelector("#firstName")).getTagName();
        driver.findElement(By.className("form")).getTagName();
        driver.findElement(By.id("firstName")).getTagName();
        driver.findElement(By.xpath("//*[@class='form-list']")).getTagName();
        // Text bị ẩn không get được
        driver.findElement(By.id("")).getText();

        // Chụp hình bị lỗi và lưu dưới dạng nào
        // Byte
        // File (lưu thành 1 hình có kích thước ở trong ổ cứng: .png/ .jpf/..)
        // BASE64 (hình dạng Text)
        driver.findElement(By.id("")).getScreenshotAs(OutputType.BASE64);

        // Hành vi giống phím Enter
        driver.findElement(By.id("")).submit();
    }

    @Test
    public void TC_02_Url() {

    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
