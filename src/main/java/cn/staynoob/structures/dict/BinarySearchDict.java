package cn.staynoob.structures.dict;

import java.util.*;

/**
 * 基于有序数组与二分查找实现字典集
 * 默认 a.compareTo(b)==0 是 a.equals(b) 的充要条件
 * Created by xy on 16-5-16.
 */
public class BinarySearchDict<K extends Comparable<K>,V> implements Dictionary<K,V>{
    private K[] keys;
    private V[] values;
    private int size=0;

    private static final int DEFAULT_INITIAL_CAPACITY=11;
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    public BinarySearchDict() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    public BinarySearchDict(int capacity) {
        keys=(K[])new Comparable[capacity];
        values=(V[])new Object[capacity];
    }

    private void grow(int minCapacity){
        int oldCapacity = keys.length;
        int newCapacity = oldCapacity + (oldCapacity < 64 ? oldCapacity + 2 : oldCapacity >> 1);

        // overflow-conscious code
        if (newCapacity - MAX_ARRAY_SIZE > 0)
            newCapacity = hugeCapacity(minCapacity);
        keys = Arrays.copyOf(keys, newCapacity);
        values = Arrays.copyOf(values, newCapacity);
    }

    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) // overflow
            throw new OutOfMemoryError();
        return (minCapacity > MAX_ARRAY_SIZE) ?
            Integer.MAX_VALUE :
            MAX_ARRAY_SIZE;
    }

    /**
     * O(N)
     * @param key
     * @param value
     */
    @Override
    public void put(K key, V value) {
        if(key==null)
            throw new NullPointerException();

        int index=binarySearch(key);
        if(key.equals(keys[index])) {
            values[index]=value;
            return;
        }

        if(size==keys.length) grow(size+1);
        System.arraycopy(keys,index,keys,index+1,size - index);
        System.arraycopy(values,index,values,index+1,size - index);
        size++;
        keys[index]=key;
        values[index]=value;
    }

    /**
     * O(lgN)
     * @param key
     * @return
     */
    @Override
    public V get(K key) {
        int index=binarySearch(key);
        if(key.equals(keys[index])) return values[index];
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void delete(K key) {
        int index=binarySearch(key);
        if(!keys[index].equals(key))
            throw new NoSuchElementException();
        System.arraycopy(keys,index,keys,index-1,size - index);
        System.arraycopy(values,index,values,index-1,size - index);
        size--;
        keys[size - 1]=null;
        values[size - 1]=null;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    /**
     * O(lgN)
     * @param key
     * @return
     */
    @Override
    public boolean contains(K key) {
        int index=binarySearch(key);
        return key.equals(keys[index]);
    }

    @Override
    public Set<K> keySet() {
        return new AbstractSet<K>() {
            @Override
            public Iterator<K> iterator() {
                return new Iterator<K>() {
                    private int current=0;
                    @Override
                    public boolean hasNext() {
                        return current<size;
                    }

                    @Override
                    public K next() {
                        return keys[current++];
                    }
                };
            }

            @Override
            public int size() {
                return size;
            }
        };
    }

    /**
     * 查找命中返回元素的索引，未命中则返回数组中小于该键的元素个数
     * @param key
     * @return
     */
    private int binarySearch(K key){
        int lo=0,hi=size - 1;
        while (lo <= hi){
            int mid = (lo + hi) >> 1;
            int res=keys[mid].compareTo(key);
            if (res>0) lo=mid + 1;
            else if(res<0) hi=mid - 1;
            else return mid;
        }
        return lo;
    }
}
