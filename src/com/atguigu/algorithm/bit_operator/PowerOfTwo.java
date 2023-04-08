package com.atguigu.algorithm.bit_operator;

public class PowerOfTwo {
    // 方法一：数学方法，连续除以2判断余数
    public boolean isPowerOfTwo1(int n){
        // 处理特殊情况
        if (n <= 0) return false;

        // 不停地除以二，直到结果为奇数
        while (n % 2 == 0){
            n /= 2;
        }

        // 如果最终n变成了1，就是2的整次幂
        return n == 1;
    }

    // 方法二：位运算，和自身减一做位与
    public boolean isPowerOfTwo2(int n){
        if (n <= 0) return false;
        return (n & n - 1) == 0;
    }

    // 方法三：位运算，和相反数做位与
    public boolean isPowerOfTwo(int n){
        if (n <= 0) return false;
        return (n & -n) == n;
    }

    public static void main(String[] args) {
        PowerOfTwo powerOfTwo = new PowerOfTwo();
        System.out.println(powerOfTwo.isPowerOfTwo(218));
        System.out.println(powerOfTwo.isPowerOfTwo(128));
    }
}
