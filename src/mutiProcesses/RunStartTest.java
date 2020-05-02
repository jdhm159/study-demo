package mutiProcesses;

import generic.ChildClass;
import generic.GenericClass;
import sun.awt.windows.ThemeReader;

public class RunStartTest {

    //使用多线程不要直接run 实现了run方法的Runnable和Thread，因为直接run只会在当前线程中按顺序执行方法，而不会创建新线程执行任务。
    //正确方法：创建实现run方法的Runnable任务对象，用runnable创建线程对象，通过线程对象start创建新线程然后执行任务。
    //避免直接实现Thread的run，任务应该跟线程机制解耦，不然一个任务创建一个线程就花销太大了。
    public static void main(String[] args) {
//        new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 1000; i++) {
//                    System.out.println("from runnable");
//                }
//            }
//        }.run();
//
//        System.out.println("from main ");

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    System.out.println("from run");
                }
            }
        });
        thread.start();
        thread.run();

        for (int i = 0; i < 10; i++) {
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!");
        }


    }
}
