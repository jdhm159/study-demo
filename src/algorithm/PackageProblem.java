package algorithm;

import java.util.List;
import java.util.stream.Collectors;

//未经验证
public class PackageProblem {

    static Double maxValue = Double.MIN_VALUE;
    static Double weightLoad;
    static List<Good> goods;

    static class Good {

        private Double value;
        private Double weight;

        public Good(Double value, Double weight) {
            this.value = value;
            this.weight = weight;

        }

        public Double getValue() {
            return value;
        }

        public Double getWeight() {
            return weight;
        }
    }

    public static Double solvePackageProblemByBacktracking(List<Good> goodList, Double weightLoad) {
        if (goods == null) {
            throw new RuntimeException("Goods input error: null!");
        }
        if (weightLoad == null || weightLoad < 0.0) {
            throw new RuntimeException("WeightLoad input error!");
        }
        PackageProblem.goods = goodList;
        PackageProblem.weightLoad = weightLoad;

        // 按重量从小到大重新排序
        goods = goods.stream().sorted((s1, s2) -> s1.getWeight().compareTo(s2.getWeight())).collect(Collectors.toList());

        // 采用子集树结构的回溯法
        // 使用方法递归实现深度遍历
        pickGoodByBacktracing(0.0, weightLoad, 0);

        return maxValue;
    }

    /**
     * 深度遍历子集树获得最大价值
     *
     * @param curValueSum 当前物品价值总和
     * @param leftWeightLoad 当前剩余背包负载
     * @param i 当前处于数的高度
     */
    public static void pickGoodByBacktracing(Double curValueSum, Double leftWeightLoad, int i) {
        // 处于子集树的第i层（从0层开始），决定对索引为i的物品的选择
        Good good = goods.get(i);
        if (Double.compare(leftWeightLoad, good.getWeight()) > 0) {
            if (i < goods.size() - 1) {
                pickGoodByBacktracing(curValueSum + good.getValue(), leftWeightLoad - good.getWeight(), i + 1);
                pickGoodByBacktracing(curValueSum, leftWeightLoad, i + 1);
            } else {
                maxValue = Double.max(curValueSum + good.getValue(), maxValue);
            }
        } else {
            maxValue = Double.max(curValueSum, maxValue);
        }
    }
}
