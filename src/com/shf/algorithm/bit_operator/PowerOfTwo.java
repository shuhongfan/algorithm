package com.shf.algorithm.bit_operator;

/**
 * https://leetcode.cn/problems/power-of-two/
 * 231. 2 的幂
 *
 * 给你一个整数 n，请你判断该整数是否是 2 的幂次方。如果是，返回 true ；否则，返回 false 。
 *
 * 如果存在一个整数 x 使得 n == 2x ，则认为 n 是 2 的幂次方。
 */
public class PowerOfTwo {
    /**
     * 方法一：数学方法，连续除以2判断余数
     * @param n
     * @return
     */
    public boolean isPowerOfTwo(int n) {
//        处理特殊情况
        if (n <= 0) {
            return false;
        }

//        不定的除以二，直到结果为奇数
        while (n % 2 == 0) {
            n /= 2;
        }

//        如果最终n变成了1，就是2的整次幂
        return n == 1;
    }

    /**
     * 方法二：位运算，和自身减一做位于与
     * @param n
     * @return
     */
    public boolean isPowerOfTwo2(int n){
        if (n <= 0) {
            return false;
        }
        return (n & n - 1) == 0;
    }

    /**
     * 方法三：位运算，和相反数做位与
     * @param n
     * @return
     */
    public boolean isPowerOfTwo3(int n) {
        if (n <= 0) {
            return false;
        }
        return (n & -n) == n;
    }

    public static void main(String[] args) {
        PowerOfTwo powerOfTwo = new PowerOfTwo();
        System.out.println(powerOfTwo.isPowerOfTwo3(218));
        System.out.println(powerOfTwo.isPowerOfTwo3(128));
    }
}
