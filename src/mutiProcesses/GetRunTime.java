package mutiProcesses;

public class GetRunTime {

    static void doSomething(){

    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();    //获取开始时间

        doSomething();

        long endTime = System.currentTimeMillis();    //获取结束时间
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时间
    }
}
