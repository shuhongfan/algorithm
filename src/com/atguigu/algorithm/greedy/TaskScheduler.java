package com.atguigu.algorithm.greedy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

public class TaskScheduler {
    // 方法一：模拟法
    public int leastInterval1(char[] tasks, int n){
        // 用HashMap统计每个任务出现的次数
        HashMap<Character, Integer> countMap = new HashMap<>();
        for (char task: tasks)
            countMap.put(task, countMap.getOrDefault(task, 0) + 1);

        // 任务种类数量
        int t = countMap.size();

        // 定义两个状态列表
        ArrayList<Integer> restCount = new ArrayList<>(countMap.values());    // 任务剩余次数
        ArrayList<Integer> nextAvailableTime = new ArrayList<>(Collections.nCopies(t, 1));    // 任务下次可执行时间

        int time = 0;    // 模拟CPU时钟

        // 遍历任务选择执行
        for (int i = 0; i < tasks.length; i++){
            time ++;

            int minNextAvailableTime = Integer.MAX_VALUE;
            // 1. 获取所有任务中最早可执行的时间
            for (int j = 0; j < t; j++){
                // 取还没做完的任务
                if (restCount.get(j) != 0){
                    minNextAvailableTime = Math.min(minNextAvailableTime, nextAvailableTime.get(j));
                }
            }

            // 2. 直接推进时间，执行任务
            time = Math.max(time, minNextAvailableTime);

            // 3. 选取可执行任务中，剩余次数最多的那个执行
            int maxRestCountTask = -1;    // 保存要执行任务的索引
            for (int j = 0; j < t; j ++){
                if (restCount.get(j) > 0 && nextAvailableTime.get(j) <= time){
                    // 如果比之前保存的最大剩余任务数还大，就更新
                    if (maxRestCountTask == -1 || restCount.get(j) > restCount.get(maxRestCountTask)){
                        maxRestCountTask = j;
                    }
                }
            }

            // 4. 执行任务，更新状态列表
            nextAvailableTime.set(maxRestCountTask, time + n + 1);
            restCount.set(maxRestCountTask, restCount.get(maxRestCountTask) - 1);
        }

        return time;
    }

    // 方法二：构造法
    public int leastInterval(char[] tasks, int n){
        // 用HashMap统计每个任务出现的次数
        HashMap<Character, Integer> countMap = new HashMap<>();
        for (char task: tasks)
            countMap.put(task, countMap.getOrDefault(task, 0) + 1);

        // 任务种类数量
        int t = countMap.size();

        int maxCount = 0;
        int maxNum = 0;

        // 1. 计算maxCount
        for (int count: countMap.values()){
            maxCount = Math.max(maxCount, count);
        }

        // 2. 计算maxNum
        for (char task: countMap.keySet()){
            if (countMap.get(task) == maxCount)
                maxNum ++;
        }

        // 3. 按照公式直接返回
        return Math.max(tasks.length, (maxCount - 1) * (n + 1) + maxNum);
    }

    public static void main(String[] args) {
        char[] tasks = {'A','A','A','B','B','B'};
        int n = 2;

        TaskScheduler taskScheduler = new TaskScheduler();

        System.out.println(taskScheduler.leastInterval(tasks, n));
    }
}
