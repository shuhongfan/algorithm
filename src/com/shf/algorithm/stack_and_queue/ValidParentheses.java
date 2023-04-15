package com.shf.algorithm.stack_and_queue;

import java.util.LinkedList;

/**
 * https://leetcode.cn/problems/valid-parentheses/
 * 20. 有效的括号
 *
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 每个右括号都有一个对应的相同类型的左括号。
 */
public class ValidParentheses {
    public boolean isValid(String s) {
        LinkedList<Character> stack = new LinkedList<>();

//        遍历字符串中所有元素，依次判断
        for (int i = 0; i < s.length(); i++) {
//            获取当前字符
            char ch = s.charAt(i);

//            判断当前字符是左括号还是右括号
//            如果是左括号，直接将对应的有括号入栈
            if (ch == '(') {
                stack.push(')');
            } else if (ch == '[') {
                stack.push(']');
            } else if (ch == '{') {
                stack.push('}');
            } else {
//                如果是右括号，弹栈判断是否匹配
                if (stack.isEmpty()||stack.pop() != ch) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        ValidParentheses validParentheses = new ValidParentheses();
        System.out.println(validParentheses.isValid("()"));
        System.out.println(validParentheses.isValid("()[]{}"));
        System.out.println(validParentheses.isValid("(]"));
    }
}
