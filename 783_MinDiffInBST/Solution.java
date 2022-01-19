/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {

    public int minDiffInBST(TreeNode root) {
        int min = Integer.MAX_VALUE;
        // find the min in left sub-tree
        int minLeft = Integer.MAX_VALUE;
        int maxInLeft = -100000;
        if (root.left != null) {
            minLeft = minDiffInBST(root.left);
            maxInLeft = findMaxInTree(root.left);
        }
        int diffBetweenLeftAndRoot = root.val - maxInLeft;

        int minRight = Integer.MAX_VALUE;
        int minInRight = Integer.MAX_VALUE;
        if (root.right != null) {
            minRight = minDiffInBST(root.right);
            minInRight = findMinInTree(root.right);
        }
        int diffBetweenRootAndRight = minInRight - root.val;

        // compare 4 values: minLeft, diffBetweenLeftAndRoot, diffBetweeenRootAndRight, minRight
        return findMin(minLeft, diffBetweenLeftAndRoot, diffBetweenRootAndRight, minRight);
    }

    private int findMin (int a, int b, int c, int d) {
        int min = a;
        if (b < min)
            min = b;
        if (c < min)
            min = c;
        if (d < min)
            min = d;

        return min;
    }

    private int findMaxInTree(TreeNode root) {
        int res = -1;
        while (root != null) {
            res = root.val;
            root = root.right;
        }
        return res;
    }

    private int findMinInTree(TreeNode root) {
        int res = Integer.MAX_VALUE;
        while (root != null) {
            res = root.val;
            root = root.left;
        }
        return res;
     }
}
