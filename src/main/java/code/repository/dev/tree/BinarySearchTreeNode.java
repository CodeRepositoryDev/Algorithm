package code.repository.dev.tree;

import lombok.Data;

/**
 * @author seok
 */

@Data
public class BinarySearchTreeNode {
    private int key;
    private BinarySearchTreeNode parent;
    private BinarySearchTreeNode left;
    private BinarySearchTreeNode right;

    public BinarySearchTreeNode(int key){
        this.key = key;
    }

    public BinarySearchTreeNode() {
        super();
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }

    @Override
    public String toString() {
        return "key: " + String.valueOf(key);
    }
}
