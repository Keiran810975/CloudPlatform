package Utils;

import Global.CourseId;
import Global.Status;
import Global.UserList;
import Models.Course;
import Models.Student;
import Models.Teacher;
import Models.User;
import Utils.Judge.JudgeCourse;
import Utils.Judge.JudgeType;

public class cancelCourse {
    public static boolean cancelCourse(String[] arr){
        if(!Scan.isParamNum(arr,2)){
            System.out.println("Illegal argument count");
            return false;
        }
        //当前用户为空
        if(Status.currentUserId==null){
            System.out.println("No one is online");
            return false;
        }
        String courseId=arr[1];
        //课程编号不合法
        if(!JudgeCourse.isCourseId(courseId)){
            System.out.println("Illegal course id");
            return false;
        }
        //课程号不存在
        String userId=Status.currentUserId;
        if(JudgeType.isAdministrator(userId)){
            if(!CourseId.courseStatus.containsKey(courseId)){
                System.out.println("Course does not exist");
                return false;
            }
            //管理员课程注销成功
//            Course c=CourseId.courseStatus.get(courseId);
//            String t=c.getCourseTeacher();
            CourseId.courseStatus.remove(courseId);
            CourseId.courseList.remove(courseId);
            System.out.println("Cancel course success (courseId: "+courseId+")");
            return true;
        }else{
            if(!CourseId.courseStatus.containsKey(courseId)){
                System.out.println("Course does not exist");
                return false;
            }
            User user=UserList.userList.get(userId);
            if(JudgeType.isStudent(userId)){
                Student stu=(Student)user;
                if(!stu.getCourses().containsKey(courseId)){
                    System.out.println("Course does not exist");
                    return false;
                }
                //学生注销成功
                Course cc=CourseId.courseStatus.get(courseId);
                String str=cc.getCourseTime();
                String[] parts = str.split("_");
                int x = Integer.parseInt(parts[0]); // 解析
                String[] subParts = parts[1].split("-");
                int y = Integer.parseInt(subParts[0]); // 解析y
                int z = Integer.parseInt(subParts[1]); // 解析z
                for(int i=y;i<=z;i++){
                    stu.getTimeTable()[x][i]=0;
                }
                cc.getCourseStudents().remove(Status.currentUserId);//在这个课里移除这个学生
                stu.getCourses().remove(courseId);
                System.out.println("Cancel course success (courseId: "+courseId+")");
            }else{
                Teacher tea=(Teacher)user;
                if(!tea.getCourses().containsKey(courseId)){
                    System.out.println("Course does not exist");
                    return false;
                }
                //教师注销成功
                Course cc=CourseId.courseStatus.get(courseId);
                String str=cc.getCourseTime();
                String[] parts = str.split("_");
                int x = Integer.parseInt(parts[0]); // 解析
                String[] subParts = parts[1].split("-");
                int y = Integer.parseInt(subParts[0]); // 解析y
                int z = Integer.parseInt(subParts[1]); // 解析z
                for(int i=y;i<=z;i++){
                    tea.getTimeTable()[x][i]=0;
                }
                tea.setCourseNum(tea.getCourseNum()-1);
                tea.getCourses().remove(courseId);
                tea.getCourseArray().remove(courseId);
                CourseId.courseStatus.remove(courseId);
                CourseId.courseList.remove(courseId);
                System.out.println("Cancel course success (courseId: "+courseId+")");
                return true;
            }
        }

        return true;
    }
}
