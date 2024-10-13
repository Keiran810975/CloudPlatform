package Utils;

import Utils.Judge.JudgeType;
import Global.UserList;

public class Login {
    public static boolean login(String[] arr){
        if(!Scan.isParamNum(arr,3)){
            System.out.println("Illegal argument count");
            return false;
        }

        String Id = arr[1];
        String password = arr[2];

        //学工号是否合法
        if(!JudgeType.isLegalId(Id)){
            System.out.println("Illegal user id");
            return false;
        }

        //用户是否注册
        if(!UserList.isUserExist(Id)){
            System.out.println("User does not exist");
            return false;
        }
        //用户是否已经登录
        if(UserList.isUserLogin(Id)){
            System.out.println(Id+" is online");
            return false;
        }
        //密码是否正确
        if(!UserList.userList.get(Id).getPassword().equals(password)){
            System.out.println("Wrong password");
            return false;
        }

        //登录成功

        UserList.loginList.put(Id,UserList.userList.get(Id));
        System.out.println("Welcome to ACP, "+Id);

        return true;
    }
}
