import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import org.junit.Test;

public class IteratorTest {

    @Test
    public void test1() {
        List<String> a = new ArrayList<String>();
        a.add("1");
        a.add("2");
//        for (String temp : a) {
//            if("2".equals(temp)){
//                a.remove(temp);       //这样是不正确的，会爆出ConcurrentModificationException
//            }
//        }
        Iterator<String> itr = a.iterator();
        while (itr.hasNext()) {
            String temp = itr.next();
            if ("1".equals(temp)) {
                itr.remove();       //这样不会
            }
        }
        System.out.println(a.toString());
    }

    @Test
    public void test2() {
        List<String> a = new ArrayList<String>();
        a.add("1");
        a.add("2");
        ListIterator<String> listIterator1 = a.listIterator();
        ListIterator<String> listIterator2 = a.listIterator();

        listIterator1.next();
        listIterator1.set("2"); //ListIterator上的set方法不算是结构上的改变，不会记录到modCount
        listIterator2.next();
        System.out.println(a);

        listIterator1.next();
        listIterator1.remove();
//        listIterator2.next();   //执行到这就会报出ConcurrentModificationException

    }

    @Test
    public void test3(){
        List<String> a = Arrays.asList("1","2");
        ListIterator<String> listIterator = a.listIterator();
//        listIterator.add("2");        //报出UnsupportedOperationException
        listIterator.next();
//        listIterator.add("2");        //报出UnsupportedOperationException
        Object aD = "123";
        aD.hashCode();
        System.out.println(aD.hashCode());
    }
}
