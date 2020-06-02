package algorithm;

//大数问题，通过数组来保存解决数值太大，无法用默认的数据结构保存的问题
public class BigNum implements Comparable<BigNum>{

    private static final int BASE = 10000;      //万进位
    private static final int COVERAGE = 1000;   //数组的长度     先预估计算的最大结果，再计算数组的所需长度

    public int len = 0;    //表示数组中使用到的int数，并不一定表示这个大数十进制时的位数
    public int[] value = new int[COVERAGE];  //数值逆序保存在数组中，即数值最低位存放于数组最高位
    public boolean isPositive = true;

    public static BigNum add(BigNum a, BigNum b){
        //一正一负，转为减法运算
        if (a.isPositive != b.isPositive){
            b.isPositive = !b.isPositive;
            return minus(a, b);
        }
        //下面只处理正正和负负加法
        BigNum result = new BigNum();
        int carry = 0;
        int len = 0;
        int aHigh = COVERAGE - a.len;   //a的最高位
        int bHigh = COVERAGE - b.len;   //b的最高位
        //从数组高位进行计算
        int i;
        for (i = COVERAGE - 1; i >= 0 && (i >= aHigh || i >= bHigh); i--){
            carry += a.value[i] + b.value[i];
            result.value[i] = carry % BASE;
            carry /= BASE;
            len++;
        }
        if (carry != 0){
            result.value[i] = carry;
            len++;
        }
        result.len = len;

        //此时为两负数或两整数加法，符号不变
        result.isPositive = a.isPositive;
        return result;
    }

    public static BigNum minus(BigNum a, BigNum b){
        //一正一负，转为加法运算
        if (a.isPositive != b.isPositive){
            b.isPositive = !b.isPositive;
            return add(a, b);
        }
        //下面只负责正正和负负减法处理
        if (a.len < b.len){
            return minus(b, a);
        }else if (a.len == b.len){
            if (a.isPositive && a.compareTo(b) < 0){
                return minus(b, a);
            }
            if (!a.isPositive && a.compareTo(b) > 0){
                return minus(b, a);
            }
        }
        //以下只处理正正或者负负减法，且被减数的绝对值不比减数的绝对值小
        BigNum result = new BigNum();

        int aHigh = COVERAGE - a.len;
        int bHigh = COVERAGE - b.len;
        //从最高位进行运算，应用借位思想
        int i;
        for (i = COVERAGE - 1; i >= bHigh; i--) {
            if (a.value[i] < b.value[i]){
                a.value[i - 1]--;
                a.value[i] += BASE; //借位
            }
            result.value[i] = a.value[i] - b.value[i];
        }

        for (; i >= aHigh ; i--) {
            if (a.value[i] < 0){
                a.value[i - 1]--;
                a.value[i] += BASE;
            }
            result.value[i] = a.value[i];
        }

        for (i++; i < COVERAGE; i++) {
            if (result.value[i] > 0){
                result.len++;
            }
        }

        result.isPositive = a.isPositive;   //结果符号由被减数决定
        return result;
    }


    @Override
    public int compareTo(BigNum bigNum){
        if (isPositive){
            //两正数相比
            if (bigNum.isPositive){
                if (len > bigNum.len){
                    return 1;
                }else if (len < bigNum.len){
                    return -1;
                }else {
                    for (int i = COVERAGE - len; i < COVERAGE; i++) {
                        if (value[i] > bigNum.value[i]){
                            return 1;
                        }else if (value[i] < bigNum.value[i]){
                            return -1;
                        }
                    }
                    return 0;
                }
            }else {
                return 1;
            }
        }else {
            //两负数相比
            if (!bigNum.isPositive){
                if (len > bigNum.len){
                    return -1;
                }else if (len < bigNum.len){
                    return 1;
                }else {
                    for (int i = COVERAGE - len; i < COVERAGE; i++) {
                        if (value[i] > bigNum.value[i]){
                            return -1;
                        }else if (value[i] < bigNum.value[i]){
                            return 1;
                        }
                    }
                    return 0;
                }
            }else {
                return -1;
            }
        }
    }

    @Override
    public String toString(){
        int high = COVERAGE - len;
        StringBuilder builder = new StringBuilder();
        builder.append(isPositive ? "" : "-");
        builder.append(value[high]);
        for (int i = high + 1; i < COVERAGE; i++){
            builder.append(String.format("%04d", value[i]));
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        BigNum a = new BigNum();
        BigNum b = new BigNum();
        a.isPositive = true;
        b.isPositive = true;
        a.value[996] = 1;
        a.value[997] = 2;
        a.value[998] = 1234;
        a.value[999] = 5678;
        a.len = 4;


        b.value[996] = 9999;
        b.value[997] = 1;
        b.value[998] = 1;
        b.value[999] = 6000;
        b.len = 4;

        System.out.println(a.toString());
        System.out.println("+");
        System.out.println(b.toString());
        System.out.println("=");
        System.out.println(BigNum.add(a, b).toString());

        System.out.println();
        BigNum c = new BigNum();
        BigNum d = new BigNum();
        a.isPositive = true;
        b.isPositive = true;
        c.value[997] = 1;
        c.value[998] = 0;
        c.value[999] = 1234;
        c.len = 3;

        d.value[999] = 1235;
        d.len = 1;

        System.out.println(c.toString());
        System.out.println("-");
        System.out.println(d.toString());
        System.out.println("=");
        System.out.println(BigNum.minus(c, d).toString());
    }
}

