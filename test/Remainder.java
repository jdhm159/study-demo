import org.junit.Test;

public class Remainder {

    @Test
    public void r() {
        //除数求余一般都是按照数学意义上的执行操作，不过要注意的是 取余运算的结果的符号 是由被除数的符号决定的。
        System.out.println("正数/正数：13/5 = " + (13 / 5));
        System.out.println("正数%正数：13%5 = " + (13 % 5));
        System.out.println("正数/负数：13/-5 = " + (13 / -5));
        System.out.println("正数%负数：13%-5 = " + (13 % -5));
        System.out.println("负数/正数：-13/5 = " + (-13 / 5));
        System.out.println("负数%正数：-13%5 = " + (-13 % 5));
        System.out.println("负数/负数：-13/-5 = " + (-13 / -5));
        System.out.println("负数/负数：-13%-5 = " + (-13 % -5));
    }
}
