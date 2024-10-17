package Global;

import Models.Course;

import java.util.*;

public class CourseId {
    public static int index=1;
    public static String getCourseId(){
        return "C-"+index++;
    }

    public static Map<String , Course> courseStatus=new HashMap<>();
    public static List<Course> courseList = new ArrayList<>();
    //打印课程信息
    public static void printCourses() {
        // 按教师姓名的字典序，再按课程编号的升序排序
        Collections.sort(courseList, new Comparator<Course>() {
            @Override
            public int compare(Course c1, Course c2) {
                // 先按老师姓名排序
                int teacherCompare = c1.getCourseTeacher().compareTo(c2.getCourseTeacher());
                if (teacherCompare != 0) {
                    return teacherCompare;
                }
                // 如果老师姓名相同，按课程号排序
                return c1.getCourseId().compareTo(c2.getCourseId());
            }
        });

        // 输出按顺序排列的课程信息
        for (Course course : courseList) {
            System.out.println(course.getCourseTeacher() +
                    " " + course.getCourseId() +
                    " " + course.getCourseName()+
                    " " + course.getCourseTime() +
                    " " + course.getCourseCredit() +
                    " " + course.getCoursePeriod());
        }
    }
}
