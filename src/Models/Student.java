package Models;

import java.util.HashMap;
import java.util.Map;

public class Student extends User {
    private Map<String,Course> courses = new HashMap<>();
    private String studentType;//本硕博:under/post/doc
    private int[][] timeTable= new int[8][15];//课程时间表

    public Map<String, Course> getCourses() {
        return courses;
    }

    public void setCourses(Map<String, Course> courses) {
        this.courses = courses;
    }

    public int[][] getTimeTable() {
        return timeTable;
    }

    public void setTimeTable(int[][] timeTable) {
        this.timeTable = timeTable;
    }

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
