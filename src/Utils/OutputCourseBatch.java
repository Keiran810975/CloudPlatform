package Utils;

import Global.Status;
import Global.UserList;
import Models.Teacher;
import Utils.Judge.JudgeType;

import java.io.File;
import java.io.FileOutputStream;

public class OutputCourseBatch {
    public static boolean outputCourseBatch(String[] arr){
        if(!Scan.isParamNum(arr,2)){
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
        //导出成功
        String path= arr[1];
        String targetPath;

        // 判断路径前缀并设置目标路径
        if (path.startsWith("./")) {
            targetPath = "./data/"+path.substring(2);
        }else{
            targetPath = "./data/"+path;
        }
        Teacher t=(Teacher) UserList.userList.get(Status.currentUserId);
        t.serializeCourses(targetPath);
        System.out.println("Output course batch success");
        return true;
    }
}
