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

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_21_JavascriptExecutor {
    WebDriver driver;
    JavascriptExecutor jsExecutor;
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        jsExecutor = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Url() {
        navigateToUrlByJS("http://live.techpanda.org/");
        sleepInSeconds(5);

        String domain = (String) executeForBrowser("return document.domain;");
        Assert.assertEquals(domain,"live.techpanda.org");

        String Url = (String) executeForBrowser("return document.URL;");
        Assert.assertEquals(Url, "http://live.techpanda.org/");

        hightlightElement("//a[text()='Mobile']");
        clickToElementByJS("//a[text()='Mobile']");

        hightlightElement("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
        clickToElementByJS("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");

        Assert.assertTrue(getInnerText().contains("Samsung Galaxy was added to your shopping cart."));

        hightlightElement("//a[text()='Customer Service']");
        clickToElementByJS("//a[text()='Customer Service']");

        String titlePage = (String) executeForBrowser("return document.title");
        Assert.assertEquals(titlePage,"Customer Service");

        scrollToBottomPage();

        hightlightElement("//input[@id='newsletter']");
        sendkeyToElementByJS("//input[@id='newsletter']","hung@gmail.com");

        hightlightElement("//button[@title='Subscribe']");
        clickToElementByJS("//button[@title='Subscribe']");

        Assert.assertTrue(getInnerText().contains("There was a problem with the subscription: This email address is already assigned to another user."));
        isExpectedTextInInnerText("There was a problem with the subscription: This email address is already assigned to another user.");

        navigateToUrlByJS("https://www.facebook.com/");
        sleepInSeconds(2);

        Assert.assertEquals(executeForBrowser("return document.domain"),"facebook.com");
    }

    @Test
    public void TC_02_Url() {
        navigateToUrlByJS("https://sieuthimaymocthietbi.com/account/register");
        sleepInSeconds(3);
        hightlightElement("//button[text()='Đăng ký']");
        clickToElementByJS("//button[text()='Đăng ký']");
        sleepInSeconds(2);
        Assert.assertEquals(getElementValidationMessage("//input[@id='lastName']"),"Please fill out this field.");
        sendkeyToElementByJS("//input[@id='lastName']","Abc");
        hightlightElement("//button[text()='Đăng ký']");
        clickToElementByJS("//button[text()='Đăng ký']");
        sleepInSeconds(2);
        Assert.assertEquals(getElementValidationMessage("//input[@id='firstName']"),"Please fill out this field.");
        sendkeyToElementByJS("//input[@id='firstName']","Abc");
        hightlightElement("//button[text()='Đăng ký']");
        clickToElementByJS("//button[text()='Đăng ký']");
        sleepInSeconds(2);
        Assert.assertEquals(getElementValidationMessage("//input[@id='email']"),"Please fill out this field.");
        sendkeyToElementByJS("//input[@id='email']","aa@bb@cc");
        hightlightElement("//button[text()='Đăng ký']");
        clickToElementByJS("//button[text()='Đăng ký']");
        sleepInSeconds(2);
        Assert.assertEquals(getElementValidationMessage("//input[@id='email']"),"Please enter an email address.");

    }
    @Test
    public void TC_03_Url() {
        navigateToUrlByJS("http://live.techpanda.org/");
        sleepInSeconds(3);
        clickToElementByJS("//div[@id='header-account']//a[@title = 'My Account']");
        clickToElementByJS("//a[@class='button']//span[text()='Create an Account']");
        sendkeyToElementByJS("//input[@id='firstname']","Automation");
        sendkeyToElementByJS("//input[@id='middlename']","FC");
        sendkeyToElementByJS("//input[@id='lastname']","Hung Ngo");
        sendkeyToElementByJS("//input[@id='email_address']",getEmailAddress());
        sendkeyToElementByJS("//input[@id='password']","123456789");
        sendkeyToElementByJS("//input[@id='confirmation']","123456789");
        clickToElementByJS("//button[@class='button']//span[text()='Register']");
        sleepInSeconds(3);
        Assert.assertTrue(getInnerText().contains("Thank you for registering with Main Website Store."));
        clickToElementByJS("//div[@id='header-account']//a[@title = 'Log Out']");
        sleepInSeconds(10);
        Assert.assertEquals(executeForBrowser("return document.title"),"Home page");
    }
    public void sleepInSeconds(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public Object executeForBrowser(String javaScript) {
        return jsExecutor.executeScript(javaScript);
    }

    public String getInnerText() {
        return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
    }

    public boolean isExpectedTextInInnerText(String textExpected) {
        String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
        return textActual.equals(textExpected);
    }

    public void scrollToBottomPage() {
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void navigateToUrlByJS(String url) {
        jsExecutor.executeScript("window.location = '" + url + "'");
        sleepInSeconds(3);
    }

    public void hightlightElement(String locator) {
        WebElement element = getElement(locator);
        String originalStyle = element.getAttribute("style");
        String JS = (String) jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        sleepInSeconds(2);
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    public void clickToElementByJS(String locator) {
        jsExecutor.executeScript("arguments[0].click();", getElement(locator));
        sleepInSeconds(3);
    }

    public void scrollToElementOnTop(String locator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
    }

    public void scrollToElementOnDown(String locator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
    }

    public void setAttributeInDOM(String locator, String attributeName, String attributeValue) {
        jsExecutor.executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue +"');", getElement(locator));
    }

    public void removeAttributeInDOM(String locator, String attributeRemove) {
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
    }

    public void sendkeyToElementByJS(String locator, String value) {
        jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
    }

    public String getAttributeInDOM(String locator, String attributeName) {
        return (String) jsExecutor.executeScript("return arguments[0].getAttribute('" + attributeName + "');", getElement(locator));
    }

    public String getElementValidationMessage(String locator) {
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
    }

    public boolean isImageLoaded(String locator) {
        boolean status = (boolean) jsExecutor.executeScript(
                "return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(locator));
        return status;
    }

    public WebElement getElement(String locator) {
        return driver.findElement(By.xpath(locator));
    }
    public String getEmailAddress(){
        Random rand = new Random();
        return "kenvin" + rand.nextInt(99999) + "@gmail.net";
    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
