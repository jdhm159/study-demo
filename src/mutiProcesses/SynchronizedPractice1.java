package mutiProcesses;

//  实践三、线程同步实践。一个变量count=0四个线程共享，四个线程轮流将count加1，一共轮流操作10次，
//  最终结果。计算中给出必要的打印信息，表示是轮流操作。
public class SynchronizedPractice1 {

    private final static int THREAD_NUM = 4;
    private final static int TARGET_COUNT = 100;
    private static int count = 0;
    static Thread[] threads = new Thread[THREAD_NUM];

    static class MyThread extends Thread {

        int rank;

        MyThread(int rank) {
            this.rank = rank;
        }

        //调用对象的wait,notify,notifyAll方法 要先获得这个对象的锁
        //我这里通过由线程来唤醒下一个线程实现同步
        @Override
        public void run() {
            synchronized (threads[rank]) {
                try {
                    while (true) {
                        threads[rank].wait();
                        if (count >= TARGET_COUNT){
                            synchronized (threads[(rank + 1) % THREAD_NUM]) {
                                threads[(rank + 1) % THREAD_NUM].notify();
                            }
                            break;
                        }
                        System.out.println("Thread: " + rank + " , Count: " + ++count);
                        synchronized (threads[(rank + 1) % THREAD_NUM]) {
                            threads[(rank + 1) % THREAD_NUM].notify();
                        }

                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < THREAD_NUM; i++) {
            threads[i] = new MyThread(i);
            threads[i].start();
        }
        Thread.sleep(1000); //先保证线程已经创建完毕，不然容易造成死锁：主线程在子线程创建之前就进行唤醒了
        synchronized (threads[0]) {
            threads[0].notifyAll();
        }
    }
}
