package com.atguigu.algorithm.hashmap;

import java.util.HashSet;

public class LongestConsecutiveSequence {
    // 方法一：暴力法
    public int longestConsecutiveSequence1(int[] nums){
        // 定义一个变量，保存当前最长连续序列的长度
        int maxLength = 0;

        // 遍历数组，以每个元素作为起始点，寻找连续序列
        for (int i = 0; i < nums.length; i++){
            // 保存当前元素作为起始点
            int currNum = nums[i];
            // 保存当前连续序列长度
            int currLength = 1;

            // 寻找后续数字，组成连续序列
            while ( contains(nums, currNum + 1) ){
                currLength ++;
                currNum ++;
            }

            // 判断当前连续序列长度是否为最大
            maxLength = currLength > maxLength ? currLength : maxLength;
        }

        return maxLength;
    }

    // 定义一个方法，用于在数组中寻找某个元素
    public boolean contains(int[] nums, int x){
        for (int num: nums){
            if (num == x)
                return true;
        }
        return false;
    }

    // 方法二：利用哈希表改进
    public int longestConsecutiveSequence2(int[] nums){
        // 定义一个变量，保存当前最长连续序列的长度
        int maxLength = 0;

        // 定义一个HashSet，保存所有出现的数值
        HashSet<Integer> hashSet = new HashSet<>();

        // 1. 遍历所有元素，保存到HashSet
        for (int num: nums){
            hashSet.add(num);
        }

        // 2. 遍历数组，以每个元素作为起始点，寻找连续序列
        for (int i = 0; i < nums.length; i++){
            // 保存当前元素作为起始点
            int currNum = nums[i];
            // 保存当前连续序列长度
            int currLength = 1;

            // 寻找后续数字，组成连续序列
            while ( hashSet.contains(currNum + 1) ){
                currLength ++;
                currNum ++;
            }

            // 判断当前连续序列长度是否为最大
            maxLength = currLength > maxLength ? currLength : maxLength;
        }

        return maxLength;
    }

    // 方法三：进一步改进
    public int longestConsecutiveSequence(int[] nums){
        // 定义一个变量，保存当前最长连续序列的长度
        int maxLength = 0;

        // 定义一个HashSet，保存所有出现的数值
        HashSet<Integer> hashSet = new HashSet<>();

        // 1. 遍历所有元素，保存到HashSet
        for (int num: nums){
            hashSet.add(num);
        }

        // 2. 遍历数组，以每个元素作为起始点，寻找连续序列
        for (int i = 0; i < nums.length; i++){
            // 保存当前元素作为起始点
            int currNum = nums[i];
            // 保存当前连续序列长度
            int currLength = 1;

            // 判断：只有当前元素的前驱不存在的情况下，才去进行寻找连续序列的操作
            if (!hashSet.contains(currNum - 1)) {
                // 寻找后续数字，组成连续序列
                while ( hashSet.contains(currNum + 1) ){
                    currLength ++;
                    currNum ++;
                }

                // 判断当前连续序列长度是否为最大
                maxLength = currLength > maxLength ? currLength : maxLength;
            }
        }

        return maxLength;
    }

    public static void main(String[] args) {
        int[] nums1 = {100,4,200,1,3,2};
        int[] nums2 = {0,3,7,2,5,8,4,6,0,1};

        LongestConsecutiveSequence longestConsecutiveSequence = new LongestConsecutiveSequence();
        System.out.println(longestConsecutiveSequence.longestConsecutiveSequence(nums1));
        System.out.println(longestConsecutiveSequence.longestConsecutiveSequence(nums2));
    }
}
