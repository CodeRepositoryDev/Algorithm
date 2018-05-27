package code.repository.dev.huffman;

import lombok.Data;

@Data
public class HuffmanNode implements Comparable<HuffmanNode> {
    Character key;
    int count;

    HuffmanNode left;
    HuffmanNode right;

    public HuffmanNode(char key, int count) {
        this.key = key;
        this.count = count;
    }

    public HuffmanNode(HuffmanNode left, HuffmanNode right) {
        count = left.count + right.count;

        this.left = left;
        this.right = right;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }

    @Override
    public int compareTo(HuffmanNode o) {
        if (this.count > o.count) {
            return 1;
        } else if (this.count < o.count) {
            return -1;
        }

        return 0;
    }
}
