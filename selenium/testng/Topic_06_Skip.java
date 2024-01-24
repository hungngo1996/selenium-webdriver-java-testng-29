package selenium.testng;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Skip {

    @BeforeClass
    public void init(){
        System.out.println("Pre-condition");
    }
    @Test
    public void Priority_01_SearchWithDate(){

    }
    @Test (enabled = false)
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
