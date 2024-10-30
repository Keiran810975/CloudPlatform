
import java.util.Scanner;
import Global.Commends;
import Utils.*;

public class Test {

    private boolean loop=true;
    private String key="";
    Scanner scanner = new Scanner(System.in);
    private Commends cc = new Commends();

    public static void main(String[] args) {
        Test test = new Test();
        test.mainMenu();
    }

    private void mainMenu(){
        while(loop){
            //System.out.println("请输入指令：");
            String input = scanner.nextLine();
            String[] inputArray = Scan.splitInput(input);
            key = inputArray[0];
            //如果key不为空字符串
            if(key.equals("")){
                continue;
            }
            if(!cc.commends.containsKey(key)){
                System.out.println("Command '"+key+"' not found");
            }else{
                switch (key){
                    case Commends.QUIT:
                        if(Quit.quit(inputArray)){
                            loop=false;
                        }
                        break;
                    case Commends.REGISTER:
                        Register.register(inputArray);
                        break;
                    case Commends.LOGIN:
                        if(!Login.login(inputArray)){
                            continue;
                        }
                        break;
                    case Commends.LOGOUT:
                        Logout.logout(inputArray);
                        break;
                    case Commends.PRINTINFO:
                        Print.printInfo(inputArray);
                        break;
                    case Commends.CREATECOURSE:
                        CreateCourse.createCourse(inputArray);
                        break;
                    case Commends.LISTCOURSE:
                        CheckCourse.checkCourse(inputArray);
                        break;
                    case Commends.SELECTCOURSE:
                        chooseCourse.chooseCourse(inputArray);
                        break;
                    case Commends.CANCELCOURSE:
                        cancelCourse.cancelCourse(inputArray);
                        break;
                    case Commends.SWITCH:
                        Switch.switchUser(inputArray);
                        break;
                    case Commends.OUTPUTCOURSEBATCH:

                        break;
                    case Commends.INPUTCOURSEBATCH:

                        break;
                    case Commends.LISTSTUDENT:
                        ListStudent.listStudent(inputArray);
                        break;
                    case Commends.REMOVESTUDENT:
                        RemoveStudent.removeStudent(inputArray);
                        break;
                    case Commends.LISTCOURSESCHEDULE:
                        ListCourseSchedule.listCourseSchedule(inputArray);
                        break;

                    default:
                        break;
                }
            }
        }
    }
}
