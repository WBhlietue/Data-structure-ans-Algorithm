package Lab3;

import java.util.Random;

import Lab2.MyChain;
import dataStructures.ArrayStack;
import utilities.*;

public class MyStack extends ArrayStack {
    public MyStack() {
        super(1);
    }

    public MyStack(Object[] arr) {
        super(1);
        AddRange(arr);
    }

    public Object[] toArray() {
        Object[] t = (new Object[0]);
        try {
            while (true) {
                Object el = pop();
                t = ChangeArrayLength.changeLength1D(t, t.length + 1);
                t[t.length - 1] = el;
            }
        } catch (Exception e) {

        }
        AddRange(Reverse(t));
        return t;
    }

    Object[] Reverse(Object[] arrs) {
        Object[] objs = new Object[arrs.length];
        for (int i = 0; i < objs.length; i++) {
            objs[i] = arrs[arrs.length - 1 - i];
        }
        return objs;
    }

    public MyStack AddRange(Object[] arr) {
        for (Object item : arr) {
            push(item);
        }
        return this;
    }

    public boolean exists(Object ele) {
        Object[] arr = toArray();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == ele) {
                return true;
            }
        }
        return false;
    }

    public MyStack unique() {
        MyStack stk = new MyStack();
        Object[] objs = Reverse(toArray());
        for (int i = 0; i < objs.length; i++) {
            if (!stk.exists(objs[i])) {
                stk.push(objs[i]);
            }
        }
        return stk;
    }

    public MyStack rand() {
        Random random = new Random();
        MyChain chain = new MyChain();
        chain.AddRange(toArray());
        MyStack stack = new MyStack();
        while (chain.size() > 0) {
            int a = Math.abs(random.nextInt()) % (chain.size());
            stack.push(chain.get(a));
            chain.remove(a);
        }
        return stack;
    }

    @Override
    public String toString() {
        Object[] obj = toArray();
        String str = "";
        for(int i = 0; i < obj.length; i++){
            str += obj[i] + " ";
        }
        return str;
    }

}
