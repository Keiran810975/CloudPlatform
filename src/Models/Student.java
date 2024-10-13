package Models;

public class Student extends User {
    private String studentType;//本硕博:under/post/doc

    public Student(String id, String name, String password, String confirmPassword, String type, String studentType) {
        super(id, name, password, confirmPassword, type);
        this.studentType = studentType;
    }

    public String getStudentType() {
        return studentType;
    }

    public void setStudentType(String studentType) {
        this.studentType = studentType;
    }
}
