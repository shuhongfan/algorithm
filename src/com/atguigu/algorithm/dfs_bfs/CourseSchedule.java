package com.atguigu.algorithm.dfs_bfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

public class CourseSchedule {
    // 方法一：BFS
    public boolean canFinish1(int numCourses, int[][] prerequisites){
        // 定义数组保存所有节点入度
        int[] inDegrees = new int[numCourses];

        // 定义HashMap存储邻接矩阵
        HashMap<Integer, ArrayList<Integer>> followUpCourses = new HashMap<>();

        // 1. 遍历先决条件，计算入度和后续节点
        for (int[] prerequisite: prerequisites){
            inDegrees[prerequisite[0]] ++;    // 后修课程入度加1

            // 获取先修课程的后续节点列表
            ArrayList<Integer> followUpCourseList = followUpCourses.getOrDefault(prerequisite[1], new ArrayList<>());
            followUpCourseList.add(prerequisite[0]);    // 后修课程加入列表
            followUpCourses.put(prerequisite[1], followUpCourseList);
        }

        // 定义队列保存当前可以学习的课程，入度为0的课程
        LinkedList<Integer> selectableCourses = new LinkedList<>();

        // 2. 启动BFS，将入度为0的所有课程入队
        for (int i = 0; i < numCourses; i++){
            if (inDegrees[i] == 0)
                selectableCourses.offer(i);
        }

        // 用一个变量记录已学过的课程数量
        int finishedCoursesNum = 0;

        // 3. 不停地出队（学习课程），将后续课程入度减1，并将新的入度为0的课程入队
        while (!selectableCourses.isEmpty()){
            int course = selectableCourses.poll();    // 出队
            finishedCoursesNum ++;

            // 遍历当前课程的后续课程，入度减1
            for (int followUpCourse: followUpCourses.getOrDefault(course, new ArrayList<>())){
                inDegrees[followUpCourse] --;
                // 如果当前后续课程入度减成1，入队
                if (inDegrees[followUpCourse] == 0)
                    selectableCourses.offer(followUpCourse);
            }
        }

        // 4. 判断是否学完所有课程
        return finishedCoursesNum == numCourses;
    }

    // 方法二：DFS
    public boolean canFinish(int numCourses, int[][] prerequisites){
        // 定义HashMap存储邻接矩阵
        HashMap<Integer, ArrayList<Integer>> followUpCourses = new HashMap<>();
        // 1. 遍历先决条件，计算后续节点
        for (int[] prerequisite: prerequisites){
            // 获取先修课程的后续节点列表
            ArrayList<Integer> followUpCourseList = followUpCourses.getOrDefault(prerequisite[1], new ArrayList<>());
            followUpCourseList.add(prerequisite[0]);    // 后修课程加入列表
            followUpCourses.put(prerequisite[1], followUpCourseList);
        }

        // 定义一个栈，优先搜索最后要学习的课程
        Stack<Integer> lastCourses = new Stack<>();

        // 定义一个数组，保存课程是否在当前搜索路径上出现过
        boolean[] isSearched = new boolean[numCourses];

        boolean canFinish = true;

        // 2. 遍历每一个节点进行DFS
        for (int i = 0; i < numCourses && canFinish; i ++){
            if (!lastCourses.contains(i)){
                // 不在栈内就搜索，用一个递归方法返回当前路径是否有效
                canFinish = dfs(followUpCourses, lastCourses, isSearched, i);
            }
        }
        return canFinish;
    }

    // 实现辅助DFS方法
    public boolean dfs(HashMap<Integer, ArrayList<Integer>> followUpCourses, Stack<Integer> lastCourses, boolean[] isSearched, int i){
        // 当前节点在路径中出现
        isSearched[i] = true;

        // 遍历所有后续课程，递归调用做深度搜索
        for (int followUpCoures: followUpCourses.getOrDefault(i, new ArrayList<>())){
            if (isSearched[followUpCoures])
                return false;
            else {
                if(!dfs(followUpCourses, lastCourses, isSearched, followUpCoures))
                    return false;
            }
        }

        // 后续节点处理完毕，当前节点入栈
        lastCourses.push(i);

        // 状态回溯
        isSearched[i] = false;

        return true;
    }


    public static void main(String[] args) {
        int[][] prerequisites = {{1, 0}};
        int[][] prerequisites2 = {{1, 0}, {0, 1}};

        CourseSchedule courseSchedule = new CourseSchedule();

        System.out.println(courseSchedule.canFinish(2, prerequisites));
        System.out.println(courseSchedule.canFinish(2, prerequisites2));
    }
}
