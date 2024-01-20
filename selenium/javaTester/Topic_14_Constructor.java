package javaTester;

public class Topic_14_Constructor {
    // Là 1 cái hàm cùng tên vs class
    // không có kiểu dữ liệu trả về
    // Trong 1 class có thể có nhiều Constructor
    // 1 class nếu không define 1 Constructor cụ thể thì nó sẽ có một Constructor rỗng (default)
    // Nếu mình define thì khi khởi tạo bắt buộc phải gọi tới Constructor mà mình define

    public Topic_14_Constructor (String name){
        System.out.println("name = " + name);
    }
    public Topic_14_Constructor (String name, int student){
        System.out.println("name = " + name + ", student = " + student);
    }
    public static void main(String[] args) {
        Topic_14_Constructor topic01 = new Topic_14_Constructor("Automation FC");
        Topic_14_Constructor topic02 = new Topic_14_Constructor("Automation FC", 14);
    }
}
