package com.atguigu.algorithm.dynamic_programming;

public class LCS {
    // 动态规划
    public int longestCommonSubSequence(String text1, String text2){
        int l1 = text1.length();
        int l2 = text2.length();

        // 定义一个二维矩阵
        int[][] dp = new int[l1+1][l2+1];

        // 遍历所有状态，递推求解
        for (int i = 1; i <= l1; i++){
            for (int j = 1; j <= l2; j++){
                // 判断两个新增字符关系，进行状态转移
                if (text1.charAt(i-1) == text2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1] );
                }
            }
        }

        return dp[l1][l2];
    }

    public static void main(String[] args) {
        String str1 = "abcde";
        String str2 = "ace";

        LCS lcs = new LCS();
        System.out.println(lcs.longestCommonSubSequence(str1, str2));
        System.out.println(lcs.longestCommonSubSequence("abc", "def"));
    }
}
