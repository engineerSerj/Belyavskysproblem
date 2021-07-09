package org.serj;

import java.math.BigInteger;


/*
Author: Koltsov Sergei

   n = 1(1, 1)                n = 2(2, 1)                     n = 3(2, 3)         ->  n = 4(3, 5)
  * * * * * *                * * * * * *                     * * * * * *              n = 5(7, 6)
  *                          *         *                     *         *              n = 6(?, ?)
  *                          *         *                     *         *               ........
  *                          *         *                     *         *              n = 100(?, ?)
  *                          *         * * * * * *           *         * * * * * *
                                                                                 *
                                                                                 *
                                                                                 *
                                                                       * * * * * *
                                                                       *
                                                                       *
                                                                       *
                                                                       * * * * * *


        https://ru.wikipedia.org/wiki/Кривая_дракона
        https://ru.wikipedia.org/wiki/Числа_Якобсталя

        0, 1, 1, 3, 5, 11, 21, 43, 85, 171, 341, 683, 1365, 2731, 5461, 10 923, 21 845, 43 691, 87 381, 174 763, 349 525, …

        1.
        n)  (x, y)
        1)  (1, 1)             1
        2)  (2, 1)                1
        3)  (2, 3)                3    (1 * 2 + 1)
        4)  (3, 5)             3
        5)  (7, 6)             7       (3 * 2 + 1)
        6)  (11, 7)               7
        7)  (12, 15)              15   (7 * 2 + 1)
        8)  (15, 23)           15
        9)  (31, 26)           31         .....
        10) (47, 31)              31
        11) (52, 63)              63
        12) (63, 95)           63
        13) (127, 106)         127
        14) (191, 127)            127
        15) (212, 255)            255
        16) (255, 383)         255

        2.                  0, 1, 1, 3, 5, 11, 21, 43, 85, 171, 341, ...
        n)  (x, y)          x  y
        1)  (1, 1)             1                               1
        2)  (2, 1)          2                        for x: 2        (1 + 1)-> 1*4+1
        3)  (2, 3)          2         (2 + 0)                                                  2
        4)  (3, 5)             5                                                        for y:    5  (2 + 3)->  3*4-1
        5)  (7, 6)             6      (5 + 1)                  6
        6)  (11, 7)         11                              11       (6 + 5)-> 5*4+1
        7)  (12, 15)        12        (11 + 1)                                                 12
        8)  (15, 23)           23                                                                 23 (12 + 11)->11*4-1
        9)  (31, 26)           26     (23 + 3)                 26
        10) (47, 31)        47                              47       (26 + 21)->21*4+1
        11) (52, 63)        52        (47 + 5)                                                 52
        12) (63, 95)           95                                                                 95 (52 + 43)->43*4-1
        13) (127, 106)         106    (95 + 11)                106
        14) (191, 127)      191                             191      (106 + 85)
        15) (212, 255)      212       (191 + 21)                                               212
        16) (255, 383)         383                                                                383(212 + 171) ...

        */

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