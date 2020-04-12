package simple_fun.algorithm.DP.linear_dp;

/**
 * @Description: 线性动态规划的问题汇总
 * @Author: 67ng
 * @Date: 2020/4/11
 */
public class Solution {

    /**
     * @Name: 300.最长上升子序列
     * @Description: 给定一个无序的整数数组，找到其中最长上升子序列的长度。
     * @Linked: https://leetcode-cn.com/problems/longest-increasing-subsequence/solution/zui-chang-shang-sheng-zi-xu-lie-by-leetcode-soluti/
     */
    //DP，时间复杂度O(n^2)，空间复杂度O(n)
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0)
            return 0;

        int[] dp = new int[nums.length];//dp[j] 代表nums[0...j]中以nums[j]结尾的最长上升子序列
        dp[0] = 1;
        int maxans = 1;
        for (int i = 1; i < dp.length; i++) {
            int maxval = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    maxval = Math.max(maxval, dp[j]);
                }
            }
            dp[i] = maxval + 1;//状态转移方程dp[i]=max(dp[j])+1
            maxans = Math.max(maxans, dp[i]);//LIS = max(dp[i]),其中0≤i<n
        }
        return maxans;
    }

    //贪心和二分查找，时间复杂度O(nlogn)，空间复杂度O(n)
    public int lengthOfLIS2(int[] nums) {
        int len = 1, n = nums.length;
        if (n == 0) return 0;
        int[] d = new int[n + 1];//d[i] 表示长度为 i 的最长上升子序列的末尾元素的最小值
        d[len] = nums[0];
        for (int i = 1; i < n; ++i) {
            if (nums[i] > d[len]) {
                d[++len] = nums[i];//如果nums[i] > d[len]，则直接加入到d数组末尾，并更新len=len+1
            } else {
                int l = 1, r = len, pos = 0; // 如果找不到说明所有的数都比 nums[i] 大，此时要更新 d[l]，所以这里将 pos 设为 0
                while (l <= r) {
                    int mid = (l + r) >> 1;
                    if (d[mid] < nums[i]) {
                        pos = mid;
                        l = mid + 1;
                    } else r = mid - 1;
                }
                d[pos + 1] = nums[i];
            }
        }
        return len;
    }

    /**
     * @Name: 1143.最长公共子序列
     * @Description: 给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子序列。
     * @Linked: https://leetcode-cn.com/problems/longest-common-subsequence/solution/a-fei-xue-suan-fa-zhi-by-a-fei-8/
     */
    public int longestCommonSubsequence(String text1, String text2) {
        if (text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0) return 0;
        int m = text1.length(), n = text2.length();
        int[][] dp = new int[m + 1][n + 1];//dp[i][j]表示索引分别为i、j的s1和s2的LCS长度
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                else
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
            }
        }
        return dp[m][n];
    }

    /**
     * @Name: 72.编辑距离
     * @Description: 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
     * 你可以对一个单词进行如下三种操作：
     * 1.插入一个字符
     * 2.删除一个字符
     * 3.替换一个字符
     * @Linked: https://leetcode-cn.com/problems/edit-distance/
     */

    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        // 有一个字符串为空串
        if (n * m == 0)
            return n + m;
        // DP 数组，用 D[i][j] 表示 A 的前 i 个字母和 B 的前 j 个字母之间的编辑距离。
        int[][] D = new int[n + 1][m + 1];
        // 边界状态初始化
        for (int i = 0; i < n + 1; i++) //字符串 A 为空，如从空转换到 ro，显然编辑距离为字符串 B 的长度，这里是 2；
            D[i][0] = i;
        for (int j = 0; j < m + 1; j++) //同上理
            D[0][j] = j;

//        状态转移方程：
//        若 A 和 B 的最后一个字母相同：
//        D[i][j] =min(D[i][j−1]+1,D[i−1][j]+1,D[i−1][j−1])
//        若 A 和 B 的最后一个字母不同：
//        D[i][j]=1+min(D[i][j−1],D[i−1][j],D[i−1][j−1])

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                int left = D[i - 1][j] + 1;
                int down = D[i][j - 1] + 1;
                int left_down = D[i - 1][j - 1];
                if (word1.charAt(i - 1) != word2.charAt(j - 1))
                    left_down += 1;
                D[i][j] = Math.min(left, Math.min(down, left_down));
            }
        }
        return D[n][m];
    }

    /**
     * @Name: 44.通配符匹配
     * @Description: 给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。
     * '?' 可以匹配任何单个字符。
     * '*' 可以匹配任意字符串（包括空字符串）。
     * @Linked: https://leetcode-cn.com/problems/wildcard-matching/
     */
    //DP，时间复杂度O(S*P)，空间复杂度O(S*P)
    public boolean isMatch(String s, String p) {
        int sLen = s.length(), pLen = p.length();

        // base cases
        if (p.equals(s) || p.equals("*")) return true;
        if (p.isEmpty() || s.isEmpty()) return false;

        // init all matrix except [0][0] element as False
        boolean[][] d = new boolean[pLen + 1][sLen + 1];
        d[0][0] = true;

        // DP compute
        for (int pIdx = 1; pIdx < pLen + 1; pIdx++) {
            // the current character in the pattern is '*'
            if (p.charAt(pIdx - 1) == '*') {
                int sIdx = 1;
                // d[p_idx - 1][s_idx - 1] is a string-pattern match
                // on the previous step, i.e. one character before.
                // Find the first idx in string with the previous math.
                while ((!d[pIdx - 1][sIdx - 1]) && (sIdx < sLen + 1)) sIdx++;
                // If (string) matches (pattern),
                // when (string) matches (pattern)* as well
                d[pIdx][sIdx - 1] = d[pIdx - 1][sIdx - 1];
                // If (string) matches (pattern),
                // when (string)(whatever_characters) matches (pattern)* as well
                while (sIdx < sLen + 1) d[pIdx][sIdx++] = true;
            }
            // the current character in the pattern is '?'
            else if (p.charAt(pIdx - 1) == '?') {
                for (int sIdx = 1; sIdx < sLen + 1; sIdx++)
                    d[pIdx][sIdx] = d[pIdx - 1][sIdx - 1];
            }
            // the current character in the pattern is not '*' or '?'
            else {
                for (int sIdx = 1; sIdx < sLen + 1; sIdx++) {
                    // Match is possible if there is a previous match
                    // and current characters are the same
                    d[pIdx][sIdx] = d[pIdx - 1][sIdx - 1] &&
                            (p.charAt(pIdx - 1) == s.charAt(sIdx - 1));
                }
            }
        }
        return d[pLen][sLen];
    }

    //回溯，时间复杂度O(SlogP)，空间复杂度O(1)
    public boolean isMatch2(String s, String p) {
        int sLen = s.length(), pLen = p.length();
        int sIdx = 0, pIdx = 0;
        int starIdx = -1, sTmpIdx = -1;

        while (sIdx < sLen) {
            // If the pattern caracter = string character
            // or pattern character = '?'
            if (pIdx < pLen && (p.charAt(pIdx) == '?' || p.charAt(pIdx) == s.charAt(sIdx))) {
                ++sIdx;
                ++pIdx;
            }
            // If pattern character = '*'
            else if (pIdx < pLen && p.charAt(pIdx) == '*') {
                // Check the situation
                // when '*' matches no characters
                starIdx = pIdx;
                sTmpIdx = sIdx;
                ++pIdx;
            }
            // If pattern character != string character
            // or pattern is used up
            // and there was no '*' character in pattern
            else if (starIdx == -1) {
                return false;
            }
            // If pattern character != string character
            // or pattern is used up
            // and there was '*' character in pattern before
            else {
                // Backtrack: check the situation
                // when '*' matches one more character
                pIdx = starIdx + 1;
                sIdx = sTmpIdx + 1;
                sTmpIdx = sIdx;
            }
        }

        // The remaining characters in the pattern should all be '*' characters
        for (int i = pIdx; i < pLen; i++)
            if (p.charAt(i) != '*') return false;
        return true;
    }

    public static void main(String[] args) {


    }

}
