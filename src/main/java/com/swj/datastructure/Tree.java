package com.swj.datastructure;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * 功能说明:
 *
 * @author wink~
 */
public class Tree {
    private static void init(List<Test> testList) {
        testList.add(new Test("004001001", "第四章节第一章节第一章节"));
        testList.add(new Test("003", "第三章节"));
        testList.add(new Test("001", "第一章节"));
        testList.add(new Test("004", "第四章节"));
        testList.add(new Test("003002", "第三章节第二章节"));
        testList.add(new Test("003002001", "第三章节第二章节第二个节点"));
        testList.add(new Test("001001", "第一章节第一个节点"));
        testList.add(new Test("003003", "第三章节第三节点"));
        testList.add(new Test("004001", "第四章节第一章节"));
        testList.add(new Test("003002001", "第三章节第二章节第一个节点"));
        testList.add(new Test("001002", "第一章节第二个节点"));
        testList.add(new Test("003001", "第三章节第一个节点"));
        testList.add(new Test("001003", "第一章节第三个节点"));
        testList.add(new Test("004001001001", "第四章节第一章节第一章节第一节点"));
        testList.add(new Test("005", "第五章节"));
        testList.add(new Test("002", "第二章节"));
    }
    private static boolean sb;

    public static void main(String[] args) {
        // init
        List<Test> testList = new ArrayList<>();
        init(testList);
        testList = testList.stream()
            .sorted(Comparator.comparing(Test::getLevelCode).reversed())
            .collect(Collectors.toList());
        testList.forEach((x) -> System.out.println(String.format("code: %s, name: %s", x.getLevelCode(), x.getName())));

/*
        TreeNode root = new TreeNode(null, new ArrayList<>());
*/
        LinkedList<Test> children = new LinkedList<>();
        String parentId;
        /*for (int i = 0; i < testList.size(); i++) {
            Test test = testList.get(i);
            TreeNode node = new TreeNode(test);
            if (test.getLevelCode().length() == 3) {
                node.setChildren(children);
                children = new LinkedList<>();
                root.children.addFirst(node);
            } else if (parentId.equals(test.getLevelCode())) {
                node.setChildren(children);
                children = new LinkedList<>();
            }

            parentId = test.getLevelCode().substring(0, test.getLevelCode().length() - 3);
        }*/
        Stack stack = new Stack();
        /*for (int i = 0; i < testList.size(); i++) {
            Test test = testList.get(i);
            TreeNode node = new TreeNode(test);
        }*/
        System.out.println(sb);
    }
}

class TreeNode<T> {
    T node;

    LinkedList<TreeNode> children;

    public TreeNode(T node, LinkedList<TreeNode> children) {
        this.node = node;
        this.children = children;
    }

    public TreeNode(T node) {
        this.node = node;
    }

    public T getNode() {
        return node;
    }

    public void setNode(T node) {
        this.node = node;
    }

    public LinkedList<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(LinkedList<TreeNode> children) {
        this.children = children;
    }
}


class Test {

    private String levelCode;

    private String name;

    public Test() {
    }

    public Test(String levelCode, String name) {
        this.levelCode = levelCode;
        this.name = name;
    }

    public String getLevelCode() {
        return levelCode;
    }

    public void setLevelCode(String levelCode) {
        this.levelCode = levelCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
