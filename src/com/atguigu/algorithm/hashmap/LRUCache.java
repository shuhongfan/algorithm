package com.atguigu.algorithm.hashmap;

import java.util.HashMap;

// 自定义实现HashMap+双向链表的缓存机制
public class LRUCache {
    // 定义双向链表的节点类
    class Node {
        int key;
        int value;
        Node next;
        Node prev;   // 指向前一个节点的指针

        public Node() {
        }

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    // 定义哈希表
    private HashMap<Integer, Node> hashMap =  new HashMap<Integer, Node>();
    // 定义属性
    private int capacity;
    private int size;

    // 定义头尾指针
    private Node head, tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;

        // 用哑节点定义哨兵，方便统一处理
        head = new Node();
        tail = new Node();

        head.next = tail;
        tail.prev = head;
    }

    // get方法
    public int get(int key){
        // 从哈希表中查找key，如果不存在的话就返回-1
        Node node = hashMap.get(key);
        if (node == null) return -1;

        // 如果存在，将当前节点移到链表末尾
        moveToTail(node);

        return node.value;
    }

    // put操作
    public void put(int key, int value){
        // 同样先在哈希表中查找key
        Node node = hashMap.get(key);

        // 如果key存在，修改value，并移到末尾
        if (node != null){
            node.value = value;
            moveToTail(node);
        }
        // 如果不存在，需要创建新的节点，插入到末尾
        else {
            Node newNode = new Node(key, value);
            hashMap.put(key, newNode);    // 保存进哈希表
            addToTail(newNode);    // 添加到双向链表的末尾
            size ++;    // 当前size增大

            // 如果超出了容量限制，删除链表头节点
            if (size > capacity){
                Node head = removeHead();
                hashMap.remove(head.key);
                size --;
            }
        }
    }

    // 移动节点到链表末尾
    private void moveToTail(Node node){
        removeNode(node);
        addToTail(node);
    }

    // 通用方法，删除链表中的某一个节点
    private void removeNode(Node node){
        // 跳过当前node
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    // 在链表末尾增加一个节点
    private void addToTail(Node node){
        node.next = tail;
        node.prev = tail.prev;    // 以原先的末尾节点作为前一个节点
        tail.prev.next = node;
        tail.prev = node;
    }

    // 删除头节点
    private Node removeHead(){
        Node realHead = head.next;
        removeNode(realHead);
        return realHead;
    }
    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1); // 缓存是 {1=1}
        lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
        System.out.println(lRUCache.get(1));   // 返回 1
        lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        System.out.println(lRUCache.get(2));    // 返回 -1 (未找到)
        lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        System.out.println(lRUCache.get(1));     // 返回 -1 (未找到)
        System.out.println(lRUCache.get(3));     // 返回 3
        System.out.println(lRUCache.get(4));     // 返回 4
    }
}
