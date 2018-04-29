package code.repository.dev.tree;

import lombok.Data;

/**
 * @author seok
 */

@Data
public class TreeNode {
    private int key;
    private TreeNode parent;
    private TreeNode left;
    private TreeNode right;

    public TreeNode(int key){
        this.key = key;
    }

    public TreeNode() {
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
