package com.shf.algorithm.binary_tree;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * https://leetcode.cn/problems/validate-binary-search-tree/
 * 98. 验证二叉搜索树
 *
 * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 *
 * 有效 二叉搜索树定义如下：
 *
 * 节点的左子树只包含 小于 当前节点的数。
 * 节点的右子树只包含 大于 当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 *
 */
public class ValidBST {
    /**
     * 方法一：先序遍历
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }

        return validator(root, null, null);
    }

    /**
     * 定义一个辅助校验器，用来传入上下界递归调用
     * @param root
     * @param lowerBound
     * @param upperBound
     * @return
     */
    public boolean validator(TreeNode root, Integer lowerBound, Integer upperBound) {
        if (root == null) {
            return true;
        }

//        1.判断当前节点的值是否在上下界范围内，如果超出直接返回false
        if (lowerBound != null && root.val <= lowerBound) {
            return false;
        }
        if (upperBound != null && root.val >= upperBound) {
            return false;
        }

//        2.递归判断左右子树
        return validator(root.left, lowerBound, root.val)
                && validator(root.right, root.val, upperBound);
    }

//    定义一个列表
    ArrayList<Integer> inOrderArray = new ArrayList<>();

    /**
     * 方法二：中序遍历
     * @param root
     * @return
     */
    public boolean isValidBST2(TreeNode root) {
//        1.中序遍历，得到升序数组
        inOrder(root);

//        2.遍历数组，判断是否升序
        for (int i = 0; i < inOrderArray.size(); i++) {
//            挨个判断是否比前一个大
            if (i > 0 && inOrderArray.get(i) <= inOrderArray.get(i - 1)) {
                return false;
            }
        }

        return true;
    }

    /**
     * 方法三：用栈实现
     * @param root
     * @return
     */
    public boolean isValidBST3(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        double preValue = -Double.MAX_VALUE;

//        遍历访问所有节点
        while (root != null || !stack.isEmpty()) {
//            迭代访问节点的左孩子，并入栈
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

//            只要栈不为空，就弹出栈顶元素，依次处理
            if (!stack.isEmpty()) {
                root = stack.pop();
            }
            if (root.val <= preValue) {
                return false;
            }

            preValue = root.val;
            root = root.right;
        }
        return true;
    }

    /**
     * 实现一个中序遍历的方法
     * @param root
     */
    private void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }

        inOrder(root.left);
        inOrderArray.add(root.val);
        inOrder(root.right);
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(1);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(3);
        TreeNode node5 = new TreeNode(6);

        node1.left = node2;
        node1.right = node3;
        node3.left = node4;
        node3.right = node5;

        ValidBST validBST = new ValidBST();
        System.out.println(validBST.isValidBST3(node1));
    }
}
