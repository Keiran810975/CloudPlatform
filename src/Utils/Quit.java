package Utils;

import Global.Status;
import Global.UserList;
import Models.User;

public class Quit {
    public static boolean quit(String[] arr) {
        if(!Scan.isParamNum(arr,1)){
            System.out.println("Illegal argument count");
            return false;
        }
//        String id;
//        if(Status.currentUserId!=null){
//            id = Status.currentUserId;
//            System.out.println(id+" Bye~");
//        }
        Status.currentUserId=null;
        for (String s : UserList.loginIdList) {
            if(UserList.isUserLogin(s)){
                System.out.println(s+" Bye~");
            }
        }
        UserList.loginIdList.clear();
        System.out.println("----- Good Bye! -----");
        return true;
    }
}
