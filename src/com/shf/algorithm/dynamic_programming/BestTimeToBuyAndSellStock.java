package com.shf.algorithm.dynamic_programming;

/**
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/
 * 121. 买卖股票的最佳时机
 *
 * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
 *
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
 *
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 */
public class BestTimeToBuyAndSellStock {

    /**
     * 方法一：暴力法
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int maxProfit = 0;

        for (int i = 0; i < prices.length-1; i++) {
            for (int j = i; j < prices.length; j++) {
                int currProfit = prices[j] - prices[i];
                maxProfit = Math.max(maxProfit, currProfit);
            }
        }
        return maxProfit;
    }

    /**
     * 方法二：动态规划
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
//        定义状态：保存到目前为止的最小价格和最大利润
        int minPrice = Integer.MAX_VALUE;
        int maxProfix = 0;

//        遍历数组元素，以当前价格作为卖出点进行比较
        for (int i = 0; i < prices.length; i++) {
            minPrice = Math.min(minPrice, prices[i]);
            maxProfix = Math.max(maxProfix, prices[i] - minPrice);
        }
        return maxProfix;
    }
    public static void main(String[] args) {
        int[] prices1 = {7, 1, 5, 3, 6, 4};
        int[] prices2 = {7, 6, 4, 3, 1};

        BestTimeToBuyAndSellStock bestTimeToBuyAndSellStock = new BestTimeToBuyAndSellStock();
        System.out.println(bestTimeToBuyAndSellStock.maxProfit2(prices1));
        System.out.println(bestTimeToBuyAndSellStock.maxProfit2(prices2));
    }
}
