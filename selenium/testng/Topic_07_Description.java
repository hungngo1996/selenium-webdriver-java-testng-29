package selenium.testng;

import jdk.jfr.Description;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Description {

    @BeforeClass
    public void init(){
        System.out.println("Pre-condition");
    }
    @Test(description = "JIRA#64675 - Search new user")
    //Hiển thị trong Log/ Report HTML
    public void Priority_01_SearchWithDate(){

    }
    @Test
    public void Priority_02_SearchWithBilling(){

    }
    @Test
    public void Priority_03_SearchWithProduct(){

    }
    @AfterClass
    public void after(){
        System.out.println("Post-condition");
    }
}
