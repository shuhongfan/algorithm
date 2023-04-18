package com.shf.algorithm.dynamic_programming;

/**
 * https://leetcode.cn/problems/climbing-stairs/
 * 70. 爬楼梯
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 *
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 */
public class ClimbingStairs {
    /**
     * 方法一：动态规划
     * @param n
     * @return
     */
    public int climbStairs(int n) {
//        定义两个临时变量，保存前两个状态
        int pre2 = 1, pre1 = 1;
        int curr;

        for (int i = 1; i < n; i++) {
//            定义一个临时变量，保存当前的状态
            curr = pre1 + pre2;
            pre2 = pre1;
            pre1 = curr;
        }
        return pre1;
    }

    /**
     * 方法二：数学公式法
     * @param n
     * @return
     */
    public int climbStairs2(int n) {
        double sqrt_5 = Math.sqrt(5);
        double fib = ( Math.pow((1 + sqrt_5) / 2, n + 1) - Math.pow((1 - sqrt_5) / 2, n + 1) ) / sqrt_5;
        return (int) fib;
    }

    public static void main(String[] args) {
        ClimbingStairs climbingStairs = new ClimbingStairs();
        System.out.println(climbingStairs.climbStairs2(3));
    }
}
