package com.shf.algorithm.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * https://leetcode.cn/problems/kth-largest-element-in-an-array/
 * 215. 数组中的第K个最大元素
 *
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 *
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 * 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
 */
public class FindKthLargest {
    /**
     * 方法一：直接排序(调库)
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest1(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    /**
     * 方法二：基于快排的选择
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest2(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, nums.length - k);
    }

    /**
     * 实现快速选择方法
     * @param nums
     * @param start
     * @param end
     * @param index
     * @return
     */
    private int quickSelect(int[] nums, int start, int end, int index) {
//        找到pivot的位置返回
        int position = randomPatition(nums, start, end);

//        判断当前pivot位置是否为index
        if (position == index) {
            return nums[position];
        } else {
            return position > index ? quickSelect(nums, start, position - 1, index) : quickSelect(nums, position + 1, end, index);
        }
    }

    /**
     * 实现一个随机分区方法
     * @param nums
     * @param start
     * @param end
     * @return
     */
    private int randomPatition(int[] nums, int start, int end) {
        Random random = new Random();
//        随机生成pivot的位置
        int randIndex = start + random.nextInt(end - start + 1);
        QuickSort.swap(nums, start, randIndex);
        return QuickSort.partition2(nums, start, end);
    }

    /**
     * 方法三：基于堆排序的选择
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest3(int[] nums, int k) {
        int n = nums.length;

//        保存堆的大小，初始就是n
        int heapSize = n;

//        1. 构建大顶堆
        buildMaxHeap(nums, heapSize);

//        2.指向k-1次删除堆顶元素操作
        for (int i = n - 1; i > n - k; i--) {
//            将堆顶元素交换到当前堆的末尾
            QuickSort.swap(nums, 0, i);
            heapSize--;
            maxHeapify(nums, 0, heapSize);
        }

//        3.返回当前对顶元素
        return nums[0];
    }

    /**
     * 实现构建大顶堆
     * @param nums
     * @param heapSize
     */
    private void buildMaxHeap(int[] nums, int heapSize) {
        for (int i = heapSize / 2 - 1; i >= 0; i--) {
            maxHeapify(nums, i, heapSize);
        }
    }

    /**
     * 定义一个调整成大顶堆方法
     * @param nums
     * @param top
     * @param heapSize
     */
    private void maxHeapify(int[] nums, int top, int heapSize) {
//        定义左右子节点
        int left = top * 2 + 1;
        int right = top * 2 + 2;

//        保存当前最大元素的索引位置
        int largest = top;

//        比较左右子节点，记录最大元素索引位置
        if (right < heapSize && nums[right] > nums[largest]) {
            largest = right;
        }
        if (left < heapSize && nums[left] > nums[largest]) {
            largest = left;
        }

//        将最大元素换到堆顶
        if (largest != top) {
            QuickSort.swap(nums, top, largest);

//            递归调用，继续下沉
            maxHeapify(nums, largest, heapSize);
        }

    }

    public static void main(String[] args) {
        int[] nums1 = {3, 2, 1, 5, 6, 4};
        int[] nums2 = {3, 2, 3, 1, 2, 4, 5, 5, 6};

        FindKthLargest findKthLargest = new FindKthLargest();
        System.out.println(findKthLargest.findKthLargest3(nums1, 2));
        System.out.println(findKthLargest.findKthLargest3(nums2, 4));
    }
}
