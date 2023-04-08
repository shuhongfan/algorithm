package com.atguigu.algorithm.dynamic_programming;

public class CoinChange {
    // 动态规划
    public int coinChange(int[] coins, int amount){
        int n = coins.length;    // 硬币种类数

        // 定义dp数组，保存金额为i的对应最少硬币数为dp[i]
        int[] dp = new int[amount + 1];

        // 初始状态
        dp[0] = 0;

        // 遍历状态，依次转移
        for (int i = 1; i <= amount; i++){
            // 保留当前硬币的最小数量
            int minNum = Integer.MAX_VALUE;

            // 遍历所有可能的硬币面值，作为凑出i金额的最后一个硬币
            for (int coin: coins){
                // 必须coin不能超过i，并且i-coin的金额可以凑出来
                if (coin <= i && dp[i - coin] != -1){
                    minNum = Math.min(minNum, dp[i - coin] + 1);
                }
            }

            dp[i] = minNum == Integer.MAX_VALUE ? -1 : minNum;
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
