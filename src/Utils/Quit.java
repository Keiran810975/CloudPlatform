package Utils;

import Global.Status;

public class Quit {
    public static boolean quit(String[] arr) {
        if(!Scan.isParamNum(arr,1)){
            System.out.println("Illegal argument count");
            return false;
        }
        String id;
        if(Status.currentUserId!=null){
            id = Status.currentUserId;
            System.out.println(id+" Bye~");
        }
        System.out.println("----- Good Bye! -----");
        return true;
    }
}
