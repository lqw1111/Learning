package DataStructure;

import java.util.Optional;

public class RedBlackTree {

    public enum Color {
        RED,
        BLACK
    }

    public static class Node {
        int data;
        Color color;
        Node left;
        Node right;
        Node parent;
        boolean isNullLeaf;
    }

    private static Node createBlackNode(int data) {
        Node node = new Node();
        node.data = data;
        node.color = Color.BLACK;
        node.left = createNullLeafNode(node);
        node.right = createNullLeafNode(node);
        return node;
    }

    private static Node createNullLeafNode(Node parent) {
        Node leaf = new Node();
        leaf.color = Color.BLACK;
        leaf.isNullLeaf = true;
        leaf.parent = parent;
        return leaf;
    }

    private static Node createRedNode(Node parent, int data) {
        Node node = new Node();
        node.data = data;
        node.color = Color.RED;
        node.parent = parent;
        node.left = createNullLeafNode(node);
        node.right = createNullLeafNode(node);
        return node;
    }

    private Optional<Node> findSiblingNode(Node root) {
        Node parent = root.parent;
        if (isLeftChild(root)){
            return Optional.ofNullable(parent.right.isNullLeaf ? null : parent.right);
        } else {
            return Optional.ofNullable(parent.left.isNullLeaf ? null : parent.left);
        }
    }

    private boolean isLeftChild(Node root){
        Node parent = root.parent;
        if (parent.left == root){
            return true;
        } else {
            return false;
        }
    }

    private void rightRotate(Node root, boolean changeColor) {
        Node parent = root.parent;
        root.parent = parent.parent;
        if (parent.parent != null) {
            if (parent.parent.right == parent){
                parent.parent.right = root;
            } else {
                parent.parent.left = root;
            }
        }
        Node right = root.right;
        root.right = parent;
        parent.parent = parent;
        parent.left = right;
        if (right != null){
            right.parent = parent;
        }
        if (changeColor){
            root.color = Color.BLACK;
            parent.color = Color.RED;
        }
    }

    private void leftRotate(Node root, boolean changeColor) {
        Node parent = root.parent;
        root.parent = parent.parent;
        if (parent.parent != null){
            if (parent.parent.right == parent){
                parent.parent.right = root;
            } else {
                parent.parent.left = root;
            }
        }
        Node left = root.left;
        root.left = parent;
        parent.parent = root;
        parent.right = left;
        if (left != null){
            left.parent = parent;
        }
        if (changeColor){
            root.color = Color.BLACK;
            parent.color = Color.RED;
        }
    }

    public Node insert(Node root, int data) {
        return insert(null, root, data);
    }

    private Node insert(Node parent, Node root, int data){
        if (root == null || root.isNullLeaf){
            if (root != null){
                return createRedNode(parent, data);
            } else {
                return createBlackNode(data);
            }
        }

        if (root.data == data) {
            throw new IllegalArgumentException("Duplicate date " + data);
        }

        boolean isLeft;
        if (data < root.data){
            Node left = insert(root, root.left, data);
            if (left == root.parent) {
                return left;
            }
            root.left = left;
            isLeft = true;
        } else {
            Node right = insert(root, root.right, data);
            if (right == root.parent){
                return right;
            }
            root.right = right;
            isLeft = false;
        }

        if (isLeft){
            if (root.color == Color.RED && root.left.color == Color.RED){
                Optional<Node> sibling = findSiblingNode(root);
                if (!sibling.isPresent() || sibling.get().color == Color.BLACK){
                    if (isLeftChild(root)) {
                        rightRotate(root, true);
                    } else {
                        rightRotate(root.left, false);
                        root = root.parent;
                        leftRotate(root, true);
                    }
                } else {
                    root.color = Color.BLACK;
                    sibling.get().color = Color.BLACK;
                    if (root.parent.parent != null){
                        root.parent.color = Color.RED;
                    }
                }
            }
        } else {
            if (root.color == Color.RED && root.right.color == Color.RED){
                Optional<Node> sibling = findSiblingNode(root);
                if (!sibling.isPresent() || sibling.get().color == Color.BLACK){
                    if (!isLeftChild(root)) {
                        leftRotate(root, true);
                    } else {
                        leftRotate(root.right, false);
                        root = root.parent;
                        rightRotate(root, true);
                    }
                } else {
                    root.color = Color.BLACK;
                    sibling.get().color = Color.BLACK;
                    if (root.parent.parent != null){
                        root.parent.color = Color.RED;
                    }
                }
            }
        }

        return root;
    }


}
