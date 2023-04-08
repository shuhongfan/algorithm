package com.atguigu.algorithm.strings;

public class MultiplyStrings {

    // 方法一：基于字符串相加的竖式乘法
    public String multiply1(String num1, String num2){
        // 处理特殊情况，有一个乘数为0，结果就为0
        if (num1.equals("0") || num2.equals("0")) return "0";

        // 定义输出结果，直接定义为String，调用字符串相加方法
        String result = "0";

        // 从个位开始遍历num2的每一位，跟num1相乘，并叠加计算结果
        for (int i = num2.length() - 1; i >= 0; i --){
            // 取出num2的当前数位，作为当前乘法的第二个乘数
            int n2 = num2.charAt(i) - '0';
            // 用一个StringBuffer保存乘积结果
            StringBuffer curResult = new StringBuffer();
            int carry = 0;    // 定义进位

            // 1. 因为结果是倒序，所以当前n2对应数位要补的0，应该先写入curResult,补n-1-i个0
            for (int j = 0; j < num2.length() - 1 - i; j++)
                curResult.append("0");

            // 2. 从个位开始遍历num1中的每一位，与n2相乘，并叠加
            for ( int j = num1.length() - 1; j >= 0; j-- ){
                // 取出num1的当前数位，作为当前乘法的第一个乘数
                int n1 = num1.charAt(j) - '0';
                // 计算当前数位的乘积结果
                int product = n1 * n2 + carry;

                curResult.append(product % 10);
                carry = product / 10;
            }

            // 3. 所有数位乘法计算完毕，如果有进位，需要将进位单独作为一位保存下来
            if (carry != 0) curResult.append(carry);

            // 现在就得到了num1和当前位n2的最终乘积

            // 4. 将当前乘积叠加到result中
            AddStrings addStrings = new AddStrings();
            result = addStrings.addStrings(result, curResult.reverse().toString());
        }

        return result;
    }

    // 方法二：竖式乘法优化
    public String multiply(String num1, String num2){
        // 处理特殊情况，有一个乘数为0，结果就为0
        if (num1.equals("0") || num2.equals("0")) return "0";

        // 定义一个数组，保存计算结果的每一位
        int[] resultArray = new int[num1.length() + num2.length()];

        // 遍历num1和num2的每个数位，做乘积，然后找到对应数位，填入结果数组
        for (int i = num1.length() - 1; i >= 0; i--){
            int n1 = num1.charAt(i) - '0';
            for (int j = num2.length() - 1; j >= 0; j--){
                int n2 = num2.charAt(j) - '0';
                // 计算乘积
                int product = n1 * n2;

                // 保存到结果数组
                int sum = product + resultArray[i+j+1];
                resultArray[i+j+1] = sum % 10;    // 叠加结果的个位保存到i+j+1位置
                resultArray[i+j] += sum / 10;
            }
        }

        // 将结果数组转成String输出
        StringBuffer result = new StringBuffer();
        int start = resultArray[0] == 0 ? 1 : 0;    // 如果最高位为0，从1开始遍历
        for (int i = start; i < resultArray.length; i++)
            result.append(resultArray[i]);

        return result.toString();
    }

    public static void main(String[] args) {
        String num1 = "123";
        String num2 = "456";

        MultiplyStrings multiplyStrings = new MultiplyStrings();
        System.out.println(multiplyStrings.multiply(num1, num2));
    }
}
