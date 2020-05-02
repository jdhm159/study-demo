package generic;

public class ChildClass extends GenericClass {
    //从父类那里继承来的Comparable<GenericClass>  或者是 Comparable<Object>

    public int a = 1;  //子类有两个a，一个是 this.a，另外一个是 super.a

    public void fucTest(){}

    public void fucTest(double o){
        System.out.println("This is double");
    }

    public void fucTest(Double o){
        System.out.println("This is Double");
    }

    public void fucTest(Float o){
        System.out.println("This is Float");
    }

    @Override
    public String toString(){
        return "This is ChildClass";
    }

    public void bar(){
        System.out.println(a);
    }

    //覆盖超类方法，返回类型也要与原方法兼容，定义为原返回类型的子类型
//    public String far(){
    public Integer far(){

        return 1;
    }

}
