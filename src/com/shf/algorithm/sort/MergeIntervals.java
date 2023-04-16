package com.shf.algorithm.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * https://leetcode.cn/problems/merge-intervals/
 * 56. 合并区间
 *
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
 */
public class MergeIntervals {
    /**
     * 按区间左边界排序
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
//        定义一个结果数组
        ArrayList<int[]> result = new ArrayList<>();

//        1. 将所有区间按照左边界排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

//        2. 遍历排序后的区间，逐个合并
        for (int[] interval : intervals) {
//            记录当前的左右边界
            int left = interval[0], right = interval[1];

//            获取结果数长度
            int length = result.size();

//            如果left比最后一个区间的右边界大，不能合并，直接添加到结果
            if (length == 0 || left > result.get(length - 1)[1]) {
                result.add(interval);
            } else {
//                可以合并
                int mergedLeft = result.get(length - 1)[0];
                int mergeRight = Math.max(result.get(length - 1)[1], right);
                result.set(length - 1, new int[]{mergedLeft, mergeRight});
            }
        }

        return result.toArray(new int[result.size()][]);
    }

    public static void main(String[] args) {
        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        MergeIntervals mergeIntervals = new MergeIntervals();

        int[][] merged = mergeIntervals.merge(intervals);

        for (int[] ints : merged) {
            System.out.println(ints[0] + "\t" + ints[1]);
        }
    }
}
