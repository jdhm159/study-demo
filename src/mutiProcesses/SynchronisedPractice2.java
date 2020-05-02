package mutiProcesses;

import java.util.Scanner;

// 实践一、主线程循环等待用户输入一个整数，两次输入之间sleep(5)秒。
// 创建4个子线程，子线程等待主线程中用户输入的数，等到后输出（自己线程的编号，该整数的值）；
// 如果该数正好等于自己的编号，自己结束退出，否则继续等待。
public class SynchronisedPractice2 {

    static Scanner scanner = new Scanner(System.in);
    static String input = "";
    static Object objectLock = new Object();  //作为对象锁使用

    public static class SonThread extends Thread {

        String rank;

        SonThread(String rank) {
            this.rank = rank;
        }

        @Override
        public void run() {
            checkInput();
        }

        public void checkInput() {
            synchronized (objectLock) {
                while (true) {
                    System.out.println("thread rank: " + rank + " input = " + input);
                    if (!input.equals(rank)) {
                        try {
                            objectLock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    } else {
                        System.out.println("Thread " + rank + "结束！");
                        break;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        for (int i = 1; i <= 4; i++) {
            Thread thread = new SonThread(i + "");
            thread.start();
        }

        while ((input = scanner.nextLine()) != "q") {
            synchronized (objectLock) {
                objectLock.notifyAll();
            }
            Thread.sleep(2000);
            // 因为线程数已知，这个例子可以通过设立线程结束标志，检查线程是否都结束了，然后主线程接着结束
        }
    }
}
