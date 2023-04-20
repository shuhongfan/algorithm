package com.shf.algorithm.bit_operator;

/**
 * https://leetcode.cn/problems/hamming-distance/
 * 461. 汉明距离
 * 两个整数之间的 汉明距离 指的是这两个数字对应二进制位不同的位置的数目。
 *
 * 给你两个整数 x 和 y，计算并返回它们之间的汉明距离。
 */
public class HammingDistance {
    /**
     * 方法一：异或，调库统计1的个数
     * @param x
     * @param y
     * @return
     */
    public int hammingDistance(int x, int y) {
        return Integer.bitCount(x ^ y);
    }

    /**
     * 方法二：自定义实现统计1的个数，逐位右移
     * @param x
     * @param y
     * @return
     */
    public int hammingDistance2(int x, int y){
//        得到异或结果
        int xor = x ^ y;

//        保存当前1的个数
        int count = 0;

//        逐位右移，直到结果为0
        while (xor != 0) {
//            如果最后一位为1，count++
            if ((xor & 1) == 1) {
                count++;
            }
//            右移一位
            xor >>= 1;
        }

        return count;
    }

    /**
     * 方法三：快速位移
     * @param x
     * @param y
     * @return
     */
    public int hammingDistance3(int x, int y) {
//        得到异或结果
        int xor = x ^ y;
//        保存当前1的个数
        int count = 0;

//        快速位移,每次寻找当前最后面的一个1,直接消去
        while (xor != 0) {
            xor &= xor - 1;
            count++;
        }

        return count;
    }

    public static void main(String[] args) {
        HammingDistance hammingDistance = new HammingDistance();
        System.out.println(hammingDistance.hammingDistance3(1, 4));
    }
}
