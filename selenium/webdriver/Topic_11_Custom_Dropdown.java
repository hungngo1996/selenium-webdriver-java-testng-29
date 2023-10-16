package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_11_Custom_Dropdown {
    WebDriver driver;
    WebDriverWait expliciWait;
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        expliciWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_JQuery() {
        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

        selectItemdropdown("//span[@id='speed-button']", "//ul[@id='speed-menu']//div", "Slower");
        sleepInSeconds(2);
        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='speed-button']//span[@class='ui-selectmenu-text']")).getText(),"Slower");
        selectItemdropdown("//span[@id='files-button']", "//ul[@id='files-menu']//div", "ui.jQuery.js");
        sleepInSeconds(2);
        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='files-button']//span[@class='ui-selectmenu-text']")).getText(),"ui.jQuery.js");

        selectItemdropdown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "15");
        sleepInSeconds(2);
        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text']")).getText(),"15");

        selectItemdropdown("//span[@id='salutation-button']", "//ul[@id='salutation-menu']//div", "Mr.");
        sleepInSeconds(2);
        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='salutation-button']//span[@class='ui-selectmenu-text']")).getText(),"Mr.");
        //3.1 - Nếu như item cần chọn nó hiển thị thì click vào
        //3.2 - Nếu như item cần chọn nằm bên dưới thì 1 số trường hợp cần scroll xuống để hiển thị lên rồi mới chọn (Angular/ React/ VueJS/..)
        //4 - Trước khi click cần kiểm tra nếu như text của item bằng vs item cần chọn thì click vào
    }

    @Test
    public void TC_02_React() {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
        selectItemdropdown("//div[@class='ui fluid selection dropdown']","//div[@class='visible menu transition']//span","Matt");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(),"Matt");
        sleepInSeconds(2);
        selectItemdropdown("//div[@class='ui fluid selection dropdown']","//div[@class='visible menu transition']//span","Jenny Hess");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(),"Jenny Hess");
        sleepInSeconds(2);
        selectItemdropdown("//div[@class='ui fluid selection dropdown']","//div[@class='visible menu transition']//span","Christian");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(),"Christian");
        sleepInSeconds(2);
    }
    @Test
    public void TC_03_VueJS() {
        driver.get("https://mikerodham.github.io/vue-dropdowns/");
        selectItemdropdown("//li[@class='dropdown-toggle']","//ul[@class='dropdown-menu']//a","First Option");
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(),"First Option");
        sleepInSeconds(2);

        selectItemdropdown("//li[@class='dropdown-toggle']","//ul[@class='dropdown-menu']//a","Second Option");
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(),"Second Option");
        sleepInSeconds(2);

        selectItemdropdown("//li[@class='dropdown-toggle']","//ul[@class='dropdown-menu']//a","Third Option");
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(),"Third Option");
        sleepInSeconds(2);
    }
    @Test
    public void TC_04_EditableDropdown() {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
        selectItemInEditabledDropdown("//input[@class='search']","//div[@class='visible menu transition']//span","Afghanistan");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(),"Afghanistan");
        sleepInSeconds(2);

        selectItemInEditabledDropdown("//input[@class='search']","//div[@class='visible menu transition']//span","Australia");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(),"Australia");
        sleepInSeconds(2);

        selectItemInEditabledDropdown("//input[@class='search']","//div[@class='visible menu transition']//span","Belgium");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(),"Belgium");
        sleepInSeconds(2);
    }
    @Test
    public void TC_05_NopCommerce() {
        driver.get("https://demo.nopcommerce.com/register");
        selectItemdropdown("//select[@name='DateOfBirthDay']","//select[@name='DateOfBirthDay']/option","21");
        Assert.assertTrue(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']/option[@value='21']")).isSelected());
        selectItemdropdown("//select[@name='DateOfBirthMonth']","//select[@name='DateOfBirthMonth']/option","November");
        Assert.assertTrue(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']/option[@value='11']")).isSelected());
        selectItemdropdown("//select[@name='DateOfBirthYear']","//select[@name='DateOfBirthYear']/option","1996");
        Assert.assertTrue(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']/option[@value='1996']")).isSelected());
    }
    public void sleepInSeconds(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void selectItemdropdown(String parentXpath, String childItemXpath, String itemTextExpected){
        driver.findElement(By.xpath(parentXpath)).click();
        sleepInSeconds(1);
        List<WebElement> allItems = expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childItemXpath)));
        for (WebElement items: allItems){
            if (items.getText().equals(itemTextExpected)){
                items.click();
                break;
            }
        }
    }
    public void selectItemInEditabledDropdown(String parentXpath, String childItemXpath, String itemTextExpected) {
        driver.findElement(By.xpath(parentXpath)).clear();
        driver.findElement(By.xpath(parentXpath)).sendKeys(itemTextExpected);
        sleepInSeconds(1);
        List<WebElement> allItems = expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childItemXpath)));
        for (WebElement items: allItems){
            if (items.getText().equals(itemTextExpected)){
                items.click();
                break;
            }
        }
    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
