import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Collectors;
import org.junit.Test;

public class SortingAlgorithm {

    int[] array = {5, 4, 3, 2, 1};


    private void showArray() {
        System.out.println(Arrays.stream(array).boxed().collect(Collectors.toList()));
    }

    private void swap(int x, int y) {
        int temp = array[x];
        array[x] = array[y];
        array[y] = temp;
    }

    @Test
    public void bubbleSorting() {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    swap(j, j + 1);
                }
            }
        }
        showArray();
    }

    @Test
    public void bestBubbleSorting() {
        boolean didSwap;       //优化的冒泡排序，最好情况下时间复杂度为O(n)
        for (int i = 0; i < array.length - 1; i++) {
            didSwap = false;
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    swap(j, j + 1);
                    didSwap = true;
                }
            }
            if (!didSwap) {
                return;
            }
        }
        showArray();
    }

    @Test
    public void insertSorting() {
        for (int i = 1; i < array.length; i++) {
            int temp = array[i];    //抽取的元素
            int p = i - 1;            //当前跟抽取元素比较的索引
            while (p >= 0 && temp < array[p]) {
                array[p + 1] = array[p];
                p--;
            }
            array[p + 1] = temp;
        }
        showArray();
    }

    private void quickSort(int from, int to) {
        if (from < to) {
            int first = array[from];        //基准值
            int low = from;
            int high = to;
            while (low != high) {
                while (array[high] > first && high > low) {
                    high--;
                }
                array[low] = array[high];

                while (array[low] < first && high > low) {
                    low++;
                }
                array[high] = array[low];
            }
            array[low] = first;
            quickSort(from, low - 1);
            quickSort(low + 1, to);
        }

    }

    @Test
    public void quickSorting() {
        quickSort(0,array.length-1);
        showArray();
    }

}
