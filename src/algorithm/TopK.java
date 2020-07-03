package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/*
取TopK数方法：
    1）全局排序
    2）局部排序
    3）堆（红黑树）
    4）随机选择+partition
 */
public class TopK {

    // 借助堆的取TopK方法
    public static List<Integer> getTopKbyHeap(int[] arr, int k, boolean getMax) {
        if (arr == null) {
            return null;
        }
        if (arr.length <= k) {
            return Arrays.stream(arr).boxed().collect(Collectors.toList());
        }
        // 如果取最大的k个值，就使用小顶堆，优先淘汰堆里最小的一个值；，如果取最小的k，使用大顶堆
        PriorityQueue<Integer> heap = getMax
            ? new PriorityQueue<>(k)
            : new PriorityQueue<>(k, Comparator.reverseOrder());           //写法三

//            : new PriorityQueue<>((o1, o2) -> o2.compareTo(o1));      写法二

//            : new PriorityQueue<>(new Comparator<Integer>() {         写法一
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return o1 > o2 ? -1 : o1 < o2 ? 1 : 0;
//            }
//        })

        // 先将堆填满
        for (int i = 0; i < k; i++) {
            heap.offer(arr[i]);
        }

        // 然后剩余的数与堆中最值比较，合适的替换然后进堆
        if (getMax) {
            int minInHeap;       // 小顶堆的最小值，时间复杂度O(1)
            for (int i = k; i < arr.length; i++) {
                minInHeap = heap.peek();
                if (minInHeap < arr[i]) {
                    heap.poll();
                    heap.offer(arr[i]);       // 删除加入替换掉，时间复杂度O(logk)
                }
            }
        } else {
            int maxInHeap;       // 大顶堆的最大值，时间复杂度O(1)
            for (int i = k; i < arr.length; i++) {
                maxInHeap = heap.peek();
                if (maxInHeap > arr[i]) {
                    heap.poll();
                    heap.offer(arr[i]);       // 删除加入替换掉，时间复杂度O(logk)
                }
            }
        }

        return heap.stream().collect(Collectors.toList());
    }

    // 随机选取+parition方法
    public static List<Integer> getTopKbyPartition(int[] arr, int k, boolean ifGetMax){
        if (arr == null) {
            return null;
        }
        if (arr.length <= k) {
            return Arrays.stream(arr).boxed().collect(Collectors.toList());
        }

        int targetIndex = ifGetMax ? arr.length - k -1 : k;
        partition(arr, 0, arr.length - 1, targetIndex);
        List<Integer> res = new ArrayList<>();
        if (ifGetMax){
            for (int i = targetIndex + 1; i < arr.length; i++) {
                res.add(arr[i]);
            }
        }else {
            for (int i = 0; i < targetIndex; i++) {
                res.add(arr[i]);
            }
        }
        return res;
    }

    private static void swap(int[] arr, int a, int b){
        if (a == b){
            return;
        }
        arr[a] = arr[a] ^ arr[b];
        arr[b] = arr[a] ^ arr[b];
        arr[a] = arr[a] ^ arr[b];
    }

    private static void partition(int[] arr, int left, int right, int targetIndex){
        if (left >= right){
            return;
        }
        int randomIndex = left + (int)(Math.random() * (right - left + 1));     // 随机选取一个基准值划分数组
        int base = arr[randomIndex];
        int less = left - 1;
        int great = right + 1;
        swap(arr, left, randomIndex);
        int i = left + 1;
        while (i < great){
            if (arr[i] <= base){
                swap(arr, i++, ++less);
            }else {
                swap(arr, i, --great);
            }
        }
        i = i - 1;      // 基准值所放的地方

        if (targetIndex == i){
            return;
        }
        if (targetIndex > i){
            partition(arr, i + 1, right, targetIndex);
        }else {
            partition(arr, left, i - 1, targetIndex);
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 5, 4, 6, 2, 3};
        System.out.println(getTopKbyHeap(arr, 3, false));

        int[] arr2 = {1, 5, 4, 3, 2, 3};
        System.out.println(getTopKbyHeap(arr2, 3, true));

        int[] arr3 = {1, 5, 4, 6, 2, 3};
        System.out.println(getTopKbyPartition(arr, 3, false));

        int[] arr4 = {1, 5, 4, 3, 2, 3};
        System.out.println(getTopKbyPartition(arr2, 3, true));
    }
}
