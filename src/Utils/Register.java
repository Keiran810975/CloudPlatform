package Utils;
import java.util.Scanner;
import Models.User;
import Models.Student;
import Models.Teacher;
import Models.Administrator;
import Utils.Judge.JudgeType;
import Utils.Judge.JudgeInfo;
import Global.UserList;
public class Register {

    public static boolean register(String[] arr){
        if(!Scan.isParamNum(arr,6)){
            System.out.println("Illegal argument count");
            return false;
        }
        String Id = arr[1];
        String name = arr[2];
        String password = arr[3];
        String confirmPassword = arr[4];
        String identityType = arr[5];

        //学工号是否合法
        if(!JudgeType.isLegalId(Id)){
            System.out.println("Illegal user id");
            return false;
        }
        //用户已注册
        if(UserList.isUserExist(Id)){
            System.out.println("User id exists");
            return false;
        }
        //名字是否合法
        if(!JudgeInfo.isName(name)){
            System.out.println("Illegal user name");
            return false;
        }
        //密码是否合法
        if(!JudgeInfo.isPassword(password)){
            System.out.println("Illegal password");
            return false;
        }
        //确认密码是否和密码一致
        if(!JudgeInfo.isConfirmPassword(password,confirmPassword)){
            System.out.println("Passwords do not match");
            return false;
        }
        //身份类型是否合法
        if(!JudgeType.isType(identityType)){
            System.out.println("Illegal identity");
            return false;
        }
        //注册成功
        System.out.println("Register success");

        //为新用户创建user对象，插入到userlist
        if(identityType.equals("Student")){
            if(JudgeType.isStudentUnder(Id)){
                User newUser = new Student(Id,name,password,confirmPassword,identityType,"under");
                UserList.userList.put(Id,newUser);
            }
            else if(JudgeType.isStudentPost(Id)){
                User newUser = new Student(Id,name,password,confirmPassword,identityType,"post");
                UserList.userList.put(Id,newUser);
            }
            else if(JudgeType.isStudentDoc(Id)){
                User newUser = new Student(Id,name,password,confirmPassword,identityType,"doc");
                UserList.userList.put(Id,newUser);
            }
        }
        else if(identityType.equals("Teacher")){
            User newUser = new Teacher(Id,name,password,confirmPassword,identityType);
            UserList.userList.put(Id,newUser);
        }
        else if(identityType.equals("Administrator")){
            User newUser = new Administrator(Id,name,password,confirmPassword,identityType);
            UserList.userList.put(Id,newUser);
        }
        //UserList.loginIdList.add(Id);

        return true;
    }
}
