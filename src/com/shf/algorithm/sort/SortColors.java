package com.shf.algorithm.sort;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/sort-colors/
 * 75. 颜色分类
 * 给定一个包含红色、白色和蓝色、共 n 个元素的数组 nums ，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * <p>
 * 我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 * <p>
 * 必须在不使用库内置的 sort 函数的情况下解决这个问题。
 */
public class SortColors {

    /**
     * 方法一：调库
     *
     * @param nums
     */
    public void sortColors1(int[] nums) {
        Arrays.sort(nums);
    }

    /**
     * 方法二：基于选择排序
     *
     * @param nums
     */
    public void sortColors2(int[] nums) {
//        定义一个指针，指向当前应该填入元素的位置
        int curr = 0;

//        1.遍历数组，将所有0交换到数组头部
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                QuickSort.swap(nums, curr++, i);
            }
        }

//        2.遍历数组，将所有1交换到中间位置，接着之前的curr继续
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                QuickSort.swap(nums, curr++, i);
            }
        }
    }

    /**
     * 方法三：基于计数排序
     *
     * @param nums
     */
    public void sortColors3(int[] nums) {
        int count0 = 0, count1 = 0, count2 = 0;

//        遍历数组，统计0,1,2的个数
        for (int num : nums) {
            if (num == 0) {
                count0++;
            } else if (num == 1) {
                count1++;
            } else {
                count2++;
            }
        }

//        将0,1，2按照个数依次填入nums数组
        for (int i = 0; i < nums.length; i++) {
            if (i < count0) {
                nums[i] = 0;
            } else if (i < count0 + count1) {
                nums[i] = 1;
            } else {
                nums[i] = 2;
            }
        }
    }

    /**
     * 方法四：基于快速排序
     *
     * @param nums
     */
    public void sortColors4(int[] nums) {
//        定义左右指针
        int left = 0, right = nums.length - 1;

//        定义一个遍历所有元素的指针
        int i = left;

//        循环判断，遍历元素
        while (left < right && i <= right) {
//            1. 如果是2，换到末尾，右指针左移
            if (i <= right && nums[i] == 2) {
                QuickSort.swap(nums, i, right--);
            }

//            2. 如果是0，换到头部，左指针右移
            if (nums[i] == 0) {
                QuickSort.swap(nums, i, left++);
            }

//            3. i++,继续遍历
            i++;
        }
    }

    public static void main(String[] args) {
        int[] nums = {2, 0, 2, 1, 1, 0};
        QuickSort.printArray(nums);

        SortColors sortColors = new SortColors();
        sortColors.sortColors4(nums);
        QuickSort.printArray(nums);
    }
}
