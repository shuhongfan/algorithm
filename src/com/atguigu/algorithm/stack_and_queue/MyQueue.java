package com.atguigu.algorithm.stack_and_queue;

import java.util.Stack;

// 用两个栈实现队列：入队时翻转
public class MyQueue {
    // 定义两个栈
    Stack<Integer> stack1;
    Stack<Integer> stack2;

    public MyQueue() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    // 入队方法
    public void push(int x){
        // 1. 首先将stack1中所有元素弹出，压入stack2
        while (!stack1.isEmpty())
            stack2.push( stack1.pop() );

        // 2. 将新元素压入stack1
        stack1.push(x);

        // 3. 再将stack2中所有元素弹出，压入stack
        while (!stack2.isEmpty())
            stack1.push( stack2.pop() );
    }

    // 出队方法
    public int pop(){
        return stack1.pop();
    }

    // 获取队首元素
    public int peek(){
        return stack1.peek();
    }

    // 判空
    public boolean empty(){
        return stack1.isEmpty();
    }

    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue();
        myQueue.push(1); // queue is: [1]
        myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
        myQueue.peek(); // return 1
        myQueue.pop(); // return 1, queue is [2]
        myQueue.empty(); // return false
    }
}
