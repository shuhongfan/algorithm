package com.shf.algorithm.arrays;

/**
 * https://leetcode.cn/problems/rotate-image/
 * 48. 旋转图像
 * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
 *
 * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
 */
public class RotateImage {
    /**
     * 方法一：数学方法（装置再翻转）
     * @param matrix
     */
    public void rotate1(int[][] matrix) {
        int n = matrix.length;

//        1. 转置矩阵
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

//        2.翻转每一行
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n/2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n - j - 1];
                matrix[i][n-j-1]=temp;
            }
        }
    }

    /**
     * 方法二：分治（分为四部分旋转）
     * @param matrix
     */
    public void rotate2(int[][] matrix){
        int n = matrix.length;

//        遍历四分之一矩阵，左上角
        for (int i = 0; i < n / 2 + n % 2; i++) { // n%2 奇数考虑中间，偶数不考虑中间
            for (int j = 0; j < n / 2; j++) {
//                对于matrix[i][j],需要找到不同的四个矩阵中对应的另外三个位置和元素
//                定义一个临时数组，保存对应的四个元素
                int[] temp = new int[4];
                int row = i;
                int col = j;

//                行列转换的规律：row+newCol=n-1,col=newRow
                for (int k = 0; k < 4; k++) {
                    temp[k] = matrix[row][col];
                    int x = row;
                    row = col;
                    col = n - 1 - x;
                }
                
//                再次遍历需要处理的四个位置，将旋转之后的数据填入
                for (int k = 0; k < 4; k++) {
//                    用上一个值替换当前的位置
                    matrix[row][col] = temp[(k + 3) % 4];
                    int x = row;
                    row = col;
                    col = n - 1 - x;
                }
                
            }
        }
    }

    /**
     * 方法三：分治改进
     * @param matrix
     */
    public void rotate3(int[][] matrix){
        int n = matrix.length;

        for (int i = 0; i < (n + 1) / 2; i++) {
            for (int j = 0; j < n / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - j - 1][i]; // 将上一个位置的元素填入
                matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
                matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
                matrix[j][n - i - 1] = temp;
            }
        }
    }


    public static void main(String[] args) {
        int[][] image1 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        int[][] image2 = {
                {5, 1, 9, 11},
                {2, 4, 8, 10},
                {13, 3, 6, 7},
                {15, 14, 12, 16}
        };

        RotateImage rotateImage = new RotateImage();
        rotateImage.rotate3(image1);
        printImage(image1);

        rotateImage.rotate3(image2);
        printImage(image2);
    }

    private static void printImage(int[][] image) {
        for (int[] line : image) {
            for (int point : line) {
                System.out.print(point+"\t");
            }
            System.out.println();
        }
    }
}
