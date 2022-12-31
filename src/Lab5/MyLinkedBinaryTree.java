package Lab5;

import java.util.Scanner;

import Lab3.MyStackT;
import dataStructures.BinaryTree;
import dataStructures.LinkedBinaryTree;

public class MyLinkedBinaryTree extends LinkedBinaryTree {

    public MyLinkedBinaryTree(Object element, MyBinaryTreeNode left, MyBinaryTreeNode right) {
        SetRoot(new MyBinaryTreeNode());
        GetRoot().setElement(element);
        GetRoot().setLeftChild(left);
        GetRoot().setRightChild(right);
    }

    public void SwapSubTrees() {
        MyBinaryTreeNode rt = (MyBinaryTreeNode) GetRoot();
        MyBinaryTreeNode left = (MyBinaryTreeNode) rt.getLeftChild();
        MyBinaryTreeNode right = (MyBinaryTreeNode) rt.getRightChild();
        rt.setLeftChild(right);
        rt.setRightChild(left);
    }

    public MyBinaryTreeNode getRightChild() {
        return (MyBinaryTreeNode) GetRoot().getRightChild();
    }

    public MyBinaryTreeNode getLeftChild() {
        return (MyBinaryTreeNode) GetRoot().getLeftChild();
    }

    public void setRightChild(MyBinaryTreeNode node) {
        GetRoot().setRightChild(node);
    }

    public void setLeftChild(MyBinaryTreeNode node) {
        GetRoot().setLeftChild(node);
    }

    public String GetString() {
        String s = "";
        Object elem = GetRoot().getElement();
        MyBinaryTreeNode r = (MyBinaryTreeNode) GetRoot().getRightChild();
        MyBinaryTreeNode l = (MyBinaryTreeNode) GetRoot().getLeftChild();
        return "-" + (elem == null ? "null" : elem) + "\n |-" + (l == null ? "null" : GetString(l, s))
                + "\n |-"
                + (r == null ? "null" : GetString(r, s));
    }

    public String GetString(MyBinaryTreeNode node, String s) {

        s += " |";
        Object elem = node.getElement();
        MyBinaryTreeNode r = (MyBinaryTreeNode) node.getRightChild();
        MyBinaryTreeNode l = (MyBinaryTreeNode) node.getLeftChild();
        String elem1 = (l == null ? "" : "\n |" + s + "-" + GetString(l, s));
        String elem2 = (r == null ? "" : "\n |" + s + "-" + GetString(r, s));
        if (elem == null) {
            return "";
        }
        return elem + elem1 + elem2;
    }

    public String Infix() {
        String l = getLeftChild() == null ? "" : ((MyBinaryTreeNode) getLeftChild()).Infix();
        String r = getRightChild() == null ? "" : ((MyBinaryTreeNode) getRightChild()).Infix();
        if (((String) GetRoot().getElement()).charAt(0) == '*' || ((String) GetRoot().getElement()).charAt(0) == '/') {
            if (l.length() > 2) {
                l = "(" + l + ")";
            }
            if (r.length() > 2) {
                r = "(" + r + ")";
            }
        }
        String str = l + "" + GetRoot().getElement() + "" + r;
        return str;
    }

    public String Prefix() {
        String l = getLeftChild() == null ? "" : ((MyBinaryTreeNode) getLeftChild()).Prefix();
        String r = getRightChild() == null ? "" : ((MyBinaryTreeNode) getRightChild()).Prefix();
        String str = GetRoot().getElement() + "" + l + "" + r;
        return str;
    }

    public String Postfix() {
        String l = getLeftChild() == null ? "" : ((MyBinaryTreeNode) getLeftChild()).Postfix();
        String r = getRightChild() == null ? "" : ((MyBinaryTreeNode) getRightChild()).Postfix();
        String str = l + "" + r + "" + GetRoot().getElement();
        return str;
    }

    public static MyLinkedBinaryTree FromInfix(String str) {
        MyBinaryTreeNode node = null;
        MyBinaryTreeNode tmp = null;
        boolean inBrack = false;
        for (int i = 0; i < str.length(); i++) {
            MyBinaryTreeNode noo;
            if (str.charAt(i) == '(') {
                inBrack = true;
                continue;
            } else if (str.charAt(i) == ')') {
                inBrack = false;
                node.setRightChild(tmp);
                tmp = null;
                continue;
            }
            if (inBrack) {
                noo = tmp;
            } else {
                noo = node;
            }
            if (noo == null) {
                noo = new MyBinaryTreeNode();
                noo.setElement(str.charAt(i));
            } else {
                if (isOperator(str.charAt(i))) {
                    if (i != 1 && (str.charAt(i) == '*' || str.charAt(i) == '/')) {
                        if (str.charAt(i + 1) != '(' && str.charAt(i + 1) != ')') {
                            MyBinaryTreeNode n = new MyBinaryTreeNode(str.charAt(i), noo.getRightChild(),
                                    new MyBinaryTreeNode(str.charAt(i + 1)));
                            noo.setRightChild(n);
                            i++;
                        } else {
                            MyBinaryTreeNode n = new MyBinaryTreeNode(str.charAt(i), noo.getRightChild(),
                                    GetSubInfix(str, i));
                            noo.setRightChild(n);
                            while (str.charAt(i) != ')') {
                                i++;
                            }
                        }
                    } else {
                        noo = new MyBinaryTreeNode(str.charAt(i), noo, null);
                    }
                } else {
                    noo.setRightChild(new MyBinaryTreeNode(str.charAt(i)));
                }
            }
            if (inBrack) {
                tmp = noo;
            } else {

                node = noo;
            }
        }
        MyLinkedBinaryTree tree = new MyLinkedBinaryTree(node.getElement(), (MyBinaryTreeNode) node.getLeftChild(),
                (MyBinaryTreeNode) node.getRightChild());
        return tree;
    }

    static MyBinaryTreeNode GetSubInfix(String str, int start) {
        MyBinaryTreeNode node = null;
        for (int i = start + 2; i < str.length(); i++) {
            if (str.charAt(i) == ')') {
                return node;
            }
            if (node == null) {
                node = new MyBinaryTreeNode();
                node.setElement(str.charAt(i));
            } else {
                if (isOperator(str.charAt(i))) {
                    if (i != 1 && (str.charAt(i) == '*' || str.charAt(i) == '/')) {
                        if (str.charAt(i + 1) != '(' && str.charAt(i + 1) != ')') {
                            MyBinaryTreeNode n = new MyBinaryTreeNode(str.charAt(i), node.getRightChild(),
                                    new MyBinaryTreeNode(str.charAt(i + 1)));
                            node.setRightChild(n);
                            i++;
                        } else {

                        }
                    } else {
                        node = new MyBinaryTreeNode(str.charAt(i), node, null);
                    }
                } else {
                    node.setRightChild(new MyBinaryTreeNode(str.charAt(i)));
                }
            }
        }
        return null;
    }

    public static MyLinkedBinaryTree FromPostfix(String str) {
        MyStackT<MyBinaryTreeNode> nodes = new MyStackT<>();
        for (int i = 0; i < str.length(); i++) {
            nodes.push(new MyBinaryTreeNode(str.charAt(i)));
            if (isOperator((char) ((MyBinaryTreeNode) nodes.peek()).getElement())
                    && ((MyBinaryTreeNode) nodes.peek()).Single()) {
                MyBinaryTreeNode top = (MyBinaryTreeNode) nodes.pop();
                MyBinaryTreeNode right = (MyBinaryTreeNode) nodes.pop();
                MyBinaryTreeNode left = (MyBinaryTreeNode) nodes.pop();
                top.setLeftChild(left);
                top.setRightChild(right);
                nodes.push(top);
            }

        }
        MyBinaryTreeNode node = (MyBinaryTreeNode) nodes.peek();
        MyLinkedBinaryTree tree = new MyLinkedBinaryTree(node.getElement(), (MyBinaryTreeNode) node.getLeftChild(),
                (MyBinaryTreeNode) node.getRightChild());
        return tree;
    }

    public static MyLinkedBinaryTree FromPrefix(String str) {
        MyStackT<MyBinaryTreeNode> nodes = new MyStackT<>();
        for (int i = str.length() - 1; i >= 0; i--) {
            nodes.push(new MyBinaryTreeNode(str.charAt(i)));
            if (isOperator((char) ((MyBinaryTreeNode) nodes.peek()).getElement())
                    && ((MyBinaryTreeNode) nodes.peek()).Single()) {
                MyBinaryTreeNode top = (MyBinaryTreeNode) nodes.pop();
                MyBinaryTreeNode left = (MyBinaryTreeNode) nodes.pop();
                MyBinaryTreeNode right = (MyBinaryTreeNode) nodes.pop();
                top.setLeftChild(left);
                top.setRightChild(right);
                nodes.push(top);
            }

        }
        MyBinaryTreeNode node = (MyBinaryTreeNode) nodes.peek();
        MyLinkedBinaryTree tree = new MyLinkedBinaryTree(node.getElement(), (MyBinaryTreeNode) node.getLeftChild(),
                (MyBinaryTreeNode) node.getRightChild());
        return tree;
    }

    static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    public float Calculate() {
        Scanner sc = new Scanner(System.in);
        String postfix = Postfix();
        MyStackT<Float> exps = new MyStackT<>();
        for (int i = 0; i < postfix.length(); i++) {
            if (isOperator(postfix.charAt(i))) {
                float a = (float) exps.pop();
                float b = (float) exps.pop();
                switch (postfix.charAt(i)) {
                    case '+':
                        exps.push(a + b);
                        break;
                    case '-':
                        exps.push(b - a);
                        break;
                    case '*':
                        exps.push(a * b);
                        break;
                    case '/':
                        exps.push(b / a);
                        break;
                }
            } else {
                System.out.print(postfix.charAt(i) + " = ");
                exps.push(sc.nextFloat());
            }
        }
        sc.close();
        return (float) exps.peek();
    }

    @Override
    public String toString() {
        return GetString();
    }
}
