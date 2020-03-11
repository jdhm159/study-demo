import java.util.LinkedList;
import java.util.List;

public class OverFlowTest {

    public static final void main(String[] args){
        int a = Integer.MAX_VALUE;
        int b = Integer.MIN_VALUE;
        if (a < b) {
            System.out.println("a < b");
        }
        if (a - b < 0) {
            System.out.println("a - b < 0");    //会成功打印出来
        }
    }

}
