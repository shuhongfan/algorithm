package com.atguigu.algorithm.stack_and_queue;

import java.util.LinkedList;
import java.util.Queue;

// 使用两个队列实现自定义栈
public class MyStack {
    // 定义两个队列
    Queue<Integer> queue1;
    Queue<Integer> queue2;

    public MyStack() {
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
    }

    // 入栈方法
    public void push(int x){
        // 1. 把x保存到queue2中
        queue2.offer(x);

        // 2. 将queue1中所有元素依次出队，然后放入queue2
        while (!queue1.isEmpty()){
            queue2.offer( queue1.poll() );
        }

        // 3. 交换两个队列
        Queue<Integer> temp = queue1;
        queue1 = queue2;
        queue2 = temp;
    }

    // 出栈操作
    public int pop(){
        // queue1出队就是出栈
        return queue1.poll();
    }

    // 获取栈顶元素
    public int top(){
        return queue1.peek();
    }

    // 判断为空
    public boolean empty(){
        return queue1.isEmpty();
    }
}
