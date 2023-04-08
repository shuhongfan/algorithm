package com.atguigu.algorithm.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class MergeIntervals {
    // 按区间左边界排序
    public int[][] merge(int[][] intervals){
        // 定义一个结果数组
        ArrayList<int[]> result = new ArrayList<>();

        // 1. 将所有区间按照左边界排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        // 2. 遍历排序后的区间，逐个合并
        for (int[] interval: intervals){
            // 记录当前的左右边界
            int left = interval[0], right = interval[1];

            // 获取结果数组长度
            int length = result.size();

            // 如果left比最后一个区间的右边界大，不能合并，直接添加到结果
            if ( length == 0 || left > result.get(length-1)[1] ){
                result.add(interval);
            } else {
                // 可以合并
                int mergedLeft = result.get(length-1)[0];
                int mergedRight = Math.max(result.get(length-1)[1], right);
                result.set(length - 1, new int[]{mergedLeft, mergedRight});
            }
        }

        return result.toArray(new int[result.size()][]);
    }

    public static void main(String[] args) {
        int[][] intervals = {{1,3},{2,6},{8,10},{15,18}};
        MergeIntervals mergeIntervals = new MergeIntervals();

        for (int[] interval: mergeIntervals.merge(intervals)){
            QuickSort.printArray(interval);
        }
    }
}
