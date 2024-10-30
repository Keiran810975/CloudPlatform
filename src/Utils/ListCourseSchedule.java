package Utils;

import Global.Status;
import Global.UserList;
import Models.Student;
import Utils.Judge.JudgeType;

public class ListCourseSchedule {
    public static boolean listCourseSchedule(String[] arr){
        if(!((Scan.isParamNum(arr,2))||(Scan.isParamNum(arr,1)))){
            System.out.println("Illegal argument count");
            return false;
        }
        //无用户在线
        if(Status.currentUserId==null){
            System.out.println("No one is online");
            return false;
        }

        //无参数
        if(Scan.isParamNum(arr,1)){
            //权限不为student
            if(!JudgeType.isStudent(Status.currentUserId)){
                System.out.println("Permission denied");
                return false;
            }
            //学生没选课
            Student stu= (Student) UserList.userList.get(Status.currentUserId);
            if(stu.getCourses().isEmpty()){
                System.out.println("Student does not select course");
                return false;
            }
            //查看自己的课表
            stu.printSortedCourses();
            System.out.println("List course schedule success");
            return true;
        }else{
            //有参数
            //权限不为administrator
            if(!JudgeType.isAdministrator(Status.currentUserId)){
                System.out.println("Permission denied");
                return false;
            }
            //学工号不合法
            String id=arr[1];
            if(!JudgeType.isLegalId(id)){
                System.out.println("Illegal user id");
                return false;
            }
            //学工号对应的用户不存在
            if(!UserList.isUserExist(id)){
                System.out.println("User does not exist");
                return false;
            }
            // 学工号对应的用户不是学生
            if(!JudgeType.isStudent(id)){
                System.out.println("User id does not belong to a Student");
                return false;
            }
            //学生尚未选课
            Student stu= (Student) UserList.userList.get(id);
            if(stu.getCourses().isEmpty()){
                System.out.println("Student does not select course");
                return false;
            }
            //查看课表成功
            stu.printSortedCourses();
            System.out.println("List course schedule success");

        }

        return true;
    }
}
