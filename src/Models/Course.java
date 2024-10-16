package Models;

public class Course {
    private String courseId;//课程号
    private String courseName;
    private String courseTime;//课程时间
    private String courseCredit;//学分
    private String coursePeriod;//学时
    private String courseTeacher;

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
