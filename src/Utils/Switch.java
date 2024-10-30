package Utils;

import Global.Status;
import Global.UserList;
import Utils.Judge.JudgeType;

public class Switch {
    public static boolean switchUser(String[] arr) {
        if (!Scan.isParamNum(arr, 2)) {
            System.out.println("Illegal argument count");
            return false;
        }
        String id=arr[1];
        //学工号不合法
        if(!JudgeType.isLegalId(id)){
            System.out.println("Illegal user id");
            return false;
        }
        //用户不存在
        //用户是否注册
        if(!UserList.isUserExist(id)){
            System.out.println("User does not exist");
            return false;
        }
        //未登录
        if(!UserList.isUserLogin(id)){
            System.out.println(id+" is not online");
            return false;
        }
        //切换用户成功
        Status.currentUserId=id;
        System.out.println("Switch to "+id);
        return true;
    }
}
