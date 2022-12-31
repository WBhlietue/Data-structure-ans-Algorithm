import Lab5.MyBinaryTreeNode;
import Lab5.MyLinkedBinaryTree;

public class Lab5Main {
    public static void main(String[] args) {
        MyLinkedBinaryTree t = new MyLinkedBinaryTree("-",
                new MyBinaryTreeNode("+", new MyBinaryTreeNode("a"),
                        new MyBinaryTreeNode("*", new MyBinaryTreeNode("b"),
                                new MyBinaryTreeNode("+", new MyBinaryTreeNode("c"), new MyBinaryTreeNode("d")))),
                new MyBinaryTreeNode("e"));

        System.out.println("tree: \n" + t);
        System.out.println("infix = " + t.Infix());
        System.out.println("postfix = " + t.Postfix());
        System.out.println("prefix = " + t.Prefix());
        System.out.println("from postfix:\n" + MyLinkedBinaryTree.FromPostfix(t.Postfix()));
        System.out.println("from prefix:\n" + MyLinkedBinaryTree.FromPrefix(t.Prefix()));
        System.out.println("from infix:\n" + MyLinkedBinaryTree.FromInfix(t.Infix()));

        System.out.println("infix = " + t.Infix());
        System.out.println(t.Calculate() + "");
    }
}
