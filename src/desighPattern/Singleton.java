package desighPattern;

public class Singleton {

    //构造方式私有化
    private Singleton(){
    }

    //静态属性指向实例
    private static final Singleton singleton = new Singleton();

    //饿汉式
    public static Singleton getInstance(){
        return singleton;
    }

    public boolean s(){
        return singleton.singleton == singleton;            //true
    }

    public static void main(String[] args) {
        String a = "xiaomeng2";
        final String b = "xiaomeng";
        String d = "xiaomeng";
        String c = b + 2;
        String e = d + 2;
        System.out.println((a == c));
        System.out.println((a == e));
    }

}
