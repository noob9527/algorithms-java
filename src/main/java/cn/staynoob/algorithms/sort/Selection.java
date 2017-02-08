package cn.staynoob.algorithms.sort;

/**
 * Created by xy on 16-4-26.
 */
public class Selection extends AbstractSorter{

    public Selection(Comparable[] arr) {
        super(arr);
    }

    @Override
    public void sort() {
        if (arr==null) return;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i+1; j < arr.length; j++) {
                if(arr[i].compareTo(arr[j])>0) exchange(i,j);
            }
        }
    }

}
