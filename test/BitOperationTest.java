import org.junit.Test;

public class BitOperationTest {

    // & 按位与
    // | 按位或
    // ^ 按位异或

    @Test
    public void foo() {
        //java用补码存储
        int a = -5;
        System.out.println(Integer.toBinaryString(a));
        System.out.println(Integer.toBinaryString(a >> 1));
        System.out.println(Integer.toBinaryString(a << 1));
        System.out.println(Integer.toBinaryString(a >>> 1));    //左边的0给去掉了
        //补0操作
        String b = Integer.toBinaryString(a >>> 1);
        while (b.length() < 32) {
            b = "0" + b;
        }
        System.out.println(b);

        // >>为算术移位符，根据正负数给高位补1或0，如果是正数就补0，负数则补1
        // >>>为逻辑移位符,不管正数还是负数都补0
        // <<为左移位符，低位补0，不存在<<<
    }

    //另一种转二进制的方式
    private static String toBin(int num) {
        char[] chs = new char[Integer.SIZE];
        for (int i = 0; i < Integer.SIZE; i++) {
            chs[Integer.SIZE - 1 - i] = (char) ((num >> i & 1) + '0');
        }
        return new String(chs);
    }

    //该方法源自HashMap中的方法，用于计算大于或者等于当前容量的最近2次幂数
    private int tableSizeFor(int cap) {
        int MAXIMUM_CAPACITY = 1<<16;
        int n = cap - 1;    //这个操作让获得的值大于 或 等于 原值
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }
}
