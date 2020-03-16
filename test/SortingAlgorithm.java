import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Collectors;
import org.junit.Test;

public class SortingAlgorithm {

    int[] array = {5, 4, 3, 2, 1,7,9,0};
    //将该序列升序排序

    private void showArray() {
        System.out.println(Arrays.stream(array).boxed().collect(Collectors.toList()));
    }

    private void swap(int x, int y) {
        int temp = array[x];
        array[x] = array[y];
        array[y] = temp;
    }

    //冒泡排序
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

    //插入排序
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

    //快速排序
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
        quickSort(0, array.length - 1);
        showArray();
    }

    //堆排序，升序使用最大堆，降序使用最小堆,此处用升序
    private void sink(int i, int length) {
        int index;                    //要与父节点进行比较的子结点的索引
        //i是否非叶子节点，length/2 -1 为最后一个非叶子结点的位置
        if (i <= (length / 2 - 1)) {
            if ((2 * i + 2) < length) {
                index = (array[2 * i + 1] > array[2 * i + 2]) ? (2 * i + 1) : (2 * i + 2);
            } else {
                index = 2 * i + 1;
            }
            //子结点跟父结点进行比较
            if (array[i] < array[index]) {
                swap(i, index);
                //交换了，则子结点的树结构可能会被扰乱了，需要重新调整
                if (index <= (length / 2 - 1)) {
                    sink(index, length);
                }
            }
        }
    }

    @Test
    public void heapSorting() {
        //先构造最大堆
        for (int i = (array.length / 2 - 1); i >= 0; i--) {
            sink(i, array.length);
        }
        for (int i = 0; i < array.length - 1; i++) {
            showArray();
            swap(0, array.length - i - 1);
            sink(0, array.length - i - 1);
        }
        showArray();
    }
}
