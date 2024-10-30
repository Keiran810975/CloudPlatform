package Global;
import Models.User;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

public class UserList {
    //哈希表，存储所有用户信息，键是工号(id)，值是对象User
    public static Map<String, User> userList = new HashMap<String, User>();

    //存储已经登录的用户的信息
    public static Map<String, User> loginList = new HashMap<String, User>();

    //用户学工号列表
    public static Set<String> loginIdList = new LinkedHashSet<>();

    //用户已存在
    public static boolean isUserExist(String id){
        return userList.containsKey(id);
    }
    //用户已登录
    public static boolean isUserLogin(String id){
        return loginList.containsKey(id);
    }
}
