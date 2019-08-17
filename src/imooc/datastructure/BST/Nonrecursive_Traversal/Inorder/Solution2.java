package imooc.datastructure.BST.Nonrecursive_Traversal.Inorder;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/// Source : https://leetcode.com/problems/binary-tree-inorder-traversal/solution/
/// Author : liuyubobobo
/// Time   : 2018-05-30

// Another Classic Non-Recursive algorithm for inorder traversal
// Time Complexity: O(n), n is the node number in the tree
// Space Complexity: O(h), h is the height of the tree
/**
 * Description:
 *
 * @date: 2018/11/26 16:48
 */
public class Solution2 {
    public List<Integer> inorderTraversal(TreeNode root) {

        ArrayList<Integer> res = new ArrayList<Integer>();
        if (root == null)
            return res;

        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.empty()) {

            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                res.add(cur.val);
                cur = cur.right;
            }
        }
        return res;
    }
}
