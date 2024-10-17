package Utils.Judge;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JudgeCourse {
    //是否是合法课程编号
    public static boolean isCourseId(String courseId){
        String regex = "^C-[1-9]\\d*$";
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
        String regex = "^[1-7]_(1[0-4]|[1-9])-(1[0-4]|[1-9])$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(courseTime);

        // 检查是否符合基本格式
        if (matcher.matches()) {
            // 提取星期和时间
            String[] parts = courseTime.split("_");
            int weekDay = Integer.parseInt(parts[0]); // 星期
            String[] times = parts[1].split("-");
            int startTime = Integer.parseInt(times[0]); // 开始时间
            int endTime = Integer.parseInt(times[1]);   // 结束时间

            // 检查 X ≤ Y
            return startTime <= endTime;
        }

        return false; // 格式不匹配
    }

    //合法学分
    public static boolean isCredit(String courseCredit) {
        try {
            double credit = Double.parseDouble(courseCredit); // 使用 Double 解析
            return credit > 0 && credit <= 12; // 检查学分是否在有效范围内
        } catch (NumberFormatException e) {
            return false; // 如果无法解析，返回 false
        }
    }


    //合法学时
    public static boolean isPeriod(String coursePeriod){
        int period=Integer.parseInt(coursePeriod);
        if(period>0&&period<=1280)return true;
        return false;
    }
}
