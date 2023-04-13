package com.shf.algorithm.linked_list;

import java.util.List;
import java.util.Stack;

/**
 * https://leetcode.cn/problems/remove-nth-node-from-end-of-list/
 * 19. 删除链表的倒数第 N 个结点
 * <p>
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 */
public class RemoveNthFromEnd {
    /**
     * 方法一：计算链表长度
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd1(ListNode head, int n) {
//        1. 遍历链表，得到长度l
        int l = getLength(head);

//        2. 从前到后继续遍历，找到正数第l-n+1个元素
//        定义一个哨兵节点，next指向头结点
        ListNode sentinel = new ListNode(-1, head);

        ListNode curr = sentinel;
        for (int i = 0; i < l - n; i++) {
//          找到第l-n个节点
            curr = curr.next;
        }

//        跳过第l-n+1个节点
        curr.next = curr.next.next;
        return sentinel.next;
    }

    /**
     * 定义一个计算链表长度的方法
     *
     * @param head
     * @return
     */
    public static int getLength(ListNode head) {
        int length = 0;
        while (head != null) {
            length++;
            head = head.next;
        }
        return length;
    }

    /**
     * 方法二：使用栈
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd2(ListNode head, int n) {
//        定义一个哨兵节点，next指向头结点
        ListNode sentinel = new ListNode(-1, head);
        ListNode curr = sentinel;

//        定义一个栈
        Stack<ListNode> stack = new Stack<>();

//        1. 将所有节点入栈
        while (curr != null) {
            stack.push(curr);
            curr = curr.next;
        }

//        2.弹栈n次
        for (int i = 0; i < n; i++) {
            stack.pop();
        }

        stack.peek().next = stack.peek().next.next;
        return sentinel.next;
    }

    /**
     * 方法三：双指针
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd3(ListNode head, int n) {
//        定义一个哨兵节点，next指向头结点
        ListNode sentinel = new ListNode(-1, head);

//        定义前后双向指针
        ListNode first = sentinel, second = sentinel;

//        1. first先走n+1步
        for (int i = 0; i < n + 1; i++) {
            first = first.next;
        }

//        2. first、second同时前进，当first变为null，second就是倒数第n+1个节点
        while (first != null) {
            first = first.next;
            second = second.next;
        }

//        3.删除倒数第n个节点
        second.next = second.next.next;
        return sentinel;
    }

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);

        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        listNode5.next = null;
        TestLinkedList.printList(listNode1);

        RemoveNthFromEnd removeNthFromEnd = new RemoveNthFromEnd();
        removeNthFromEnd.removeNthFromEnd3(listNode1, 4);

        TestLinkedList.printList(listNode1);

    }
}
