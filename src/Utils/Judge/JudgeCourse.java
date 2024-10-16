package Utils.Judge;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JudgeCourse {
    //是否是合法课程编号
    public static boolean isCourseId(String courseId){
        String regex = "C-\\d+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(courseId);
        return matcher.matches();
    }
    //是否是合法课程名字
    public static boolean isCourse(String courseName){
        String regex = "^[A-Za-z][A-Za-z0-9_-]{0,19}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(courseName);
        return matcher.matches();
    }
    //是否是合法时间
    public static boolean isTime(String courseTime){
        String regex = "^[0-7]_[1-9]|1[0-4]-[1-9]|1[0-4]$|^[0-7]_[1-9]|1[0-4]-[1-9]|1[0-4]$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(courseTime);
        return matcher.matches();
    }

    //合法学分
    public static boolean isCredit(String courseCredit){
        int credit=Integer.parseInt(courseCredit);
        if(credit>0&&credit<=12)return true;
        return false;
    }

    //合法学时
    public static boolean isPeriod(String coursePeriod){
        int period=Integer.parseInt(coursePeriod);
        if(period>0&&period<=1280)return true;
        return false;
    }
}
