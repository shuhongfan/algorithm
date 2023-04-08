package com.atguigu.algorithm.binary_tree;

public class InvertBinaryTree {
    // 1. 先序遍历
    public TreeNode invertTree1(TreeNode root){
        // 处理基准场景
        if (root == null) return null;

        // 1. 先处理根节点，交换左右子节点
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        // 2. 递归处理左右子树
        invertTree(root.left);
        invertTree(root.right);

        return root;
    }

    // 2. 后序遍历
    public TreeNode invertTree(TreeNode root){
        // 处理基准场景
        if (root == null) return null;

        // 1. 递归处理左右子树
        TreeNode left = invertTree(root.left);
        TreeNode right =  invertTree(root.right);

        // 2. 处理根节点，交换左右子节点
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

        InvertBinaryTree invertBinaryTree = new InvertBinaryTree();

        TreePrinter.printTreeLevelOrder(invertBinaryTree.invertTree(node1));
    }
}
