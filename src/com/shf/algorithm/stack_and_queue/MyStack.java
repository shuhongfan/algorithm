package com.shf.algorithm.stack_and_queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.cn/problems/implement-stack-using-queues/
 * 225. 用队列实现栈
 *
 * 请你仅使用两个队列实现一个后入先出（LIFO）的栈，并支持普通栈的全部四种操作（push、top、pop 和 empty）。
 *
 * 实现 MyStack 类：
 *
 * void push(int x) 将元素 x 压入栈顶。
 * int pop() 移除并返回栈顶元素。
 * int top() 返回栈顶元素。
 * boolean empty() 如果栈是空的，返回 true ；否则，返回 false 。
 */
public class MyStack {
    //    定义两个队列
    Queue<Integer> queue1;
    Queue<Integer> queue2;

    public MyStack() {
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
    }

    /**
     * 入栈方法
     * @param x
     */
    public void push(int x) {
//        1. 把x保存到queue2中
        queue2.offer(x);

//        2. 将queue1中所有元素依次出队，然后放入queue2
        while (!queue1.isEmpty()) {
            queue2.offer(queue1.poll());
        }

//        3.交换两个队列
        Queue<Integer> temp = queue1;
        queue1 = queue2;
        queue2 = temp;
    }

    /**
     * 出栈操作
     * @return
     */
    public int pop() {
//        queue1出队就是出栈
        return queue1.poll();
    }

    /**
     * 判断为空
     * @return
     */
    public boolean empty() {
        return queue1.isEmpty();
    }

}
