package com.RmqVisualizer.RmqVisualizer.SparseTableLogic;

import java.util.ArrayList;
import java.util.List;

public class RmqSolver {
    private List<Integer> powersOfTwo;

    public RmqSolver(){
        powersOfTwo = new ArrayList<Integer>();
        computePowersOfTwo();
    }

    private void computePowersOfTwo(){
        int index;
        // base cases:
        // 2^0 = 1
        // 2^1 = 2
        powersOfTwo.add(1);
        powersOfTwo.add(2);

        // recursion case:
        // 2^n = 2^(n-1) * 2
        for(index=3; index<30; index++){
            powersOfTwo.add(powersOfTwo.get(powersOfTwo.size()-1)*2);
        }
    }

    /**
     * Gets a list of numbers, returns the sparse table condensed into an array
     * @param numbers
     * @return
     */
    private List<Integer> getPrecalculationNumbers(List<Integer> numbers){
        // Algorithm explanation: https://cp-algorithms.com/data_structures/sparse-table.html
        int i, j;

        // First row of the sparse table : base case
        List<Integer> precalculatedNumbers = new ArrayList<Integer>();
        for(Integer number : numbers){
            precalculatedNumbers.add(number);
        }

        // Recursive case
        for(i=2; (1 << (i-1)) <= numbers.size(); i++){
            for(j=1; j + powersOfTwo.get(i-2) <= numbers.size(); j++){

            }
        }
        return precalculatedNumbers;
    }

}
