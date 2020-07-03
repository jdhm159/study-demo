package algorithm;

public class StringMatch {

    // 暴力算法 BF（Brute Force）
    public static int bruteForce(String mainStr, String pattern) {
        if (mainStr == null || pattern == null) {
            throw new RuntimeException("输入主字符串或匹配字串为空");
        }
        int mainStrLength = mainStr.length();
        int patternLength = pattern.length();
        if (mainStrLength < patternLength) {
            return -1;
        }
        int i = 0, j = 0;
        while (i < mainStrLength && j < patternLength) {
            // 如果当前字符相匹配
            if (mainStr.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            } else {
                // 不匹配就从i的下一位继续从头匹配
                i = i - j + 1;
                j = 0;  // 从头匹配
            }
        }
        // 如果匹配到字串
        if (j == patternLength) {
            return i - j;           // 返回匹配字串的第一个字符的所有（从0开始）
        }
        return -1;
    }

    // 最长匹配长度表（前缀和后缀一致时最大的长度）
    private static int[] getNextArray(String pattern) {
        int[] next = new int[pattern.length()];
        next[0] = 0;
        int i = 0, j = 1;
        while (j < pattern.length()) {
            if (pattern.charAt(i) == pattern.charAt(j)) {
                next[j] = i + 1;
                i++;
            } else {
                while (i > 0 && pattern.charAt(i) != pattern.charAt(j)) {                     // 找到i前一个
                    i = next[i - 1];
                }

                if (pattern.charAt(i) != pattern.charAt(j)) {
                    next[j] = i + 1;
                    i++;
                } else {
                    next[j] = 0;
                }
            }
            j++;    //每次计算next[j]
        }
        return next;
    }

    public static int KMPmatch(String mainStr, String pattern) {
        if (mainStr == null || pattern == null) {
            throw new RuntimeException("输入主字符串或匹配字串为空");
        }
        int mainStrLength = mainStr.length();
        int patternLength = pattern.length();
        if (mainStrLength < patternLength) {
            return -1;
        }
        int i = 0, j = 0;
        int[] next = getNextArray(pattern);
        while (i < mainStrLength && j < patternLength) {
            // 如果当前字符相匹配
            if (mainStr.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            } else {
                // 如果已经成功匹配一部分了
                if (j > 0) {
                    j = next[j - 1];
                }else {
                    // 没有一致前缀和后缀，那就匹配i的下一位
                    i++;
                }
            }
        }
        // 如果匹配到字串
        if (j == patternLength) {
            return i - j;           // 返回匹配字串的第一个字符的所有（从0开始）
        }
        return -1;
    }

    public static void main(String[] args) {
        // 功能测试
        System.out.println("BF：");
        System.out.println(bruteForce("abcabcd", "abc"));
        System.out.println(bruteForce("abcabcd", "abcd"));
        System.out.println(bruteForce("abcabcd", "abce"));
        // 特殊输入测试（鲁棒性测试）
//        System.out.println(bruteForce(null, null));
//        System.out.println(bruteForce("abc", "abcd"));
//        System.out.println(bruteForce("abc", null));
        // 性能测试 与 边界值测试         略

        System.out.println("KMP：");
        System.out.println(KMPmatch("abcabcd", "abc"));
        System.out.println(KMPmatch("abcabcd", "abcd"));
        System.out.println(KMPmatch("abcabcd", "abce"));
        System.out.println(KMPmatch("abcabcd", "cab"));
    }
}
