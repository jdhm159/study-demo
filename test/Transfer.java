import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Test;

public class Transfer {

    @Test
    public void transfer() {

        int[] ints = {4, 5, 3, 6, 2, 5, 1};

        List<Integer> integerList = Arrays.asList(4, 2, 5, 6, 2, 1);

        Integer[] integers = {new Integer(2),new Integer(4),new Integer(6)};    //可以交付编译器自动装箱

        //int[]->List<Integer>
        List<Integer> integerList1 = Arrays.stream(ints).boxed().collect(Collectors.toList());
//        List<Integer> list2 = Arrays.asList(ints);        这样是错误的，创建的是List<int[]>

        //int[]->Integer[]
        Integer[] integers1 = Arrays.stream(ints).boxed().toArray(Integer[]::new);

        //List<Integer>->Integer[]
        Integer[] integers2 = integerList.toArray(new Integer[0]);

        //List<Integer>->int[]
        int[] ints1 = integerList.stream().mapToInt(Integer::valueOf).toArray();

        //Integer[]->int[]
        int[] ints2 = Arrays.stream(integers).mapToInt(Integer::intValue).toArray();

        //Integer[]->List<Integer>
        List<Integer> integerList2 = Arrays.asList(integers);
    }
}
