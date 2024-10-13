package Utils;

import java.util.ArrayList;
import java.util.List;

public class Scan {
    public static String[] splitInput(String input) {
        // 使用正则表达式匹配多个空格，分隔字符串并去除多余的空字符串
        return input.trim().split("\\s+");
    }

    //判断参数个数是否正确
    public static boolean isParamNum(String[] arr, int num) {
        if(arr.length != num) {
            return false;
        }
        return true;
    }
}
