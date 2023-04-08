package com.atguigu.algorithm.binary_tree;

public class Recursion {
    // 定义一个计算阶乘的方法
    public static int factorial(int n){
        if (n == 0) return 1;    // 基准情形
        return factorial(n-1) * n;
    }

    // 尾递归
    public static int factorial2(int n, int acc){
        if (n == 0) return acc;
        return factorial2(n - 1, acc * n);
    }

    public static void main(String[] args) {
        System.out.println(factorial2(6, 1));
    }
}
