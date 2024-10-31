package Models;

import java.io.Serializable;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Student extends User implements Serializable {
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

    //打印课表
    public void printSortedCourses() {
        courses.values().stream()
                .sorted(Comparator.comparingInt((Course c) -> getDayOfWeek(c.getCourseTime())) // 按星期几排序
                        .thenComparingInt(c -> getStartClass(c.getCourseTime())))             // 按课程开始节次排序
                .forEach(c -> System.out.println(c.getCourseTime() + " " + c.getCourseName() + " " +
                        c.getCourseCredit() + " " + c.getCoursePeriod() + " " + c.getCourseTeacher()));
    }

    // 提取星期几
    private int getDayOfWeek(String courseTime) {
        String[] parts = courseTime.split("_");
        return Integer.parseInt(parts[0]);
    }

    // 提取开始的节次
    private int getStartClass(String courseTime) {
        Pattern pattern = Pattern.compile("_(\\d+)-");
        Matcher matcher = pattern.matcher(courseTime);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }
        return 0; // 如果格式有误，返回0
    }
}
