/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.generala;

import java.util.Arrays;

/**
 *
 * @author Marcelo
 */
public class Generala {
    protected int[] dices;

    public Generala(int d1, int d2, int d3, int d4, int d5)
    {
        dices = new int[]{d1, d2, d3, d4, d5};
    }

    public static int chance(int... dices)
    {
        return Arrays.stream(dices).sum();
    }

    // '(int... dice)' es similar a tener public static int generala(int d1, int d2, int d3 , etc) pero permite realizar operaciones como -> for (int die : dice)
    //es una forma de decir que el metodo puede aceptar 1 o más parametros de tipo int ... lista de parametros dinamicos.
    public static int generala(int... dices)
    {
        int[] counts = theCountsArray(dices);
        for (int i = 0; i < 6; i++)
            if (counts[i] == 5)
                return 50;
        return 0;
    }

    public static int ones(int... dices) {
        return sumOfEqualDice(1, dices);
    }

    public static int twos(int... dices) {
        return sumOfEqualDice(2, dices);
    }

    public static int threes(int... dices) {
        return sumOfEqualDice(3, dices);
    }

    public int fours()
    {
        return sumOfEqualDice(4, dices);
    }

    public int fives()
    {
        return sumOfEqualDice(5, dices);
    }

    public int sixes()
    {
        return sumOfEqualDice(6, dices);
    }

    private static int sumOfEqualDice(int comparatorNum, int... dices)
    {
        return Arrays.stream(dices).filter(d->d==comparatorNum).sum();
    }

    public static int score_pair(int... dices)
    {
        int[] counts = theCountsArray(dices);
        for (int i = 0; i < 6; i++)
            if (counts[5-i] >= 2)
                return (6-i)*2;
        return 0;
    }

    public static int two_pair(int... dices)
    {
        int[] counts = theCountsArray(dices);
        int n = 0;
        int score = 0;
        for (int i = 0; i < 6; i++)
            if (counts[5-i] >= 2) {
                n++;
                score += (6-i);
            }
        if (n == 2)
            return score * 2;
        else
            return 0;
    }

    public static int three_of_a_kind(int... dices)
    {
        return ofAKind(3, dices);
    }

    public static int four_of_a_kind(int... dices)
    {
        return ofAKind(4, dices);
    }

    private static int ofAKind(int numOfTheKind, int... dices)
    {
        int[] counts = theCountsArray(dices);
        for (int i = 0; i < 6; i++)
            if (counts[i] >= numOfTheKind)
                return (i+1) * numOfTheKind;
        return 0;
    }

    public static int smallStraight(int... dices)
    {
        if (isStraight(dices))
            return 15;
        return 0;
    }

    public static int largeStraight(int... dices)
    {
        if (isStraight(dices))
            return 20;
        return 0;
    }

    private static boolean isStraight(int... dices)
    {
        int[] counts = theCountsArray(dices);
        return (Arrays.stream(counts).filter(c->c==1).count()==5);
    }

    public static int fullHouse(int... dices)
    {
        int val_of_double = 0;
        int val_of_triple = 0;

        int[] counts = theCountsArray(dices);

        for (int i = 0; i < 6; i++) {
            if (counts[i] == 2) {
                val_of_double = (i+1)*2;
            }
            if (counts[i] == 3) {
                val_of_triple = (i+1)*3;
            }
        }

        return val_of_double + val_of_triple;
    }

    private static int[] theCountsArray(int... dices) {
        int[] counts = new int[6];
        for ( int dice : dices)
            counts[dice-1]++;
        return counts;
    }
}

