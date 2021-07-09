package org.serj;

import java.math.BigInteger;

public class test2 {
    public static void main(String[] args) {

        calculateFigureSize(1000);
    }

    public static void calculateFigureSize(int iter) {
        BigInteger width = BigInteger.ONE;
        BigInteger height = BigInteger.ONE;
        int jc1 = 0;
        int jc2 = 2;
        System.out.println("1)" + width + " : " + height);
        for (int i = 2; i <= iter; i++) {
            BigInteger foldsCount = foldsCountCalc(i);
            BigInteger jacCount1 = jacobsthalNum(jc1);
            BigInteger jacCount2 = jacobsthalNum(jc2);
            BigInteger tmp;

            if (foldsCountRemainder(foldsCount, 3)) {
                tmp = width;
                width = height.add(jacCount2);
                height = tmp;
                jc2++;
            }
            if (foldsCountRemainder(foldsCount, 7)) {
                width = width.add(jacCount1);
                height = BigInteger.valueOf(2).pow((i + 1) / 2).subtract(BigInteger.ONE);
                jc1++;
            }
            if (foldsCountRemainder(foldsCount, 5)) {
                tmp = height;
                height = width.add(jacCount2);
                width = tmp;
                jc2++;
            }
            if (foldsCountRemainder(foldsCount, 1)) {
                width = BigInteger.valueOf(2).pow((i + 1) / 2).subtract(BigInteger.ONE);
                height = height.add(jacCount1);
                jc1++;
            }
            System.out.println(i + ")" + width + " : " + height);
        }
    }

    public static BigInteger jacobsthalNum(int n) {
        BigInteger jac = ((BigInteger.valueOf(2).pow(n).subtract(BigInteger.valueOf(-1).pow(n))).divide(BigInteger.valueOf(3)));
        return jac;
    }

    public static BigInteger foldsCountCalc(int n) {
        return BigInteger.valueOf(2).pow(n).subtract(BigInteger.ONE);
    }

    public static boolean foldsCountRemainder(BigInteger b, int n) {
        int rem = b.remainder(BigInteger.TEN).intValue();
        return rem == n;
    }
}