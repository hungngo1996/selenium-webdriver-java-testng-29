package selenium.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_13_Checkbox_Radio {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Checkbox_telerik() {
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
        sleepInSeconds(5);

        By dualZoneCheckbox = By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input");

        checktoElement(dualZoneCheckbox);

        // verify checkbox is choose
        Assert.assertTrue(driver.findElement(dualZoneCheckbox).isSelected());

        uncheckToElemnt(dualZoneCheckbox);

        Assert.assertFalse(driver.findElement(dualZoneCheckbox).isSelected());
    }

    @Test
    public void TC_02_Radio_telerik() {
        driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
        By twoPetro = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input");
        By twoDiesel = By.xpath("//label[text()='2.0 Diesel, 103kW']/preceding-sibling::input");

        checktoElement(twoPetro);
        //verify
        Assert.assertTrue(driver.findElement(twoPetro).isSelected());
        Assert.assertFalse(driver.findElement(twoDiesel).isSelected());

        checktoElement(twoDiesel);
        //verify
        Assert.assertTrue(driver.findElement(twoDiesel).isSelected());
        Assert.assertFalse(driver.findElement(twoPetro).isSelected());
    }
    @Test
    public void TC_03_Select_All_Checkbox() {
        driver.get("https://automationfc.github.io/multiple-fields/");
        List<WebElement> allCheckbox = driver.findElements(By.xpath("//div[@class='form-input-wide']//input[@type='checkbox']"));
        for (WebElement checkbox : allCheckbox){
            if (!checkbox.isSelected()) {
                checkbox.click();
                //verify
                Assert.assertTrue(checkbox.isSelected());
            }
        }
        for (WebElement checkbox : allCheckbox){
            if (checkbox.isSelected()) {
                checkbox.click();
                //verify
                Assert.assertFalse(checkbox.isSelected());
            }
        }
        for (WebElement checkbox : allCheckbox){
            if (checkbox.getAttribute("value").equals("Heart Attack") && !checkbox.isSelected()) {
                checkbox.click();
                sleepInSeconds(1);
                //verify
                Assert.assertTrue(checkbox.isSelected());
            }else {
                Assert.assertFalse(checkbox.isSelected());
            }
        }
    }
    @Test
    public void TC_04_Custome_Radio() {
        driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
        //((JavascriptExecutor) driver).executeScript("");
        By radioButton = By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input");
        JavascriptExecutor jsExcutor = (JavascriptExecutor) driver;
        jsExcutor.executeScript("arguments[0].click();",driver.findElement(radioButton));
        //verify
        Assert.assertTrue(driver.findElement(radioButton).isSelected());
    }
    @Test
    public void TC_05_Radio_Angular() {
        driver.get("https://material.angular.io/components/radio/examples");
        By SummerRadio = By.xpath("//label[text()=' Summer ']/preceding-sibling::div//input");
        checktoElement(SummerRadio);
        Assert.assertTrue(driver.findElement(SummerRadio).isSelected());
    }
    @Test
    public void TC_06_Checkbox_Angular() {
        driver.get("https://material.angular.io/components/checkbox/examples");
        By CheckedCheckbox = By.xpath("//label[text()='Checked']/preceding-sibling::div//input");
        By IndeterminateCheckbox = By.xpath("//label[text()='Indeterminate']/preceding-sibling::div//input");

        checktoElement(CheckedCheckbox);
        checktoElement(IndeterminateCheckbox);
        //verify
        Assert.assertTrue(driver.findElement(CheckedCheckbox).isSelected());
        Assert.assertTrue(driver.findElement(IndeterminateCheckbox).isSelected());

        uncheckToElemnt(CheckedCheckbox);
        uncheckToElemnt(IndeterminateCheckbox);
        //verify
        Assert.assertFalse(driver.findElement(CheckedCheckbox).isSelected());
        Assert.assertFalse(driver.findElement(IndeterminateCheckbox).isSelected());
    }
    @Test
    public void TC_07_Custom_google_Docs() {
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform?pli=1");
        By HCMradio = By.xpath("//div[@aria-label='Hồ Chí Minh']");

        Assert.assertEquals(driver.findElement(HCMradio).getAttribute("aria-checked"),"false");
        driver.findElement(HCMradio).click();
        Assert.assertEquals(driver.findElement(HCMradio).getAttribute("aria-checked"),"true");

        By QNcheckbox = By.xpath("//div[@aria-label='Quảng Ninh' and @role='checkbox']");
        By QBcheckbox = By.xpath("//div[@aria-label='Quảng Bình' and @role='checkbox']");
        driver.findElement(QNcheckbox).click();
        driver.findElement(QBcheckbox).click();
        sleepInSeconds(2);
        Assert.assertEquals(driver.findElement(QNcheckbox).getAttribute("aria-checked"),"true");
        Assert.assertEquals(driver.findElement(QBcheckbox).getAttribute("aria-checked"),"true");

    }
    public void sleepInSeconds(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void checktoElement(By Xpath){
        if(!driver.findElement(Xpath).isSelected()){
            driver.findElement(Xpath).click();
            sleepInSeconds(2);
        }
    }
    public void uncheckToElemnt(By Xpath){
        if(driver.findElement(Xpath).isSelected()){
            driver.findElement(Xpath).click();
            sleepInSeconds(2);
        }
    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
