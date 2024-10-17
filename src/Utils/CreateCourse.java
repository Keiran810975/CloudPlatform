package Utils;
import Global.CourseId;
import Global.Status;
import Global.UserList;
import Models.Course;
import Models.Teacher;
import Models.User;
import Utils.Judge.JudgeCourse;
import Utils.Judge.JudgeType;

public class CreateCourse {
    public static boolean createCourse(String[] arr){

        if(!Scan.isParamNum(arr,5)){
            System.out.println("Illegal argument count");
            return false;
        }
        //当前无用户在线
        if(Status.currentUserId == null){
            System.out.println("No one is online ");
            return false;
        }
        //权限不是teacher
        if(!JudgeType.isTeacher(Status.currentUserId)){
            System.out.println("Permission denied");
            return false;
        }
        User user = UserList.userList.get(Status.currentUserId);
        Teacher teacher = (Teacher) user;
        //拥有课程达到上限
        if(teacher.getCourseNum()>=10){
            System.out.println("Course count reaches limit");
            return false;
        }
        String courseName=arr[1];
        String time=arr[2];
        String credit=arr[3];
        String period=arr[4];
        String str=time;
        String[] parts = str.split("_");
        int x = Integer.parseInt(parts[0]); // 解析
        String[] subParts = parts[1].split("-");
        int y = Integer.parseInt(subParts[0]); // 解析y
        int z = Integer.parseInt(subParts[1]); // 解析z
        //课程名称不合法
        if(!JudgeCourse.isCourse(courseName)){
            System.out.println("Illegal course name");
            return false;
        }
        //这个老师已经有了同名课程
        if(teacher.getCourses().containsKey(courseName)){
            System.out.println("Course name exists");
            return false;
        }
        //课程时间不合法
        if(!JudgeCourse.isTime(time)){
            System.out.println("Illegal course time");
            return false;
        }
        //课程时间冲突
        for(int i=y;i<=z;i++){
            if(teacher.getTimeTable()[x][i]==1){
                System.out.println("Course time conflicts");
                return false;
            }
        }

        //学分不合法
        if(!JudgeCourse.isCredit(credit)){
            System.out.println("Illegal course credit");
            return false;
        }
        //学时数不合法
        if(!JudgeCourse.isPeriod(period)){
            System.out.println("Illegal course period");
            return false;
        }

        //课程创建成功
        String Cid= CourseId.getCourseId();
        Course newCourse= new Course(Cid,courseName,time,credit,period,teacher.getName());
        teacher.getCourses().put(Cid,newCourse);
        teacher.getCourseArray().add(newCourse);
        //更新时间表
        for(int i=y;i<=z;i++){
            teacher.getTimeTable()[x][i]=1;
        }
        CourseId.courseList.add(newCourse);
        CourseId.courseStatus.put(Cid,newCourse);
        System.out.println("Create course success (courseId: "+Cid+")");
        return true;
    }
}
