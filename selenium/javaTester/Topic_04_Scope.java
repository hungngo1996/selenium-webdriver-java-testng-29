package javaTester;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_04_Scope {
    // Các biến được khai báo ở bên ngoài hàm -> phạm vi Class
    // Biến Global (toàn cục)
    // Có thể dùng cho tất cả các hàm trong Class
    WebDriver driver;
    String homePageUrl = "https://www.facebook.com/"; // Khai báo: Declare
    String fullName = "Automation Test"; // Khai báo + khởi tạo (Initial)
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
    }

    @Test
    public void TC_01_Url() {
        // Các biến được khai báo trong một hàm/ block code -> phạm vi cục bộ (Local)
        // Dùng trong cái hàm nó được khai báo/ block code được sinh ra
        String homePageUrl = "https://www.facebook.com/";

        // Trong 1 hàm nếu như có 2 biền cùng tên (Global/Local) thì nó ưutieenn lấy biến Local
        // 1 biến Local nếu gọi tới dùng mà chưa được khởi tạo thì sẽ bị lỗi
        // Biến Local chưa khởi tạo mà gọi ra dùng nó báo lỗi ngay (conpile code)
        driver.get(homePageUrl);

        // Nếu trong 1 hàm có 2 biến cùng tên (Global/Local)
        driver.get(this.homePageUrl);
    }

    @Test
    public void TC_02_Logo() {

    }

    @Test
    public void TC_03_Form() {

    }

    //public void  TC_04_Form(){
    //.....
    //}

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
