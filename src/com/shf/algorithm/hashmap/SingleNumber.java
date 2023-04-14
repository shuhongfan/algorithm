package com.shf.algorithm.hashmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * https://leetcode.cn/problems/single-number/
 * 136. 只出现一次的数字
 *
 * 给你一个 非空 整数数组 nums ，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 *
 * 你必须设计并实现线性时间复杂度的算法来解决此问题，且该算法只使用常量额外空间。
 */
public class SingleNumber {
    /**
     * 方法一：暴力法
     * @param nums
     * @return
     */
    public int singleNumber1(int[] nums) {
//        定义一个列表，保存当前所有出现过依次的元素
        ArrayList<Integer> singleNumList = new ArrayList<>();

//        遍历所有元素
        for (Integer num : nums) {
            if (singleNumList.contains(num)) {
//                如果已经出现过，删除列表中的元素
                singleNumList.remove(num);
            } else {
//                没有出现过，直接保存
                singleNumList.add(num);
            }
        }

        return singleNumList.get(0);
    }

    /**
     * 方法二：保存单独的元素到HashMap
     * @param nums
     * @return
     */
    public int singleNumber2(int[] nums) {
        HashMap<Integer, Integer> singleNumMap = new HashMap<>();

        for (int num : nums) {
            if (singleNumMap.get(num) != null) {
                singleNumMap.remove(num);
            } else {
                singleNumMap.put(num, 1);
            }
        }
        return singleNumMap.keySet().iterator().next();
    }

    /**
     * 方法三：用set去重，a=2*(a+b+c)-(a+b+c+b+c)
     * @param nums
     * @return
     */
    public int singleNumber3(int[] nums) {
//        定义一个HashSet进行去重
        HashSet<Integer> set = new HashSet<>();
        int arraySum = 0;
        int setSum = 0;

//        1. 遍历数组元素，保存到set，并直接求和
        for (int num : nums) {
            set.add(num);
            arraySum += num;
        }

//        2.集合所有元素求和
        for (Integer num : set) {
            setSum += num;
        }

//        3.计算结果
        return setSum * 2 - arraySum;
    }

    /**
     * 方法四：数学方法（做异或）
     * @param nums
     * @return
     */
    public int singleNumber4(int[] nums) {
        int result = 0;

//        遍历所有数据，按位做异或
        for (int num : nums) {
            result ^= num;
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {4, 1, 2, 1, 2};
        SingleNumber singleNumber = new SingleNumber();
        System.out.println(singleNumber.singleNumber4(nums));
    }
}
