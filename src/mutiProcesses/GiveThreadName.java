package mutiProcesses;

import sun.awt.windows.ThemeReader;

//实践一、线程创建和等待实践。创建四个线程，分别赋予编号0~3，每个线程输出自己的编号（给线程传递参数），主线程等待四个线程结束后退出。
public class GiveThreadName {

    public final static int THREAD_NUM = 4;

    public static class MyThread extends Thread{
        int rank;   //因为是给线程编号，编号作为线程的属性，如果设置为Runnable的属性，感觉不太对

        MyThread(int rank){
            this.rank = rank;
        }

        @Override
        public void run() {
            try {
                sleep(2000);
                System.out.println(rank);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[THREAD_NUM];
        for (int i = 0; i < THREAD_NUM; i++) {
            threads[i] = new MyThread(i);
            threads[i].start();
        }
        //通过join让主线程等待所有的子线程结束后再接着执行
        for (Thread t : threads) {
            t.join();
        }
        System.out.println("main thread die!");

    }
}
