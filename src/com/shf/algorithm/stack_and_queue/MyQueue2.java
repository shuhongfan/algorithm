package com.shf.algorithm.stack_and_queue;

import java.util.Stack;

/**
 * https://leetcode.cn/problems/implement-queue-using-stacks/
 * 232. 用栈实现队列
 *
 * 请你仅使用两个栈实现先入先出队列。队列应当支持一般队列支持的所有操作（push、pop、peek、empty）：
 *
 * 实现 MyQueue 类：
 *
 * void push(int x) 将元素 x 推到队列的末尾
 * int pop() 从队列的开头移除并返回元素
 * int peek() 返回队列开头的元素
 * boolean empty() 如果队列为空，返回 true ；否则，返回 false
 */
public class MyQueue2 {
    //    定义两个栈
    Stack<Integer> stack1;
    Stack<Integer> stack2;

    public MyQueue2() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public void push(int x) {
        stack1.push(x);
    }

    public int pop() {
//        1. 判断stack2是否为空，如果为空，就要将stack1中所有元素弹出，然后压入
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }

//        2.弹出stack2栈顶元素
        return stack2.pop();
    }

    public int peek() {
        //        1. 判断stack2是否为空，如果为空，就要将stack1中所有元素弹出，然后压入
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }

//        2.返回stack2栈顶元素
        return stack2.peek();
    }

    public boolean empty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }

    public static void main(String[] args) {
        MyQueue2 myQueue = new MyQueue2();
        myQueue.push(1); // queue is: [1]
        myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
        myQueue.peek(); // return 1
        myQueue.pop(); // return 1, queue is [2]
        myQueue.empty(); // return false
    }
}
