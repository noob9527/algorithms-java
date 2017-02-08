package cn.staynoob.algorithms.sort;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by xy on 16-4-26.
 */

public class AbstractSorterTest {
    private AbstractSorter sorter;
    private static Integer[] arr;
    private static final int inputSize=1000000;

    static {
        long seed=System.currentTimeMillis();
        Random random=new Random(seed);

        arr=new Integer[inputSize];
        for (int i = 0; i < arr.length; i++) {
            arr[i]=random.nextInt(1000);
        }
    }

    @Test
    @Ignore
    public void selectionTest() throws Exception {
        sorter=new Selection(Arrays.copyOf(arr,arr.length));
        test();
    }
    @Test
    @Ignore
    public void bubbleTest() throws Exception {
        sorter=new Bubble(Arrays.copyOf(arr,arr.length));
        test();
    }
    @Test
    @Ignore
    public void insertionTest() throws Exception {
        sorter=new Insertion(Arrays.copyOf(arr,arr.length));
        test();
    }
    @Test
    @Ignore
    public void shellTest() throws Exception {
        sorter=new Shell(Arrays.copyOf(arr,arr.length));
        test();
    }
    @Test
    @Ignore
    public void mergeTest() throws Exception {
        sorter=new Merge(Arrays.copyOf(arr,arr.length));
        test();
    }
    @Test
    public void quickTest() throws Exception {
        sorter=new Quick(Arrays.copyOf(arr,arr.length));
        test();
    }
    @Test
    public void quickXTest() throws Exception {
        sorter=new Quick(Arrays.copyOf(arr,arr.length));
        test();
    }

    @Test
    public void heapTest() throws Exception {
        sorter=new Quick(Arrays.copyOf(arr,arr.length));
        test();
    }

    @Test
    @Ignore
    public void JDKTest() throws Exception {
        Arrays.sort(Arrays.copyOf(arr,arr.length));
    }

    private void test(){
        sorter.sort();
        //System.out.println(sorter);
        Assert.assertTrue(sorter.isSorted());
    }

}