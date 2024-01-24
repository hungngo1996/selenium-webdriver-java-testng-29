package selenium.testng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_03_Assert {
    WebDriver driver;
    @Test
    public void test01(){
        // Equals - Kiểm tra 2 giá trị bằng nhau (Actual - Expected)
        // String/ boolean/ Int/ Float/..
        String fullName = "Hung Ngo";
        Assert.assertEquals(fullName,"Hung Ngo", "FullName not correctly!");

        // True - False
        // Điều kiện nhận vào là boolean (isDisplayed/ isEnabled/ isSelected/ isMultiple/ User-Defined/..)

        // Mong đợi kết quả trả về là đúng
        Assert.assertTrue(isElementDisplayed(By.cssSelector("")));
        Assert.assertTrue(isElementDisplayed(By.cssSelector("")),"");
        // Mong đợi kết quả trả về là sai
        Assert.assertFalse(isElementDisplayed(By.cssSelector("")));
    }

    private boolean isElementDisplayed(By locator){
        return driver.findElement(locator).isDisplayed();
    }
}
