package cn.staynoob.algorithms.threesum;

/**
 * O(N^3)
 * Created by xy on 16-4-26.
 */
public class ThreeSumBrute implements ThreeSum{
    private Integer[] input;

    public ThreeSumBrute(Integer[] input) {
        this.input = input;
    }

    @Override
    public int count() {
        int count=0;
        for (int i = 0; i < input.length; i++) {
            for (int j = i+1; j < input.length; j++) {
                for (int k = j+1; k < input.length; k++) {
                    if(input[i]+input[j]+input[k]==0)
                        //System.out.println(input[i] + " " + input[j] + " " + input[k]);
                        count++;
                }
            }
        }
        return count;
    }
}
