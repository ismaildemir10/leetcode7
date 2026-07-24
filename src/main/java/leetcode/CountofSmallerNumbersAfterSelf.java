package leetcode;

import java.util.*;

public class CountofSmallerNumbersAfterSelf {
    public List<Integer> countSmaller(int[] nums) {
        Set<Integer> uniqueValues = new HashSet<>();
        for (int num : nums) {
            uniqueValues.add(num);
        }
        List<Integer> sortedUniqueValues = new ArrayList<>(uniqueValues);
        sortedUniqueValues.sort(Comparator.comparingInt(a -> a));
        int uniqueCount = sortedUniqueValues.size();
        Map<Integer, Integer> valueToIndex = new HashMap<>(uniqueCount);
        for (int i = 0; i < uniqueCount; i++) {
            valueToIndex.put(sortedUniqueValues.get(i), i + 1);
        }
        BinaryIndexedTree fenwickTree = new BinaryIndexedTree(uniqueCount);
        LinkedList<Integer> result = new LinkedList<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            int compressedIndex = valueToIndex.get(nums[i]);
            fenwickTree.update(compressedIndex, 1);
            result.addFirst(fenwickTree.query(compressedIndex - 1));
        }

        return result;
    }
}

class BinaryIndexedTree {
    private int size;
    private int[] tree;
    public BinaryIndexedTree(int size) {
        this.size = size;
        this.tree = new int[size + 1];
    }
    public void update(int x, int delta) {
        while (x <= size) {
            tree[x] += delta;
            x += lowbit(x);
        }
    }
    public int query(int x) {
        int sum = 0;
        while (x > 0) {
            sum += tree[x];
            x -= lowbit(x);
        }
        return sum;
    }
    public static int lowbit(int x) {
        return x & -x;
    }
}
