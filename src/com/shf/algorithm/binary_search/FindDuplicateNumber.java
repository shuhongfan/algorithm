package com.shf.algorithm.binary_search;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * https://leetcode.cn/problems/find-the-duplicate-number/
 * 287. 寻找重复数
 * 给定一个包含 n + 1 个整数的数组 nums ，其数字都在 [1, n] 范围内（包括 1 和 n），可知至少存在一个重复的整数。
 *
 * 假设 nums 只有 一个重复的整数 ，返回 这个重复的数 。
 *
 * 你设计的解决方案必须 不修改 数组 nums 且只用常量级 O(1) 的额外空间。
 */
public class FindDuplicateNumber {
    /**
     * 方法一：使用HashMap保存每个数出现的次数
     * @param nums
     * @return
     */
    public int findDuplicate1(int[] nums) {
        HashMap<Integer, Integer> countMap = new HashMap<>();

//        遍历所有元素，统计count值
        for (int num : nums) {
//            判断当前num是否在map中出现过
            if (countMap.containsKey(num)) {
                return num; // 如果出现过，num就是重复数
            } else {
                countMap.put(num, 1); // 没有出现过，放到map中
            }
        }

        return -1;
    }

    /**
     * 方法二：使用HashSet保存数据，判断是否出现过
     * @param nums
     * @return
     */
    public int findDuplicate2(int[] nums){
        HashSet<Integer> hashSet = new HashSet<>();

//        遍历所有元素，添加到set中
        for (int num : nums) {
            if (hashSet.contains(num)) {
                return num;
            } else {
                hashSet.add(num);
            }
        }
        return -1;
    }

    /**
     * 方法三：先排序，然后找相邻的相同元素
     * @param nums
     * @return
     */
    public int findDuplicate3(int[] nums){
        Arrays.sort(nums);
//        遍历数组元素，遇到跟前一个相同的，就是重复元素
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                return nums[i];
            }
        }
        return -1;
    }

    /**
     * 方法四：二分查找，查找1~N的自然数序列，寻找target
     * @param nums
     * @return
     */
    public int findDuplicate4(int[] nums){
//        定义左右指针
        int left = 1;
        int right = nums.length - 1;

        while (left <= right) {
//            计算中间值
            int mid = (left + right) / 2;

//            对当前的mid计算count值
            int count = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] <= mid) {
                    count++;
                }
            }

//            判断count和mid本身的大小关系
            if (count <= mid) {
//                count小于等于mid自身，说明mid比target小，左指针右移
                left = mid + 1;
            } else {
                right = mid;
            }

//            左右指针重合时，找到target
            if (left == right) {
                return left;
            }
        }
        return -1;
    }

    /**
     * 方法五：快慢指针
     * @param nums
     * @return
     */
    public int findDuplicate5(int[] nums){
//        定义快慢指针
        int fast = 0, slow = 0;

//        1.寻找环内的相遇点
        do {
//            快指针一次走两步，慢指针一次走一步
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (fast != slow);

//        循环结束,slow和fast相等,都是相遇点
//        2. 寻找环的入口点
//        另外定义两个指针，固定间距
        int before = 0, after = slow;
        while (before != after) {
            before = nums[before];
            after = nums[after];
        }

//        循环结束，相遇点就是环的入口点,也就是重复元素
        return before;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 3, 4, 2, 2};
        int[] nums2 = {1, 1};

        FindDuplicateNumber findDuplicateNumber = new FindDuplicateNumber();
        System.out.println(findDuplicateNumber.findDuplicate5(nums1));
        System.out.println(findDuplicateNumber.findDuplicate5(nums2));
    }
}
