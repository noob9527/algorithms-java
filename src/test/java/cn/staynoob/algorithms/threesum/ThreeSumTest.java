package cn.staynoob.algorithms.threesum;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by xy on 16-4-26.
 */
public class ThreeSumTest {
    private ThreeSum threeSum;
    private Scanner scanner;

    @Test
    public void test1K() throws Exception {
        scanner=new Scanner(new File(this.getClass().getResource("/algs4-data/1Kints.txt").getFile()));
        test();
    }

    @Test
    public void test2K() throws Exception {
        scanner=new Scanner(new File(this.getClass().getResource("/algs4-data/2Kints.txt").getFile()));
        test();
    }

    @Test
    public void test4K() throws Exception {
        scanner=new Scanner(new File(this.getClass().getResource("/algs4-data/4Kints.txt").getFile()));
        test();
    }

    @Test
    public void test8K() throws Exception {
        scanner=new Scanner(new File(this.getClass().getResource("/algs4-data/8Kints.txt").getFile()));
        test();
    }

    private void test(){
        ArrayList<Integer> list=new ArrayList<>();
        while(scanner.hasNextInt()){
            list.add(scanner.nextInt());
        }
        //threeSum=new ThreeSumBrute(list.toArray(new Integer[0]));
        //threeSum=new ThreeSumBS(list.toArray(new Integer[0]));
        //threeSum=new ThreeSumHash(list.toArray(new Integer[0]));
        threeSum=new ThreeSumX(list.toArray(new Integer[0]));
        System.out.println(threeSum.count());
    }
}