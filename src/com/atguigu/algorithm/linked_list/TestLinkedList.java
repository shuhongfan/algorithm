package com.atguigu.algorithm.linked_list;

import java.util.LinkedList;

public class TestLinkedList {
    public static void main(String[] args) {
        // 构建一个链表，把所有节点创建出来，然后连接
        ListNode listNode1 = new ListNode(2);
        ListNode listNode2 = new ListNode(5);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(17);


        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = null;

        printList(listNode1);
    }

    // 遍历打印链表元素
    public static void printList(ListNode head){
        ListNode curNode = head;
        while (curNode != null){
            System.out.print(curNode.val + " -> ");
            curNode = curNode.next;
        }
        System.out.print("null");
        System.out.println();
    }
}
