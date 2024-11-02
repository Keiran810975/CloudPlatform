package Utils;

import Global.CourseId;
import Global.Status;
import Global.UserList;
import Models.Course;
import Models.Teacher;
import Utils.Judge.JudgeCourse;
import Utils.Judge.JudgeType;

public class ListStudent {
    public static boolean listStudent(String[] arr){
        if(!Scan.isParamNum(arr,2)){
            System.out.println("Illegal argument count");
            return false;
        }
        //当前无用户在线
        if(Status.currentUserId == null){
            System.out.println("No one is online");
            return false;
        }
        //当前用户不是老师或者管理员
        if(!(JudgeType.isTeacher(Status.currentUserId)||JudgeType.isAdministrator(Status.currentUserId))){
            System.out.println("Permission denied");
            return false;
        }
        //课程号不合法
        String courseId = arr[1];
        if(!JudgeCourse.isCourseId(courseId)){
            System.out.println("Illegal course id");
            return false;
        }
        //课程不存在
        if(JudgeType.isAdministrator(Status.currentUserId)){
            //课程编号未注册或已注销
            if(!CourseId.courseStatus.containsKey(courseId)){
                System.out.println("Course does not exist");
                return false;
            }
        }else{
            //课程编号未注册或已注销或者该课程号不属于老师
            Teacher t=(Teacher) UserList.userList.get(Status.currentUserId);
            if((!CourseId.courseStatus.containsKey(courseId))||!t.getCourses().containsKey(courseId)){
                System.out.println("Course does not exist");
                return false;
            }
        }
        //无选课学生
        Course cc=CourseId.courseStatus.get(courseId);
        if(cc.getCourseStudents().isEmpty()){
            System.out.println("Student does not select course");
            return false;
        }

        //成功输出
        cc.listStudents2();
        System.out.println("List student success");

        return true;
    }
}
