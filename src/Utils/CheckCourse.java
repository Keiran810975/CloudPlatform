package Utils;

import Global.CourseId;
import Global.Status;
import Global.UserList;
import Models.Teacher;
import Models.User;
import Utils.Judge.JudgeType;
import java.util.HashMap;


public class CheckCourse {
    public static boolean checkCourse(String[] arr){
        if(!((Scan.isParamNum(arr,2))||(Scan.isParamNum(arr,1)))){
            System.out.println("Illegal argument count");
            return false;
        }
        //无用户在线
        if(Status.currentUserId==null){
            System.out.println("No one is online");
            return false;
        }
        String userId= Status.currentUserId;
        //无可选参数
        if(Scan.isParamNum(arr,1)){
            //如果是学生或管理员
            if(JudgeType.isAdministrator(userId)||JudgeType.isStudent(userId)){
                if(CourseId.courseList.isEmpty()){
                    System.out.println("Course does not exist");
                    return false;
                }
                //打印所有课程信息
                CourseId.printCourses();
                System.out.println("List course success");
                return true;
            }
            //如果是老师
            else if(JudgeType.isTeacher(userId)){
                User user = UserList.userList.get(Status.currentUserId);
                Teacher teacher = (Teacher) user;
                if(teacher.getCourseArray().isEmpty()){
                    System.out.println("Course does not exist");
                    return false;
                }
                //打印老师所教授的课程信息
                teacher.printCourseArray();
                System.out.println("List course success");
                return true;
            }
        }
        //有可选参数
        else{
            //如果不是管理员
            if(!JudgeType.isAdministrator(userId)){
                System.out.println("Permission denied");
                return false;
            }
            String checkId = arr[1];
            //学工号不合法
            if(!JudgeType.isLegalId(checkId)){
                System.out.println("Illegal user id");
                return false;
            }
            //用户未注册
            if(!UserList.isUserExist(checkId)){
                System.out.println("User does not exist");
                return false;
            }
            //工号对应的用户权限不为 Teacher
            if(!JudgeType.isTeacher(checkId)){
                System.out.println("User id does not belong to a Teacher");
                return false;
            }
            User user = UserList.userList.get(checkId);
            Teacher teacher = (Teacher) user;
            //工号对应的老师名下无课程
            if(teacher.getCourseArray().isEmpty()){
                System.out.println("Course does not exist");
                return false;
            }
            //打印老师所教授的课程信息
            teacher.printCourseArray2();
            System.out.println("List course success");
            return  true;
        }

        return true;
    }
}
