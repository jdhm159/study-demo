import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

public class InnerClassTest {
    public static final void main(String... args){
        List<String> mm = new LinkedList<>();
        System.out.println(mm.equals(mm));
        System.out.println(mm.equals(null));

        /*
        java8不支持这种写法: List<String> sdf = new LinkedList<>(){};
        java9以上支持钻石修饰符里面不加具体的类型。
        听说scala写代码很爽。。。改天可以学习一下
         */
        List<String> sdf = new LinkedList<String>(){
            public boolean equals(Object o){
                return (this.hashCode() == o.hashCode());
            }
        };

//        System.out.println(sdf.equals(null));         //抛出空指针，猜测LinkedList内部实现remove、indexOf等方法要先判断是否null才去遍历链表的原因就是要避免空指针的发生。
        System.out.println(sdf.equals(sdf));
    }
}
