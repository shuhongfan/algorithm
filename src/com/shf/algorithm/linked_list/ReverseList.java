package com.shf.algorithm.linked_list;

/**
 * https://leetcode.cn/problems/reverse-linked-list/
 * 206. 反转链表
 *
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 */
public class ReverseList {
    /**
     * 方法一：迭代
     * @param head
     * @return
     */
    public ListNode reverseList1(ListNode head){
//        定义一个指针，指向当前访问的节点,以及上一个节点
        ListNode curr = head;
        ListNode prev = null;

//        依次迭代链表中的节点，将next指针指向prev
        while (curr != null) {
//            临时保存当前节点的下一个节点
            ListNode tempNext = curr.next;
            curr.next = prev;

//            更新指针，当前指针变为之前的next，上一个指针变成current
            prev = curr;
            curr = tempNext;
        }

//        prev指向的就是末尾的节点，也就是反转后的头结点
        return prev;
    }

    /**
     * 方法二：递归
     * @param head
     * @return
     */
    public ListNode reverseList2(ListNode head){
//        基准情况
        if (head == null || head.next == null) {
            return head;
        }

//        递归调用，翻转剩余所有节点
        ListNode restHead = head.next;
        ListNode reverseRest = reverseList2(restHead);

//        把当前节点在反转之后的链表末尾
        restHead.next = head;
//        当前节点就是链表末尾，直接指向null
        head.next = null;

        return reverseRest;
    }

    public static void main(String[] args) {
        //        构建一个链表,把所有节点创建出来，然后链接
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

        ReverseList reverseList = new ReverseList();
        TestLinkedList.printList(reverseList.reverseList2(listNode1));
    }
}
