import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import org.junit.Test;

public class GenerateRandom {

    @Test
    public void ss() {
        Random random = new Random();
        IntStream ss = random.ints(0,10);
        for(int i : ss.limit(10).toArray()){
            System.out.println(i);
        }
    }
}
