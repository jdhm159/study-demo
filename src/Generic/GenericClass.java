package Generic;

import java.util.ArrayList;
import java.util.List;

public class GenericClass implements Comparable<GenericClass> {
    //可以不传传类型参数，没指定变量，本质就是Comparable<Object>
    //直接实现原始类型语法上允许，只是为了兼容性。
    //而为了保证泛型的安全性，就应该实现 Comparable<T> 而不是 原始类型 Comparable
    //类型参数使程序具有更好的可读性和安全性，
    //编译器会检查方法参数的类型，已经在返回类型的直接返回需要的类型而不是Object，也省掉调用方法的地方再进行一次类型转换。
    //编译时出错总比运行时出错好。
    public int a = 0;
    @Override
    public int compareTo(GenericClass genericClass){            //注意这里的参数类型为Object，因为Comparable是泛型接口，由于类型擦除，泛型类型变量T会变成Object
        return 0;
    }

    @Override
    public String toString(){
        return "This is GenericClass";
    }

    //父类中用final修饰了该方法，子类就无法重写该方法，只能给使用新的方法名作为新方法以避免与父类的冲突。
    public final void foo(){
        System.out.println("This is final function from super class");
    }

    public void bar(){
        System.out.println(this.a);
    }

}

//public class GenericClass implements Comparable<GenericClass> {
//    @Override
//    public int compareTo(GenericClass genericClass){        //这里的T因为已经指定为GenericClass，所以在重写compareTo方法时，T出现的地方也要用GenericClass代替
//        return 0;
//    }
//}
