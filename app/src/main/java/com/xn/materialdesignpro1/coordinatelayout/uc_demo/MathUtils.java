package com.xn.materialdesignpro1.coordinatelayout.uc_demo;

/**
 * 计算工具类
 */
public class MathUtils {
    static int constrain(int amount, int low, int high) {
        return amount < low ? low : (amount > high ? high : amount);
    }

    static float constrain(float amount, float low, float high) {
        return amount < low ? low : (amount > high ? high : amount);
    }
}
