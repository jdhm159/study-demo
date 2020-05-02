package mutiProcesses;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

//实践二、估算Pi的值，分别用1/2/4个线程计算，求加速比。
public class CountPI {

    public final static int N = 20000000;

    public static double f(double x) {
        return 4 / (1 + Math.pow(x, 2));
    }

    public static long serialCount() {
        long startTime = System.currentTimeMillis();
        double sum = 0.0;
        for (int i = 0; i < N; i++) {
            sum += f((i + 0.5) / N);
        }
        sum /= N;
        long endTime = System.currentTimeMillis();
        System.out.println("SerialCount ThreadReturn: " + sum);
        return endTime - startTime;
    }

    public static class MyRunnable implements Runnable {

        int rank;
        int numThread;
        ThreadReturn threadReturn;

        MyRunnable(int rank, int numThread, ThreadReturn threadReturn) {
            this.rank = rank;
            this.numThread = numThread;
            this.threadReturn = threadReturn;
        }

        @Override
        public void run() {
            double sum = 0.0;
            long startTime = System.currentTimeMillis();
            for (int i = rank - 1; i < N; i += numThread) {
                sum += f((i + 0.5) / N);
            }
            sum /= N;
            long endTime = System.currentTimeMillis();
            threadReturn.setSum(sum);
            threadReturn.setRunTime(endTime - startTime);
        }
    }

    public static class ThreadReturn {

        private long runTime;
        private double sum;

        public long getRunTime() {
            return runTime;
        }

        public double getSum() {
            return sum;
        }

        public void setSum(double sum) {
            this.sum = sum;
        }

        public void setRunTime(long runTime) {
            this.runTime = runTime;
        }
    }

    public static void main(String[] args) throws InterruptedException{
        //用一个线程计算
        ThreadReturn oneThreadReturn = new ThreadReturn();
        Thread t1 = new Thread(new MyRunnable(1,1, oneThreadReturn));
        t1.start();
        t1.join();  //需要等计算线程结束再打印结果
        System.out.println("one thread: runtime = " + oneThreadReturn.getRunTime() + "ms， sum  = "+ oneThreadReturn.getSum());

        //用两个线程计算
        ThreadReturn[] twoThreadReturn = new ThreadReturn[2];
        for (int i = 0; i < twoThreadReturn.length; i++) {
            twoThreadReturn[i] = new ThreadReturn();
        }
        Thread[] t2 = new Thread[2];
        for (int i = 0; i < 2; i++) {
            t2[i] = new Thread(new MyRunnable(i + 1,2, twoThreadReturn[i]));
            t2[i].start();
            t2[i].join();
        }
        ThreadReturn twoThreadReturnSum = new ThreadReturn();
        long startTime = System.currentTimeMillis();
        for (ThreadReturn t : twoThreadReturn){
            twoThreadReturnSum.setRunTime(twoThreadReturnSum.getRunTime() + t.getRunTime());
            twoThreadReturnSum.setSum(twoThreadReturnSum.getSum() + t.getSum());
        }
        long endTime  = System.currentTimeMillis();
        twoThreadReturnSum.setRunTime(twoThreadReturnSum.getRunTime() + endTime - startTime);
        System.out.println("two thread: runtime = " + twoThreadReturnSum.getRunTime() + "ms， sum  = "+ twoThreadReturnSum.getSum());

        //用四个线程计算
        ThreadReturn[] fourThreadReturn = new ThreadReturn[4];
        for (int i = 0; i < fourThreadReturn.length; i++) {
            fourThreadReturn[i] = new ThreadReturn();
        }
        Thread[] t4 = new Thread[4];
        for (int i = 0; i < 4; i++) {
            t4[i] = new Thread(new MyRunnable(i + 1,4, fourThreadReturn[i]));
            t4[i].start();
            t4[i].join();
        }
        ThreadReturn fourThreadReturnSum = new ThreadReturn();
        long startTime2 = System.currentTimeMillis();
        for (ThreadReturn t : fourThreadReturn){
            fourThreadReturnSum.setRunTime(fourThreadReturnSum.getRunTime() + t.getRunTime());
            fourThreadReturnSum.setSum(fourThreadReturnSum.getSum() + t.getSum());
        }
        long endTime2  = System.currentTimeMillis();
        twoThreadReturnSum.setRunTime(fourThreadReturnSum.getRunTime() + endTime - startTime);
        System.out.println("four thread: runtime = " + fourThreadReturnSum.getRunTime() + "ms， sum  = "+ fourThreadReturnSum.getSum());

    }
}
