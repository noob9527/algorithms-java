package cn.staynoob.algorithms.sort;


/**
 * Created by xy on 16-4-28.
 */
public class QuickX extends AbstractSorter{
    private static final int CUTOFF=8;

    public QuickX(Comparable[] arr) {
        super(arr);
    }

    @Override
    public void sort() {
        sort(0,arr.length - 1);
    }

    private void sort(int lo,int hi){
        int length=hi-lo+1;

        if(length<=CUTOFF){
            //use insertion sort
            insertion(lo,hi);
            return;
        }else if(length<=40){
            //use median of three
            int m=median3(lo, lo + length>>1, hi);
            exchange(lo,m);
        }else{
            //use median of medians
            int eps = length/8;
            int mid = lo + length>>1;
            int m1 = median3(lo, lo + eps, lo + eps + eps);
            int m2 = median3(mid - eps, mid, mid + eps);
            int m3 = median3(hi - eps - eps, hi - eps, hi);
            int ninther = median3(m1, m2, m3);
            exchange(ninther, lo);
        }

        /*
         * Partitioning degenerates to the traditional 3-way
         * (or "Dutch National Flag") schema:
         *
         *   left part    center part              right part
         * +--------------------------------------------------+
         * lo|  < pivot  |   == pivot   |     ?    |  > pivot |
         * +--------------------------------------------------+
         *               ^              ^          ^
         *               |              |          |
         *              less            k        great
         *
         */
//        int pivot=lo,less=lo,great=hi;
//
//        for (int k = lo+1; k <= great; k++) {
//            if(arr[k].compareTo(pivot)==0) continue;
//
//            if(arr[k].compareTo(pivot)<0){
//                exchange(k,less++);
//            } else{
//                while (arr[great].compareTo(pivot)>0) great--;
//                exchange(k,great--);
//            }
//        }
//        sort(lo,less - 1);
//        sort(great + 1,hi);


        /*
         * three way partitioning
         * By Bently and McIlroy
         *
         *   left part            center part          right part
         * +-------------------------------------------------------+
         * lo|  = pivot  |  < pivot  |  ?  |  > pivot  |  = pivot  |
         * +-------------------------------------------------------+
         *               ^           ^     ^          ^
         *               |           |     |          |
         *             less->        i->  <-j       <-great
         *
         */
        int less=lo,great=hi+1;

        while(arr[++less].compareTo(arr[lo])==0 && less<hi);
        while(arr[--great].compareTo(arr[lo])==0 && great>less);

        int i=less-1,j=great+1;

        while (true) {
            while(arr[++i].compareTo(arr[lo])<0 && i<hi);
            while(arr[--j].compareTo(arr[lo])>0);

            if(i==j) exchange(i,less++);
            if(i>=j) break;

            exchange(i,j);
            if(arr[i].compareTo(arr[lo])==0) exchange(i,less++);
            if(arr[j].compareTo(arr[lo])==0) exchange(j,great--);

        }
        i=j+1;
        for (int k = lo; k < less; k++) exchange(k,j--);
        for (int k = hi; k > great; k++) exchange(k,i++);

        sort(lo,j);
        sort(i,hi);

    }

    private int median3(int i,int j,int k){
        return  (arr[i].compareTo(arr[j])<0 ?
                (arr[j].compareTo(arr[k])<0 ? j : arr[i].compareTo(arr[k])<0 ? k : i):
                (arr[j].compareTo(arr[k])<0 ? k : arr[i].compareTo(arr[k])<0 ? i : k));
    }

    //Optional 对小规模数组使用插入排序
    private void insertion(int lo,int hi) {
        for (int i = lo; i <= hi; i++) {
            for (int j = i; j > lo && arr[j].compareTo(arr[j - 1]) < 0; j--) {
                exchange(j, j - 1);
            }
        }
    }

}
