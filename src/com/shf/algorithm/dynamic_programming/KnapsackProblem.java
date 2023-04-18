package com.shf.algorithm.dynamic_programming;

/**
 * 0-1 背包问题
 * 有一个背包，容量是 W=150，有 7 个物品，物品不可分割。要求尽可
 * 能让装入背包中的物品总价值最大，但不能超过总容量
 */
public class KnapsackProblem {
    /**
     * 动态规划实现
     * @param capacity
     * @param weights
     * @param values
     * @return
     */
    public int maxValue(int capacity, int[] weights, int[] values) {
        int n = weights.length;

//        定义状态
        int[][] dp = new int[n + 1][capacity + 1];

//        遍历所有的子问题，依次计算状态
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= capacity; j++) {
//                判断当前的背包容量j是否能放下物品i
                if (j >= weights[i - 1]) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weights[i - 1]] + values[i - 1]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[n][capacity];
    }

    /**
     * 动态规划方法优化
     * @param capacity
     * @param weights
     * @param values
     * @return
     */
    public int maxValue2(int capacity, int[] weights, int[] values) {
        int n = weights.length;

//        定义状态
        int[] dp = new int[capacity + 1];

//        遍历所有的子问题，依次计算状态
        for (int i = 1; i <= n; i++) {
            for (int j = capacity; j >0; j--) {
//                判断当前的背包容量j是否能放下物品i
                if (j >= weights[i - 1]) {
                    dp[j] = Math.max(dp[j], dp[j - weights[i - 1]] + values[i - 1]);
                }
            }
        }

        return dp[capacity];
    }

    public static void main(String[] args) {
        int W = 150;
        int[] w = {35, 30, 60, 50, 40, 10, 25};
        int[] v = {10, 40, 30, 50, 35, 40, 30};

        KnapsackProblem knapsackProblem = new KnapsackProblem();
        System.out.println(knapsackProblem.maxValue2(W, w, v));
    }
}
