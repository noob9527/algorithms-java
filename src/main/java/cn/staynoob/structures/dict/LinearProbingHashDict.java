package cn.staynoob.structures.dict;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Created by xy on 16-5-29.
 */
public class LinearProbingHashDict<K,V> implements Dictionary<K,V>{

    private int size;
    private static final int DEFAULT_CAPACITY=16;
    private int capacity;
    private K[] keys;
    private V[] values;

    public LinearProbingHashDict() {
        this(DEFAULT_CAPACITY);
    }

    public LinearProbingHashDict(int capacity) {
        this.capacity = capacity <= 0 ? DEFAULT_CAPACITY: capacity;
        keys=(K[])new Object[capacity];
        values=(V[])new Object[capacity];
    }

    private void resize(int newCapacity){
        LinearProbingHashDict<K,V> dict=new LinearProbingHashDict<>(newCapacity);
        for (int i = 0; i < this.capacity; i++) {
            if(keys[i]!=null) dict.put(keys[i],values[i]);
        }
        keys=dict.keys;
        values=dict.values;
        capacity=dict.capacity;
    }

    private int hash(K key){
        return (key.hashCode() & 0x7fffffff) % capacity;
    }

    @Override
    public void put(K key, V value) {
        if(key==null)
            throw new NullPointerException();

        int index=hash(key);
        while(keys[index]!=null){
            if(key.equals(keys[index])){
                values[index]=value;
                return;
            }
            index=(index+1)%capacity;
        }
        keys[index]=key;
        values[index]=value;

        if(++size>=capacity>>1) resize(capacity<<1);
    }

    @Override
    public V get(K key) {
        if(key==null)
            throw new NullPointerException();

        int index=hash(key);
        while(keys[index]!=null){
            if(key.equals(keys[index])) return values[index];
            index=(index+1)%capacity;
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void delete(K key) {
        if(!contains(key)) return;
        size--;
        if(size<=capacity<<3) resize(capacity<<1);

        int index=hash(key);
        while (!key.equals(keys[index])) index=(index+1)%capacity;

        int next=(index+1)%capacity;
        while (hash(key)==hash(keys[next])){
            keys[index]=keys[next];
            values[index]=values[next];
            index=next;
            next=(next+1)%capacity;
        }
        keys[index]=null;
        values[index]=null;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public boolean contains(K key) {
        int index=hash(key);
        while(keys[index]!=null){
            if(key.equals(keys[index])) return true;
            index=(index+1)%capacity;
        }
        return false;
    }

    @Override
    public Set<K> keySet() {
        return new AbstractSet<K>() {
            @Override
            public Iterator<K> iterator() {
                return new Iterator<K>() {
                    private int index=0;
                    {
                        while(index<capacity && keys[index]==null) index++;
                    }

                    @Override
                    public boolean hasNext() {
                        return index<capacity;
                    }

                    @Override
                    public K next() {
                        K ele=keys[index++];
                        while(index<capacity && keys[index]==null) index++;
                        return ele;
                    }
                };
            }

            @Override
            public int size() {
                return size;
            }
        };
    }
}
