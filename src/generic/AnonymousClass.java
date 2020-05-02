package generic;

abstract class AbstractClass {
    abstract void foo();
}

interface AbstractInterface {
    void foo();
}

//匿名内部类是用来创建一个没有类名的类的对象，即这个类也没有构造方法。只用来实例化得到一个对象，后面不再需要实例化。
public class AnonymousClass {

    public static void main(String[] args) {
        AbstractClass abstactClass = new AbstractClass() {
            @Override
            void foo() {
                return;
            }
        };
        abstactClass.foo();

        AbstractInterface abstactInterface = new AbstractInterface() {
            @Override
            public void foo() {
                return;
            }
        };
        abstactInterface.foo();

        //这里不能理解为实例化抽象类与接口，而应该是创建了一个实现了抽象类与接口中抽象方法的类，用这个类实例化了一个对象。
    }

}
