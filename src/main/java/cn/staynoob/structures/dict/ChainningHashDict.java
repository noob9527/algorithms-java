package cn.staynoob.structures.dict;

import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

/**
 * 使用链表解决散列冲突
 * Created by xy on 16-5-27.
 */
public class ChainningHashDict<K extends Comparable<K>,V> implements Dictionary<K,V>{
    private int size;
    private int chainSize;
    private LinkedDict[] st;
    private static final int INIT_CAPACITY=10;

    public ChainningHashDict() {
        this(INIT_CAPACITY);
    }

    public ChainningHashDict(int chainSize) {
        this.chainSize = chainSize;
        this.st=new LinkedDict[chainSize];
        for (int i = 0; i < chainSize; i++) {
            st[i]=new LinkedDict();
        }
    }
    private int hash(K key){
        return (key.hashCode() & 0x7fffffff) % chainSize;
    }

    private void resize(int chainSize){
        ChainningHashDict<K, V> temp = new ChainningHashDict<>(chainSize);
        for (int i = 0; i < this.chainSize; i++) {
            for (Object key : st[i].keySet()) {
                temp.put((K)key, (V)st[i].get(key));
            }
        }
        this.chainSize=temp.chainSize;
        this.size=temp.size;
        this.st=temp.st;
    }

    @Override
    public void put(K key, V value) {
        if(size>=10*chainSize) resize(chainSize<<1);
        if(!contains(key)) size++;
        st[hash(key)].put(key,value);
    }

    @Override
    public V get(K key) {
        return (V)st[hash(key)].get(key);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void delete(K key) {
        if(contains(key)) size--;
        st[hash(key)].delete(key);
        if(chainSize>INIT_CAPACITY && size<=chainSize<<1) resize(chainSize>>1);
    }

    @Override
    public boolean isEmpty() {
        return size()==0;
    }

    @Override
    public boolean contains(K key) {
        return st[hash(key)].contains(key);
    }

    @Override
    public Set<K> keySet() {
        return new AbstractSet<K>() {
            private ArrayList<K> list=new ArrayList<>();
            {
                for (LinkedDict dict:st) {
                    list.addAll((Set<K>)dict.keySet());
                }
            }

            @Override
            public Iterator<K> iterator() {
                return list.iterator();
            }

            @Override
            public int size() {
                return list.size();
            }
        };
    }
}
