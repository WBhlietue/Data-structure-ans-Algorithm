package Lab4;

import dataStructures.HashTable;

public class MyHash extends HashTable {

    public MyHash(int theDivisor) {
        super(theDivisor);
    }

    public Object UpdateElement(Object key, Object value) {
        Object ret = get(key);
        if (ret != null) {
            update(key, value);
        }
        return ret;
    }

    public Object UpdateKey(Object oldKet, Object newKey){
        Object elem = get(oldKet);
        Delete(oldKet);
        update(newKey, elem);
        return elem;
    }

    public void Delete(Object key) {
        int num = search(key);
        if (table[num] != null) {
            table[num] = null;
            size--;
        }
    }


}
