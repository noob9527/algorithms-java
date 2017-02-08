package cn.staynoob.structures.dict;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * 基于链表实现字典集合，这种方式非常低效
 * Created by xy on 16-5-15.
 */
public class LinkedDict<K,V> implements Dictionary<K,V>{

    private Node<K,V> first;
    private int size;

    /**
     * takes O(N) time
     * @param key
     * @param value
     */
    @Override
    public void put(K key, V value) {
        if(key==null)
            throw new NullPointerException();
        for (Node<K,V> node=first;node!=null;node=node.next){
            if(key.equals(node.key)) {
                node.value = value;
                return;
            }
        }
        first=new Node<>(key,value,first);
        size++;
    }

    /**
     * O(N)
     * @param key
     * @return
     */
    @Override
    public V get(K key) {
        if(key==null)
            throw new NullPointerException();

        for (Node<K,V> node=first;node!=null;node=node.next){
            if(key.equals(node.key)) return node.value;
        }
        throw new NoSuchElementException();
    }

    /**
     * O(1)
     * @return
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * O(N)
     * @param key
     */
    @Override
    public void delete(K key) {
        if(first.key.equals(key)) {
            first=first.next;
            size--;
            return;
        }
        Node<K,V> node=first;
        while (node.next!=null){
            if(node.next.key.equals(key)){
                node.next=node.next.next;
                size--;
                return;
            }
            node=node.next;
        }
        throw new NoSuchElementException();
    }

    /**
     * O(1)
     * @return
     */
    @Override
    public boolean isEmpty() {
        return size==0;
    }

    /**
     * O(N)
     * @param key
     * @return
     */
    @Override
    public boolean contains(K key) {
        for (Node<K,V> node=first;node!=null;node=node.next){
            if(key.equals(node.key)) return true;
        }
        return false;
    }

    @Override
    public Set<K> keySet() {
        return new AbstractSet<K>() {
            @Override
            public Iterator<K> iterator() {
                return new Iterator<K>() {
                    Node<K,V> current=first;

                    @Override
                    public boolean hasNext() {
                        return current!=null;
                    }

                    @Override
                    public K next() {
                        Node<K,V> node=current;
                        current=current.next;
                        return node.key;
                    }
                };
            }

            @Override
            public int size() {
                return size;
            }
        };
    }

    private static class Node<K,V>{
        K key;
        V value;
        Node<K,V> next;
        public Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}
