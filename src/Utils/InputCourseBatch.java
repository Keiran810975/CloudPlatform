package Utils;

import Global.CourseId;
import Global.Status;
import Global.UserList;
import Models.Course;
import Models.Teacher;
import Utils.Judge.JudgeType;

import java.io.*;

public class InputCourseBatch {
    public static boolean inputCourseBatch(String[] arr) {
        if (!Scan.isParamNum(arr, 2)) {
            System.out.println("Illegal argument count");
            return false;
        }
        //当前无用户在线
        if(Status.currentUserId == null){
            System.out.println("No one is online");
            return false;
        }
        //用户身份不是teacher
        if(!JudgeType.isTeacher(Status.currentUserId)){
            System.out.println("Permission denied");
            return false;
        }
        //文件路径对应的文件不存在
        String path=arr[1];
        String targetPath;
        if (path.startsWith("./")) {
            targetPath = "src/data/"+path.substring(2);
        }else{
            targetPath = "src/data/"+path;
        }
        File file = new File(targetPath);
        if(!file.exists()){
            System.out.println("File does not exist");
            return false;
        }
        //文件路径对应的文件是目录
        if(file.isDirectory()){
            System.out.println("File is a directory");
            return false;
        }

        Teacher t=(Teacher)UserList.userList.get(Status.currentUserId);
        //反序列化
        try (FileInputStream fileIn = new FileInputStream(targetPath);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {

            while (true) {
                try {
                    // 逐个读取 Course 对象
                    Course cc = (Course) in.readObject();
                    //课程数量已达上限
                    if(t.getCourseNum()>=10){
                        System.out.println("Course count reaches limit");
                        break;
                    }
                    //课程名字已存在
                    if(CourseId.courseStatus.containsKey(cc.getCourseName())){
                        System.out.println("Course name already exists");
                        continue;
                    }
                    //课程时间冲突
                    String str=cc.getCourseTime();
                    String[] parts = str.split("_");
                    int x = Integer.parseInt(parts[0]); // 解析
                    String[] subParts = parts[1].split("-");
                    int y = Integer.parseInt(subParts[0]); // 解析y
                    int z = Integer.parseInt(subParts[1]); // 解析z
                    //课程时间冲突
                    boolean sig=false;
                    for(int i=y;i<=z;i++){
                        if(t.getTimeTable()[x][i]==1){
                            System.out.println("Course time conflicts");
                            sig=true;
                            break;
                        }
                    }
                    if(sig)continue;
                    //导入课程成功
                    String newCourseId=CourseId.getCourseId();
                    cc.setCourseId(newCourseId);
                    t.getCourses().put(newCourseId,cc);
                    t.getCourseArray().add(cc);
                    for(int i=y;i<=z;i++){
                        t.getTimeTable()[x][i]=1;//更新时间表
                    }
                    CourseId.courseList.add(cc);
                    CourseId.courseStatus.put(newCourseId,cc);
                    System.out.println("Create course success (courseId: "+newCourseId+")");

                } catch (EOFException e) {
                    // 到达文件末尾，退出循环
                    break;
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Input course batch success");
        return true;
    }

}
