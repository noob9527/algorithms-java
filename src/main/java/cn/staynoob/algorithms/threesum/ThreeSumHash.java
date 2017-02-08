package cn.staynoob.algorithms.threesum;

import java.util.HashMap;

/**
 * O(N^2)
 * Created by xy on 16-4-26.
 */
public class ThreeSumHash implements ThreeSum{
    private Integer[] input;

    public ThreeSumHash(Integer[] input) {
        this.input = input;
    }

    @Override
    public int count() {
        int count=0;
        HashMap<Integer,Integer> map=new HashMap<>();
        for (int i = 0; i < input.length; i++) {
            map.put(input[i],i);
        }
        for (int i = 0; i < input.length; i++) {
            for (int j = i+1; j < input.length; j++) {
                if(map.containsKey(-(input[i]+input[j]))){
                    int k=map.get(-(input[i]+input[j]));
                    if (k<=j) continue;
                    count++;
                    //System.out.println(input[i] + " " + input[j] + " " + input[k]);
                }
            }
        }
        return count;
    }
}
