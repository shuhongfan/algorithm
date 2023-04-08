package com.atguigu.algorithm.bit_operator;

public class HammingDistance {
    // 方法一：异或，调库统计1的个数
    public int hammingDistance1(int x, int y){
        return Integer.bitCount(x ^ y);
    }

    // 方法二：自定义实现统计1的个数，逐位右移
    public int hammingDistance2(int x, int y){
        int xor = x ^ y;    // 得到异或结果
        int count = 0;    // 保存当前1的个数

        // 逐位右移，直到结果为0
        while (xor != 0){
            // 如果最后一位为1，count++
            if ((xor & 1) == 1) count++;
            xor >>= 1;    // 右移一位
        }

        return count;
    }

    // 方法三：快速位移
    public int hammingDistance(int x, int y){
        int xor = x ^ y;    // 得到异或结果
        int count = 0;    // 保存当前1的个数

        // 快速位移，每次寻找当前最右面的一个1，直接消去
        while (xor != 0){
            xor &= xor - 1;
            count ++;
        }

        return count;
    }

    public static void main(String[] args) {
        HammingDistance hammingDistance = new HammingDistance();
        System.out.println(hammingDistance.hammingDistance(1, 4));
    }
}
