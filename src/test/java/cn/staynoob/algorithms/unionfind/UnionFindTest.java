package cn.staynoob.algorithms.unionfind;

import org.junit.Test;

import java.io.File;
import java.util.Scanner;

/**
 * Created by xy on 16-4-25.
 */
public class UnionFindTest {
    private Scanner scanner;
    private UnionFind uf;

    @Test
    public void tinyInputTest() throws Exception {
        scanner=new Scanner(new File(this.getClass().getResource("/algs4-data/tinyUF.txt").getFile()));
        test();
    }

    @Test
    public void mediumInputTest() throws Exception {
        scanner=new Scanner(new File(this.getClass().getResource("/algs4-data/mediumUF.txt").getFile()));
        test();
    }

    @Test
    //@Ignore
    public void largeInputTest() throws Exception {
        scanner=new Scanner(new File(this.getClass().getResource("/algs4-data/largeUF.txt").getFile()));
        test();
    }

    private void test(){
        int N=scanner.nextInt();
        uf=new WeightedQuickUnion(N);

        while (scanner.hasNext()){
            int p=scanner.nextInt();
            int q=scanner.nextInt();
            uf.union(p,q);
        }
        System.out.println(N);
        System.out.println(uf.count()+"components");
    }
}