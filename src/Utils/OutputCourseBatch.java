package Utils;

import Global.Status;
import Utils.Judge.JudgeType;

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

        return true;
    }
}
