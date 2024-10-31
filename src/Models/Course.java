package Models;

import java.io.Serializable;
import java.util.*;

public class Course implements Serializable {
    private String courseId;//课程号
    private String courseName;
    private String courseTime;//课程时间
    private String courseCredit;//学分
    private String coursePeriod;//学时
    private String courseTeacher;
    private int courseMembers;//选课人数
    private Map<String, Student> courseStudents = new HashMap<>();//学号，学生
    private List<Student> courseStudentsArray = new ArrayList<>();//学生
    public void listStudents() {
        // 自定义排序规则
        courseStudentsArray.sort(Comparator.comparing((Student s) -> {
            String id = s.getId();
            if (id.startsWith("BY")) return 1;
            else if (id.startsWith("SY")) return 2;
            else if (id.startsWith("ZY")) return 3;
            else return 4;
        }).thenComparing(Student::getId)); // 优先级相同的情况下按字典序排序

        // 输出排序后的学生ID
        courseStudentsArray.stream()
                .filter(student -> courseStudents.containsKey(student.getId()))
                .forEach(student -> System.out.println(student.getId()+": "+student.getName()));
    }

    public List<Student> getCourseStudentsArray() {
        return courseStudentsArray;
    }

    public void setCourseStudentsArray(List<Student> courseStudentsArray) {
        this.courseStudentsArray = courseStudentsArray;
    }

    public Map<String, Student> getCourseStudents() {
        return courseStudents;
    }

    public void setCourseStudents(Map<String, Student> courseStudents) {
        this.courseStudents = courseStudents;
    }

    public int getCourseMembers() {
        return courseMembers;
    }

    public void setCourseMembers(int courseMembers) {
        this.courseMembers = courseMembers;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(String courseTime) {
        this.courseTime = courseTime;
    }

    public String getCourseCredit() {
        return courseCredit;
    }

    public void setCourseCredit(String courseCredit) {
        this.courseCredit = courseCredit;
    }

    public String getCoursePeriod() {
        return coursePeriod;
    }

    public void setCoursePeriod(String coursePeriod) {
        this.coursePeriod = coursePeriod;
    }

    public String getCourseTeacher() {
        return courseTeacher;
    }

    public void setCourseTeacher(String courseTeacher) {
        this.courseTeacher = courseTeacher;
    }

    public Course(String courseId, String courseName, String courseTime, String courseCredit, String coursePeriod, String teacher) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseTime = courseTime;
        this.courseCredit = courseCredit;
        this.coursePeriod = coursePeriod;
        this.courseTeacher = teacher;
    }
}
