package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_24_Wait_02_Find_Element {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_FindElement() {
        // Case 1 - Element tim thay chi co 1 va khong can cho het timeout
        // Tim thay tra ve 1 element va qua step tiep theo
        driver.findElement(By.xpath(""));
        // Case 2 - Element tim thay nhieu hon 1 va khong can cho het timeout
        // Lay element dau tien cua n node va qua step tiep theo
        driver.findElement(By.xpath(""));
        // Case 3 - Element khong tim thay
        // Cho het timeout
        // Trong thoi gian timeout cu moi 0.5s se tim lai 1 lan
        // Neu tim thay tra ve element va qua step tiep theo
        // Neu tim lai khong thay danh fail testcases va throw exception: NoSuchElementException
        driver.findElement(By.xpath(""));
    }

    @Test
    public void TC_02_FindElements() {
        List<WebElement> elementList;
        // Case 1 - Element tim thay chi co 1 va khong can cho het timeout
        // Tim thay tra ve List Element chua dung 1 element
        elementList = driver.findElements(By.xpath(""));
        // Case 2 - Element tim thay nhieu hon 1 va khong can cho het timeout
        // Tim thay tra ve List Element chua nhieu element
        elementList = driver.findElements(By.xpath(""));
        // Case 3 - Element khong tim thay
        // Cho het timeout
        // Trong thoi gian timeout cu moi 0.5s se tim lai 1 lan
        // Neu trong thoi gian tim lai ma thay element thi cung tra ve list chua cac element do
        // Neu het timeout ma khong tim thay se tra ve list rong (empty) va khong danh fail testcase
        // Qua step tiep theo
        elementList = driver.findElements(By.xpath(""));
    }
    public void sleepInSeconds(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public String getDateTimeNow(){
        Date date = new Date();
        return date.toString();
    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
