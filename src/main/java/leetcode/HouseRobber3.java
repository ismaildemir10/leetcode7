package leetcode;
class T {
    public int robRoot;
    public int notRobRoot;
    public T(int robRoot, int notRobRoot) {
        this.robRoot = robRoot;
        this.notRobRoot = notRobRoot;
    }
}
public class HouseRobber3 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        public int rob(TreeNode root) {
            T t = robOrNotRob(root);
            return Math.max(t.robRoot, t.notRobRoot);
        }

        private T robOrNotRob(TreeNode root) {
            if (root == null)
                return new T(0, 0);
            T l = robOrNotRob(root.left);
            T r = robOrNotRob(root.right);
            return new T(root.val + l.notRobRoot + r.notRobRoot,
                    Math.max(l.robRoot, l.notRobRoot) + Math.max(r.robRoot, r.notRobRoot));
        }
    }
}