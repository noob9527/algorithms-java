package cn.staynoob.algorithms.sort;

import java.util.Arrays;

/**
 * Created by xy on 16-4-26.
 */
public abstract class AbstractSorter{
    protected Comparable[] arr;

    public AbstractSorter(Comparable[] arr){
        this.arr=arr;
    }

    public abstract void sort();

    public boolean isSorted(){
        for (int i = 0; i < arr.length - 1; i++) {
            if(arr[i].compareTo(arr[i+1])>0) return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AbstractSorter{" +
                "arr=" + Arrays.toString(arr) +
                '}';
    }

    protected void exchange(int i, int j){
        Comparable tmp=arr[i];
        arr[i]=arr[j];
        arr[j]=tmp;
    }
}
