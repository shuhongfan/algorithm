package com.shf.algorithm.arrays;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/next-permutation/
 * 31. 下一个排列
 * <p>
 * 整数数组的一个 排列  就是将其所有成员以序列或线性顺序排列。
 * <p>
 * 例如，arr = [1,2,3] ，以下这些都可以视作 arr 的排列：[1,2,3]、[1,3,2]、[3,1,2]、[2,3,1] 。
 * 整数数组的 下一个排列 是指其整数的下一个字典序更大的排列。更正式地，如果数组的所有排列根据其字典顺序从小到大排列在一个容器中，那么数组的 下一个排列 就是在这个有序容器中排在它后面的那个排列。如果不存在下一个更大的排列，那么这个数组必须重排为字典序最小的排列（即，其元素按升序排列）。
 * <p>
 * 例如，arr = [1,2,3] 的下一个排列是 [1,3,2] 。
 * 类似地，arr = [2,3,1] 的下一个排列是 [3,1,2] 。
 * 而 arr = [3,2,1] 的下一个排列是 [1,2,3] ，因为 [3,2,1] 不存在一个字典序更大的排列。
 * 给你一个整数数组 nums ，找出 nums 的下一个排列。
 * <p>
 * 必须 原地 修改，只允许使用额外常数空间。
 */
public class NextPermutation {

    /**
     * 思路：从后向前找到升序子序列，然后确定调整后续子序列的最高位，剩余部分升序排列
     *
     * @param nums
     */
    public void nextPermutation1(int[] nums) {
        int n = nums.length;

//        1. 从后向前找到升序子序列，找到第一次下降的数，位置记为k
        int k = n - 2; // 从倒数第二个数开始
        while (k >= 0 && nums[k] >= nums[k + 1]) {
            k--;
        }

//        找到k，就是需要调整部分的最高位

//        2. 如果k=-1，说明所有数降序排列，改成升序排列
        if (k == -1) {
            Arrays.sort(nums);
            return;
        }

//        3. 一般情况，k >= 0
//        3.1 依次遍历剩余降序排列的部分，找到要替换最高位的那个数
        int i = k + 2;
        while (i < n && nums[i] > nums[k]) {
            i++;
        }

//        当前的i，就是后面部分第一个比nums[k]小的数，i-1就是要替换的那个数

//        3.2 交换i-1和k位置上的数
        int temp = nums[k];
        nums[k] = nums[i - 1];
        nums[i - 1] = temp;

//        3.3 k之后的剩余部分变成升序排列，直接前后调换
        int start = k + 1;
        int end = n - 1;
        while (start < end) {
            int tmp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }


    /**
     * 方法改进：将降序数组反转的操作提取出来
     * @param nums
     */
    public void nextPermutation2(int[] nums) {
        int n = nums.length;

//        1. 从后向前找到升序子序列，找到第一次下降的数，位置记为k
        int k = n - 2;
        while (k >= 0 && nums[k] >= nums[k + 1]) {
            k--;
        }

//        找到k，就是需要调整部分的最高位

//        2. 如果k=-1，说明所有数降序排列，改成升序排列
        if (k == -1) {
            reverse(nums, 0, n - 1);
            return;
        }

//        3. 一般情况，k >= 0
//        3.1 依次遍历剩余降序排列的部分，找到要替换最高位的那个数
        int i = k + 2;
        while (i < n && nums[i] > nums[k]) {
            i++;
        }

//        当前的i，就是后面部分第一个比nums[k]小的数，i-1就是要替换的那个数

//        3.2 交换i-1和k位置上的数
        swap(nums, k, i - 1);

//        3.3 k之后的剩余部分变成升序排列，直接前后调换
        reverse(nums, k + 1, n - 1);
    }

    /**
     * 交换数组中两个元素
     * @param nums
     * @param i
     * @param j
     */
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * 反转数组的方法
     * @param nums
     * @param start
     */
    private void reverse(int[] nums, int start,int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};

        NextPermutation permutation = new NextPermutation();
        permutation.nextPermutation2(nums);

        for (int num : nums) {
            System.out.print(num + "\t");
        }
    }
}
