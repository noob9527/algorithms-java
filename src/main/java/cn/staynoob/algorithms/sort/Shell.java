package cn.staynoob.algorithms.sort;

/**
 * Created by xy on 16-4-27.
 */
public class Shell extends AbstractSorter{

    public Shell(Comparable[] arr) {
        super(arr);
    }

    @Override
    public void sort() {
        int step=1;

        /**
         * According to wikipedia
         * Shell's original sequence: N/2 , N/4 , ..., 1 (repeatedly divide by 2);
         * Knuth's increment sequence (1, 4, 13, 40, ...).
         * The Best Known Gap sequence is Sedgewick's (1, 5, 19, 41, 109,...),obtained by 9 * 4^i - 9 * 2^i + 1 and 2^{i+2} * (2^{i+2} - 3) + 1
         */
        while(step<arr.length/3) step=3*step+1;   //coz it's easy to generate

        while (step>=1){
            for (int i = step; i < arr.length; i++) {
                for (int j = i; j>=step && arr[j].compareTo(arr[j-step])<0; j-=step) {
                    exchange(j,j-step);
                }
            }
            step/=3;
        }
    }
}
