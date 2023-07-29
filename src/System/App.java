package System;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        ArrayList<User> list=new ArrayList<>();
        while (true) {
            System.out.println("欢迎来到学生管理系统");
            System.out.println("请选择操作: 1登录  2注册 3忘记密码");
            Scanner sc=new Scanner(System.in);
            String choose = sc.next();
            switch (choose){
                case "1" -> login(list);
                case "2" -> register(list);
                case "3" -> forgetPassword(list);
                case "4" -> {System.out.println("谢谢使用，再见");
                                System.exit(0);}
                default -> System.out.println("没有这个选项");

            }
        }
    }

    private static void forgetPassword( ArrayList<User> list) {
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入用户名");
        String userName = sc.next();
        boolean flag = contains(list, userName);
        if(!flag){
            System.out.println("当前用户未注册，请先注册");
            return;
        }
        System.out.println("请输入身份证号码：");
        String personID = sc.next();
        System.out.println("请输入手机号码");
        String phoneNumber=sc.next();
        //比较用户对象中的手机号码和身份证号码是否相同
        //需要把用户对象先2获取出来
        int index=findIndex(list,userName);
        User user= list.get(index);
        if(!(user.getPhoneNumber().equals(phoneNumber)&&user.getPassword().equals(personID))){
            System.out.println("身份证号码或手机号码输入有误，不能修改密码");
            return;
        }
        String password;
        while (true) {
            System.out.println("请输入新的密码");
            password=sc.next();
            System.out.println("请再次输入新的密码");
            String againPassword = sc.next();
            if(password.equals(againPassword)){
                System.out.println("两次密码一致");
                break;
            }else{
                System.out.println("两次密码不一致，请再次输入");
                continue;
            }
        }
        user.setPassword(password);
        System.out.println("密码修改成功");
    }

    private static int findIndex(ArrayList<User> list, String userName) {
        for (int i = 0; i < list.size(); i++) {
            User user=list.get(i);
            if(user.getUsername().equals(userName)){
                return i;
            }
        }
        return -1;
    }

    private static void register( ArrayList<User> list) {
        //把用户信息添加到集合中
        Scanner sc=new Scanner(System.in);
        //用户名
        String userName;
        while (true) {
            System.out.println("请输入用户名");
           userName = sc.next();
            boolean flag1= checkUsername(userName);
            if(!flag1){
                System.out.println("不满足条件，重新输入");
                continue;//结束本次循环，继续下次循环
                //System.out.println("格式满足，继续判断用户名是否唯一");
            }
             boolean flag2=contains(list,userName);
            if(flag2){
                System.out.println("该用户已存在，无法注册，请重新输入");
                continue;
            }else{
                System.out.println("该用户名可用");
                break;
            }
        }
        //密码
        String password;
        while (true) {
            System.out.println("请输入要注册的密码");
           password=sc.next();
            System.out.println("请再次输入要注册的密码");
            String againPassword = sc.next();
            if(!password.equals(againPassword)){
                System.out.println("两次密码不一致，请再次输入");
                continue;
            }else{
                System.out.println("两次密码一致，继续录入其他数据");
                break;
            }

        }
        //身份证号码
        String personID;
        while (true) {
            System.out.println("请输入身份证号码");
            personID = sc.next();
            boolean flag3=checkPersonID(personID);
            if(flag3){
                System.out.println("身份证满足要求");
                break;
            }else{
                System.out.println("号码格式有误，请重新输入");
                continue;
            }
        }
        //手机号码
        String phoneNumber;
        while (true) {
            System.out.println("请输入手机号码");
            phoneNumber = sc.next();
            boolean flag4=checkPhoneNumber(phoneNumber);
            if(flag4){
                System.out.println("手机号码格式正确");
                break;
            }else{
                System.out.println("手机号码格式不正确，请重新输入");
                continue;
            }
        }
        User u= new User(userName,password,personID,phoneNumber);//局部变量

        list.add(u);
        System.out.println("注册成功");

        printList(list);
    }

    private static void printList(ArrayList<User> list) {
        for (int i = 0; i < list.size(); i++) {
            User user=list.get(i);
            System.out.println(user.getUsername()+","+user.getPassword()+","+user.getPersonID()+","+user.getPhoneNumber());
        }
    }

    private static boolean checkPhoneNumber(String phoneNumber) {
        if(phoneNumber.length()!=11){
            return false;
        }
        if(phoneNumber.startsWith("0")){
            return false;
        }
        for (int i = 0; i < phoneNumber.length(); i++) {
            char c = phoneNumber.charAt(i);
            if(!(c>='0'&&c<='9')){
                return false;
            }
        }
        return true;
    }

    private static boolean checkPersonID(String personID) {
        if(personID.length()!=18){
            return false;
        }
        //char c = personID.charAt(0);
        //判断是不是以0开头
        boolean flag = personID.startsWith("0");
        if(flag){
            return false;
        }
        for (int i = 0; i < personID.length()-1; i++) {
            char c = personID.charAt(i);
            if(!(c>='0'&&c<='9')){
                return false;
            }
            char endeChar = personID.charAt(personID.length() - 1);
            if ((endeChar >= '0' && endeChar <= '9') || (endeChar == 'x') || (endeChar == 'X')) {
                return true;
            }
            }
        return false;
        }

    private static boolean contains(ArrayList<User> list, String userName) {
        for (int i = 0; i < list.size(); i++) {
            User user = list.get(i);
            String rightUsername=user.getUsername();
            if(rightUsername.equals(userName)){
                return true;
            }
        }
        //当循环结束时，表示集合里面的所有用户都比较完毕了，还没有一样的，则返回false
        return false;
    }

    private static boolean checkUsername(String userName) {
        int len = userName.length();
        if (len < 3 || len > 15) {
            return false;
        }
        for (int i = 0; i < userName.length(); i++) {
            char c = userName.charAt(i);
            if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9'))) {
                return false;
            }
        }
        int count=0;
        for (int i = 0; i < userName.length(); i++) {
            char c = userName.charAt(i);
            if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))) {
                count++;//统计字符的数量
                break;
            }
        }
        return count>0;
    }


    private static void login( ArrayList<User> list) {
        Scanner sc=new Scanner(System.in);
        for (int i = 0; i < 3; i++) {
            System.out.println("请输入用户名");
            String username=sc.next();
            //判断用户名是否存在
            boolean flag = contains(list, username);
            if(!flag){
                System.out.println("该用户未注册，请先注册再登录");
                return;
            }
            System.out.println("请输入密码");
            String password=sc.next();
            while (true) {
                String rightCode=getCode();
                System.out.println("当前正确的验证码为："+rightCode);
                System.out.println("请输入验证码");
                String code=sc.next();
                if(code.equalsIgnoreCase(rightCode)){//忽略大小写
                    System.out.println("验证码正确");
                    break;
                }else {
                    System.out.println("验证码错误");
                    continue;
                }
            }

            //封装思想的应用
            //可以把一些零散的数据封装成一个对象
            //以后传递参数的时候，只要传递一个整体就可以了，不要管这些零散的数据
            User useInfo=new User(username,password,null,null);
            boolean result= checkUserInfo(list,useInfo);
            if(result){
                System.out.println("登录成功，开始使用学生管理系统");
                //创建对象调用方法，启动学生管理系统
                StudentSystem ss=new StudentSystem();
                ss.startStudentSystem();

                break;
            }else{
                //System.out.println("登录失败，用户名或密码错误");
                if(i==2){
                    System.out.println("当前账号被锁定");
                    return;
                }else{
                    System.out.println("登录失败，用户名或密码错误,还剩下"+(2-i)+"次机会");
                }
            }
        }

    }

    private static boolean checkUserInfo(ArrayList<User> list,User useInfo) {
        for (int i = 0; i < list.size(); i++) {
            User user=list.get(i);
            if(user.getUsername().equals(useInfo.getUsername())&&user.getPassword().equals(useInfo.getPassword())){
                return true;
            }
        }
        return false;
    }

    //生成验证码
    private static  String getCode(){
        //创建一个集合添加所有的大写和小写字母
        ArrayList<Character> list=new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            list.add((char)('a'+i));
            list.add((char)('A'+i));
        }
        StringBuilder sb=new StringBuilder();
        Random r=new Random();
        for (int i = 0; i < 4; i++) {
            int index = r.nextInt(list.size());
           char c= list.get(index);
           sb.append(c);
        }
        System.out.println(sb);
        int number = r.nextInt(10);
        sb.append(number);
        //如果要修改字符串中的内容，要先把字符串变成字符数组，然后再创建一个新的字符串
        char[] arr = sb.toString().toCharArray();//先变成字符串，再变成字符索引
        int randomInderx = r.nextInt(arr.length);
        char temp=arr[randomInderx];
        arr[randomInderx]=arr[arr.length-1];
        arr[arr.length-1]=temp;

        return new String(arr);
        //System.out.println(list);
    }
}
