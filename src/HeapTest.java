import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;


/**
 * 实现最大堆
 */
public class HeapTest<T extends Comparable> {

    private List<T> arrayList;
    private int size;

    /**
     * 因为泛型类型擦除，无法获得 泛型T 的类型，也就是说实例化泛型类型变量 new T()这样是错误的 需要让调用者传递构造器表达式或者具体类型Class。 通过构造器引用或者反射调用Class.newInstance来构造泛型对象
     */
    public HeapTest(Supplier<T> constr) {
        arrayList = new ArrayList<>();
        T t = constr.get();
        arrayList.add(t);   //占用掉索引为0的位置
        size = 0;
    }

    public void add(T i) {
        arrayList.add(i);
        swim(++size);
    }

    private void swim(int i) {
        while (i > 1 && compare(i, i >> 1) > 0) {
            swap(i, (i >> 1));
            i >>= 1;
        }
    }

    private int compare(int a, int b) {
        return arrayList.get(a).compareTo(arrayList.get(b));
    }

    private void swap(int a, int b) {
        T temp = arrayList.get(a);
        arrayList.set(a, arrayList.get(b));
        arrayList.set(b, temp);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size(){
        return size;
    }

    @Override
    public String toString() {
        StringBuffer sB = new StringBuffer();
        sB.append("[");
        for (int i = 1; i < size; i++) {
            sB.append(" " + arrayList.get(i) + ",");
        }
        sB.append(" " + arrayList.get(size));
        sB.append("]");
        return sB.toString();
    }

    public T remove() {
        T max = arrayList.get(1);
        arrayList.set(1, arrayList.remove(size--));
        sink(1);
        return max;
    }

    private void sink(int i) {
        int tempIndex = i;
        int indexToComp;
        while ((i = i * 2) <= size) {
            if (i < size) {
                indexToComp = compare(i, i + 1) > 0 ? i : ++i;
            } else {
                indexToComp = i;
            }
            if (compare(tempIndex, indexToComp) >= 0) {
                break;
            }
            swap(tempIndex, indexToComp);
            tempIndex = i;
        }

    }

    public static final void main(String[] args) {
//        HeapTest<Integer> heapTest = new HeapTest<Integer>(Integer::new);     //Integer不存在默认无参构造器
//        Integer integer = new Integer();                                      //这样会显示找不到构造器，Integer::new这样的构造器引用是错误的
        HeapTest<Integer> heapTest = new HeapTest<Integer>(() -> 0);        //只能老老实实写lambda表达式
        heapTest.add(1);
        System.out.println(heapTest.toString());
        heapTest.add(15);
        System.out.println(heapTest.toString());
        heapTest.add(4);
        System.out.println(heapTest.toString());
        heapTest.add(50);
        System.out.println(heapTest.toString());
        heapTest.add(-1);
        System.out.println(heapTest.toString());
        heapTest.add(50);
        System.out.println(heapTest.toString());
        heapTest.add(60);
        System.out.println(heapTest.toString());

        heapTest.remove();
        System.out.println(heapTest.toString());
        heapTest.remove();
        System.out.println(heapTest.toString());
        heapTest.remove();
        System.out.println(heapTest.toString());
        heapTest.remove();
        System.out.println(heapTest.toString());

        heapTest.add(100);
        System.out.println(heapTest.toString());

    }
}
