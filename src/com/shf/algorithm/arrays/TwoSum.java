package com.shf.algorithm.arrays;

import java.util.HashMap;

/**
 * 1. 两数之和
 * https://leetcode.cn/problems/two-sum/
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 *
 * 你可以按任意顺序返回答案。
 */
public class TwoSum {
    /**
     * 方法一：暴力法，穷举所有两数组合
     * 空间复杂度：o(1)
     * 时间复杂度：o(n^2)
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum1(int[] nums, int target) {
//        记录循环次数
        int n = nums.length;

//        双重for循环
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (target == nums[i] + nums[j]) {
                    return new int[]{i, j};
                }
            }
        }

//        如果找不到,抛出异常
        throw new IllegalArgumentException("no solution");
    }

    /**
     * 方法二:哈希表保存所有数的信息
     * 空间复杂的：o(n)
     * 时间复杂度：o(1)
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum2(int[] nums, int target) {
//        定义一个哈希表
        HashMap<Integer, Integer> map = new HashMap<>();

        int n = nums.length;

//        1. 遍历数组,将数据全部存入hash表
        for (int i = 0; i < n; i++) {
            map.put(nums[i], i);
        }

//        2. 再次遍历数组,寻找每个数对应的那个数是否存在
        for (int i = 0; i < n; i++) {
            int thatNum = target - nums[i];

//            如果那个数存在 并且不是当前数自身,就直接返回结果
            if (map.containsKey(thatNum) && map.get(thatNum)!=i) {
                return new int[]{i, map.get(thatNum)};
            }
        }

        //        如果找不到,抛出异常
        throw new IllegalArgumentException("no solution");
    }


    /**
     * 方法三：改进，遍历一次哈希表
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum3(int[] nums, int target) {
//        定义一个哈希表
        HashMap<Integer, Integer> map = new HashMap<>();

        int n = nums.length;

//       遍历数组,寻找每个数对应的那个数是否存在（只向前寻找）
        for (int i = 0; i < n; i++) {
            int thatNum = target - nums[i];

//            如果那个数存在 并且不是当前数自身,就直接返回结果
            if (map.containsKey(thatNum)) {
                return new int[]{map.get(thatNum), i};
            }

//            将数据全部存入hash表
            map.put(nums[i], i);
        }

        //        如果找不到,抛出异常
        throw new IllegalArgumentException("no solution");
    }

    public static void main(String[] args) {
        int[] input = {2, 7, 11, 15};
        int[] input2 = {3, 3};
        int target = 9;
        int target2 = 6;

        // 定义一个大数组，进行性能测试
        int[] input3 = new int[1000000];
        for (int i = 0; i < input3.length; i++){
            input3[i] = input3.length - i;
        }
        int target3 = 567890;

//        为了计算程序运行时间，开始计算和计算完成分别计时
        long startTime = System.currentTimeMillis();

        TwoSum twoSum = new TwoSum();
        int[] output = twoSum.twoSum3(input3, target3);

        long end = System.currentTimeMillis();
        System.out.println("程序运行时间：" + (end - startTime)+"ms");

        for (int index : output) {
            System.out.print(index+"\t");
        }
    }
}
