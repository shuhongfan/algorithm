package com.shf.algorithm.binary_tree;

import com.sun.org.apache.xerces.internal.impl.dv.xs.AnyURIDV;

/**
 * https://leetcode.cn/problems/invert-binary-tree/
 * 226. 翻转二叉树
 *
 * 给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。
 */
public class InvertTree {
    /**
     * 1. 先序遍历
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
//        处理基准场景
        if (root == null) {
            return null;
        }

//       1.先处理根节点，交换左右节点
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

//        2. 递归处理左右子树
        invertTree(root.left);
        invertTree(root.right);

        return root;
    }

    /**
     * 2.后序遍历
     * @param root
     * @return
     */
    public TreeNode invertTree2(TreeNode root) {
//        处理基准场景
        if (root == null) {
            return null;
        }

//        1.递归处理左右子树
        TreeNode left = invertTree2(root.left);
        TreeNode right = invertTree2(root.right);

//        2.处理根节点，交换左右子节点
        root.left = right;
        root.right = left;

        return root;
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

        TreePrinter.printTreeLevelOrder(node1);
        System.out.println();

        InvertTree invertTree = new InvertTree();
        TreePrinter.printTreeLevelOrder(invertTree.invertTree2(node1));
    }
}
