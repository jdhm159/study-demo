import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import org.junit.Test;

public class CollectionTest {

    @Test
    public void foo(){

        Set<Integer> set = new HashSet<>();
        set.add(null);

        Set<Integer> set1 = new TreeSet<>();
        set.add(null);

        Map<String,String> map = new HashMap<>();
        map.put(null,null);

        //TreeMap不允许插入键值为null的对
        TreeMap<String,String> map1 = new TreeMap<>();
//        map1.put(null,null);
        map1.put("",null);


        //ConcurrentHashMap同样对null零容忍
        ConcurrentHashMap<String,String> concurrentHashMap = new ConcurrentHashMap<>();
//        concurrentHashMap.put(null,null);
        concurrentHashMap.put("","");

        //HashTable对null零容忍
        Hashtable<String,String> hashtable = new Hashtable<>();
//        hashtable.put(null,"");       //都会报出
//        hashtable.put("",null);       //空指针 NullPointerException
        hashtable.put("","");

    }
}
