package com.shf.algorithm.greedy;

/**
 * https://leetcode.cn/problems/jump-game-ii/
 * 45. 跳跃游戏 II
 *
 * 给定一个长度为 n 的 0 索引整数数组 nums。初始位置为 nums[0]。
 *
 * 每个元素 nums[i] 表示从索引 i 向前跳转的最大长度。换句话说，如果你在 nums[i] 处，你可以跳转到任意 nums[i + j] 处:
 *
 * 0 <= j <= nums[i]
 * i + j < n
 * 返回到达 nums[n - 1] 的最小跳跃次数。生成的测试用例可以到达 nums[n - 1]。
 */
public class JumpGameII {
    /**
     * 方法一：反向跳跃
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
//        定义一个变量保存跳跃步数
        int steps = 0;

//        定义循环变量
        int curPosition = nums.length - 1;

//        不定的向前跳跃，以最远的距离
        while (curPosition > 0) {
//            从前到后遍历数组元素，找到当前距离最远的 “上一步位置”
            for (int i = 0; i < curPosition; i++) {
                if (i + nums[i] >= curPosition) {
                    curPosition = i; // 从前到后，第一次能跳到当前位置的位置，就是最远的上一步位置
                    steps++;
                    break;
                }
            }
        }
        return steps;
    }

    /**
     * 方法二：正向跳跃，考虑能够跳到最远的两步
     * @param nums
     * @return
     */
    public int jump2(int[] nums) {
        int steps = 0;

//        定义双指针，指向当前位置跳一步和两步分别能到的最远位置
        int farthest = nums[0];
        int nextFarthest = farthest;

//        不停寻找下一步的合适位置
//        遍历currPosition ~ farthest范围内所有元素，选择第二步跳跃最远的当前一步的选择
        for (int i = 0; i <= nums.length-1; i++) {
//         如果比之前第二步最远距离大，更新
            if (i + nums[i] > nextFarthest) {
                nextFarthest = i + nums[i];
            }

//         添加步数增长条件: 处理到farthest
            if (i == farthest) {
                //            当前一步完成
                steps++;
                farthest = nextFarthest;
            }
        }
        return steps;
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 1, 4};

        JumpGameII jumpGameII = new JumpGameII();
        System.out.println(jumpGameII.jump2(nums));
    }
}
