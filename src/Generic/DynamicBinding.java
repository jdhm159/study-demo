package Generic;

public class DynamicBinding {
    public static void main(String[] args) {

        A a = new B();
        int result = a.getResult();
        System.out.println("result: "+ result);

        int result1 = ((A)a).getResult();       //强制类型转换改变的是声明类型
        System.out.println("result: "+ result);
    }
}

class A {
    public int i = 10;

    public int getResult() {
        System.out.println("走A类方法");
        return i+10;
    }
}

class B extends A {
    public int i = 20;

    public int getResult() {
        System.out.println("走B类方法");
        return i + 20;
    }
}