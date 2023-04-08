package com.atguigu.algorithm.arrays;

import java.util.Arrays;
import java.util.HashMap;

public class TwoSum {
    // 方法一：暴力法，穷举所有两数组合
    public int[] twoSum1(int[] nums, int target){
        int n = nums.length;
        // 双重for循环
        for (int i = 0; i < n - 1; i++){
            for (int j = i + 1; j < n; j++){
                if (nums[i] + nums[j] == target){
                    return new int[]{i, j};
                }
            }
        }
        // 如果找不到，抛出异常
        throw new IllegalArgumentException("no solution");
    }

    // 方法二：哈希表保存所有数的信息
    public int[] twoSum2(int[] nums, int target){
        int n = nums.length;

        // 定义一个哈希表
        HashMap<Integer, Integer> map = new HashMap<>();

        // 1. 遍历数组，将数据全部保存入hash表
        for (int i = 0; i < n; i++){
            map.put(nums[i], i);
        }

        // 2. 再次遍历数组，寻找每个数对应的那个数是否存在
        for (int i = 0; i < n; i++){
            int thatNum = target - nums[i];
            // 如果那个数存在，并且不是当前数自身，就直接返回结果
            if (map.containsKey(thatNum) && map.get(thatNum) != i )
                return new int[]{i, map.get(thatNum)};
        }

        // 如果找不到，抛出异常
        throw new IllegalArgumentException("no solution");
    }

    // 方法三：改进，遍历一次哈希表
    public int[] twoSum(int[] nums, int target){
        int n = nums.length;

        // 定义一个哈希表
        HashMap<Integer, Integer> map = new HashMap<>();

        // 遍历数组，寻找每个数对应的那个数是否存在（只向前寻找）
        for (int i = 0; i < n; i++){
            int thatNum = target - nums[i];
            // 如果那个数存在，并且不是当前数自身，就直接返回结果
            if (map.containsKey(thatNum) )
                return new int[]{map.get(thatNum), i};

            map.put(nums[i], i);
        }

        // 如果找不到，抛出异常
        throw new IllegalArgumentException("no solution");
    }

    public static void main(String[] args) {
        int[] input = {2,7,11,15};
        int[] input2 = {3, 1, 3};
        int target = 9;
        int target2 = 6;

        // 定义一个大数组，进行性能测试
        int[] input3 = new int[1000000];
        for (int i = 0; i < input3.length; i++)
            input3[i] = input3.length - i;

        int target3 = 567890;

        // 为了计算程序运行时间，开始计算和计算完成分别计时
        long startTime = System.currentTimeMillis();

        TwoSum twoSum = new TwoSum();
        int[] output = twoSum.twoSum1(input3, target3);

        long endTime = System.currentTimeMillis();

        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");

        for (int index: output)
            System.out.print(index + "\t");
    }
}
