package com.atguigu.algorithm.greedy;

public class JumpGame {
    // 贪心实现
    public boolean canJump(int[] nums){
        // 定义一个变量，保存当前最远能跳到的位置
        int farthest = 0;
        // 遍历数组，更新farthest
        for (int i = 0; i < nums.length; i++){
            // 判断当前i在可以到达的范围内，更新farthest
            if (i <= farthest){
                farthest = Math.max(farthest, i + nums[i]);
                // 如果farthest已经达到了末尾，直接返回true
                if (farthest >= nums.length - 1)
                    return true;
            } else {
                // 如果i已经到不了了
                return false;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        JumpGame jumpGame = new JumpGame();

        int[] nums1 = {2,3,1,1,4};
        int[] nums2 = {3,2,1,0,4};

        System.out.println(jumpGame.canJump(nums1));
        System.out.println(jumpGame.canJump(nums2));
    }
}
