package com.zlb.datastructure;

import android.zlb.java.Binary;

import java.util.List;
import java.util.Queue;

/**
 * Created by zhanglibin on 2014/7/25.
 */
public class BinaryTreeFactory<T> {
    private static BinaryTreeFactory mBinaryTreeFactory;

    private BinaryTreeFactory() {

    }

    public static BinaryTreeFactory getInstance() {
        if(mBinaryTreeFactory == null) {
            mBinaryTreeFactory = new BinaryTreeFactory();
        }
        return mBinaryTreeFactory;
    }

    public BinaryTree createBinaryTree(Queue<T> datas) {
        T data = datas.poll();
        BinaryTree root;
        if(data == null) {
            return null;
        } else {
            root = new BinaryTree();
            root.setData(data);
            root.setLeft(createBinaryTree(datas));
            root.setRight(createBinaryTree(datas));
        }
        return root;
    }

    public BinaryTree createBinaryTree2(List<T> datas) {
        BinaryTree binaryTree = null;
        for(int i = 0; i < datas.size(); i++) {
            addToBinaryTree(binaryTree, datas.get(i));
        }
        return binaryTree;
    }

    private void addToBinaryTree(BinaryTree binaryTree, T data) {

    }

    private boolean isEmpty(String string) {
        return string == null || string.trim().equals("");
    }
}

/**
 在java5中新增加了java.util.Queue接口，用以支持队列的常见操作。该接口扩展了java.util.Collection接口。
 Queue使用时要尽量避免Collection的add()和remove()方法，而是要使用offer()来加入元素，使用poll()来获取并移出元素。它们的优
 点是通过返回值可以判断成功与否，add()和remove()方法在失败的时候会抛出异常。 如果要使用前端而不移出该元素，使用
 element()或者peek()方法。
 值得注意的是LinkedList类实现了Queue接口，因此我们可以把LinkedList当成Queue来用.
 **/