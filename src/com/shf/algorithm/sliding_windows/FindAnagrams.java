package com.shf.algorithm.sliding_windows;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/find-all-anagrams-in-a-string/
 * 438. 找到字符串中所有字母异位词
 *
 * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 *
 * 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
 */
public class FindAnagrams {
    /**
     * 方法一：暴力法，枚举所有的长度为p.length()的子串
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams1(String s, String p) {
//        定义一个结果列表
        ArrayList<Integer> result = new ArrayList<>();

//        1. 统计p中所有字符频次
        int[] pCharCounts = new int[26];

        for (int i = 0; i < p.length(); i++) {
            pCharCounts[p.charAt(i) - 'a']++;
        }

//        2. 遍历s，以每一个字符作为起始，考察长度为p.length()的子串
        for (int i = 0; i <= s.length()-p.length(); i++) {
//            3. 判断当前子串是否为p的字母异位词
            boolean isMatched = true;

//            定义一个数组，统计子串中所有字符频次
            int[] subStrCharCounts = new int[26];

            for (int j = i; j < i + p.length(); j++) {
                subStrCharCounts[s.charAt(j) - 'a']++;

//                判断当前字符频次，如果超过了p的频次，就一定不符合要求
                if (subStrCharCounts[s.charAt(j) - 'a'] > pCharCounts[s.charAt(j) - 'a']) {
                    isMatched = false;
                    break;
                }
            }

            if (isMatched) {
                result.add(i);
            }
        }
        return result;
    }

    /**
     * 方法二：滑动窗口发，分别移动起始和结束位置
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams2(String s, String p){
//        定义一个结果列表
        ArrayList<Integer> result = new ArrayList<>();

//        1. 统计p中所有字符频次
        int[] pCharCounts = new int[26];

        for (int i = 0; i < p.length(); i++) {
            pCharCounts[p.charAt(i) - 'a']++;
        }

//        统计子串中所有字符出现频次的数组
        int[] subStrCharCounts = new int[26];

//        定义双指针，指向窗口的起始和结束位置
        int start = 0, end = 1;

//        2. 移动指针，总是截取字符出现频次全部小于等于p中字符频次的子串
        while (end <= s.length()) {
//            当前新增字符
            char newChar = s.charAt(end - 1);
            subStrCharCounts[newChar - 'a']++;

//            3. 判断当前子串是否符合要求
//            如果新增字符导致子串中频次超出了p中频次，那么移动start，消除新增字符影响
            while (subStrCharCounts[newChar - 'a'] > pCharCounts[newChar - 'a'] && start < end) {
                char removedChar = s.charAt(start);
                subStrCharCounts[removedChar - 'a']--;
                start++;
            }

//            如果当前子串长度等于p的长度，那么就是一个字母异位词
            if (end - start == p.length()) {
                result.add(start);
            }
            end++;
        }
        return result;
    }

    public static void main(String[] args) {
        String s = "cbaebabacd", p = "abc";

        FindAnagrams findAnagrams = new FindAnagrams();
        List<Integer> result = findAnagrams.findAnagrams2(s, p);
        for (Integer index : result) {
            System.out.print(index+"\t");
        }
    }
}
