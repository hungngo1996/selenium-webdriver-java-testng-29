package selenium.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_03_System_Info {

    public static void main(String args[]){
        String OsName = System.getProperty("os.name");
        System.out.println(OsName);
    }
}
