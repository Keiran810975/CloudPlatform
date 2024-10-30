package Global;
import java.util.HashMap;
import java.util.Map;

public class Commends {
    public static final String QUIT= "quit";
    public static final String REGISTER = "register";
    public static final String LOGIN = "login";
    public static final String LOGOUT = "logout";
    public static final String PRINTINFO = "printInfo";//打印用户信息
    public static final String CREATECOURSE = "createCourse";//创建课程
    public static final String LISTCOURSE = "listCourse";//查看课程
    public static final String SELECTCOURSE = "selectCourse";//选课
    public static final String CANCELCOURSE = "cancelCourse";//住校课程
    public static final String SWITCH = "switch";//切换用户
    public static final String INPUTCOURSEBATCH = "inputCourseBatch";//批量导入课程
    public static final String OUTPUTCOURSEBATCH = "outputCourseBatch";//批量导出课程
    public static final String LISTSTUDENT = "listStudent";//查看选课学生
    public static final String REMOVESTUDENT = "removeStudent";//移除选课学生
    public static final String LISTCOURSESCHEDULE = "listCourseSchedule";//查看课表


    //创建一个map，指令为键，对应的值为1
    public static final Map<String, Integer> commends = new HashMap<String, Integer>(){{
        put(QUIT,1);
        put(REGISTER,1);
        put(LOGIN,1);
        put(LOGOUT,1);
        put(PRINTINFO,1);
        put(CREATECOURSE,1);
        put(LISTCOURSE,1);
        put(SELECTCOURSE,1);
        put(CANCELCOURSE,1);
        put(SWITCH,1);
        put(INPUTCOURSEBATCH,1);
        put(OUTPUTCOURSEBATCH,1);
        put(LISTSTUDENT,1);
        put(REMOVESTUDENT,1);
        put(LISTCOURSESCHEDULE,1);
    }};
}
