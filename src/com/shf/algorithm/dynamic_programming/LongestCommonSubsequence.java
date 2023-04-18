package com.shf.algorithm.dynamic_programming;

/**
 * https://leetcode.cn/problems/longest-common-subsequence/
 * 1143. 最长公共子序列
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
 *
 * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 *
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
 * 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
 */
public class LongestCommonSubsequence {
    public int longestCommonSubsequence(String text1, String text2) {
        int l1 = text1.length();
        int l2 = text2.length();

//        定义一个二维矩阵
        int[][] dp = new int[l1 + 1][l2 + 1];

//        遍历所有状态，递推求解
        for (int i = 1; i <= l1; i++) {
            for (int j = 1; j <= l2; j++) {
//                判断两个新增字符关系，进行状态转移
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[l1][l2];
    }

    public static void main(String[] args) {
        String str1 = "abcde";
        String str2 = "ace";

        LongestCommonSubsequence longestCommonSubsequence = new LongestCommonSubsequence();
        System.out.println(longestCommonSubsequence.longestCommonSubsequence(str1, str2));
    }
}
