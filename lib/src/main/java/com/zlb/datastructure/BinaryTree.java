package com.zlb.datastructure;

/**
 * Created by zhanglibin on 2014/7/25.
 */
public class BinaryTree<T> {
    private T data;
    private BinaryTree left;
    private BinaryTree right;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public BinaryTree getLeft() {
        return left;
    }

    public void setLeft(BinaryTree left) {
        this.left = left;
    }

    public BinaryTree getRight() {
        return right;
    }

    public void setRight(BinaryTree right) {
        this.right = right;
    }

    public static void main(String[] args) {

    }

    @Override
    public String toString() {
        return "data = " + data + " , left = "
                + (left == null ? "null" : left.getData())
                + " , right = "
                + (right == null ? "null" : right.getData())
                + " , "
                + super.toString();
    }
}
