import org.junit.Test;

public class StringTest {
    @Test
    public void s1(){
        String a = "java";
        System.out.println(a.intern() == "java");   // true, == 比较的是引用，这里a是从常量池中获得的。
        String b = new String("java");  //因为常量池里面已经存在java了，这里返回的是堆中创建的String对象
        System.out.println( b.intern() == b);   // false，因为b.intern是常量池中的String而不是队中新创建的
        System.out.println( b.intern() == a);   // true
        String c = "ja" + "va";
        System.out.println(c == a);     // true

        String d = "ja";
        String e = new String("va");
        String f = d + e;
        System.out.println(a == f);   //false
        System.out.println(a == f.intern());   //true

    }

    @Test
    public void s2(){
        String str1 = new String("ja") + new String("va");
        System.out.println(str1.intern() == str1);                              //false
        System.out.println(str1 == "java");                                     //false
    }

    @Test
    public void s3(){
        String str2 = new String("stu") + new String("dy");
        System.out.println(str2.intern() == str2);                              //true
        System.out.println(str2 == "study");                                    //true

        //先解释这里为什么都为true
        //第一条语句，这里一共生成了三个对象，分别是放入常量池的stu、dy 和 堆区的 study，这里并不会将study放入常量池
        //第二条语句，这里执行了str2.intern，所以会到常量池寻找，正如上面所说常量池中还不存在study，所以这里会往常量池中放入一个study的引用并返回
        //  这里要注意的是1.7及以后常量池被放进堆空间中，导致intern()的功能有所不同，它会将常量池study的引用指向 上面创建的 study，
        //  而不是1.6及以下版本的 另外在堆中创建一个study对象然后将引用放入方法区（perm）中的常量池中。
        //第二条语句，我这里测试用的是1.8版本jdk，所以明显intern()得到的String跟str2引用同一个对象。
        //第三条语句，字面量study从常量池中获得，也跟str2引用同一个对象。

        //那么为什么上面java的例子不行，据说是因为在启动时"java"在别的地方被用作了类变量，故已经事先被放入常量池了

        //不同版本虚拟机可能会得到结果不同，需要实践验证

        //详情查阅博客：https://blog.csdn.net/seu_calvin/article/details/52291082
    }

    @Test
    public void s4(){
        String str2 = "stu" + new String("dy");         //不会检查常量池中的study
//        System.out.println(str2 == "study");              //如果加上这条代码，结果全为false
        System.out.println(str2.intern() == str2);              //true
        System.out.println(str2 == "study");                    //true
    }

    @Test
    public void s5(){
        String str = "stu" + "dy";          //猜测 由于优化策略，只有字面量的运算能在编译或者启动期完成，运算结果study会在常量池中检查
                                                    //而带有String对象的就不能提前得到结果了
        System.out.println(str == "study");         //true
    }

    @Test
    public void s6(){
        String str = "stu" + "d" + new String("y");         //注释掉该行代码，结果变为true，说明stud会被事先放入常量池
        String str1 = new String("stu") + new String("d");
        System.out.println(str1.intern() == str1);          //false
    }

    @Test
    public void s7(){
        char[] s = {'a','b','c'};
        String ss = new String(s);  //构造方法中使用Array.copyOf方法拷贝新数组作为底层维护使用
        System.out.println(ss); //"abc"
        s[1] = '?';
        System.out.println(ss); //"abc"

    }

    @Test
    public void s8(){
        String ab = "ab";
        String a = "a";
        String b = "b";
        String c = a + b;       //因为这里是两个对象的拼接，创建一个新的对象
        System.out.println(ab == c);        //false
    }

    @Test
    public void s9(){
        String ab = "ab";
        final String a = "a";
        final String b = "b";
        String c = a + b;       //由于final，虚拟机进行了优化，相当于字面量相加 String c = "a" + "b"
        System.out.println(ab == c);        //true
    }

    @Test
    public void s10(){
        overload(null);     //输出: String ，重载的动态绑定机制是优先选择匹配度更高的，因为满足String比Object要难，所以优先匹配String参数方法
    }

    private void overload(String a){
        System.out.println("String");
    }

    private void overload(Object a){
        System.out.println("Object");
    }

    //如果加上以下方法就不能准确动态绑定到合适的方法了，调用方法报错，需要指定目标方法才能继续执行
//    private void overload(Integer a){
//        System.out.println(a);
//    }
}
