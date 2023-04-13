package com.shf.algorithm.linked_list;

/**
 * https://leetcode.cn/problems/merge-two-sorted-lists/
 * 21. 合并两个有序链表
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 */
public class MergeTwoLists {

    /**
     * 方法一：迭代法
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
//        1.首先，定义一个哨兵节点，它的next指向最终结果的头结点
        ListNode sentinel = new ListNode(-1);

//        保存当前结果链表里面的最后一个节点，作为判断比较的前一个节点
        ListNode prev = sentinel;

//        迭代遍历两个链表，知道至少有一个为null
        while (l1 != null && l2 != null) {
//            比较当前两个链表的头结点，较小的那个就接在结果链表末尾
            if (l1.val <= l2.val) {
                prev.next = l1; // 将l1头结点连接到prev后面
                prev = l1; // 指针向前移动，以下一个节点作为链表头结点
                l1 = l1.next;
            } else {
                prev.next = l2; // 将l2头结点连接到prev后面
                prev = l2; // 指针向前移动，以下一个节点作为链表头结点
                l2 = l2.next;
            }
        }

//        循环结束，最多还有一个链表没有遍历完成，因为已经排序,可以直接把剩余节点连接到结果链表上
        prev.next = (l1 == null) ? l2 : l1;
        return sentinel.next;
    }

    /**
     * 方法二：递归
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
//        基准情况
        if (l1 == null) {
            return l2;
        }

        if (l2 == null) {
            return l1;
        }

//        比较头结点
        if (l1.val <= l2.val) {
//            l1头结点较小，直接提取出来，剩下的l1和l2继续合并,接在l1后面
            l1.next = mergeTwoLists2(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists2(l1, l2.next);
            return l2;
        }
    }

    public static void main(String[] args) {
        ListNode listNode11 = new ListNode(1);
        ListNode listNode12 = new ListNode(2);
        ListNode listNode13 = new ListNode(4);
        ListNode listNode21 = new ListNode(1);
        ListNode listNode22 = new ListNode(3);
        ListNode listNode23 = new ListNode(4);

        listNode11.next = listNode12;
        listNode12.next = listNode13;
        listNode13.next = null;
        listNode21.next = listNode22;
        listNode22.next = listNode23;
        listNode23.next = null;

        TestLinkedList.printList(listNode11);
        TestLinkedList.printList(listNode21);

        MergeTwoLists mergeTwoLists = new MergeTwoLists();
        TestLinkedList.printList(mergeTwoLists.mergeTwoLists2(listNode11, listNode21));
    }
}
