package Utils.Judge;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
//用来存判断一个用户是否是学生，老师，管理员的方法
public class JudgeType {
    //判断身份类型是否合法
    public static boolean isType(String type){
        if(type.equals("Student")||type.equals("Teacher")||type.equals("Administrator")){
            return true;
        }
        return false;
    }

    //判断是否是本科生
    public static boolean isStudentUnder(String id){
        String regexStu1 = "^(19|20|21|22|23|24)(0[1-9]|[1-3][0-9]|4[0-3])([1-6])(00[1-9]|0[1-9][0-9]|[1-9][0-9]{2})$";
        Pattern pattern = Pattern.compile(regexStu1);
        Matcher matcher = pattern.matcher(id);
        return matcher.matches();
    }
    //判断是否是硕士生
    public static boolean isStudentPost(String id){
        String regexStu2 = "^(SY|ZY)(21|22|23|24)(0[1-9]|[1-3][0-9]|4[0-3])([1-6])(0[1-9]|[1-9][0-9])$";
        Pattern pattern = Pattern.compile(regexStu2);
        Matcher matcher = pattern.matcher(id);
        return matcher.matches();
    }
    //判断是否是博士生
    public static boolean isStudentDoc(String id){
        String regexStu3 = "^BY(19|20|21|22|23|24)(0[1-9]|[1-3][0-9]|4[0-3])([1-6])(0[1-9]|[1-9][0-9])$";
        Pattern pattern = Pattern.compile(regexStu3);
        Matcher matcher = pattern.matcher(id);
        return matcher.matches();
    }
    //是否是学生
    public static boolean isStudent(String id){
        return isStudentUnder(id)||isStudentPost(id)||isStudentDoc(id);
    }

    //是否是老师
    public static boolean isTeacher(String id){
        String regexTeacher = "^(0000[1-9]|000[1-9][0-9]|00[1-9][0-9]{2}|0[1-9][0-9]{3}|[1-9][0-9]{4})$";
        Pattern pattern = Pattern.compile(regexTeacher);
        Matcher matcher = pattern.matcher(id);
        return matcher.matches();
    }

    //是否是管理员
    public static boolean isAdministrator(String id){
        String regexAdministrator = "^AD(00[1-9]|0[1-9][0-9]|[1-9][0-9]{2})$";
        Pattern pattern = Pattern.compile(regexAdministrator);
        Matcher matcher = pattern.matcher(id);
        return matcher.matches();
    }
    //判断学工号是否合法
    public static    boolean isLegalId(String id){
        return isStudent(id)||isTeacher(id)||isAdministrator(id);
    }
}
