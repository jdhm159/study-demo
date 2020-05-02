package mutiProcesses;

import java.util.concurrent.atomic.AtomicInteger;

//实践二、线程互斥实践。四个线程并行，两个线程互斥写整数变量s12，两个互斥写整数变量s34。
public class MutualExclusionPractice {

    private static final Object lockFor12 = new Object();
    private static Integer s12 = new Integer(0);
    private static AtomicInteger s34 = new AtomicInteger();

    public static void write12(int value) {
        synchronized(lockFor12) {
//        synchronized (s12) {           //锁s12这个对象不正确，因为s12可以变
            s12 = value;                //s12 这里改变了值，地址也变了，第二次写入获得的是另外的锁，自然也就锁不住这个方法
            System.out.println("s12");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void write34(int value) {
        s34.set(value);
        System.out.println("s34");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    write12(12);
                }
            }).start();
        }
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    write34(34);
                }
            }).start();
        }
    }
}
