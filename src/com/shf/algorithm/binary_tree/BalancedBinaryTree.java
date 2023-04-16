package com.shf.algorithm.binary_tree;

/**
 * https://leetcode.cn/problems/balanced-binary-tree/
 * 110. 平衡二叉树
 *
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 *
 * 本题中，一棵高度平衡二叉树定义为：
 *
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。
 */
public class BalancedBinaryTree {
    /**
     * 方法一：先序遍历
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }

        return Math.abs(height(root.left) - height(root.right)) <= 1
                && isBalanced(root.left)
                && isBalanced(root.right);
    }

    /**
     * 定义一个计算树高度的方法
     * @param root
     * @return
     */
    public int height(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return Math.max(height(root.left), height(root.right)) + 1;
    }

    /**
     * 方法二：后序遍历
     * @param root
     * @return
     */
    public boolean isBalanced2(TreeNode root) {
        return balancedHeight(root) > -1;
    }

    /**
     * 定义一个直接判断当前数是否平衡的方法，也返回高度
      * @param root
     * @return
     */
    public int balancedHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }

//        递归计算左右子树高度
        int leftHeight = balancedHeight(root.left);
        int rightHeight = balancedHeight(root.right);

//        如果子树不平衡，直接返回-1
        if (leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }

//        如果平衡，返回当前高度
        return Math.max(leftHeight, rightHeight) + 1;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(9);
        TreeNode node3 = new TreeNode(20);
        TreeNode node4 = new TreeNode(15);
        TreeNode node5 = new TreeNode(7);

        node1.left = node2;
        node1.right = node3;
        node3.left = node4;
        node3.right = node5;

        BalancedBinaryTree balancedBinaryTree = new BalancedBinaryTree();
        System.out.println(balancedBinaryTree.isBalanced2(node1));
    }
}
