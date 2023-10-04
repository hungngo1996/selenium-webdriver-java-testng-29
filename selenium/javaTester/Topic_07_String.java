package javaTester;

public class Topic_07_String {
    public static void main(String[] args) {
        String firstName = "Automation", LastName = "FC";
        String fullName = firstName + " " + LastName;
        System.out.println(fullName);
        fullName = firstName.concat(" ").concat(LastName);
        System.out.println(fullName);
        String Msg = "Welcome" +  " " + fullName + " " +"to International Hotel";
        System.out.println(Msg);
    }
}
