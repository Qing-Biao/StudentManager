package System;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentSystem {
    public static void startStudentSystem() {
        ArrayList<Student> list=new ArrayList<>();
        boolean flag=true;
      loop: while (flag) {  //添加标记，用于指定
            System.out.println("-----------------欢迎来到学生管理系统-------------------");
            System.out.println("1:添加学生");
            System.out.println("2:删除学生");
            System.out.println("3:修改学生");
            System.out.println("4:查询学生");
            System.out.println("5:退出");
            System.out.println("请输入您的选择：");
            Scanner sc=new Scanner(System.in);
            String choose = sc.next();//容错率更高
            switch (choose){
                case "1"-> addStudent( list);
                case "2"-> deleteStudent(list);
                case "3"-> updateStudent( list);
                case "4"-> quieryStudent( list);
                case "5"-> {
                    System.out.println("退出");
                    flag=false;
                    //break loop;
                    System.exit(0); //停止虚拟机运行
                }
                default -> System.out.println("没有这个选项");//写一段，测一段
            }
            /*if(!flag)
                break;*/
        }
    }

    //添加学生
    public static void addStudent(ArrayList<Student> list) {
        Scanner sc=new Scanner(System.in);
        Student s=new Student();

      loop: while (true) {
            System.out.println("请输入学生的ID");
            String id = sc.next();
            s.setId(id);
            boolean result=contains(list,id);
            if(!result)
            {
                System.out.println("请输入学生的姓名");
                String name = sc.next();
                s.setName(name);

                System.out.println("请输入学生的年龄");
                int age = sc.nextInt();
                s.setAge(age);

                System.out.println("请输入学生的家庭住址");
                String address = sc.next();
                s.setAddress(address);
                list.add(s);
                System.out.println("添加成功");
                break loop;
            }else{
                System.out.println("该id已存在,请重新输入");
            }
        }
    }

    public static void deleteStudent(ArrayList<Student> list) {
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入要删除学生的ID");
        String id = sc.next();
        int index=getIndex(list,id);
        if(index>=0){
            list.remove(index);
            System.out.println("该学生删除成功");
        }else {
            System.out.println("该id不存在,删除失败");
        }
    }

    public static void updateStudent(ArrayList<Student> list) {
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入要修改学生的ID");
        String id = sc.next();
        int index=getIndex(list,id);
        if(index>=0){
            System.out.println("该学生ID存在");
            System.out.println("请输入学生的姓名");
            //获取要修改的学生对象
            Student stu=list.get(index);
            String newName = sc.next();
            stu.setName(newName);

            System.out.println("请输入学生的年龄");
            int newAge = sc.nextInt();
            stu.setAge(newAge);

            System.out.println("请输入学生的家庭住址");
            String newAddress = sc.next();
            stu.setAddress(newAddress);

        }else {
            System.out.println("该id不存在,修改失败");
        }
    }

    public static void quieryStudent(ArrayList<Student> list) {
        System.out.println("查询学生");
        if (list.size() == 0) {
            System.out.println("当前无学生信息，请添加后再查询");
            return;//结束方法
        }
        System.out.println("id\t姓名\t年龄\t家庭住址");
        for (int i = 0; i < list.size(); i++) {
            Student stu = list.get(i);
            System.out.println(stu.getId() + "\t" + stu.getName() + "\t" + stu.getAge() + "\t" + stu.getAddress());
        }
    }

    //判断id在集合中是否存在
    public static  boolean contains(ArrayList<Student> list,String  id){
       /* for (int i = 0; i < list.size(); i++) {
            Student stu=list.get(i);
            String sid=stu.getId();
           if(sid.equals(id)){
                return true;
           }
        }
        return false;*/
       return getIndex(list,id)>=0;
    }

    //通过id获取索引的方法
    public static int getIndex(ArrayList<Student> list,String id) {
        for (int i = 0; i < list.size(); i++) {
            Student stu=list.get(i);
            String sid=stu.getId();
            if(sid.equals(id)){
                return i;
            }
        }
        return -1;
    }
}
