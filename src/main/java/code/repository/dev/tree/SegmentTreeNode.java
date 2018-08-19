package code.repository.dev.tree;

import lombok.Data;

@Data
public class SegmentTreeNode {
    private int key;
    private int value;
    private SegmentTreeNode left;
    private SegmentTreeNode right;

    public SegmentTreeNode() {
        super();
    }

    private int initValue(int[] a, int key, int start, int end) {
        if (start == end) {
            return this.value = a[start];
        } else {
            return this.value = initValue(a, key * 2, start, (start + end) / 2) + initValue(a, key * 2 + 1, (start + end) / 2, (start + end) / 2 + 1);
        }
    }
}
