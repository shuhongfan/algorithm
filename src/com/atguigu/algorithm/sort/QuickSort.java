package com.atguigu.algorithm.sort;

public class QuickSort {
    // 快排核心算法，递归调用
    public static void qSort(int[] nums, int start, int end){
        // 基准情况
        if (start >= end) return;

        // 1. 找到一个pivot，把数组划分成两部分，返回pivot索引位置
        int index = partition(nums, start, end);

        // 2. 递归排序左右两部分
        qSort(nums, start, index - 1);
        qSort(nums, index + 1, end);
    }

    // 分区方法
    public static int partition1(int[] nums, int start, int end){
        int pivot = nums[start];    // 以第一个元素作为中心点
        // 定义双指针
        int left = start, right = end;
        // 要返回的pivot位置索引
        int position = start;

        while (left < right){
            // 左指针向右移，找到一个比pivot大的数，就停下来
            while (left < right && nums[left] <= pivot)
                left ++;
            while (left < right && nums[right] >= pivot)
                right --;

            // 判断左右指针是否相遇，如果没有相遇，交换两个元素
            if (left < right)
                swap(nums, left, right);
            else {
                // 如果已经相遇，填入pivot
                // 要判断当前位置和pivot的大小，确定到底填入哪个位置
                if ( nums[left] < pivot) {
                    position = left;
                    swap(nums, start, left);
                } else {
                    position = left - 1;
                    swap(nums, start, left - 1);
                }
            }
        }

        return position;
    }

    public static int partition(int[] nums, int start, int end){
        int pivot = nums[start];
        int left = start, right = end;

        while (left < right){
            // 左移右指针，找到一个比pivot小的数，填入空位
            while (left < right && nums[right] >= pivot)
                right --;

            nums[left] = nums[right];

            while (left < right && nums[left] <= pivot)
                left ++;

            nums[right] = nums[left];
        }

        nums[left] = pivot;

        return left;
    }

    public static void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {3, 45, 78, 36, 52, 11, 39, 36, 52};

        qSort(nums, 0, nums.length - 1);

        printArray(nums);
    }

    public static void printArray(int[] arr){
        for (int num: arr){
            System.out.print(num + "\t");
        }
        System.out.println();
    }
}
