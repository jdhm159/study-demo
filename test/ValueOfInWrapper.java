import org.junit.Test;

public class ValueOfInWrapper {
    //对象包装器 Integer、Boolean、Double等的相关面试题目

    @Test
    public void foo1() {
        Integer i1 = 100;
        Integer i2 = 100;
        Integer i3 = 200;
        Integer i4 = 200;

        System.out.println(i1 == i2);     //true
        System.out.println(i3 == i4);     //false

    }
    // 结果分析，上面的四个Integer对象都是先通过对int进行包装再赋值的，主要通过自动调用valueOf()方法，
    // 而valueOf方法通过阅读源码可以知道，该方法会先检查相应的对象内存池来决定是否需要新建对象。一般内存池对象为-128~127
    // Long、Short、Byte 也是同样的是实现

    @Test
    public void foo2() {

        Double i1 = 100.0;
        Double i2 = 100.0;
        Double i3 = 200.0;
        Double i4 = 200.0;

        System.out.println(i1==i2); //false
        System.out.println(i3==i4); //false
    }
    //Double、Float没有上面Integer相同的IntegerCache设置，因为一段区域内Double与Float能容纳的对象个数是无数个的。

    @Test
    public void foo3(){
        Boolean i1 = false;
        Boolean i2 = false;
        Boolean i3 = true;
        Boolean i4 = true;

        System.out.println(i1==i2);     //true
        System.out.println(i3==i4);     //true
    }
    // Boolean内定义两个静态成员属性 TRUE 和 FALSE，上面通过valueOf()将对象指向了这两个值

    @Test
    public void foo4(){
        Integer a = 1;
        System.out.println((Object)a instanceof  Long);     //false,不同类型
        Long b = 2l;
        System.out.println((Object)b instanceof  Long);     //true
    }

    @Test
    public void foo5(){
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;
        Long h = 2L;

        System.out.println(c==d);           //true
        System.out.println(e==f);           //false
        System.out.println(c==(a+b));       //true
        System.out.println(c.equals(a+b));  //true
        System.out.println(g==(a+b));       //true
        System.out.println(g.equals(a+b));  //false     这里因为a+b的结果是int，自动装箱成Integer，因为Integer与Long类型不一样所以为false
        System.out.println(g.equals(a+h));  //true
    }
}
