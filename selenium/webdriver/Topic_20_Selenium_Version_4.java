package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_20_Selenium_Version_4 {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_Selenium_Version_4() {
        // Driver đang ở trang Home
        driver.get("https://skills.kynaenglish.vn/");
        System.out.println("windowID " + driver.getWindowHandle());
        System.out.println("driver " + driver.toString());
        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());

        // Window mới - driver nhảy qua window mới này nhưng không có tạo ra driver mới
        driver.switchTo().newWindow(WindowType.WINDOW);
        driver.get("https://skills.kynaenglish.vn/danh-sach-khoa-hoc/ky-nang-cong-viec?utm_source=kyna.site&utm_medium=homepage_newmenu");
        System.out.println("windowID " + driver.getWindowHandle());
        System.out.println("driver " + driver.toString());
        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());
        driver.findElement(By.xpath("//span[@id='select2-facet-level-container']")).click();
        sleepInSeconds(2);
        List<WebElement> allItems = driver.findElements(By.xpath("//span[@class='select2-results']/ul/li"));
        for (WebElement item : allItems)
        {
            if (item.getText().equals("Cơ bản")){
                item.click();
                break;
            }
        }
        sleepInSeconds(3);

        //Tại Window này nhảy qua Tab mới driver nhảy qua Tab mới từ Window trước đó
        driver.switchTo().newWindow(WindowType.TAB).get("https://skills.kynaenglish.vn/khoa-hoc-tron-bo-vba-cach-tang-hieu-qua-va-nang-suat-cong-viec-tren-excel");
        System.out.println("windowID " + driver.getWindowHandle());
        System.out.println("driver " + driver.toString());
        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());
        driver.findElement(By.xpath("//a[@class='btn-buy-now dang-ky-hoc crs-btn-buy']")).click();
        sleepInSeconds(3);
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
