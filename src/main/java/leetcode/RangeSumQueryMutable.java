package leetcode;

public class RangeSumQueryMutable {
    private int[] tree;
    private int n;

    public RangeSumQueryMutable(int[] nums) {
        n = nums.length;
        tree = new int[2 * n];
        build(nums);
    }

    private void build(int[] nums) {
        // yaprakları doldur
        for (int i = 0; i < n; i++) {
            tree[n + i] = nums[i];
        }
        // iç düğümleri oluştur
        for (int i = n - 1; i > 0; i--) {
            tree[i] = tree[2 * i] + tree[2 * i + 1];
        }
    }

    public void update(int index, int val) {
        int pos = index + n;
        tree[pos] = val;
        while (pos > 1) {
            pos /= 2;
            tree[pos] = tree[2 * pos] + tree[2 * pos + 1];
        }
    }

    public int sumRange(int left, int right) {
        int sum = 0;
        left += n;
        right += n;
        while (left <= right) {
            if ((left & 1) == 1) sum += tree[left++];
            if ((right & 1) == 0) sum += tree[right--];
            left /= 2;
            right /= 2;
        }
        return sum;
    }
}
