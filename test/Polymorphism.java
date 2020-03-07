import java.util.Random;
import java.util.stream.IntStream;
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
        System.out.println(t.getClass());   //output:class ChildClass
    }
}
