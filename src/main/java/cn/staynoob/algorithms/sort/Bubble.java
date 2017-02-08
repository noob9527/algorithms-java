package cn.staynoob.algorithms.sort;

/**
 * Created by xy on 16-4-26.
 */
public class Bubble extends AbstractSorter{

    public Bubble(Comparable[] arr) {
        super(arr);
    }

    @Override
    public void sort() {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length-i-1; j++) {
                if(arr[j].compareTo(arr[j+1])>0) exchange(j,j+1);
            }
        }
    }
}
