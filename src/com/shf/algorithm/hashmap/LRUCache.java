package com.shf.algorithm.hashmap;

import java.util.HashMap;

/**
 * https://leetcode.cn/problems/lru-cache/
 * 146. LRU 缓存
 * <p>
 * 请你设计并实现一个满足  LRU (最近最少使用) 缓存 约束的数据结构。
 * 实现 LRUCache 类：
 * LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；如果不存在，则向缓存中插入该组 key-value 。如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。
 * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
 */
public class LRUCache { // 自定义实现HashMap+双向链表的缓存机制
    class Node{
        int key;
        int value;
        Node next;
        Node prev; // 指向前一个节点的指针

        public Node() {

        }

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

//    定义哈希表
    private HashMap<Integer, Node> hashMap = new HashMap<>();

//    定义属性
    private int capacity; //最大内存大小
    private int size; // 已经占用内存大小

//    定义头尾指针
    private Node head, tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;

//        用哑结点定义哨兵，方便统一处理
        head = new Node();
        tail = new Node();

        head.next = tail;
        tail.prev = head;
    }

    /**
     * get方法
     * @param key
     * @return
     */
    public int get(int key) {
//        从哈希表中查找key，如果不存在的话就返回-1
        Node node = hashMap.get(key);
        if (node == null) {
            return -1;
        }

//        如果存在，将当前节点移到链表末尾
        moveToTail(node);
        return node.value;
    }

    /**
     * 移动节点到末尾
     * @param node
     */
    private void moveToTail(Node node) {
        removeNode(node);
        addToTail(node);
    }

    /**
     * 在链表末尾增加一个节点
     * @param node
     */
    private void addToTail(Node node) {
        node.next = tail;
        node.prev = tail.prev; // 以原先的末尾节点作为前一个节点
        tail.prev.next = node;
        tail.prev = node;
    }

    /**
     * 通用方法，删除链表中的某一个节点
     * @param node
     */
    private void removeNode(Node node) {
//        跳过当前node
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    /**
     * put操作
     * @param key
     * @param value
     */
    public void put(int key, int value) {
//        同样先在哈希表中查找key
        Node node = hashMap.get(key);

//        如果key存在，修改value，并移动到末尾
        if (node != null) {
            node.value = value;
            moveToTail(node);
        } else { // 如果不存在，需要创建新的节点，插入到末尾
            Node newNode = new Node(key, value);
            hashMap.put(key, newNode); // 保存进哈希表
            addToTail(newNode); // 添加到双向链表的末尾
            size++; // 当前size增大

//            如果超出了容量限制，删除链表头结点
            if (size > capacity) {
                Node head = removeHead();
                hashMap.remove(head.key);
                size--;
            }
        }
    }

    /**
     * 删除头结点
     * @return
     */
    private Node removeHead() {
        Node realHead = head.next;
        removeNode(realHead);
        return realHead;
    }

    public static void main(String[] args) {
        LRUCache LRUCache = new LRUCache(2);
        LRUCache.put(1, 1); // 缓存是 {1=1}
        LRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
        System.out.println(LRUCache.get(1));   // 返回 1
        LRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        System.out.println(LRUCache.get(2));    // 返回 -1 (未找到)
        LRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        System.out.println(LRUCache.get(1));     // 返回 -1 (未找到)
        System.out.println(LRUCache.get(3));     // 返回 3
        System.out.println(LRUCache.get(4));     // 返回 4
    }
}
