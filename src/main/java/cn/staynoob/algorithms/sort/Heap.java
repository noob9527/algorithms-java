package cn.staynoob.algorithms.sort;

/**
 * Created by xy on 16-5-14.
 */
public class Heap extends AbstractSorter{

    private int size;

    public Heap(Comparable[] arr) {
        super(arr);
        size=arr.length;
    }

    @Override
    public void sort() {
        //takes O(N) time
        heapfy();

        //takes O(NlogN) time
        while(size>1){
            exchange(0,--size);
            siftDown(0);
        }
    }

    private void heapfy(){
        for (int i = size>>1; i >= 0 ; i--) {
            siftDown(i);
        }
    }

    private void siftDown(int k){
        int half=size>>1;
        while(k<half){
            int left=k<<1+1,right=left+1;
            int tmp=left;
            if(right<size && arr[right].compareTo(arr[left]) > 0) tmp=right;
            exchange(k,tmp);
            k=tmp;
        }
    }
}
