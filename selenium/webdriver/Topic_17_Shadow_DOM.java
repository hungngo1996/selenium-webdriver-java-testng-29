package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_17_Shadow_DOM {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Url() {
        driver.get("https://automationfc.github.io/shadow-dom/");
        WebElement Shadowhost = driver.findElement(By.xpath("//div[@id='shadow_host']"));
        SearchContext Shadowroot = Shadowhost.getShadowRoot();
        String Sometext = Shadowroot.findElement(By.cssSelector("span#shadow_content>span")).getText();
        System.out.println(Sometext);
        Assert.assertEquals(Sometext,"some text");
        List<WebElement> Inputs = Shadowroot.findElements(By.cssSelector("span"));
        Integer InputNumber = Inputs.size();
        System.out.println(InputNumber);
        WebElement Checkbox = Shadowroot.findElement(By.cssSelector("input[type='checkbox']"));
        Assert.assertFalse(Checkbox.isSelected());

        WebElement Shadowhostnested = Shadowroot.findElement(By.cssSelector("div#nested_shadow_host"));
        SearchContext shadowrootchild = Shadowhostnested.getShadowRoot();
        String Nestedtext = shadowrootchild.findElement(By.cssSelector("div#nested_shadow_content>div")).getText();
        System.out.println(Nestedtext);
        Assert.assertEquals(Nestedtext,"nested text");
    }
    @Test
    public void TC_02_Url() {
        driver.get("https://shopee.vn/");
        sleepInSeconds(3);
        SearchContext Shadowroot = driver.findElement(By.xpath("//shopee-banner-popup-stateful")).getShadowRoot();
        if (Shadowroot.findElements(By.cssSelector("div.home-popup__content")).size() > 0 && Shadowroot.findElements(By.cssSelector("div.home-popup__content")).get(0).isDisplayed()){
            Shadowroot.findElement(By.cssSelector("div.shopee-popup__close-btn")).click();
            sleepInSeconds(2);
        }
        driver.findElement(By.cssSelector("input.shopee-searchbar-input__input")).sendKeys("Iphone 15 Pro Max");
        sleepInSeconds(2);
        driver.findElement(By.cssSelector("button.shopee-searchbar__search-button")).click();
        sleepInSeconds(4);
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
