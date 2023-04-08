package com.atguigu.algorithm.bit_operator;

public class PoorPig {
    // 多进制的编码问题
    public int poorPigs(int buckets, int minutesToDie, int minutesToTest){
        int k = minutesToTest / minutesToDie;
        return (int)Math.ceil(Math.log(buckets) / Math.log(k + 1));
    }

    public static void main(String[] args) {
        PoorPig poorPig = new PoorPig();

        System.out.println(poorPig.poorPigs(4, 15, 15));
        System.out.println(poorPig.poorPigs(4, 15, 30));
        System.out.println(poorPig.poorPigs(1000, 15, 60));
    }
}
