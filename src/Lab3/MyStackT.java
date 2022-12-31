package Lab3;

import java.util.Random;

import Lab2.MyChain;
import dataStructures.ArrayStack;
import utilities.*;

public class MyStackT<T> extends ArrayStack {
    public MyStackT() {
        super(1);
    }

    public MyStackT(T[] arr) {
        super(1);
        AddRange(arr);
    }

    public T[] toArray() {
        Object[] t = (new Object[0]);
        try {
            while (true) {
                Object el = pop();
                t = ChangeArrayLength.changeLength1D(t, t.length + 1);
                t[t.length - 1] = el;
            }
        } catch (Exception e) {

        }
        AddRange(Reverse((T[]) t));
        return (T[]) t;
    }

    public T[] Reverse(T[] arrs) {
        Object[] objs = new Object[arrs.length];
        for (int i = 0; i < objs.length; i++) {
            objs[i] = arrs[arrs.length - 1 - i];
        }
        return (T[]) objs;
    }

    public MyStackT AddRange(T[] arr) {
        for (T item : arr) {
            push(item);
        }
        return this;
    }

    public boolean exists(T ele) {
        T[] arr = toArray();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == ele) {
                return true;
            }
        }
        return false;
    }

    public MyStackT unique() {
        MyStackT<T> stk = new MyStackT<>();
        T[] objs = Reverse(toArray());
        for (int i = 0; i < objs.length; i++) {
            if (!stk.exists(objs[i])) {
                stk.push(objs[i]);
            }
        }
        return stk;
    }

    public MyStackT rand() {
        Random random = new Random();
        MyChain chain = new MyChain();
        chain.AddRange(toArray());
        MyStackT<T> stack = new MyStackT<>();
        while (chain.size() > 0) {
            int a = Math.abs(random.nextInt()) % (chain.size());
            stack.push(chain.get(a));
            chain.remove(a);
        }
        return stack;
    }

    @Override
    public String toString() {
        T[] obj = toArray();
        String str = "";
        for (int i = 0; i < obj.length; i++) {
            str += obj[i] + " ";
        }
        return str;
    }

    public String GetString() {
        T[] obj = Reverse(toArray());
        String str = "";
        for (int i = 0; i < obj.length; i++) {
            str += obj[i] + "<br>";
        }
        return str;
    }
}
