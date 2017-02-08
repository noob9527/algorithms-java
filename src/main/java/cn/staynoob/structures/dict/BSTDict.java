package cn.staynoob.structures.dict;

import java.util.*;

/**
 * 二叉查找树实现符号表
 * 默认 a.compareTo(b)==0 是 a.equals(b) 的充要条件
 * Created by xy on 16-5-16.
 */
public class BSTDict<K extends Comparable<K>,V> implements Dictionary<K,V>{

    private Node<K,V> root;

    @Override
    public void put(K key, V value) {
        if(key==null)
            throw new NullPointerException();
        root=put(root,key,value);
    }

    private Node<K,V> put(Node<K,V> node,K key,V value){
        if(node==null) return new Node<>(key,value);

        int res=key.compareTo(node.key);
        if(res > 0) node.right = put(node.right,key,value);
        else if(res < 0) node.left=put(node.left,key,value);
        else node.value=value;

        node.size=size(node.left) + size(node.right) + 1;
        return node;
    }

    @Override
    public V get(K key) {
        if(key==null)
            throw new NullPointerException();
        Node<K,V> node=root;
        while(node!=null){
            int res=key.compareTo(node.key);
            if(res > 0) node=node.right;
            else if(res<0) node=node.left;
            else return node.value;
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return root.size;
    }
    private int size(Node<K,V> node){
        if(node==null) return 0;
        return node.size;
    }

    @Override
    public void delete(K key) {
        if(key==null)
            throw new NullPointerException();
        root=delete(root,key);
    }

    private Node<K,V> delete(Node<K,V> node,K key){
        if (node==null)
            throw new NoSuchElementException();

        int res=key.compareTo(node.key);
        if(res>0) node.right=delete(node.right,key);
        else if(res<0) node.left=delete(node.left,key);
        else {
            if(node.left==null) return node.right;
            else if(node.right==null) return node.left;
            else{
                Node<K,V> tmp=node;
                node=min(node.right);
                node.left=tmp.left;
                node.right=deleteMin(tmp.right);
            }
        }

        node.size=size(node.left) + size(node.right) + 1;
        return node;
    }

    private Node<K,V> deleteMin(Node<K,V> node){
        if (node.left==null) return node.right;
        node.left=deleteMin(node.left);
        node.size=size(node.left) + size(node.right) + 1;
        return node;
    }

    @Override
    public boolean isEmpty() {
        return root.size==0;
    }

    @Override
    public boolean contains(K key) {
        Node<K,V> node=root;
        while (node!=null){
            int res=key.compareTo(node.key);
            if (res > 0) node=node.right;
            else if(res < 0) node=node.left;
            else return true;
        }
        return false;
    }

    @Override
    public Set<K> keySet() {
        return new AbstractSet<K>() {
            private List<K> list;

            @Override
            public Iterator<K> iterator() {
                List<K> list=new ArrayList<>(root.size);
                collect(list,root);
                return list.iterator();
            }

            @Override
            public int size() {
                return root.size;
            }

            private void collect(List<K> list,Node<K,V> node){
                if(node==null) return;
                if(node.left!=null) collect(list,node.left);
                list.add(node.key);
                if(node.right!=null) collect(list,node.right);
            }
        };
    }

    public K min(){
        return min(root).key;
    }

    private Node<K,V> min(Node<K,V> node){
        while (node.left!=null) node=node.left;
        return node;
    }

    private static class Node<K,V>{
        int size;
        Node<K,V> left,right;
        K key;
        V value;

        Node(K key,V value) {
            this.value = value;
            this.key = key;
            this.size = 1;
        }
    }
}
