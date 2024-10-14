package Utils;
import Global.UserList;
import Global.Status;
import Utils.Judge.JudgeType;
public class Logout {
    public static boolean logout(String[] arr){
        if(!(Scan.isParamNum(arr,2))||(Scan.isParamNum(arr,1))){
            System.out.println("Illegal argument count");
            return false;
        }
        //如果无参数
        if(Scan.isParamNum(arr,1)){
            if(Status.currentUserId==null){
                System.out.println("No one is online");
                return false;
            }
            String Id=Status.currentUserId;
            System.out.println(Id+" Bye~");
            Status.currentUserId=null;
            return true;
        }
        if(Scan.isParamNum(arr,2)){
            String Id= arr[1];
            //如果不是管理员身份
            if(!JudgeType.isAdministrator(Id)){
                System.out.println("Permission denied");
                return false;
            }
            //学工号不合法
            if(!JudgeType.isLegalId(Id)){
                System.out.println("Illegal user id");
                return false;
            }
            //不存在
            if(!UserList.isUserExist(Id)){
                System.out.println("User does not exist");
                return false;
            }
            //不在线
            if(!UserList.isUserLogin(Id)){
                System.out.println(Id+" is not online");
                return false;
            }
            System.out.println(Id+" Bye~");
            Status.currentUserId=null;
            return true;
        }

        return true;
    }
}
