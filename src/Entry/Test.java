package Entry;
import java.util.Scanner;
import Global.Commends;
import Utils.Login;
import Utils.Register;
import Utils.Scan;
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
            System.out.println("请输入指令：");
            String input = scanner.nextLine();
            String[] inputArray = Scan.splitInput(input);
            key = inputArray[0];

            if(!cc.commends.containsKey(key)){
                System.out.println("Command '"+key+"' not found");
            }else{
                switch (key){
                    case Commends.QUIT:
                        if(!Scan.isParamNum(inputArray,1)){
                            System.out.println("Illegal argument count");
                            continue;
                        }
                        System.out.println("----- Good Bye! -----");
                        loop=false;
                        break;
                    case Commends.REGISTER:
                        if(!Register.register(inputArray)){
                            continue;
                        }
                        break;
                    case Commends.LOGIN:
                        if(!Login.login(inputArray)){
                            continue;
                        }
                        break;
                    case Commends.LOGOUT:
                        break;
                    case Commends.PRINTINFO:
                        break;
                    case Commends.CREATECOURSE:
                        break;
                    case Commends.LISTCOURSE:
                        break;
                    case Commends.SELECTCOURSE:
                        break;
                    case Commends.CANCELCOURSE:
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
