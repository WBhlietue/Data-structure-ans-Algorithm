package Lab1;

import dataStructures.ArrayLinearList;

public class MyArrayLinearList extends ArrayLinearList {

    private int max, min;

    public void Add(int num) {
        Add(num, size);
    }

    public void Insert(int num, int index) {
        Add(num, index);
    }

    void Add(int num, int index) {
        add(index, num);
        if (size == 1) {
            max = num;
            min = num;
        } else {
            if (max < num) {
                max = num;
            }
            if (min > num) {
                min = num;
            }
        }
    }

    public void ReCalc(){
        max = (int)get(0);
        min = (int)get(0);
        
        for(int i = 1; i < size; i++){
            if((int)get(i) > max){
                max = (int)get(i);
            }
            if((int)get(i) < min){
                min = (int)get(i);
            }
        }
    }

    public int Get(int index) {
        try {

            return (int) get(index);
        } catch (Exception e) {
            return -1;
        }
    }

    public int Sum() {
        int sum = 0;
        for (int i = 0; i < size; i++) {
            sum += (int) get(i);
        }
        return sum;
    }

    public int Max() {
        return max;
    }

    public int Min() {
        return min;
    }

    public float Avarage() {
        return Sum() / (float) size;
        
    }

    public MyArrayLinearList RemoveOdd() {
        MyArrayLinearList list = new MyArrayLinearList();
        for (int i = 0; i < size; i++) {
            if ((int) get(i) % 2 == 1) {
                list.add(list.size, get(i));
                remove(i);
                i--;
            }
        }
        ReCalc();
        list.ReCalc();
        return list;
    }

    public MyArrayLinearList Sort() {
        MyArrayLinearList list = new MyArrayLinearList();
        for (int i = 0; i < size; i++) {
            int j;
            for (j = 0; j < list.size; j++) {
                if (Get(i) < list.Get(j)) {
                    break;
                }
            }
            list.Insert((int) get(i), j);

        }
        return list;
    }

}