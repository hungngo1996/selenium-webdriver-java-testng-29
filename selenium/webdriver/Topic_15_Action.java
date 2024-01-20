package selenium.webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.io.*;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_15_Action {
    WebDriver driver;
    Actions action;
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        action = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    @Test
    public void TC_01_Hover_Tooltip() {
        driver.get("https://automationfc.github.io/jquery-tooltip/");
        action.moveToElement(driver.findElement(By.cssSelector("input#age"))).perform();
        sleepInSeconds(3);
        Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(),"We ask for your age only for statistical purposes.");
    }

    @Test
    public void TC_02_Hover_Menu_fahasa() {
        driver.get("https://www.fahasa.com/");
        action.moveToElement(driver.findElement(By.cssSelector("span.icon_menu"))).perform();
        action.moveToElement(driver.findElement(By.xpath("//a[@title='Bách Hóa Online - Lưu Niệm']"))).perform();
        action.click(driver.findElement(By.xpath("//div[contains(@class,'fhs_menu_content')]//a[text()='Thiết Bị Số - Phụ Kiện Số']"))).perform();
        //Assert.assertEquals(driver.findElement(By.cssSelector("ol.breadcrumb strong")).getText()," Thiết Bị Số - Phụ Kiện Số");
        Assert.assertTrue(driver.findElement(By.xpath("//ol[@class='breadcrumb']//strong[text()='Thiết Bị Số - Phụ Kiện Số']")).isDisplayed());
    }
    @Test
    public void TC_03_ClickandHold() {
        driver.get("https://automationfc.github.io/jquery-selectable/");
        List<WebElement> allNumbers = driver.findElements(By.xpath("//ol[@id='selectable']//li"));
        action.clickAndHold(allNumbers.get(0))
                .moveToElement(allNumbers.get(14))
                .release()
                .perform();
        sleepInSeconds(2);
        List<String> allNumbersExpected = new ArrayList<>();
        allNumbersExpected.add("1");
        allNumbersExpected.add("2");
        allNumbersExpected.add("3");
        allNumbersExpected.add("5");
        allNumbersExpected.add("6");
        allNumbersExpected.add("7");
        allNumbersExpected.add("9");
        allNumbersExpected.add("10");
        allNumbersExpected.add("11");
        allNumbersExpected.add("13");
        allNumbersExpected.add("14");
        allNumbersExpected.add("15");

        List<WebElement> allNumbersSelected = driver.findElements(By.xpath("//ol[@id='selectable']//li[contains(@class,'ui-selected')]"));
        Assert.assertEquals(allNumbersSelected.size(),12);

        List<String> allNumbersActual = new ArrayList<>();
        for (WebElement element: allNumbersSelected ){
            allNumbersActual.add(element.getText());
        }
        Assert.assertEquals(allNumbersActual,allNumbersExpected);
    }
    @Test
    public void TC_04_ClickandHold() {
        driver.get("https://automationfc.github.io/jquery-selectable/");
        List<WebElement> allNumbers = driver.findElements(By.xpath("//ol[@id='selectable']//li"));
        Keys cmdCtrl = Platform.getCurrent().is(Platform.WINDOWS) ? Keys.CONTROL : Keys.COMMAND;

        action.clickAndHold(allNumbers.get(0))
                .moveToElement(allNumbers.get(11))
                .release()
                .perform();
        action.keyDown(cmdCtrl).perform();
        action.clickAndHold(allNumbers.get(12))
                .moveToElement(allNumbers.get(14)).release().keyUp(cmdCtrl).perform();
        sleepInSeconds(2);

        List<WebElement> allNumbersSelected = driver.findElements(By.xpath("//ol[@id='selectable']//li[contains(@class,'ui-selected')]"));
        Assert.assertEquals(allNumbersSelected.size(),15);
    }
    @Test
    public void TC_05_ClickandHold() {
        driver.get("https://automationfc.github.io/jquery-selectable/");
        List<WebElement> allNumbers = driver.findElements(By.xpath("//ol[@id='selectable']//li"));
        Keys cmdCtrl = Platform.getCurrent().is(Platform.WINDOWS) ? Keys.CONTROL : Keys.COMMAND;

        action.keyDown(cmdCtrl)
                .click(allNumbers.get(0))
                .click(allNumbers.get(2))
                .click(allNumbers.get(5))
                .click(allNumbers.get(10))
                .release()
                .keyUp(cmdCtrl)
                .perform();
        sleepInSeconds(2);

        List<WebElement> allNumbersSelected = driver.findElements(By.xpath("//ol[@id='selectable']//li[contains(@class,'ui-selected')]"));
        Assert.assertEquals(allNumbersSelected.size(),4);
    }
    @Test
    public void TC_06_DoubleClick() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        WebElement doubleClickbutton = driver.findElement(By.xpath("//button[text() = 'Double click me']"));
        JavascriptExecutor JsExe = (JavascriptExecutor) driver;
        JsExe.executeScript("arguments[0].scrollIntoView(true);", doubleClickbutton);
        sleepInSeconds(3);
        action.doubleClick(doubleClickbutton).perform();
        Assert.assertEquals(driver.findElement(By.xpath("//p[@id='demo']")).getText(),"Hello Automation Guys!");
    }
    @Test
    public void TC_07_RightClick() {
        driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
        //Chưa click - invisible
        WebElement Contextmenu = driver.findElement(By.xpath("//ul[@class='context-menu-list context-menu-root']"));
        Assert.assertFalse(Contextmenu.isDisplayed());
        //Right-Click
        WebElement rightClickbutton = driver.findElement(By.xpath("//span[text() = 'right click me']"));
        action.contextClick(rightClickbutton).perform();
        //visible
        sleepInSeconds(2);
        Assert.assertTrue(Contextmenu.isDisplayed());
        //hover Past
        action.moveToElement(driver.findElement(By.xpath("//span[text()='Paste']//parent::li"))).perform();
        //verify
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='context-menu-item context-menu-icon context-menu-icon-paste context-menu-visible']")).isDisplayed());
        //click Past
        action.click(driver.findElement(By.xpath("//li[@class='context-menu-item context-menu-icon context-menu-icon-paste context-menu-visible']"))).perform();
        sleepInSeconds(2);
        driver.switchTo().alert().accept();
        sleepInSeconds(2);
        Assert.assertFalse(Contextmenu.isDisplayed());
    }
    @Test
    public void TC_08_DragandDrop_HTML4() {
        driver.get("https://automationfc.github.io/kendo-drag-drop/");
        WebElement smallcicle = driver.findElement(By.xpath("//div[@id='draggable']"));
        WebElement bigcicle = driver.findElement(By.xpath("//div[@id='droptarget']"));
        action.dragAndDrop(smallcicle,bigcicle).perform();
        sleepInSeconds(2);
        Assert.assertEquals(bigcicle.getText(),"You did great!");
        Assert.assertEquals(Color.fromString(bigcicle.getCssValue("background-color")).asHex().toLowerCase(),"#03a9f4");
    }
    @Test
    public void TC_09_DragandDrop_HTML5_Css() throws IOException {
        driver.get("https://automationfc.github.io/drag-drop-html5/");
        String columnA = "div#column-a";
        String columnB = "div#column-b";
        String projectPath = System.getProperty("user.dir");
        String drapandDropfile = projectPath + "/drapandDrop/drap_and_drop_helper.js";
        String jsContentFile = getContentFile(drapandDropfile);
        jsContentFile = jsContentFile + "$('" + columnA + "').simulateDragDrop({ dropTarget: '" + columnB + "'});";
        JavascriptExecutor JsExe = (JavascriptExecutor) driver;
        JsExe.executeScript(jsContentFile);
        sleepInSeconds(3);
        //A -> B
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a")).getText(),"B");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b")).getText(),"A");
        //B -> A
        JsExe.executeScript(jsContentFile);
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a")).getText(),"A");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b")).getText(),"B");
    }
    @Test
    public void TC_09_DragandDrop_HTML5_Xpath() throws AWTException {
        driver.get("https://automationfc.github.io/drag-drop-html5/");
        String columnA = "//div[@id='column-a']";
        String columnB = "//div[@id='column-b']";
        dragAndDropHTML5ByXpath(columnA,columnB);
        sleepInSeconds(3);
        //A -> B
        Assert.assertEquals(driver.findElement(By.xpath(columnA)).getText(),"B");
        Assert.assertEquals(driver.findElement(By.xpath(columnB)).getText(),"A");
        //B -> A
        dragAndDropHTML5ByXpath(columnA,columnB);
        Assert.assertEquals(driver.findElement(By.xpath(columnA)).getText(),"A");
        Assert.assertEquals(driver.findElement(By.xpath(columnB)).getText(),"B");
    }
    public void dragAndDropHTML5ByXpath(String sourceLocator, String targetLocator) throws AWTException {

        WebElement source = driver.findElement(By.xpath(sourceLocator));
        WebElement target = driver.findElement(By.xpath(targetLocator));

        // Setup robot
        Robot robot = new Robot();
        robot.setAutoDelay(500);

        // Get size of elements
        Dimension sourceSize = source.getSize();
        Dimension targetSize = target.getSize();

        // Get center distance
        int xCentreSource = sourceSize.width / 2;
        int yCentreSource = sourceSize.height / 2;
        int xCentreTarget = targetSize.width / 2;
        int yCentreTarget = targetSize.height / 2;

        Point sourceLocation = source.getLocation();
        Point targetLocation = target.getLocation();

        // Make Mouse coordinate center of element
        sourceLocation.x += 20 + xCentreSource;
        sourceLocation.y += 110 + yCentreSource;
        targetLocation.x += 20 + xCentreTarget;
        targetLocation.y += 110 + yCentreTarget;

        // Move mouse to drag from location
        robot.mouseMove(sourceLocation.x, sourceLocation.y);

        // Click and drag
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseMove(((sourceLocation.x - targetLocation.x) / 2) + targetLocation.x, ((sourceLocation.y - targetLocation.y) / 2) + targetLocation.y);

        // Move to final position
        robot.mouseMove(targetLocation.x, targetLocation.y);

        // Drop
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }
    public void sleepInSeconds(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public String getContentFile(String filePath) throws IOException {
        Charset cs = Charset.forName("UTF-8");
        FileInputStream stream = new FileInputStream(filePath);
        try {
            Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
            StringBuilder builder = new StringBuilder();
            char[] buffer = new char[8192];
            int read;
            while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
                builder.append(buffer, 0, read);
            }
            return builder.toString();
        } finally {
            stream.close();
        }
    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
