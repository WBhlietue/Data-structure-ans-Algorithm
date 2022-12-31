package TestStack;

import dataStructures.Stack;

@SuppressWarnings("unchecked")
public class TestStack<T> implements Stack {
    Chain last;
    int size;

    public TestStack(T item) {
        size = 0;
        last = new Chain(item);
    }

    public TestStack() {
        size = 0;
    }

    public TestStack(TestStack<T> clone) {
        size = 0;
        for (int i = 0; i < clone.size; i++) {
            this.push(clone.Get(i));
        }
    }

    public T Get(int index) {
        if (index >= size) {
            return null;
        } else {
            int num = size - index - 1;
            Chain c = last;
            for (int i = 0; i < num; i++) {
                c = c.previous;
            }
            return (T) c.item;
        }
    }

    public boolean isEmpty() {
        return (size > 0 ? false : true);
    }

    @Override
    public String toString() {
        String s = "[";
        for (int i = 0; i < size; i++) {
            s += (Get(i) + " ");
        }
        if (s.charAt(s.length()-1) == ' ') {
            s = s.substring(0, s.length() - 1);
        }
        s += "]";
        return s;
    }

    @Override
    public boolean empty() {
        if (size > 0) {
            last = null;
            size = 0;
            System.gc();
            return true;
        }
        return false;
    }

    @Override
    public T peek() {
        return (T) last.item;
    }

    @Override
    public void push(Object theObject) {
        if (size == 0) {
            last = new Chain((T) theObject);
        } else {
            last = new Chain((T) theObject, last);
        }
        size++;

    }

    @Override
    public T pop() {
        if (size > 0) {
            Chain rem = last;
            last = last.previous;
            size--;
            return (T) rem.item;
        } else {
            return null;
        }
    }
}
