import static org.junit.Assert.assertEquals;

import algorithm.BigNum;
import org.junit.Test;

public class BigNumTest {
    @Test
    public void compareToTest1(){
        //正正，相同长度，一大一小
        BigNum c = new BigNum();
        BigNum d = new BigNum();
        c.isPositive = true;
        d.isPositive = true;
        c.len = 2;
        d.len = 2;
        c.value[998] = 123;
        d.value[998] = 122;
        c.value[999] = 999;
        d.value[999] = 999;
        assertEquals(1, c.compareTo(d));
    }

    @Test
    public void compareToTest2(){
        //正正，相同长度，相同值
        BigNum c = new BigNum();
        BigNum d = new BigNum();
        c.isPositive = true;
        d.isPositive = true;
        c.len = 2;
        d.len = 2;
        c.value[998] = 122;
        d.value[998] = 122;
        c.value[999] = 999;
        d.value[999] = 999;
        assertEquals(0, c.compareTo(d));
    }

    @Test
    public void compareToTest3(){
        //正正，相同长度，一大一小
        BigNum c = new BigNum();
        BigNum d = new BigNum();
        c.isPositive = true;
        d.isPositive = true;
        c.len = 2;
        d.len = 2;
        c.value[998] = 122;
        d.value[998] = 123;
        c.value[999] = 999;
        d.value[999] = 999;
        assertEquals(-1, c.compareTo(d));
    }

    @Test
    public void compareToTest4(){
        //正正，不同长度
        BigNum c = new BigNum();
        BigNum d = new BigNum();
        c.isPositive = true;
        d.isPositive = true;
        c.len = 2;
        d.len = 1;
        c.value[998] = 122;
        c.value[999] = 999;
        d.value[999] = 999;
        assertEquals(1, c.compareTo(d));
    }

    @Test
    public void compareToTest5(){
        //正正，不同长度
        BigNum c = new BigNum();
        BigNum d = new BigNum();
        c.isPositive = true;
        d.isPositive = true;
        c.len = 1;
        d.len = 2;
        d.value[998] = 122;
        c.value[999] = 999;
        d.value[999] = 999;
        assertEquals(-1, c.compareTo(d));
    }

    @Test
    public void compareToTest6(){
        //正负
        BigNum c = new BigNum();
        BigNum d = new BigNum();
        c.isPositive = true;
        d.isPositive = false;
        c.len = 1;
        d.len = 2;
        d.value[998] = 122;
        c.value[999] = 999;
        d.value[999] = 999;
        assertEquals(1, c.compareTo(d));
    }

    @Test
    public void compareToTest7(){
        //负正
        BigNum c = new BigNum();
        BigNum d = new BigNum();
        c.isPositive = false;
        d.isPositive = true;
        c.len = 1;
        d.len = 2;
        d.value[998] = 122;
        c.value[999] = 999;
        d.value[999] = 999;
        assertEquals(-1, c.compareTo(d));
    }

    @Test
    public void compareToTest8(){
        //负负，相同长度，相同大小
        BigNum c = new BigNum();
        BigNum d = new BigNum();
        c.isPositive = false;
        d.isPositive = false;
        c.len = 2;
        d.len = 2;
        c.value[998] = 122;
        d.value[998] = 122;
        c.value[999] = 999;
        d.value[999] = 999;
        assertEquals(0, c.compareTo(d));
    }
    @Test
    public void compareToTest9(){
        //负负，相同长度，一大一小
        BigNum c = new BigNum();
        BigNum d = new BigNum();
        c.isPositive = false;
        d.isPositive = false;
        c.len = 2;
        d.len = 2;
        c.value[998] = 1;
        d.value[998] = 2;
        c.value[999] = 999;
        d.value[999] = 999;
        assertEquals(1, c.compareTo(d));
    }

    @Test
    public void compareToTest10(){
        //负负，相同长度，一小一大
        BigNum c = new BigNum();
        BigNum d = new BigNum();
        c.isPositive = false;
        d.isPositive = false;
        c.len = 2;
        d.len = 2;
        c.value[998] = 2;
        d.value[998] = 1;
        c.value[999] = 999;
        d.value[999] = 999;
        assertEquals(-1, c.compareTo(d));
    }

    @Test
    public void compareToTest11(){
        //负负，不同长度
        BigNum c = new BigNum();
        BigNum d = new BigNum();
        c.isPositive = false;
        d.isPositive = false;
        c.len = 2;
        d.len = 1;
        c.value[998] = 2;
        c.value[999] = 999;
        d.value[999] = 999;
        assertEquals(-1, c.compareTo(d));
    }

    @Test
    public void compareToTest12(){
        //负负，不同长度
        BigNum c = new BigNum();
        BigNum d = new BigNum();
        c.isPositive = false;
        d.isPositive = false;
        c.len = 1;
        d.len = 2;
        d.value[998] = 2;
        c.value[999] = 999;
        d.value[999] = 999;
        assertEquals(1, c.compareTo(d));
    }
}