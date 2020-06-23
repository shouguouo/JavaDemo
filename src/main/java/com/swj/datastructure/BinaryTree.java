package com.swj.datastructure;

import java.util.Stack;

/**
 * @author swj
 * @date 2018/2/9
 */
public class BinaryTree {

    /**
     * a为父节点 b为左节点 c为右节点
     * @param args
     */
    public static void main(String[] args) {
        Node a = new Node(Integer.valueOf(1));
        Node b = new Node(Integer.valueOf(2));
        Node c = new Node(Integer.valueOf(3));

        a.left = b;
        a.right = c;

        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(3);bst.insert(2);bst.insert(1);
        bst.insert(5);bst.insert(4);bst.insert(6);
        System.out.println(bst.contains(2));
        bst.midOrderWithoutRecurs();

    }
    /**
     * 二叉查找树：对于树中任意一个节点 都满足 它的左节点上的所有节点的值都比该节点小
     * 它的右节点上的所有节点的值都比该节点大
     * 根据这个定义 二叉查找树的插入过程就是递归的判断待插入数据和树中节点值的大小关系 找到待插入数据的位置
     *
     */

}

class Node<T>{
    public T data;
    public Node left;
    public Node right;
    int state = 0;

    public Node(T data) {
        this.data = data;
    }
}

class BinarySearchTree<T extends Comparable<T>>{

    private Node<T> root;

    /**
     * 插入一个数据
     * @param i
     * @return  插入成功返回true
     */
    public boolean insert(T i){
        if (root == null){
            root = new Node<T>(i);
            return true;
        }
        Node<T> current = root;
        while (true){
            if (i.compareTo(current.data) < 0) {
                if (current.left != null) {
                    current = current.left;
                } else {
                    current.left = new Node(i);
                    break;
                }
            }else {
                if (current.right != null){
                    current = current.right;
                }else {
                    current.right = new Node(i);
                    break;
                }
            }
        }
        return true;
    }

    /**
     * 查找某个数据是否存在
     * @param i
     * @return 存在返回true
     */
    public boolean contains(T i){
        if (root == null){
            return false;
        }
        Node current = root;
        while(true){
            if (i.compareTo((T) current.data) == 0){
                return true;
            }else if (i.compareTo((T) current.data) < 0){
                if (current.left == null){
                    return false;
                }
                current = current.left;
            }else {
                if (current.right  == null){
                    return false;
                }
                current = current.right;
            }
        }
    }

    /**
     * 前序遍历传入的二叉查找树(递归)
     * @param n
     */
    public static void preOrder(Node n){
        if (n == null){
            return;
        }
        System.out.println(n.data);

        if (n.left != null){
            preOrder(n.left);
        }
        if (n.right != null){
            preOrder(n.right);
        }
    }

    /**
     * 中序遍历(非递归)
     */
    public void midOrderWithoutRecurs() {
        if (root == null){
            return;
        }
        Stack<Node<T>> s = new Stack<>();
        Node<T> current;

        s.push(root);

        while (! s.isEmpty()) {
            current = s.peek();
            if (current.state == 0) {
                if (current.left != null){
                    s.push(current.left);
                }
                current.state = 1;
            }
            else if (current.state == 1) {
                System.out.println(current.data);
                current.state = 2;
            }
            else if (current.state == 2) {
                if (current.right != null){
                    s.push(current.right);
                }
                current.state = 3;
            }
            else if (current.state == 3) {
                s.pop();
                current.state = 0;
            }
        }
    }

    public void remove(T i){
        //TODO 删除节点
    }
}