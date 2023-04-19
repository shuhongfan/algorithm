package com.shf.algorithm.backtrack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 * https://leetcode.cn/problems/permutations/
 * 46. 全排列
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 */
public class Permutations {
    /**
     * 回溯法实现
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
//        定义保存结果的List
        ArrayList<List<Integer>> result = new ArrayList<>();

//        用一个ArrayList保存一组解
        ArrayList<Integer> solution = new ArrayList<>();

//        从0位置开始填充
        backtrack(nums, result, solution, 0);

        return result;
    }

    //    定义一个辅助集合，保存已经用过的数
    HashSet<Integer> filledNums = new HashSet<Integer>();

    /**
     * 实现一个回溯方法，方便递归调用
     *
     * @param nums
     * @param result
     * @param solution
     * @param i
     */
    private void backtrack(int[] nums, ArrayList<List<Integer>> result, ArrayList<Integer> solution, int i) {
        int n = nums.length;

//        首先判断退出递归调用的场景
        if (i >= n) {
            result.add(new ArrayList<>(solution));
        } else {
//            需要对i位置选数填入，遍历数组中所有数，取没有用过的数进行填充
            for (int j = 0; j < n; j++) {
                if (!filledNums.contains(nums[j])) {
//                    没用过直接填入
                    solution.add(nums[j]);
                    filledNums.add(nums[j]);

//                    递归调用，处理下一个位置
                    backtrack(nums, result, solution, i + 1);

//                    回溯，回退状态
                    solution.remove(i);
                    filledNums.remove(nums[j]);
                }
            }
        }

    }

    /**
     * 空间优化
     * @param nums
     * @return
     */
    public List<List<Integer>> permute2(int[] nums) {
//        定义保存结果的List
        ArrayList<List<Integer>> result = new ArrayList<>();

//        用一个ArrayList保存一组解
        ArrayList<Integer> solution = new ArrayList<>();

//        把nums复制到solution
        for (int num : nums) {
            solution.add(num);
        }

//        从0位置开始填充数
        backtrack2(result, solution, 0);

        return result;
    }

    private void backtrack2(ArrayList<List<Integer>> result, ArrayList<Integer> solution, int i) {
        int n = solution.size();

//        首先判断退出递归调用的场景
        if (i >= n) {
            result.add(new ArrayList<>(solution));
        } else {
//            需要对i位置选数填入，遍历数组中所有数，取没有用过的数进行填充
            for (int j = 0; j < n; j++) {
                Collections.swap(solution, i, j);

//                递归调用，处理后面的位置
                backtrack2(result, solution, i + 1);

//                回溯
                Collections.swap(solution, i, j);
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};

        Permutations permutations = new Permutations();
        List<List<Integer>> permute = permutations.permute2(nums);

        for (List<Integer> solution : permute) {
            for (Integer num : solution) {
                System.out.print(num+"\t");
            }
            System.out.println();
        }
    }
}
