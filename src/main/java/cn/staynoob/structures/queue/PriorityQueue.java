package cn.staynoob.structures.queue;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * Created by xy on 16-5-14.
 */
public class PriorityQueue<E> implements Queue<E>{
    private final Comparator<? super E> comparator;

    private static final int DEFAULT_INITIAL_CAPATICY=11;

    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    /**
     * 使用数组构建堆有序的完全二叉树
     * 这里参考JDK源码，使用了0位置，通过2k+1,2k+2得到子节点位置。(k-1)>>1得到父节点位置
     */
    private E[] elementData;

    private int size=0;

    public PriorityQueue() {
        this(DEFAULT_INITIAL_CAPATICY,null);
    }

    public PriorityQueue(int capacity, Comparator<? super E> comparator) {
        this.elementData = (E[])new Object[capacity];
        this.comparator = comparator;
    }

    public PriorityQueue(E[] arr, Comparator<? super E> comparator){
        this.comparator = comparator;
        this.elementData = arr;
        this.size = elementData.length;
        heapfy();
    }

    private void grow(int minCapacity){
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity < 64 ? oldCapacity + 2 : oldCapacity >> 1);

        // overflow-conscious code
        if (newCapacity - MAX_ARRAY_SIZE > 0)
            newCapacity = hugeCapacity(minCapacity);
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) // overflow
            throw new OutOfMemoryError();
        return (minCapacity > MAX_ARRAY_SIZE) ?
            Integer.MAX_VALUE :
            MAX_ARRAY_SIZE;
    }

    @Override
    public boolean add(E e) {
        return offer(e);
    }

    @Override
    public boolean offer(E e) {
        if(e==null)
            throw new NullPointerException();
        if(size + 1 > elementData.length) grow(size + 1);
        elementData[++size - 1] = e;
        siftUp(size);
        return true;
    }

    @Override
    public E remove() {
        if(size==0)
            throw new NoSuchElementException();
        return poll();
    }

    @Override
    public E poll() {
        if(size==0) return null;
        E item=elementData[0];
        elementData[0]=elementData[size - 1];
        elementData[size--]=null;
        siftDown(0);
        return item;
    }

    @Override
    public E elements() {
        if(size==0)
            throw new NoSuchElementException();
        return elementData[0];
    }

    @Override
    public E peek() {
        return size==0 ? null : elementData[0];
    }

    /**
    * Inserts item x at position k, maintaining heap invariant by
    * promoting x up the tree until it is less than or equal to
    * its parent, or is the root.some implementation use 'swim'
    */
    private void siftUp(int k){
        while (k>0 && less((k-1)>>1,k)){
            exchange((k-1)>>1,k);
            k = (k-1)>>1;
        }
    }

    /**
     * similar to siftUp,some implementation use 'sink'
     * @param k
     */
    private void siftDown(int k){
        int half=this.size>>1;
        while (k < half){
            int left=(k<<1)+1,right=left+1;
            int tmp = left;
            if(right < this.size && less(left,right)) tmp=right;
            if(less(tmp,k)) break;
            exchange(tmp,k);
            k=tmp;
        }
    }

    private void heapfy(){
        for (int i = size>>1; i >= 0 ; i--) {
            siftDown(i);
        }
    }

    /***************************************************************************
     * Helper function.
     ***************************************************************************/
    private boolean isMaxHeap(){
        return isMaxHeap(0);
    }

    private boolean isMaxHeap(int k){
        if(k > this.size) return true;
        int left = (k<<1)+1,right = left+1;
        if(left<=size && less(k,left)) return false;
        if(right<=size && less(k,right)) return false;
        return isMaxHeap(left) && isMaxHeap(right);
    }

    private boolean less(int i,int j){
        if(comparator==null){
            return ((Comparable<? super E>)elementData[i]).compareTo(elementData[j]) < 0;
        } else {
            return comparator.compare(elementData[i],elementData[j]) < 0;
        }
    }

    private void exchange(int i,int j){
        E tmp = elementData[i];
        elementData[i]=elementData[j];
        elementData[j]=tmp;
    }

}
