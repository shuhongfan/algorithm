package com.shf.algorithm.dynamic_programming;

/**
 * https://leetcode.cn/problems/house-robber/
 * 198. 打家劫舍
 * <p>
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * <p>
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 */
public class HouseRobber {
    /**
     * 动态规划
     *
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        int n = nums.length;
        if (nums == null || n == 0) {
            return 0;
        }

        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = nums[0];

//        遍历状态，依次转移
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i - 1]);
        }

        return dp[n];
    }

    /**
     * 动态规划空间优化
     *
     * @param nums
     * @return
     */
    public int rob2(int[] nums) {
        int n = nums.length;
        if (nums == null || n == 0) {
            return 0;
        }

        int pre2 = 0;
        int pre1 = nums[0];

//        遍历状态，依次转移
        for (int i = 1; i < n; i++) {
            int curr = Math.max(pre1, pre2 + nums[i]);
            pre2 = pre1;
            pre1 = curr;
        }
        return pre1;
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 9, 3, 1};
        HouseRobber houseRobber = new HouseRobber();
        System.out.println(houseRobber.rob(nums));
    }
}
