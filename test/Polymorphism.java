import Generic.ChildClass;
import Generic.GenericClass;
import org.junit.Test;

public class Polymorphism {


    @Test
    public void ta() {
        //测试自动装箱时的变量类型
        Integer i = 1;
        System.out.println(i.getClass());   //output:class java.lang.Integer
    }

    @Test
    public void tb() {
        //测试应用多态时变量的类型到底是什么
        GenericClass t = new ChildClass();
        System.out.println(t.getClass());   //output:class Generic.ChildClass

        //测试继承方法能否被调用
//        t.fucTest();                  //显示找不到方法，猜测原因是 使用了多态，用父类的类型做了声明，因此只会到 实例化的父类对象域中找对应的方法
        ((ChildClass) t).fucTest();     //强转类型之后就可以了，这里是个难以理解的地方，按照t的实际类型ChildClass，其方法表中是有fucTest的
        ((ChildClass) t).fucTest(1);   //但因为这里的声明类型是GenericClass，即使通过动态绑定绑定到了子类的方法，也不能通过编译，所以这里才需要强转类型

        System.out.println(t.toString());   //方法调用通过动态绑定找到最合适的方法，且动态绑定依赖的是对象的实际类型

        // 通过上面能发现一个现象，如果多态中实际对象没有重写到声明对象的方法，如果想调用到子类（即实际对象）的方法时需要类型强制转换
        // 但如果调用父类中也存在，子类给重写的方法，则不需要强制转换，而且通过动态绑定绑定到了子类重写的方法上。
        // 要解释这个现象，其实很简单，
        // 核心就是面向对象的思想。
        // 鸟不一定会游泳
        // 但如果子类成功重写了父类方法，则就由动态绑定绑定到子类的方法
    }

    @Test
    public void sd(){
        GenericClass t = new ChildClass();      //t 存在两个域a，super.a = 0 / this.a = 1
        t.bar();                                  //output: 1   通过子类重写的bar方法输出了子类的域
        t.foobar();                               //output: 0   通过父类的方法foobar方法输出了父类的域
        System.out.println(t.a);                  //output: 0   因为域没有多态，所以由编译器解析的域a来自GenericClass，编译器观察的是声明类型
        System.out.println(((ChildClass)t).a);    //output: 1   因为域没有多态，所以由编译器解析的域a来自ChildClass，编译器观察的是声明类型


        //这样的确会很让人混淆
        //java编译原理原话：
        //  尽管这看起来好像会成为一个容易令人混淆的问题，但是在实践中，它实际从来不会发生。
        //  首先，你通常会将所有的域都设置成private，因此不能直接访问它们，其副作用是只能调用方法来访问。
        //  另外，你可能不会对基类中的域和导出类的域赋予相同的名字，因为这种做法容易令人混淆。

        //这告诉我们要按规范编码，遇到容易令人混淆的地方就尽量避开它，就跟表达式的括号一样，这样你好我好大家都好
    }
}
