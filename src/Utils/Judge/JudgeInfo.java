package Utils.Judge;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JudgeInfo {
    //判断是否是合法的名字
    public static boolean isName(String n) {
        String regex = "^[A-Za-z](\\w{3,15})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(n);
        return matcher.matches();
    }

    //判断是否是合法的密码
    public static boolean isPassword(String password) {
        String regex = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@_$%])[A-Za-z\\d@_$%]{6,16}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    //确认密码
    public static boolean isConfirmPassword(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }

    //判断参数数量是否合法
    public static boolean isLegal(int num, int min, int max) {
        return num >= min && num <= max;
    }

}
