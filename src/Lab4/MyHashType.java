package Lab4;

import dataStructures.HashTable;

public class MyHashType<K, V> extends HashTable {

    public MyHashType(int theDivisor) {
        super(theDivisor);
    }

    public V UpdateElement(K key, V value) {
        V ret = (V) get(key);
        // if (ret != null) {
        put(key, value);
        // }
        return ret;
    }

    public V UpdateKey(K oldKet, K newKey) {
        V elem = (V) get(oldKet);
        Delete(oldKet);
        put(newKey, elem);
        return elem;
    }

    public void Delete(K key) {
        int num = search(key);
        if (table[num] != null) {
            table[num] = null;
            size--;
        }
    }

    public void Put(K key, V value) {
        put(key, value);
    }

    public V Get(K key) {
        return (V) get(key);
    }

}
