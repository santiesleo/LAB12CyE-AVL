package model;

import java.util.*;

public class AVL<K extends Comparable<K>, T> implements IAVL<K, T> {

    private Node<K, T> root;

    public Node<K, T> getRoot() {
        return root;
    }

    @Override
    public void insert(K key, T value) {
        root = insert(root, key, value);
    }

    private Node<K, T> insert(Node<K, T> node, K key, T value) {
        if (node == null) {
            node = new Node<K, T>(key, value);
            return node;
        }
        if (key.compareTo(node.getKey()) < 0) {
            node.setLeft(insert(node.getLeft(), key, value));
        } else if (key.compareTo(node.getKey()) > 0) {
            node.setRight(insert(node.getRight(), key, value));
        } else {
            node.setData(value);
            return node;
        }
        node.setHeight(1 + Math.max(height(node.getLeft()), height(node.getRight())));
        int balance = getBalance(node);
        if (balance > 1 && key.compareTo(node.getLeft().getKey()) < 0) {
            return rightRotate(node);
        }
        if (balance < -1 && key.compareTo(node.getRight().getKey()) > 0) {
            return leftRotate(node);
        }
        if (balance > 1 && key.compareTo(node.getLeft().getKey()) > 0) {
            node.setLeft(leftRotate(node.getLeft()));
            return rightRotate(node);
        }
        if (balance < -1 && key.compareTo(node.getRight().getKey()) < 0) {
            node.setRight(rightRotate(node.getRight()));
            return leftRotate(node);
        }
        return node;
    }

    private Node<K, T> minValueNode(Node<K, T> node) {
        Node<K, T> current = node;
        while (current.getLeft() != null) {
            current = current.getLeft();
        }
        return current;
    }

    private int height(Node<K, T> node) {
        if (node == null) {
            return 0;
        }
        return node.getHeight();
    }

    private int getBalance(Node<K, T> node) {
        if (node == null) {
            return 0;
        }
        return height(node.getLeft()) - height(node.getRight());
    }

    private Node<K, T> rightRotate(Node<K, T> node) {
        Node<K, T> leftChild = node.getLeft();
        Node<K, T> rightOfLeftChild = leftChild.getRight();
        leftChild.setRight(node);
        node.setLeft(rightOfLeftChild);
        node.setHeight(1 + Math.max(height(node.getLeft()), height(node.getRight())));
        leftChild.setHeight(1 + Math.max(height(leftChild.getLeft()), height(leftChild.getRight())));
        return leftChild;
    }

    private Node<K, T> leftRotate(Node<K, T> node) {
        Node<K, T> rightChild = node.getRight();
        Node<K, T> leftOfRightChild = rightChild.getLeft();
        rightChild.setLeft(node);
        node.setRight(leftOfRightChild);
        node.setHeight(1 + Math.max(height(node.getLeft()), height(node.getRight())));
        rightChild.setHeight(1 + Math.max(height(rightChild.getLeft()), height(rightChild.getRight())));
        return rightChild;
    }

    public void setRoot(Node<K, T> root) {
        this.root = root;
    }

    public T getRootData() {
        if (root == null) {
            return null;
        }
        return this.root.getData();
    }

    @Override
    public String inOrder() {
        StringBuilder sb = new StringBuilder();
        inOrder(root, sb);
        return sb.toString();
    }

    private void inOrder(Node<K, T> node, StringBuilder sb) {
        if (node != null) {
            inOrder(node.getLeft(), sb);
            sb.append(node.getKey().toString()).append("(BF=" + getBalance(node) + ") ");
            if (node == findMaxNode(root)) {
                sb.deleteCharAt(sb.length() - 1);
            }
            inOrder(node.getRight(), sb);
        }
    }


    @Override
    public String preOrder() {
        StringBuilder sb = new StringBuilder();
        preOrder(root, sb);
        return sb.toString();
    }

    private void preOrder(Node<K, T> node, StringBuilder sb) {
        if (node != null) {
            sb.append(node.getKey().toString()).append("(BF=" + getBalance(node) + ") ");
            preOrder(node.getLeft(), sb);
            if (node == findMaxNode(root)) {
                sb.deleteCharAt(sb.length() - 1);
            }
            preOrder(node.getRight(), sb);
        }
    }

    private Node<K, T> findMaxNode(Node<K, T> node) {
        if (node.getRight() == null) {
            return node;
        }
        return findMaxNode(node.getRight());
    }

}