package com.atguigu.algorithm.dynamic_programming;

public class HouseRobber {
    // 动态规划
    public int rob1(int[] nums){
        // 特殊情况
        if (nums == null || nums.length == 0) return 0;

        int n = nums.length;

        // 定义状态，将前i个房屋能偷到的最大金额保存到dp[i]
        int[] dp = new int[n+1];

        // 定义初始状态
        dp[0] = 0;
        dp[1] = nums[0];

        // 遍历状态，依次转移
        for (int i = 2; i <= n; i++){
            dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i-1]);
        }

        return dp[n];
    }

    // 空间优化
    public int rob(int[] nums){
        // 特殊情况
        if (nums == null || nums.length == 0) return 0;

        int n = nums.length;

        // 定义初始状态
        int pre2 = 0;
        int pre1 = nums[0];


        // 遍历状态，依次转移
        for (int i = 1; i < n; i++){
            int curr = Math.max(pre1, pre2 + nums[i]);
            pre2 = pre1;
            pre1 = curr;
        }

        return pre1;
    }

    public static void main(String[] args) {
        int[] nums = {2,7,9,3,1};
        int[] nums2 = {2,7,1,3,9};

        HouseRobber houseRobber = new HouseRobber();

        System.out.println(houseRobber.rob(nums));
        System.out.println(houseRobber.rob(nums2));
    }
}
