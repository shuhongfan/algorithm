package com.shf.algorithm.backtrack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * https://leetcode.cn/problems/letter-combinations-of-a-phone-number/
 * 17. 电话号码的字母组合
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 *
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 */
public class LetterCombinationsOfPhoneNumber {
    /**
     * 定义一个 HashMap，保存数字对应的字母
     */
    HashMap<Character, String> numberMap = new HashMap<Character, String>()
    {
        {
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }
    };

    public List<String> letterCombinations(String digits) {
//        定义List保存结果
        ArrayList<String> result = new ArrayList<>();

//        用一个StringBuffer保存当前一个可行解
        StringBuffer combination = new StringBuffer();

//        处理特殊情况
        if ("".equals(digits)) {
            return result;
        }

//        从第一个数组开始回溯处理
        backtrack(digits, result, combination, 0);

        return result;
    }

    /**
     * 回溯方法
     * @param digits
     * @param result
     * @param combination
     * @param i
     */
    private void backtrack(String digits, ArrayList<String> result, StringBuffer combination, int i) {
        int n = digits.length();

//        判断当前深度搜索是否完成，如果完成直接添加到result里
        if (i >= n) {
            result.add(combination.toString());
        } else {
//            如果没搜索完，处理当前的数字
            char digit = digits.charAt(i);
//            取出可能对应的字母
            String letters = numberMap.get(digit);

//            遍历所有可能的字母
            for (int j = 0; j < letters.length(); j++) {
//                添加当前字母到可行解中
                combination.append(letters.charAt(j));

//                递归调用，继续处理后续数字
                backtrack(digits, result, combination, i + 1);

//                回溯
                combination.deleteCharAt(i);
            }
        }
    }

    public static void main(String[] args) {
        LetterCombinationsOfPhoneNumber letterCombinationsOfPhoneNumber = new LetterCombinationsOfPhoneNumber();
        for (String letterCombination : letterCombinationsOfPhoneNumber.letterCombinations("23")) {
            System.out.print(letterCombination+"\t");
        }

    }

}
