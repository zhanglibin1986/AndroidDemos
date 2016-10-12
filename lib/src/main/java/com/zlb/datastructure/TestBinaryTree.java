package com.zlb.datastructure;

import android.zlb.java.Binary;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by zhanglibin on 2014/7/25.
 */
public class TestBinaryTree {

    public static void main(String[] args) {
        BinaryTreeFactory factory = BinaryTreeFactory.getInstance();
        BinaryTree binaryTree = factory.createBinaryTree(getData());

        TestBinaryTree test = new TestBinaryTree();
        System.out.println("preOrderTraverse:");
        test.preOrderTraverse(binaryTree);
        System.out.println("inOrderTraverse:");
        test.inOrderTraverse(binaryTree);
//        test.postOrderTraverse(binaryTree);
//        BinaryTree tree = new BinaryTree();
//        System.out.println("data:" + tree);
//        changeTree(tree);
//        System.out.println("data:" + tree);
    }

    private static Queue getData() {
        LinkedList<String> datas = new LinkedList<String>();
        datas.offer("a");
        datas.offer("b");
        datas.offer("c");
        datas.offer("d");
        datas.offer("e");
        datas.offer("f");
        datas.offer("g");
        datas.offer("h");
        return datas;
    }

    private static void changeTree(BinaryTree tree) {
        tree.setData("changed");
        System.out.println(tree);
        tree = new BinaryTree();
        tree.setData("aa");
        System.out.println(tree);
    }

    /**
     * 先序遍历二叉树
     * @param binaryTree
     */
    private boolean preOrderTraverse(BinaryTree binaryTree) {
        if(binaryTree != null) {
            System.out.println(binaryTree.toString());
            if(visit(binaryTree) != null) {
                if(preOrderTraverse(binaryTree.getLeft())) {
                    if(preOrderTraverse(binaryTree.getRight())) {
                        return true;
                    }
                };
            }
            return false;
        } else {
            return true;
        }
    }

    /**
     * 中序
     * @param binaryTree
     */
    private boolean inOrderTraverse(BinaryTree binaryTree) {
        if(binaryTree != null) {
            if(inOrderTraverse(binaryTree.getLeft())) {
                System.out.println(binaryTree.toString());
                if(visit(binaryTree) != null) {
                    if(inOrderTraverse(binaryTree.getRight())) {
                        return true;
                    };
                }
            }
            return false;
        } else {
            return true;
        }
    }

    /**
     * 后序
     * @param binaryTree
     */
    private boolean postOrderTraverse(BinaryTree binaryTree) {
        if(binaryTree.getData() != null) {
            if(postOrderTraverse(binaryTree.getLeft())) {
                if(postOrderTraverse(binaryTree.getRight())) {
                    System.out.println(binaryTree.toString());
                    if(visit(binaryTree) != null) {
                        return true;
                    }
                }
            }
            return false;
        } else {
            return true;
        }
    }

    public static String visit(BinaryTree tree) {

        System.out.println("--------" + tree.getData());
        return tree.getData().toString();
    }
}
