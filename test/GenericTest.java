import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import org.junit.Test;

public class GenericTest {
    @Test
    public void foo(){
        List<String> sd = new ArrayList<>();

        Class s = Integer.class;
        s = String[].class;
//        s = List<String>.class;   //不合法
        s = sd.getClass();

    }
}
