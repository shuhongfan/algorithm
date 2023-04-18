package com.shf.algorithm.greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * https://leetcode.cn/problems/task-scheduler/
 * 621. 任务调度器
 *
 * 给你一个用字符数组 tasks 表示的 CPU 需要执行的任务列表。其中每个字母表示一种不同种类的任务。任务可以以任意顺序执行，并且每个任务都可以在 1 个单位时间内执行完。在任何一个单位时间，CPU 可以完成一个任务，或者处于待命状态。
 *
 * 然而，两个 相同种类 的任务之间必须有长度为整数 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。
 *
 * 你需要计算完成所有任务所需要的 最短时间 。
 */
public class TaskScheduler {
    /**
     * 方法一：模拟法
     * @param tasks
     * @param n
     * @return
     */
    public int leastInterval(char[] tasks, int n) {
//        用HashMap统计每个任务出现的次数
        HashMap<Character, Integer> countMap = new HashMap<>();
        for (char task : tasks) {
            countMap.put(task, countMap.getOrDefault(task, 0) + 1);
        }

//        任务种类数量
        int t = countMap.size();

//        定义两个状态列表
        ArrayList<Integer> restCount = new ArrayList<>(countMap.values());  // 任务剩余次数
        ArrayList<Integer> nextAvailableTime = new ArrayList<>(Collections.nCopies(t, 1));

//        模拟CPU时钟
        int time = 0;

//        遍历任务选择执行
        for (int i = 0; i < tasks.length; i++) {
            time++;

            int minNextAvailableTime = Integer.MAX_VALUE;
//            1. 获取所有任务重最早可以执行的时间
            for (int j = 0; j < t; j++) {
//                取没有做完的任务
                if (restCount.get(j) != 0) {
                    minNextAvailableTime = Math.min(minNextAvailableTime, nextAvailableTime.get(j));
                }
            }

//            2.直接推进时间，执行任务
            time = Math.max(time, minNextAvailableTime);

//            3. 选取可执行任务中，剩余次数最多的那个执行
            int maxRestCountTask = -1; // 保存要执行任务的索引
            for (int j = 0; j < t; j++) {
                if (restCount.get(j) > 0 && nextAvailableTime.get(j) <= time) {
//                    如果比之前保存的最大剩余任务数还大，就更新
                    if (maxRestCountTask == -1 || restCount.get(j) > restCount.get(maxRestCountTask)) {
                        maxRestCountTask = j;
                    }
                }
            }

//            4. 执行任务，更新状态列表
            nextAvailableTime.set(maxRestCountTask, time + n + 1);
            restCount.set(maxRestCountTask, restCount.get(maxRestCountTask) - 1);
        }

        return time;
    }

    /**
     * 方法二：构造法
     * @param tasks
     * @param n
     * @return
     */
    public int leastInterval2(char[] tasks, int n) {
//        用HashMap统计每个任务出现的次数
        HashMap<Character, Integer> countMap = new HashMap<>();
        for (char task : tasks) {
            countMap.put(task, countMap.getOrDefault(task, 0) + 1);
        }

//        任务种类数量
        int t = countMap.size();
        int maxCount = 0;
        int maxNum = 0;

//        1.计算maxCount
        for (Integer count : countMap.values()) {
            maxCount = Math.max(maxCount, count);
        }

//        2.计算maxNum
        for (Character task : countMap.keySet()) {
            if (countMap.get(task) == maxCount) {
                maxNum++;
            }
        }

//        3. 按照公式直接返回
        return (maxCount - 1) * (n + 1) + maxNum;


    }

    public static void main(String[] args) {
        char[] tasks = {'A', 'A', 'A', 'B', 'B', 'B'};
        int n = 2;

        TaskScheduler taskScheduler = new TaskScheduler();
        System.out.println(taskScheduler.leastInterval2(tasks, n));
    }
}
