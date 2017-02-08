package cn.staynoob.algorithms.sort;

/**
 * Created by xy on 16-4-26.
 */
public class Insertion extends AbstractSorter{

    public Insertion(Comparable[] arr) {
        super(arr);
    }

    @Override
    public void sort() {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if(arr[j].compareTo(arr[j-1])>0) break;//这里可以通过二分查找来定位插入的位置
                exchange(j,j-1);
            }
        }
    }
}
