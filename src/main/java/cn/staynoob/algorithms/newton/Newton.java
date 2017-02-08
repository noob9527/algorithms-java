package cn.staynoob.algorithms.newton;

/**
 * Created by xy on 16-6-4.
 */
public class Newton {
    private Newton() {}

    public static double sqrt(double input){
        double x=input;
        for (int i = 0; i < 5; i++) {
            x=(x+input/x)/2;
            System.out.println(i+"次迭代:"+x);
        }
        return x;
    }

    public static double reciprocal(double input){
        double x=0.1;
        for (int i = 0; i < 10; i++) {
            x=x*(2-(input*x));
            System.out.println(i+"次迭代:"+x);
        }
        return x;
    }

    public static float quickReciprocalSqrt(float number){
        float y=Float.intBitsToFloat(0x5f3759df-(Float.floatToIntBits(number)>>1));
//        System.out.println(y);
//        System.out.println(y=y*(1.5f-number/2*y*y));
//        System.out.println(y=y*(1.5f-number/2*y*y));
        return y;
    }

}
