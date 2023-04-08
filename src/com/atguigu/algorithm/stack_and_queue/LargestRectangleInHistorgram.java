package com.atguigu.algorithm.stack_and_queue;

import java.util.Stack;

public class LargestRectangleInHistorgram {
    // 方法一：暴力法（遍历所有可能的宽度）
    public int largestRectangleArea1(int[] heights){
        // 定义变量保存最大面积
        int largestArea = 0;

        // 遍历数组，作为矩形左边界
        for (int left = 0; left < heights.length; left ++){
            // 定义变量保存当前矩形高度
            int currHeight = heights[left];

            // 遍历数组，选取矩形右边界
            for (int right = left; right < heights.length; right ++){
                // 确定当前矩形的高度
                currHeight = (heights[right] < currHeight) ? heights[right] : currHeight;

                // 计算当前矩形面积
                int currArea = (right - left + 1) * currHeight;

                // 更新最大面积
                largestArea = (currArea > largestArea) ? currArea : largestArea;
            }
        }

        return largestArea;
    }

    // 方法二：双指针法（遍历所有可能的高度）
    public int largestRectangleArea2(int[] heights){
        // 定义变量保存最大面积
        int largestArea = 0;

        // 遍历数组，以每个柱子高度作为最终矩形的高度
        for (int i = 0; i < heights.length; i++){
            // 保存当前高度
            int height = heights[i];

            // 定义左右指针
            int left = i, right = i;

            // 寻找左边界，左指针左移
            while (left >= 0){
                if (heights[left] < height) break;
                left --;
            }
            // 寻找右边界，右指针右移
            while (right < heights.length){
                if (heights[right] < height) break;
                right ++;
            }

            // 计算当前宽度
            int width = right - left - 1;

            // 计算面积
            int currArea = height * width;
            largestArea = (currArea > largestArea) ? currArea : largestArea;
        }

        return largestArea;
    }

    // 方法三：双指针法改进
    public int largestRectangleArea3(int[] heights){
        // 定义变量保存最大面积
        int largestArea = 0;

        // 定义两个数组，保存每个柱子对应的左右边界
        int n = heights.length;
        int[] lefts = new int[n];
        int[] rights = new int[n];

        // 遍历数组，计算左边界
        for (int i = 0; i < n; i++) {
            // 保存当前高度
            int height = heights[i];

            // 定义左指针
            int left = i - 1;

            // 左指针左移，寻找左边界
            while (left >= 0){
                if (heights[left] < height) break;
                left = lefts[left];    // 如果左边柱子更高，就直接跳到它的左边界柱子再判断
            }

            lefts[i] = left;
        }

        // 遍历数组，计算右边界
        for (int i = n - 1; i >= 0; i--) {
            // 保存当前高度
            int height = heights[i];

            // 定义右指针
            int right = i + 1;

            // 右指针右移，寻找右边界
            while (right < n){
                if (heights[right] < height) break;
                right = rights[right];    // 如果右边柱子更高，就直接跳到它的右边界柱子再判断
            }

            rights[i] = right;
        }

        // 遍历所有柱子，计算面积
        for (int i = 0; i < n; i++){
            int currArea = (rights[i] - lefts[i] - 1) * heights[i];
            largestArea = (currArea > largestArea) ? currArea : largestArea;
        }

        return largestArea;
    }

    // 方法四：单调栈
    public int largestRectangleArea4(int[] heights){
        // 定义变量保存最大面积
        int largestArea = 0;

        // 定义两个数组，保存每个柱子对应的左右边界
        int n = heights.length;
        int[] lefts = new int[n];
        int[] rights = new int[n];

        // 定义一个栈
        Stack<Integer> stack = new Stack<>();

        // 遍历所有柱子，作为当前高度，先找左边界
        for (int i = 0; i < n ; i ++){
            while ( !stack.isEmpty() && heights[stack.peek()] >= heights[i] ){
                stack.pop();
            }

            // 所有大于等于当前高度的元素全部弹出，找到了左边界
            lefts[i] = stack.isEmpty() ? -1 : stack.peek();

            stack.push(i);
        }

        stack.clear();

        // 遍历所有柱子，作为当前高度，寻找右边界
        for (int i = n - 1; i >= 0; i --){
            while ( !stack.isEmpty() && heights[stack.peek()] >= heights[i] ){
                stack.pop();
            }

            // 所有大于等于当前高度的元素全部弹出，找到了左边界
            rights[i] = stack.isEmpty() ? n : stack.peek();

            stack.push(i);
        }

        // 遍历所有柱子，计算面积
        for (int i = 0; i < n; i++){
            int currArea = (rights[i] - lefts[i] - 1) * heights[i];
            largestArea = (currArea > largestArea) ? currArea : largestArea;
        }

        return largestArea;
    }

    // 方法五：单调栈优化
    public int largestRectangleArea(int[] heights){
        // 定义变量保存最大面积
        int largestArea = 0;

        // 定义两个数组，保存每个柱子对应的左右边界
        int n = heights.length;
        int[] lefts = new int[n];
        int[] rights = new int[n];

        // 初始化rights为右哨兵n
        for (int i = 0; i < n; i ++) rights[i] = n;

        // 定义一个栈
        Stack<Integer> stack = new Stack<>();

        // 遍历所有柱子，作为当前高度，先找左边界
        for (int i = 0; i < n ; i ++){
            while ( !stack.isEmpty() && heights[stack.peek()] >= heights[i] ){
                // 栈顶元素如果小于当前元素，那么它的右边界就是当前元素
                rights[stack.peek()] = i;
                stack.pop();
            }

            // 所有大于等于当前高度的元素全部弹出，找到了左边界
            lefts[i] = stack.isEmpty() ? -1 : stack.peek();

            stack.push(i);
        }

        // 遍历所有柱子，计算面积
        for (int i = 0; i < n; i++){
            int currArea = (rights[i] - lefts[i] - 1) * heights[i];
            largestArea = (currArea > largestArea) ? currArea : largestArea;
        }

        return largestArea;
    }

    public static void main(String[] args) {
        int[] heights = {2,1,5,6,2,3};

        LargestRectangleInHistorgram largestRectangleInHistorgram = new LargestRectangleInHistorgram();

        System.out.println(largestRectangleInHistorgram.largestRectangleArea(heights));
    }
}
