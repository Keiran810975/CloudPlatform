package Utils;

import Global.Status;
import Global.UserList;
import Models.Course;
import Models.Student;
import Models.Teacher;
import Models.User;
import Utils.Judge.JudgeCourse;
import Utils.Judge.JudgeType;
import Global.CourseId;

public class chooseCourse {
    public static boolean chooseCourse(String[] arr){
        if(!Scan.isParamNum(arr,2)){
            System.out.println("Illegal argument count");
            return false;
        }

        //当前无用户在线
        if(Status.currentUserId == null){
            System.out.println("No one is online");
            return false;
        }
        String studentId=Status.currentUserId;
        //权限不为 Student
        if(!JudgeType.isStudent(studentId)){
            System.out.println("Permission denied");
            return false;
        }
        String courseId=arr[1];
        //课程编号不合法
        if(!JudgeCourse.isCourseId(courseId)){
            System.out.println("Illegal course id");
            return false;
        }
        //课程不存在或已注销
        if(!CourseId.courseStatus.containsKey(courseId)){
            System.out.println("Course does not exist");
            return false;
        }
        //课程时间冲突
        User user = UserList.userList.get(Status.currentUserId);
        Student stu=(Student)user;
        Course cc=CourseId.courseStatus.get(courseId);
        String str=cc.getCourseTime();
        String[] parts = str.split("_");
        int x = Integer.parseInt(parts[0]); // 解析
        String[] subParts = parts[1].split("-");
        int y = Integer.parseInt(subParts[0]); // 解析y
        int z = Integer.parseInt(subParts[1]); // 解析z
        for(int i=y;i<=z;i++){
            if(stu.getTimeTable()[x][i]==1){
                System.out.println("Course time conflicts");
                return false;
            }
        }

        //已选课程人数大于等于30人
        if(cc.getCourseMembers()>=30){
            System.out.println("Course capacity is full");
            return false;
        }
        //选课成功
        cc.setCourseMembers(cc.getCourseMembers()+1);
        cc.getCourseStudents().put(stu.getId(),stu);
        cc.getCourseStudentsArray().add(stu);
        for(int i=y;i<=z;i++){
            stu.getTimeTable()[x][i]=1;
        }
        stu.getCourses().put(courseId,cc);
        System.out.println("Select course success (courseId: "+courseId+")");
        

        return true;
    }
}
