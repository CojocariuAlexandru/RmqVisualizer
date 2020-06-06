package com.RmqVisualizer.RmqVisualizer.SparseTableLogic;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
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
    public List<Integer> getPrecalculationNumbers(List<Integer> numbers){
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
                precalculatedNumbers.add(Math.min(precalculatedNumbers.get(translateMatrixIntoArrayCoordinates(i-1, j, numbers.size()))
                                                , precalculatedNumbers.get(translateMatrixIntoArrayCoordinates(i-1, j+powersOfTwo.get(i-2), numbers.size()))
                                        )
                );
            }
        }
        return precalculatedNumbers;
    }

    public Integer getMinimumFromRange(List<Integer> numbers, List<Integer> precalculatedNumbers, Integer leftIndex, Integer rightIndex){
        Integer segmentLength;
        Integer powerOfTwoUsed;
        segmentLength = rightIndex - leftIndex + 1;
        powerOfTwoUsed = smallestPowerOfTwoSmallerThan(segmentLength);
        return Math.min(precalculatedNumbers.get(translateMatrixIntoArrayCoordinates(powerOfTwoUsed+1, leftIndex, numbers.size())),
                        precalculatedNumbers.get(translateMatrixIntoArrayCoordinates(powerOfTwoUsed+1, leftIndex+segmentLength-powersOfTwo.get(powerOfTwoUsed), numbers.size()))
        );
    }

    private Integer translateMatrixIntoArrayCoordinates(Integer line, Integer column, Integer numbersSize){
        line = line - 1;
        return line*numbersSize - (powersOfTwo.get(line) - 1 - line) + column;
    }

    public Integer smallestPowerOfTwoSmallerThan(Integer maximum){
        Integer leftIndex = -1;
        Integer rightIndex = 35;
        Integer middleIndex = 0;
        while(rightIndex - leftIndex > 1){
            middleIndex = (leftIndex + rightIndex) / 2;
            if(powersOfTwo.get(middleIndex) < maximum){
                leftIndex = middleIndex;
            }
            else{
                rightIndex = middleIndex;
            }
        }
        if(maximum == powersOfTwo.get(rightIndex)){
            return rightIndex;
        }
        else{
            return rightIndex - 1;
        }
    }
}