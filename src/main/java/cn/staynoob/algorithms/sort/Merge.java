package cn.staynoob.algorithms.sort;

/**
 * Created by xy on 16-4-27.
 */
public class Merge extends AbstractSorter{

    //当数组长度小于该值时使用插入排序
    private static final int CUTOFF=7;

    public Merge(Comparable[] arr) {
        super(arr);
    }

    private Comparable[] aux;

    @Override
    public void sort() {
        aux=new Comparable[arr.length];
        sort(0,arr.length - 1);
    }

    private void sort(int lo,int hi){
        if(hi<=lo+CUTOFF){
            insertion(lo,hi);
            return;
        }
        int mid=(lo+hi)>>1;
        sort(lo,mid);
        sort(mid+1,hi);
        merge(lo,mid,hi);
    }

    private void merge(int lo,int mid,int hi){
        if(arr[mid].compareTo(arr[mid+1])<0) return; //needn't merge
        System.arraycopy(arr,lo,aux,0,hi-lo+1);

        int i=0,j=mid-lo+1;
        for (int k=lo; k <= hi; k++){
            if(i>mid-lo) arr[k]=aux[j++];
            else if(j>hi-lo) arr[k]=aux[i++];
            else if(aux[i].compareTo(aux[j])<0) arr[k]=aux[i++];
            else arr[k]=aux[j++];
        }
    }

    //Optional 对小规模数组使用插入排序
    private void insertion(int lo,int hi){
        for (int i = lo; i <= hi; i++) {
            for (int j = i; j > lo && arr[j].compareTo(arr[j-1])<0; j--) {
                exchange(j,j-1);
            }
        }
    }
}
