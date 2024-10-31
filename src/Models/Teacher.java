package Models;

import Global.CourseId;

import java.io.*;
import java.util.*;

public class Teacher extends User implements Serializable {
    private Map<String,Course> courses = new HashMap<>();//课程号，课程
    private List<Course> courseArray = new ArrayList<>();//课程号，课程>
    //private Set<Course> courseArray = new LinkedHashSet<>();//课程号
    private int courseNum = 0;
    public void printCourseArray() {
        // 按课程编号升序排序
        Collections.sort(courseArray, new Comparator<Course>() {
            @Override
            public int compare(Course c1, Course c2) {
                // 提取课程编号中的数字部分
                String courseId1 = c1.getCourseId().replaceAll("[^0-9]", ""); // 提取数字部分
                String courseId2 = c2.getCourseId().replaceAll("[^0-9]", "");

                // 如果提取出的数字部分为空，保持原始字符串比较
                if (courseId1.isEmpty() || courseId2.isEmpty()) {
                    return c1.getCourseId().compareTo(c2.getCourseId());
                }

                // 将字符串转换为整数并进行比较
                return Integer.parseInt(courseId1) - Integer.parseInt(courseId2);
            }
        });

        // 打印课程信息：课程号、课程名称、课程时间、学分、学时
        for (Course course : courseArray) {
            String id=course.getCourseId();
            if(CourseId.courseStatus.containsKey(id)){
                System.out.print(
                        course.getCourseId() +
                        " " + course.getCourseName()+
                        " " + course.getCourseTime() +
                        " ");
                double number = Double.parseDouble(course.getCourseCredit());
                System.out.printf("%.1f ", number);
                System.out.println(course.getCoursePeriod());
            }
        }
    }
    public void printCourseArray2() {
        // 按课程编号升序排序
        Collections.sort(courseArray, new Comparator<Course>() {
            @Override
            public int compare(Course c1, Course c2) {
                // 提取课程编号中的数字部分
                String courseId1 = c1.getCourseId().replaceAll("[^0-9]", ""); // 提取数字部分
                String courseId2 = c2.getCourseId().replaceAll("[^0-9]", "");

                // 如果提取出的数字部分为空，保持原始字符串比较
                if (courseId1.isEmpty() || courseId2.isEmpty()) {
                    return c1.getCourseId().compareTo(c2.getCourseId());
                }

                // 将字符串转换为整数并进行比较
                return Integer.parseInt(courseId1) - Integer.parseInt(courseId2);
            }
        });

        // 打印课程信息：课程号、课程名称、课程时间、学分、学时
        for (Course course : courseArray) {
            String id=course.getCourseId();
            if(CourseId.courseStatus.containsKey(id)){
                System.out.print(course.getCourseTeacher()+
                        " "+course.getCourseId() +
                                " " + course.getCourseName()+
                                " " + course.getCourseTime() +
                                " ");
                double number = Double.parseDouble(course.getCourseCredit());
                System.out.printf("%.1f ", number);
                System.out.println(course.getCoursePeriod());
            }
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

    public void serializeCourses(String path) {
        // 创建目标文件对象
        File targetFile = new File(path);
        // 获取父目录并创建
        File parentDir = targetFile.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();  // 创建所有必要的父目录
        }

        // 开始序列化
        try (FileOutputStream fileOut = new FileOutputStream(targetFile);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {

            // 遍历courseArray并检查课程是否在courses哈希表中
            for (Course course : courseArray) {
                if (courses.containsKey(course.getCourseId())) {
                    out.writeObject(course);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
