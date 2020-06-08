package com.RmqVisualizer.RmqVisualizer;

import com.RmqVisualizer.RmqVisualizer.SparseTableLogic.RmqSolver;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class RmqVisualizerApplicationTests {

	@Autowired
	private RmqSolver solver = new RmqSolver();

	@Test
	public void testMatrixArrayTranslation(){
		assertEquals((int)solver.translateMatrixIntoArrayCoordinates(1, 1,10), 1);
		assertEquals((int)solver.translateMatrixIntoArrayCoordinates(1, 2,10), 2);
		assertEquals((int)solver.translateMatrixIntoArrayCoordinates(1, 3,10), 3);
		assertEquals((int)solver.translateMatrixIntoArrayCoordinates(1, 4,10), 4);
		assertEquals((int)solver.translateMatrixIntoArrayCoordinates(1, 5,10), 5);
		assertEquals((int)solver.translateMatrixIntoArrayCoordinates(1, 6,10), 6);
		assertEquals((int)solver.translateMatrixIntoArrayCoordinates(1, 7,10), 7);
		assertEquals((int)solver.translateMatrixIntoArrayCoordinates(1, 8,10), 8);
		assertEquals((int)solver.translateMatrixIntoArrayCoordinates(1, 9,10), 9);
		assertEquals((int)solver.translateMatrixIntoArrayCoordinates(1, 10,10), 10);
		assertEquals((int)solver.translateMatrixIntoArrayCoordinates(2, 1,10), 11);
		assertEquals((int)solver.translateMatrixIntoArrayCoordinates(2, 2,10), 12);
		assertEquals((int)solver.translateMatrixIntoArrayCoordinates(3, 1,10), 20);
		assertEquals((int)solver.translateMatrixIntoArrayCoordinates(4, 1,10), 27);
		assertEquals((int)solver.translateMatrixIntoArrayCoordinates(4, 2,10), 28);
		assertEquals((int)solver.translateMatrixIntoArrayCoordinates(4, 3,10), 29);
		assertEquals((int)solver.translateMatrixIntoArrayCoordinates(2, 1,5), 6);

	}

	@Test
	public void testSmallestPowerOfTwo(){
		assertEquals((int)solver.smallestPowerOfTwoSmallerThan(100), 6);
		assertEquals((int)solver.smallestPowerOfTwoSmallerThan(64), 6);
		assertEquals((int)solver.smallestPowerOfTwoSmallerThan(2), 1);
		assertEquals((int)solver.smallestPowerOfTwoSmallerThan(4), 2);
		assertEquals((int)solver.smallestPowerOfTwoSmallerThan(8), 3);
		assertEquals((int)solver.smallestPowerOfTwoSmallerThan(16), 4);
		assertEquals((int)solver.smallestPowerOfTwoSmallerThan(32), 5);
		assertEquals((int)solver.smallestPowerOfTwoSmallerThan(1), 0);

	}

	@Test
	public void testPrecalculationNumbers(){
		List<Integer> testList = new ArrayList<>();
		List<Integer> expectedResult = new ArrayList<>();
		testList.addAll(Arrays.asList(1, 2, 3, 4, 5));
		expectedResult.addAll(Arrays.asList(1, 2, 3, 4, 5, 1, 2, 3, 4, 1, 2));
		assertEquals(expectedResult, solver.getPrecalculationNumbers(testList));

		testList.clear();
		expectedResult.clear();

		testList.addAll(Arrays.asList(10, 58, 21, 5, 2, 18));
		expectedResult.addAll(Arrays.asList(10, 58, 21, 5, 2, 18, 10, 21, 5, 2, 2, 5, 2, 2));
		assertEquals(expectedResult, solver.getPrecalculationNumbers(testList));

		testList.clear();
		expectedResult.clear();

		testList.addAll(Arrays.asList(1, 1, 1, 1));
		expectedResult.addAll(Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1));
		assertEquals(expectedResult, solver.getPrecalculationNumbers(testList));

		testList.clear();
		expectedResult.clear();

		testList.addAll(Arrays.asList(1));
		expectedResult.addAll(Arrays.asList(1));
		assertEquals(expectedResult, solver.getPrecalculationNumbers(testList));
	}

	@Test
	public void testRMQ(){
		List<Integer> testList = new ArrayList<>();
		//                            0   1  2   3    4     5  6  7  8  9 10 11 12  13      14    15
		testList.addAll(Arrays.asList(40, 2, 19, 129, 1577, 5, 2, 1, 2 ,3, 1, 1, 1, 599999, 2987, 214));
		assertEquals((int)solver.getMinimumFromRange(testList, solver.getPrecalculationNumbers(testList), 1, 3), 2);
		assertEquals((int)solver.getMinimumFromRange(testList, solver.getPrecalculationNumbers(testList), 1, 5), 2);
		assertEquals((int)solver.getMinimumFromRange(testList, solver.getPrecalculationNumbers(testList), 2, 2), 19);
		assertEquals((int)solver.getMinimumFromRange(testList, solver.getPrecalculationNumbers(testList), 11, 13), 1);
		assertEquals((int)solver.getMinimumFromRange(testList, solver.getPrecalculationNumbers(testList), 13, 15), 214);
		assertEquals((int)solver.getMinimumFromRange(testList, solver.getPrecalculationNumbers(testList), 5, 6), 2);
		assertEquals((int)solver.getMinimumFromRange(testList, solver.getPrecalculationNumbers(testList), 0, 0), 40);
		assertEquals((int)solver.getMinimumFromRange(testList, solver.getPrecalculationNumbers(testList), 10, 12), 1);


	}

}
