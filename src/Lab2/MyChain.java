package Lab2;

import java.io.Serializable;

import dataStructures.*;

public class MyChain<T> extends Chain implements Serializable {

    public MyChain(MyChain chain) {
        for (int i = 0; i < chain.size; i++) {
            Add((T)chain.get(i));
        }
    }

    public MyChain() {

    }

    public void Add(T num) {
        Add(num, size);
    }

    public void Insert(T num, int index) {
        Add(num, index);
    }

    void Add(T num, int index) {
        add(index, num);

    }

    public T Get(int index) {
        try {

            return (T) get(index);
        } catch (Exception e) {
            return null;
        }
    }

    public T[] ToArray() {
        T[] obj = (T[]) new Object[size];
        for (int i = 0; i < size; i++) {
            obj[i] = (T) get(i);
        }
        return obj;
    }

    public void AddRange(T[] obj) {
        for (T item : obj) {
            Add(item);
        }
    }

    public MyChain Union(MyChain chain) {
        MyChain ch = new MyChain(this);
        for (int i = 0; i < chain.size; i++) {
            if (this.indexOf(chain.get(i)) == -1) {
                ch.Add(chain.get(i));
            }
        }
        return ch;
    }

    public MyChain Intersection(MyChain chain) {
        MyChain t = new MyChain(chain);
        MyChain c = new MyChain();
        for (int i = 0; i < this.size; i++) {
            int j = t.indexOf(get(i));
            if (j >= 0) {
                c.Add(get(i));
                t.remove(j);
            }
        }

        return c;
    }

}
