package leetcode;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class FlattenNestedListIterator {
    public interface NestedInteger {

              public boolean isInteger();
              public Integer getInteger();
              public List<NestedInteger> getList();
  }
    public FlattenNestedListIterator(List<NestedInteger> nestedList) {addInteger(nestedList);
    }

    //@Override
    public Integer next() {
        return q.poll();
    }

    //@Override
    public boolean hasNext() {
        return !q.isEmpty();
    }

    private Queue<Integer> q = new ArrayDeque<>();

    private void addInteger(final List<NestedInteger> nestedList) {
        for (final NestedInteger ni : nestedList)
            if (ni.isInteger())
                q.offer(ni.getInteger());
            else
                addInteger(ni.getList());}
}
