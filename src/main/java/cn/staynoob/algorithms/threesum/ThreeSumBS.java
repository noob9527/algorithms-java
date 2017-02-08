package cn.staynoob.algorithms.threesum;

import java.util.Arrays;

/**
 * O(N^2 logN)
 * Created by xy on 16-4-26.
 */
public class ThreeSumBS implements ThreeSum{

    private Integer[] input;

    public ThreeSumBS(Integer[] input) {
        this.input = input;
    }

    @Override
    public int count() {
        int count=0;
        Arrays.sort(input, (x,y)->x-y);
        for (int i = 0; i < input.length; i++) {
            for (int j = i+1; j < input.length; j++) {
                int k=Arrays.binarySearch(input,-(input[i]+input[j]));
                if(k>j){
                    System.out.println(input[i] + " " + input[j] + " " + input[k]);
                    count++;
                }
            }
        }
        return count;
    }
}
