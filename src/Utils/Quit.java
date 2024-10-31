package Utils;

import Global.Status;
import Global.UserList;
import Models.User;

import java.io.File;

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
        deleteDataDirectory();
        System.out.println("----- Good Bye! -----");
        return true;
    }


    public static void deleteDataDirectory() {
        String path = "src/data";
        File directory = new File(path);
        deleteDirectory(directory);
    }

    private static boolean deleteDirectory(File directory) {
        // 检查文件夹是否存在且为目录
        if (!directory.exists() || !directory.isDirectory()) {
            return false;
        }

        // 递归删除文件夹中的所有文件和子文件夹
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteDirectory(file);  // 递归删除子目录
                } else {
                    file.delete();  // 删除文件
                }
            }
        }

        return true;
    }
}
