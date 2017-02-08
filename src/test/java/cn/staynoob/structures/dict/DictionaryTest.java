package cn.staynoob.structures.dict;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by xy on 16-5-15.
 */
public class DictionaryTest {
    private Dictionary<String,Integer> dict;
    private Scanner scanner;
    private final int minLength=1;

    @Before
    public void setUp() throws Exception {
        scanner=new Scanner(new File(this.getClass().getResource("/algs4-data/tale.txt").getFile()));
    }

    /**
     * O(N^2)
     * @throws Exception
     */
    @Test
    @Ignore
    public void linkedDictTest() throws Exception {
        dict=new LinkedDict<>();
        test();
    }

    /**
     * O(N^2)
     * @throws Exception
     */
    @Test
    @Ignore
    public void binarySearchDictTest() throws Exception {
        dict=new BinarySearchDict<>();
        test();
    }

    @Test
    public void BSTDictTest() throws Exception {
        dict=new BSTDict<>();
        test();
    }

    @Test
    public void RedBlackBSTDictTest() throws Exception {
        dict=new RedBlackBSTDict<>();
        test();
    }

    @Test
    public void ChainningHashDict() throws Exception {
        dict=new ChainningHashDict<>();
        test();
    }

    @Test
    public void LinearProbingHashDict() throws Exception {
        dict=new LinearProbingHashDict<>();
        test();
    }

    private void test(){

        while (scanner.hasNext()){
            String word=scanner.next();
            if(word.length() < minLength) continue;
            if(dict.contains(word)) dict.put(word,dict.get(word) + 1);
            else dict.put(word,1);
            //System.out.println(word+":"+dict.size());
        }

        int v=0;
        String k="";

        Set<String> set=dict.keySet();
        for (String key : set) {
            int value=dict.get(key);
            if(value>v) {
                k=key;
                v=value;
            }
        }
        System.out.println("size:"+dict.size());
        System.out.println(k+":"+v);
    }
}