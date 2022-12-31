import Lab1.*;
import Lab2.*;
import TestStack.TestStack;

public class App {
    public static void main(String[] args) {
        TestStack<Integer> stack = new TestStack<>();
        for (int i = 0; i < 10; i++) {
            stack.push(i);
        }
        System.out.println(stack);
        stack.pop();
        stack.push(45);
        System.out.println(stack);

        System.out.println(stack.empty());
        System.out.println(stack);

        stack.push(2764);
        stack.push(2563);
        stack.push(75);
        stack.push(7353);
        stack.push(532);
        stack.push(23);
        stack.push(5);
        System.out.println(stack);
        System.out.println(stack.peek());
        Object o = (Object)null;
    }
}
