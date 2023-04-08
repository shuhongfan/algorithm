package com.atguigu.algorithm.sliding_windows;

import java.util.HashMap;

public class MinWindowSubstring {
    // 方法一：暴力法，枚举s中所有子串
    public String minWindow1(String s, String t) {
        // 定义最小子串，保存结果，初始为空字符串
        String minSubString = "";

        // 定义一个HashMap，保存t中字符出现的频次
        HashMap<Character, Integer> tCharFrequency = new HashMap<>();

        // 统计t中字符频次
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            int count = tCharFrequency.getOrDefault(c, 0);
            tCharFrequency.put(c, count + 1);
        }

        // 接下来在s中搜索覆盖子串
        // 遍历所有字符，作为当前子串的起始位置
        for (int i = 0; i < s.length(); i++) {
            // 遍历i之后不小于t长度的位置，作为子串的结束位置
            for (int j = i + t.length(); j <= s.length(); j++) {
                // 统计s子串中每个字符出现的频次
                // 定义一个HashMap，保存s子串中字符出现的频次
                HashMap<Character, Integer> subStrCharFrequency = new HashMap<>();

                // 统计子串中字符频次
                for (int k = i; k < j; k++) {
                    char c = s.charAt(k);
                    int count = subStrCharFrequency.getOrDefault(c, 0);
                    subStrCharFrequency.put(c, count + 1);
                }

                // 如果当前子串符合覆盖子串的要求，并且比之前的最小子串要短，就替换
                if (check(tCharFrequency, subStrCharFrequency) && (minSubString.equals("") || j - i < minSubString.length())) {
                    minSubString = s.substring(i, j);
                }

            }
        }
        return minSubString;
    }

    // 方法二：滑动窗口
    public String minWindow2(String s, String t) {
        // 定义最小子串，保存结果，初始为空字符串
        String minSubString = "";

        // 定义一个HashMap，保存t中字符出现的频次
        HashMap<Character, Integer> tCharFrequency = new HashMap<>();

        // 统计t中字符频次
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            int count = tCharFrequency.getOrDefault(c, 0);
            tCharFrequency.put(c, count + 1);
        }

        // 定义左右指针，指向滑动窗口的起始和结束位置
        int start = 0, end = t.length();
        while (end <= s.length()) {
            // 定义一个HashMap，保存s子串中字符出现的频次
            HashMap<Character, Integer> subStrCharFrequency = new HashMap<>();

            // 统计子串中字符频次
            for (int k = start; k < end; k++) {
                char c = s.charAt(k);
                int count = subStrCharFrequency.getOrDefault(c, 0);
                subStrCharFrequency.put(c, count + 1);
            }

            // 如果当前子串符合覆盖子串的要求，并且比之前的最小子串要短，就替换
            if (check(tCharFrequency, subStrCharFrequency)) {
                if (minSubString.equals("") || end - start < minSubString.length()) {
                    minSubString = s.substring(start, end);
                }
                // 只要是覆盖子串，就移动初始位置，缩小窗口，寻找当前的局部最优解
                start++;
            } else {
                // 如果不是覆盖子串，需要扩大窗口，继续寻找新的子串
                end++;
            }
        }
        return minSubString;
    }

    // 方法三：滑动窗口优化
    public String minWindow3(String s, String t) {
        // 定义最小子串，保存结果，初始为空字符串
        String minSubString = "";

        // 定义一个HashMap，保存t中字符出现的频次
        HashMap<Character, Integer> tCharFrequency = new HashMap<>();

        // 统计t中字符频次
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            int count = tCharFrequency.getOrDefault(c, 0);
            tCharFrequency.put(c, count + 1);
        }

        // 定义左右指针，指向滑动窗口的起始和结束位置
        int start = 0, end = 1;

        // 定义一个HashMap，保存s子串中字符出现的频次
        HashMap<Character, Integer> subStrCharFrequency = new HashMap<>();

        while (end <= s.length()) {

            // end增加之后，新增的字符
            char newChar = s.charAt(end - 1);

            // 新增字符频次加1
            if (tCharFrequency.containsKey(newChar)) {
                subStrCharFrequency.put(newChar, subStrCharFrequency.getOrDefault(newChar, 0) + 1);
            }

            // 如果当前子串符合覆盖子串的要求，并且比之前的最小子串要短，就替换
            while (check(tCharFrequency, subStrCharFrequency) && start < end) {
                if (minSubString.equals("") || end - start < minSubString.length()) {
                    minSubString = s.substring(start, end);
                }

                // 对要删除的字符，频次减1
                char removedChar = s.charAt(start);

                if (tCharFrequency.containsKey(removedChar)) {
                    subStrCharFrequency.put(removedChar, subStrCharFrequency.getOrDefault(removedChar, 0) - 1);
                }

                // 只要是覆盖子串，就移动初始位置，缩小窗口，寻找当前的局部最优解
                start ++;
            }
            // 如果不是覆盖子串，需要扩大窗口，继续寻找新的子串
            end++;
        }
        return minSubString;
    }

    // 方法四：进一步优化
    public String minWindow(String s, String t) {
        // 定义最小子串，保存结果，初始为空字符串
        String minSubString = "";

        // 定义一个HashMap，保存t中字符出现的频次
        HashMap<Character, Integer> tCharFrequency = new HashMap<>();

        // 统计t中字符频次
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            int count = tCharFrequency.getOrDefault(c, 0);
            tCharFrequency.put(c, count + 1);
        }

        // 定义左右指针，指向滑动窗口的起始和结束位置
        int start = 0, end = 1;

        // 定义一个HashMap，保存s子串中字符出现的频次
        HashMap<Character, Integer> subStrCharFrequency = new HashMap<>();

        // 定义一个“子串贡献值”变量，统计t中的字符在子串中出现了多少
        int charCount = 0;

        while (end <= s.length()) {

            // end增加之后，新增的字符
            char newChar = s.charAt(end - 1);

            // 新增字符频次加1
            if (tCharFrequency.containsKey(newChar)) {
                subStrCharFrequency.put(newChar, subStrCharFrequency.getOrDefault(newChar, 0) + 1);
                // 如果子串中频次小于t中频次，当前字符就有贡献
                if (subStrCharFrequency.get(newChar) <= tCharFrequency.get(newChar))
                    charCount ++;
            }

            // 如果当前子串符合覆盖子串的要求，并且比之前的最小子串要短，就替换
            while ( charCount == t.length() && start < end) {
                if (minSubString.equals("") || end - start < minSubString.length()) {
                    minSubString = s.substring(start, end);
                }

                // 对要删除的字符，频次减1
                char removedChar = s.charAt(start);

                if (tCharFrequency.containsKey(removedChar)) {
                    subStrCharFrequency.put(removedChar, subStrCharFrequency.getOrDefault(removedChar, 0) - 1);
                    // 如果子串中的频次如果不够t中的频次，贡献值减少
                    if (subStrCharFrequency.get(removedChar) < tCharFrequency.get(removedChar))
                        charCount --;
                }

                // 只要是覆盖子串，就移动初始位置，缩小窗口，寻找当前的局部最优解
                start ++;
            }
            // 如果不是覆盖子串，需要扩大窗口，继续寻找新的子串
            end ++;
        }
        return minSubString;
    }

    // 提炼一个方法，用于检查当前子串是否是一个覆盖t的子串
    public boolean check(HashMap<Character, Integer> tFreq, HashMap<Character, Integer> subStrFreq) {
        // 遍历t中每个字符的频次，与subStr进行比较
        for (char c : tFreq.keySet()) {
            if (subStrFreq.getOrDefault(c, 0) < tFreq.get(c)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";

        MinWindowSubstring minWindowSubstring = new MinWindowSubstring();
        System.out.println(minWindowSubstring.minWindow(s, t));
    }
}
