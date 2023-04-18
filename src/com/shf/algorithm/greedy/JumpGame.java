package com.shf.algorithm.greedy;

/**
 * https://leetcode.cn/problems/jump-game/
 * 55. 跳跃游戏
 *
 * 给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
 *
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 *
 * 判断你是否能够到达最后一个下标。
 */
public class JumpGame {
    /**
     * 贪心实现
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
//        定义一个变量，保存当前最远能够跳到的位置
        int farthest = 0;

//        遍历数组，更新farthest
        for (int i = 0; i < nums.length; i++) {
//            判断当前i在可以到达的范围内，更新farthest
            if (i <= farthest) {
                farthest = Math.max(farthest, i + nums[i]);

//                如果farthest已经达到了末尾，直接返回true
                if (farthest >= nums.length - 1) {
                    return true;
                }
            } else {
//             如果i已经到不了
                return false;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        JumpGame jumpGame = new JumpGame();
        int[] nums1 = {2, 3, 1, 1, 4};
        int[] nums2 = {3, 2, 1, 0, 4};

        System.out.println(jumpGame.canJump(nums1));
        System.out.println(jumpGame.canJump(nums2));
    }
}
