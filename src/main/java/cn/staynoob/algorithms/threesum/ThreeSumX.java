package cn.staynoob.algorithms.threesum;

import java.util.Arrays;

/**
 * O(N^2)最优算法
 * see https://en.wikipedia.org/wiki/3SUM
 * Created by xy on 16-4-26.
 */
public class ThreeSumX implements ThreeSum{

    private Integer[] input;

    public ThreeSumX(Integer[] input) {
        this.input=input;
    }
    @Override
    public int count() {
        int count=0;
        Arrays.sort(input);

        int a,b,c,start,end;
        for (int i = 0; i < input.length - 3; i++) {
            a=input[i];
            start=i+1;
            end=input.length-1;
            while (start<end){
                b=input[start];
                c=input[end];
                if(a+b+c==0){
                    //System.out.println(a+" "+b+" "+c);
                    count++;
                    end--;
                }else if (a+b+c>0){
                    end--;
                }else {
                    start++;
                }
            }
        }
        return count;
    }
}
