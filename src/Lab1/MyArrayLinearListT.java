package Lab1;

import java.io.Serializable;

import dataStructures.ArrayLinearList;

public class MyArrayLinearListT<T> extends ArrayLinearList implements Serializable {

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

    public T[] toArray() {
        T[] arr = (T[]) (new Object[size]);
        for (int i = 0; i < size; i++) {
            arr[i] = Get(i);
        }
        return arr;
    }

}