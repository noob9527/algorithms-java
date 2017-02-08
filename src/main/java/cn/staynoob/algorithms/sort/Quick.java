package cn.staynoob.algorithms.sort;

/**
 * Created by xy on 16-4-28.
 */
public class Quick extends AbstractSorter {
    public Quick(Comparable[] arr) {
        super(arr);
    }

    @Override
    public void sort() {
        sort(0, arr.length - 1);
    }

    private void sort(int lo, int hi) {
        if (hi <= lo) return;
        // arr[lo...point]<=arr[point]<=arr[point...hi]
        int point = partition(lo, hi);
        sort(lo, point - 1);
        sort(point + 1, hi);
    }

    private int partition(int lo, int hi) {
        int i = lo, j = hi + 1;
        while (true) {
            while (arr[++i].compareTo(arr[lo]) < 0 && i < hi) ;
            while (arr[--j].compareTo(arr[lo]) > 0) ; //这里不需要判断j>lo，因为当j=lo时，arr[j]>arr[lo]不可能成立
            if (i >= j) break;
            exchange(i, j);
        }
        exchange(lo, j);
        return j;
    }

}
