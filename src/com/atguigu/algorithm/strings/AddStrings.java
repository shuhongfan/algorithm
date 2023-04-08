package com.atguigu.algorithm.strings;

public class AddStrings {
    public String addStrings(String num1, String num2){
        // 定义一个StringBuffer，保存最终的结果
        StringBuffer result = new StringBuffer();

        // 定义遍历两个字符串的初始位置
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        int carry = 0;    // 用一个变量保存当前的进位

        // 从个位开始依次遍历所有数位，只要还有数没有计算，就继续；其他数位补0
        while ( i >= 0 || j >= 0 || carry != 0 ){
            // 取两数当前的对应数位
            int n1 = i >= 0 ? num1.charAt(i) - '0' : 0;     // 字符要将ascii码转换为数字
            int n2 = j >= 0 ? num2.charAt(j) - '0' : 0;

            // 对当前数位求和
            int sum = n1 + n2 + carry;

            // 把sum的个位保存到结果中，十位作为进位保存下来
            result.append(sum % 10);
            carry = sum / 10;

            // 移动指针，继续遍历下一位
            i --;
            j --;
        }

        return result.reverse().toString();
    }

    public static void main(String[] args) {
        String num1 = "745346";
        String num2 = "8564";

        AddStrings addStrings = new AddStrings();
        System.out.println(addStrings.addStrings(num1, num2));

//        char c = '0';
//        int i = c;
//        System.out.println(c);
//        System.out.println(i);
    }
}
