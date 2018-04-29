package code.repository.dev.tree;

import lombok.Data;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author seok
 */
@Data
public class BinarySearchTree {
    private static final TreeNode NO_SUCH_KEY = null;
    private TreeNode root;

    public TreeNode searchNode(TreeNode node, int key) {
        System.out.println(node);

        if (node.isLeaf()) {
            return node;
        }

        if (node.getKey() == key) {
            return node;
        } else if (node.getKey() < key) {
            if (node.getRight() != null) {
                return searchNode(node.getRight(), key);
            } else {
                return node;
            }
        } else {
            if (node.getLeft() != null) {
                return searchNode(node.getLeft(), key);
            } else {
                return node;
            }
        }
    }

    public void insertNode(int key) {
        TreeNode parentNode = searchNode(root, key);

        if (parentNode.getKey() == key) {
            throw new IllegalStateException("duplicated key");
        }

        TreeNode child = new TreeNode();
        child.setParent(parentNode);
        child.setKey(key);

        if (parentNode.getKey() < key) {
            parentNode.setRight(child);
        } else {
            parentNode.setLeft(child);
        }
    }

    public void deleteNode(int key) {
        TreeNode node = searchNode(root, key);

        if (node.getKey() != key) {
            throw new IllegalStateException("no such key");
        }

        TreeNode parent = node.getParent();

        if (node.isLeaf()) {
            if (parent.getLeft().getKey() == key) {
                parent.setLeft(null);
            } else if (parent.getRight().getKey() == key) {
                parent.setRight(null);
            }
        } else {
            if (node.getRight() == null) {
                if (parent.getLeft().getKey() == key) {
                    parent.setLeft(node.getLeft());
                } else if (parent.getRight().getKey() == key) {
                    parent.setRight(node.getLeft());
                }
            } else if (node.getLeft() == null) {
                if (parent.getLeft().getKey() == key) {
                    parent.setLeft(node.getRight());
                } else if (parent.getRight().getKey() == key) {
                    parent.setRight(node.getRight());
                }
            } else {
                TreeNode replaceNode = searchNode(node.getLeft(), key);
                replaceNode.getParent().setLeft(replaceNode.getLeft());
                replaceNode.setRight(node.getRight());
                if (parent.getLeft().getKey() == key) {
                    parent.setLeft(replaceNode);
                } else if (parent.getRight().getKey() == key) {
                    parent.setRight(replaceNode);
                }
            }
        }
    }

    public void preOrder(TreeNode root) {
        if (root != null) {
            System.out.println("node: " + root.getKey());
            preOrder(root.getLeft());
            preOrder(root.getRight());
        }
    }

    public void inOrder(TreeNode root) {
        if (root != null) {
            inOrder(root.getLeft());
            System.out.println("node: " + root.getKey());
            inOrder(root.getRight());
        }
    }

    public void postOrder(TreeNode root) {
        if (root != null) {
            postOrder(root.getLeft());
            postOrder(root.getRight());
            System.out.println("node: " + root.getKey());
        }
    }

    public void levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode parent = queue.poll();
            System.out.println("node: " + parent.getKey());

            if(parent.getLeft() != null){
                queue.add(parent.getLeft());
            }

            if(parent.getRight() != null){
                queue.add(parent.getRight());
            }
        }
    }
}
