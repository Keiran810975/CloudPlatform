package Models;

import java.util.*;

public class Teacher extends User{
    private Map<String,Course> courses = new HashMap<>();//课程号，课程
    private List<Course> courseArray = new ArrayList<>();//课程号，课程>
    //private Set<Course> courseArray = new LinkedHashSet<>();//课程号
    private int courseNum = 0;
    public void printCourseArray() {
        // 按课程编号升序排序
        Collections.sort(courseArray, new Comparator<Course>() {
            @Override
            public int compare(Course c1, Course c2) {
                return c1.getCourseId().compareTo(c2.getCourseId());
            }
        });

        // 打印课程信息：课程号、课程名称、课程时间、学分、学时
        for (Course course : courseArray) {
            System.out.println("Course ID: " + course.getCourseId() +
                    ", Course Name: " + course.getCourseName() +
                    ", Time: " + course.getCourseTime() +
                    ", Credit: " + course.getCourseCredit() +
                    ", Period: " + course.getCoursePeriod());
        }
    }

    public int[][] getTimeTable() {
        return timeTable;
    }

    public void setTimeTable(int[][] timeTable) {
        this.timeTable = timeTable;
    }

    private int[][] timeTable= new int[8][15];//课程时间表

    public List<Course> getCourseArray() {
        return courseArray;
    }

    public void setCourseArray(List<Course> courseArray) {
        this.courseArray = courseArray;
    }

    public Teacher(String id, String name, String password, String confirmPassword, String type) {
        super(id, name, password, confirmPassword, type);
    }

    public Map<String, Course> getCourses() {
        return courses;
    }

    public void setCourses(Map<String, Course> courses) {
        this.courses = courses;
    }

    public int getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(int courseNum) {
        this.courseNum = courseNum;
    }
}
