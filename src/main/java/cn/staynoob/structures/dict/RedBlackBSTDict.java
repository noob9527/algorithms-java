package cn.staynoob.structures.dict;

import java.util.*;

/**
 * Created by xy on 16-5-25.
 */
public class RedBlackBSTDict<K extends Comparable<K>,V> implements Dictionary<K,V>{

    private static final boolean RED=false;
    private static final boolean BLACK=true;

    private Node<K,V> root;
    @Override
    public void put(K key, V value) {
        if(key==null)
            throw new NullPointerException();
        root=put(root,key,value);
    }



    @Override
    public V get(K key) {
        if(key==null)
            throw new NullPointerException();
        Node<K,V> node=root;
        while(node!=null){
            int res=key.compareTo(node.key);
            if(res>0) node=node.right;
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
    public void delete(K key){
        if(key==null)
            throw new NullPointerException();
        root=delete(root,key);
    }
    private Node<K,V> delete(Node<K,V> node,K key){
        //i have no idea about it yet
        return null;
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

    private static class Node<K,V>{
        K key;
        V value;
        Node<K,V> left,right;
        int size=1;
        boolean color;

        public Node(K key, V value,boolean color) {
            this.key = key;
            this.value = value;
            this.color=color;
        }

        public void flipColor(){
            this.left.color=!this.left.color;
            this.right.color=!this.right.color;
            this.color=!this.color;
        }
    }

    private Node<K,V> put(Node<K,V> node,K key,V value){
        if(node==null){
            return new Node<K,V>(key,value,RED);
        }

        int res=key.compareTo(node.key);
        if(res>0) node.right=put(node.right,key,value);
        else if(res<0) node.left=put(node.left,key,value);
        else node.value=value;

        node=balance(node);
        node.size=size(node.left)+size(node.right)+1;
        return node;
    }

    private Node<K,V> balance(Node<K,V> node){
        if(!isRed(node.left) && isRed(node.right)) node=rotateLeft(node);
        if(isRed(node.left) && isRed(node.left.left)) node=rotateRight(node);
        if(isRed(node.left) && isRed(node.right)) node.flipColor();
        return node;
    }

    private Node<K,V> rotateLeft(Node<K,V> node){
        //exchange color
        boolean tmp=node.color;
        node.color=node.right.color;
        node.right.color=tmp;

        Node<K,V> tmpNode=node.right;
        node.right=node.right.left;
        tmpNode.left=node;

        node.size=size(node.left)+size(node.right)+1;
        return tmpNode;
    }

    private Node<K,V> rotateRight(Node<K,V> node){
        //exchange color
        boolean tmp=node.color;
        node.color=node.left.color;
        node.left.color=tmp;

        Node<K,V> tmpNode=node.left;
        node.left=node.left.right;
        tmpNode.right=node;

        node.size=size(node.left)+size(node.right)+1;
        return tmpNode;
    }

    private boolean isRed(Node<K,V> node){
       return node!=null && node.color==RED;
    }
}
