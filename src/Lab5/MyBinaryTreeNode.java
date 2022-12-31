package Lab5;

import Lab3.MyStackT;
import dataStructures.BinaryTreeNode;

public class MyBinaryTreeNode extends BinaryTreeNode {

    public MyBinaryTreeNode() {
    }

    public MyBinaryTreeNode(Object theElement) {
        super(theElement);
    }

    public MyBinaryTreeNode(Object theElement, BinaryTreeNode theleftChild, BinaryTreeNode therightChild) {
        super(theElement, theleftChild, therightChild);
    }

    public String Infix() {
        String l = getLeftChild() == null ? "" : ((MyBinaryTreeNode) getLeftChild()).Infix();
        String r = getRightChild() == null ? "" : ((MyBinaryTreeNode) getRightChild()).Infix();
        if (((String) getElement()).charAt(0) == '*' || ((String) getElement()).charAt(0) == '/') {
            if (l.length() > 2) {
                l = "(" + l + ")";
            }
            if (r.length() > 2) {
                r = "(" + r + ")";
            }
        }
        String str = l + "" + getElement() + "" + r;
        return str;
    }

    public String Prefix() {
        String l = getLeftChild() == null ? "" : ((MyBinaryTreeNode) getLeftChild()).Prefix();
        String r = getRightChild() == null ? "" : ((MyBinaryTreeNode) getRightChild()).Prefix();
        String str = getElement() + "" + l + "" + r;
        return str;
    }

    public String Postfix() {
        String l = getLeftChild() == null ? "" : ((MyBinaryTreeNode) getLeftChild()).Postfix();
        String r = getRightChild() == null ? "" : ((MyBinaryTreeNode) getRightChild()).Postfix();
        String str = l + "" + r + "" + getElement();
        return str;
    }

    public boolean Single() {
        return (getRightChild() == null && getLeftChild() == null);
    }

}
