package com.shouguouo.algorithms.datastructure;

/**
 * @author swj
 * @date 2018/2/10
 */
public class AVLTree {

    public void insert(AVLNode root, int data){
        if (data < root.data){
            if (root.left != null){
                insert(root.left, data);
            }else {
                root.left = new AVLNode(data);
                root.left.parent = root;
            }
        }else {
            if (root.right != null){
                insert(root.right, data);
            }else {
                root.right = new AVLNode(data);
                root.right.parent = root;
            }
        }
        /* 从插入的过程回溯回来的时候，计算平衡因子 */
        root.balance = calcBalance(root);

	    /* 左子树高，应该右旋 */
        if (root.balance >= 2) {
	    /* 右孙高，先左旋 */
            if (root.left.balance == -1) {
                left_rotate(root.left);
            }
            right_rotate(root);
        }

        if (root.balance <= -2)
        {
            if (root.right.balance == 1) {
                right_rotate(root.right);
            }
            left_rotate(root);
        }

        root.balance = calcBalance(root);
        root.depth = calcDepth(root);

    }

    private int calcBalance(AVLNode p) {
        int left_depth;
        int right_depth;

        if (p.left != null) {
            left_depth = p.left.depth;
        } else{
            left_depth = 0;
        }
        if (p.right != null) {
            right_depth = p.right.depth;
        } else {
            right_depth = 0;
        }
        return left_depth - right_depth;
    }

    private int calcDepth(AVLNode p) {
        int depth = 0;
        if (p.left != null) {
            depth = p.left.depth;
        }
        if (p.right != null && depth < p.right.depth) {
            depth = p.right.depth;
        }
        depth++;
        return depth;
    }

    private void right_rotate(AVLNode p) {
	    /* 一次旋转涉及到的结点包括祖父，父亲，右儿子 */
        AVLNode pParent = p.parent, pRightSon = p.left;
        AVLNode pLeftGrandSon = pRightSon.right;

	    /* 左子为父 */
        pRightSon.parent = pParent;
        if (pParent != null) {
            if (p == pParent.left) {
                pParent.left = pRightSon;
            }
            else if (p == pParent.right) {
                pParent.right = pRightSon;
            }
        }

        pRightSon.right = p;
        p.parent = pRightSon;

	    /* 右孙变左孙 */
        p.left = pLeftGrandSon;
        if (pLeftGrandSon != null) {
            pLeftGrandSon.parent = p;
        }
	    /* 重新计算平衡因子 */
        p.depth = calcDepth(p);
        p.balance = calcBalance(p);

        pRightSon.depth = calcDepth(pRightSon);
        pRightSon.balance = calcBalance(pRightSon);
    }

    private void left_rotate(AVLNode p){

    }
}

class AVLNode{
    public int data;
    public int depth;
    public int balance;
    public AVLNode parent;
    public AVLNode left;
    public AVLNode right;

    public AVLNode(int data) {
        this.data = data;
        depth = 1;
        balance = 0;
        left = null;
        right = null;
    }
}