package javaTester;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class Topic_09_List {

    @Test
    public void testList(){
        List<String> student = new ArrayList<String>();
        student.add("A");
        System.out.println(student.size());
        System.out.println(student.get(0));
    }
}
