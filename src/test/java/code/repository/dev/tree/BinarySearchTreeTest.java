package code.repository.dev.tree;

import org.junit.Test;

/**
 * @author seok
 */
public class BinarySearchTreeTest {

    @Test
    public void testBinarySearchTree() {
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        TreeNode root = new TreeNode(25);
        binarySearchTree.setRoot(root);
        binarySearchTree.insertNode(10);
        binarySearchTree.insertNode(15);
        binarySearchTree.insertNode(18);
        binarySearchTree.insertNode(8);
        binarySearchTree.insertNode(1);
        binarySearchTree.insertNode(2);
        binarySearchTree.insertNode(30);
        binarySearchTree.insertNode(44);
        binarySearchTree.insertNode(33);

        binarySearchTree.deleteNode(10);

        TreeNode searchNode = binarySearchTree.searchNode(root, 18);
        System.out.println("preOrder");
        binarySearchTree.preOrder(root);
        System.out.println("inOrder");
        binarySearchTree.inOrder(root);
        System.out.println("postOrder");
        binarySearchTree.postOrder(root);
        System.out.println("levelOrder");
        binarySearchTree.levelOrder(root);

        System.out.println("search Node : " + searchNode);
    }
}
