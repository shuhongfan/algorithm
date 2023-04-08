package com.atguigu.algorithm.backtrack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LetterCombination {
    // 用一个HashMap来记录数字和字母的对应关系
    HashMap<Character, String> numberMap = new HashMap<Character, String>(){
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

    // 回溯法
    public List<String> letterCombinations(String digits){
        // 定义List保存结果
        ArrayList<String> result = new ArrayList<>();

        // 用一个StringBuffer保存当前一个可行解
        StringBuffer combination = new StringBuffer();

        // 处理特殊情况
        if ("".equals(digits)) return result;

        // 递归方法，传入当前考察的数字位置，初始为0
        backtrack(digits, result, combination, 0);

        return result;
    }

    // 定义回溯方法
    public void backtrack(String digits, List<String> result, StringBuffer combination, int i){
        int n = digits.length();

        // 判断当前深度搜索是否完成，如果完成直接添加到result里
        if (i >= n){
            result.add(combination.toString());
        } else {
            // 如果没搜索完，处理当前的数字
            char digit = digits.charAt(i);
            // 取出可能对应的字母
            String letters = numberMap.get(digit);

            // 遍历所有可能的字母
            for (int j = 0; j < letters.length(); j++){
                // 添加当前字母到可行解中
                combination.append(letters.charAt(j));

                // 递归调用，考察下一个数字
                backtrack(digits, result, combination, i + 1);

                // 回溯，回退状态
                combination.deleteCharAt(i);
            }
        }
    }

    public static void main(String[] args) {
        String digits = "23";
        LetterCombination letterCombination = new LetterCombination();

        System.out.println(letterCombination.letterCombinations(digits));

        System.out.println(letterCombination.letterCombinations("474"));
    }
}
