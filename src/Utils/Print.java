package Utils;
import Global.Status;
import Models.User;
import Global.UserList;
import Utils.Judge.JudgeType;
public class Print {
    public static boolean printInfo(String[] arr){
        if(!((Scan.isParamNum(arr,2))||(Scan.isParamNum(arr,1)))){
            System.out.println("Illegal argument count");
            return false;
        }
        //无参数,打印当前用户信息
        if(Scan.isParamNum(arr,1)){
            if(Status.currentUserId==null){
                System.out.println("No one is online");
                return false;
            }
            User user = UserList.userList.get(Status.currentUserId);
            System.out.println(user.toString());
            return true;
        }
        //有参数
        if(Scan.isParamNum(arr,2)){
            String id=arr[1];
            if(!JudgeType.isAdministrator(id)){
                System.out.println("Permission denied");
                return false;
            }
            if(!JudgeType.isLegalId(id)){
                System.out.println("Illegal user id");
                return false;
            }
            if(!UserList.isUserExist(id)){
                System.out.println("User does not exist");
                return false;
            }
            User user = UserList.userList.get(id);
            System.out.println(user.toString());
            return true;
        }
        return true;
    }
}
