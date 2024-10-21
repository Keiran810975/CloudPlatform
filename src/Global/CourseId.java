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

                // 如果老师姓名相同，按课程号中的数字部分排序
                String courseId1 = c1.getCourseId().replaceAll("[^0-9]", ""); // 提取数字部分
                String courseId2 = c2.getCourseId().replaceAll("[^0-9]", "");

                // 如果课程号中没有数字部分，按原字符串进行比较
                if (courseId1.isEmpty() || courseId2.isEmpty()) {
                    return c1.getCourseId().compareTo(c2.getCourseId());
                }

                // 将字符串转换为整数并比较
                return Integer.parseInt(courseId1) - Integer.parseInt(courseId2);
            }
        });

        // 输出按顺序排列的课程信息
        for (Course course : courseList) {
            String id=course.getCourseId();
            if(courseStatus.containsKey(id)){
                System.out.print(course.getCourseTeacher() +
                        " " + course.getCourseId() +
                        " " + course.getCourseName()+
                        " " + course.getCourseTime() +
                        " ");
                double number = Double.parseDouble(course.getCourseCredit());
                System.out.printf("%.1f ", number);
                System.out.println(course.getCoursePeriod());
            }
        }
    }
}
