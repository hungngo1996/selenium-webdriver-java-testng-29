package javaTester;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Assert {
    WebDriver driver;
   public void verifyTestNG() {
       driver = new FirefoxDriver();
       driver.get("https://www.facebook.com/");
        // Trong Java có rất nhiều thư viện để verify
        // Testing Framework (Unit/ Intergration/ UI Automation Test)
        // JUnit 4/ Test NG/ JUnit 5/ Hamcrest/ AssertJ/...

        // Kiểu dữ liệu nhận vào là boolean (True/False)
        // Khi mong muốn điều kiện trả về là đúng thì dùng assertTrue để verify
        Assert.assertTrue(driver.getPageSource().contains("Facebook helps you connect"));
        // Khi mong muốn điều kiện trả về là sai thì dùng assertFalse
        Assert.assertFalse(driver.getPageSource().contains("Automation Test"));

        // Các hàm trả về kiểu dữ liệu là boolean
        // Quy tắc: bắt đầu với tiền tố là isXXX
        // WebElement
       driver.findElement(By.id("")).isDisplayed();
       driver.findElement(By.id("")).isEnabled();
       driver.findElement(By.id("")).isSelected();
       new Select(driver.findElement(By.id(""))).isMultiple();


       // Mong đợi 1 điều kiện giống như thực tế (Tuyệt đối)
       // Actual = Expected
       Assert.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com/");
       Assert.assertEquals(driver.findElement(By.id("")).getText(),"Automation Test");

       // Unit Test
       Object name = null;
       Assert.assertNull(name);

       name = "Testing";
       Assert.assertNotNull(name);
   }
}
