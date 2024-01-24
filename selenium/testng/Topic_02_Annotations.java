package selenium.testng;


import org.testng.annotations.*;

public class Topic_02_Annotations {
    @BeforeClass
    public void beforeClass(){
        System.out.println("Before Class");
    }
    @AfterClass
    public void afterClass(){
        System.out.println("After Class");
    }
    @BeforeMethod
    public void beforMethod(){
        System.out.println("Before Method");
    }
    @AfterMethod
    public void afterMethod(){
        System.out.println("After Method");
    }
    @BeforeSuite
    public void beforeSuite(){
        System.out.println("Before Suite");
    }
    @AfterSuite
    public void afterSuite(){
        System.out.println("After Suite");
    }
    @BeforeTest
    public void beforeTest(){
        System.out.println("Before Test");
    }
    @AfterTest
    public void afterTest(){
        System.out.println("After Test");
    }
    @Test
    public void Test01(){
        System.out.println("Test 01");
    }
    @Test
    public void Test02(){
        System.out.println("Test 02");
    }
    @Test
    public void Test03(){
        System.out.println("Test 03");
    }
    @BeforeGroups
    public void beforeGroups(){
        System.out.println("Before Groups");
    }
    @AfterGroups
    public void afterGroups(){
        System.out.println("After Groups");
    }
}
