package com.atguigu.algorithm.stack_and_queue;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Stack;

public class MyQueue2 {
    // 定义两个栈
    Stack<Integer> stack1;
    Stack<Integer> stack2;

    public MyQueue2() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public void push(int x){
        stack1.push(x);
    }

    public int pop(){
        // 1. 判断stack2是否为空，如果为空，就要将stack1中所有元素弹出，然后压入
        if (stack2.isEmpty()){
            while (!stack1.isEmpty())
                stack2.push( stack1.pop() );
        }

        // 2. 弹出stack2栈顶元素
        return stack2.pop();
    }

    public int peek(){
        // 1. 判断stack2是否为空，如果为空，就要将stack1中所有元素弹出，然后压入
        if (stack2.isEmpty()){
            while (!stack1.isEmpty())
                stack2.push( stack1.pop() );
        }

        // 2. 返回stack2栈顶元素
        return stack2.peek();
    }

    public boolean empty(){
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
