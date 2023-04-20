package com.shf.algorithm.bfs_dfs;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * https://leetcode.cn/problems/serialize-and-deserialize-binary-tree/
 * 297. 二叉树的序列化与反序列化
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 *
 * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 *
*/
public class TreeSerialization {
    /**
     * 方法一：DFS，先序遍历
     *
     * 序列化方法
     * @param root
     * @return
     */
    public String serialize(TreeNode root) {
//        定义一个StringBuffer来保存序列化结果
        StringBuffer result = new StringBuffer();

        result.append("[");
        dfs_serialize(root, result);
        result.append("]");

        return result.toString();
    }

    /**
     * 递归方法，实现深度优先搜索
     * @param root
     * @param result
     */
    private void dfs_serialize(TreeNode root, StringBuffer result) {
        if (root == null) {
            result.append("null,");
            return;
        }

//        将当前根节点的值序列化添加到result
        result.append(root.val + ",");

//        递归处理左右子树
        dfs_serialize(root.left, result);
        dfs_serialize(root.right, result);
    }

    /**
     * 方法一：DFS，先序遍历
     *
     * 反序列化方法
     * @param data
     * @return
     */
    public TreeNode deserialize(String data) {
//        首先将数据进行切分，得到每个节点的值，保存成一个链表
        String[] dataArr = data.split(",");
        LinkedList<String> dataList = new LinkedList<>(Arrays.asList(dataArr));

//        删掉方括号
        String firstElement = dataList.getFirst().substring(1);
        dataList.removeFirst();
        dataList.addFirst(firstElement);

        String lastElement = dataList.getLast().substring(0, dataList.getLast().length() - 1);
        dataList.removeLast();
        dataList.addLast(lastElement);

        return dfs_deserialize(dataList);
    }

    /**
     * 实现递归方法，辅助反序列化
     * @param dataList
     * @return
     */
    private TreeNode dfs_deserialize(LinkedList<String> dataList) {
//        基准情况，遇到null，就是叶子节点的子节点，返回null
        if (dataList.getFirst().equals("null")) {
            dataList.removeLast();
            return null;
        }

//        获取当前节点
        TreeNode node = new TreeNode(Integer.valueOf(dataList.getFirst()));
        dataList.removeFirst(); // 处理完就删除

//        递归调用，定义当前节点的左右子节点
        node.left = dfs_deserialize(dataList);
        node.right = dfs_deserialize(dataList);
        return node;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(4);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(7);
        TreeNode node4 = new TreeNode(1);
        TreeNode node5 = new TreeNode(3);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(9);

        node1.left = node2;
        node1.right = node3;

        node2.left = node4;
        node2.right = node5;

        node3.left = node6;
        node3.right = node7;

        TreeSerialization treeSerialization = new TreeSerialization();
        System.out.println(treeSerialization.serialize(node1));

        TreeNode newTree = treeSerialization.deserialize(treeSerialization.serialize(node1));
    }
}
