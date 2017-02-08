package cn.staynoob.algorithms.newton;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Created by xy on 16-6-4.
 */
public class NewtonTest {
    @Test
    public void sqrtTest() throws Exception {
        System.out.println("参考值 :1.414213562373095");
        Newton.sqrt(2d);
    }

    @Test
    public void reciprocalTest() throws Exception {
        Newton.reciprocal(5);
    }

    @Test
    public void quickReciprocalSqrt() throws Exception {
        Newton.quickReciprocalSqrt(4);
    }
}