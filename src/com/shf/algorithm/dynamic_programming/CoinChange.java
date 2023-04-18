package com.shf.algorithm.dynamic_programming;

/**
 * https://leetcode.cn/problems/coin-change/
 * 322. 零钱兑换
 *
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 *
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 *
 * 你可以认为每种硬币的数量是无限的。
 */
public class CoinChange {
    /**
     * 动态规划
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        int n = coins.length;
        int[] dp = new int[amount + 1];
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            int minCoinNum = Integer.MAX_VALUE;

//            遍历所有硬币面值，作为可能的“最后一步”
            for (int coin : coins) {
                if (coin <= i && dp[i - coin] != -1) {
                    minCoinNum = Math.min(minCoinNum, dp[i - coin] + 1);
                }
            }
            dp[i] = minCoinNum == Integer.MAX_VALUE ? -1 : minCoinNum;
        }
        return dp[amount];
    }

    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int amount = 11;

        CoinChange coinChange = new CoinChange();
        System.out.println(coinChange.coinChange(coins, amount));
    }
}
