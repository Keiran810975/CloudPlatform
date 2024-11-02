package Utils;

import Global.CourseId;
import Global.Status;
import Global.UserList;
import Models.Course;
import Models.Student;
import Models.Teacher;
import Utils.Judge.JudgeCourse;
import Utils.Judge.JudgeType;

import java.util.Iterator;

public class RemoveStudent {
    public static boolean removeStudent(String[] arr){
        if(!((Scan.isParamNum(arr,2))||(Scan.isParamNum(arr,3)))){
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
        //学号不合法
        String id=arr[1];
        if(!JudgeType.isLegalId(id)){
            System.out.println("Illegal user id");
            return false;
        }
        //学号对应的用户未注册
        if(!UserList.isUserExist(id)){
            System.out.println("User does not exist");
            return false;
        }
        //学号对应的用户身份不是Student
        if(!JudgeType.isStudent(id)){
            System.out.println("User id does not belong to a Student");
            return false;
        }

        //一个参数
        if(Scan.isParamNum(arr,2)){
            //如果是老师
            Student stu= (Student) UserList.userList.get(id);
            if(JudgeType.isTeacher(Status.currentUserId)){
                Teacher t= (Teacher) UserList.userList.get(Status.currentUserId);
                //如果学生没有选自己的课
                boolean sig=false;
                for (String courseId : stu.getCourses().keySet()) {
                    if(t.getCourses().containsKey(courseId)){
                        sig=true;
                        break;
                    }
                }
                if(!sig){
                    System.out.println("Student does not select course");
                    return false;
                }
                //移除成功(老师从自己的所有课程中移除该学生)
//                for (String courseId : stu.getCourses().keySet()){
//                    if(t.getCourses().containsKey(courseId)){
//                        stu.getCourses().remove(courseId);
//                    }
//                }
                Iterator<Course> iterator1 = t.getCourses().values().iterator();
                while(iterator1.hasNext()){
                    Course c=iterator1.next();
                    if(c.getCourseStudents().containsKey(id)){
                        String str=c.getCourseTime();
                        String[] parts = str.split("_");
                        int x = Integer.parseInt(parts[0]); // 解析
                        String[] subParts = parts[1].split("-");
                        int y = Integer.parseInt(subParts[0]); // 解析y
                        int z = Integer.parseInt(subParts[1]); // 解析z
                        for(int i=y;i<=z;i++){
                            stu.getTimeTable()[x][i]=0;
                        }
                        c.getCourseStudents().remove(id);
                        c.setCourseMembers(c.getCourseMembers()-1);
                    }
                }
                Iterator<String> iterator = stu.getCourses().keySet().iterator();
                while (iterator.hasNext()) {
                    String courseId = iterator.next();
                    if (t.getCourses().containsKey(courseId)) {
                        iterator.remove(); // 使用迭代器的 remove 方法
                    }
                }
                System.out.println("Remove student success");
                return true;
            }else{
                //管理员
                //学生没选过课
                if(stu.getCourses().isEmpty()){
                    System.out.println("Student does not select course");
                    return false;
                }
                //移除成功(所有课程移除该学生)
                Iterator<Course> iterator = CourseId.courseStatus.values().iterator();
                while (iterator.hasNext()) {
                    Course c = iterator.next();
                    if(c.getCourseStudents().containsKey(id)){
                        String str=c.getCourseTime();
                        String[] parts = str.split("_");
                        int x = Integer.parseInt(parts[0]); // 解析
                        String[] subParts = parts[1].split("-");
                        int y = Integer.parseInt(subParts[0]); // 解析y
                        int z = Integer.parseInt(subParts[1]); // 解析z
                        for(int i=y;i<=z;i++){
                            stu.getTimeTable()[x][i]=0;
                        }
                        c.getCourseStudents().remove(id);
                        c.setCourseMembers(c.getCourseMembers()-1);
                    }
                }
                stu.getCourses().clear();
                System.out.println("Remove student success");
                return true;
            }

        }else{
            //两个参数
            //课程号不合法
            String courseId=arr[2];
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
                Teacher t= (Teacher) UserList.userList.get(Status.currentUserId);
                if(!CourseId.courseStatus.containsKey(courseId)||!t.getCourses().containsKey(courseId)){
                    System.out.println("Course does not exist");
                    return false;
                }
            }
            // 学生未选择该课程
            Student stu= (Student) UserList.userList.get(id);
            if(!stu.getCourses().containsKey(courseId)){
                System.out.println("Student does not select course");
                return false;
            }

            //移除成功

            stu.getCourses().remove(courseId);
            Course c=CourseId.courseStatus.get(courseId);
            String str=c.getCourseTime();
            String[] parts = str.split("_");
            int x = Integer.parseInt(parts[0]); // 解析
            String[] subParts = parts[1].split("-");
            int y = Integer.parseInt(subParts[0]); // 解析y
            int z = Integer.parseInt(subParts[1]); // 解析z
            for(int i=y;i<=z;i++){
                stu.getTimeTable()[x][i]=0;
            }
            c.getCourseStudents().remove(id);
            c.setCourseMembers(c.getCourseMembers()-1);
            System.out.println("Remove student success");
            return true;
        }

        //return true;
    }
}
