package hanto.Test.studentThhughes.common.movecalculator;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.Test;

import hanto.common.HantoCoordinate;
import hanto.studentThhughes.common.coordinate.HantoCoordinateImpl;
import hanto.studentThhughes.common.movecalculator.MoveCalculator;
import hanto.studentThhughes.common.movecalculator.NMoveCalculator;

public class NMoveTest {
	
	MoveCalculator mc;
	
	@Test
	public void testNotNull()
	{
		assertNotNull(new NMoveCalculator(1));
	}
	
	
	@Test
	public void testSixResponsesFromNMoveCalculatorOfOne()
	{
		mc = new NMoveCalculator(1);
		Queue<HantoCoordinate> coordinateList = mc.calcMoveCoordinates(new HantoCoordinateImpl(0,0));
		assertTrue(6 == coordinateList.size()); 
	}
	
	@Test
	public void sampleSinglePointInList()
	{
		Queue<HantoCoordinate> coordinateList = 
				(new NMoveCalculator(1)).calcMoveCoordinates(new HantoCoordinateImpl(0,0));
		Queue<HantoCoordinateImpl> implList = new LinkedList<HantoCoordinateImpl>();
		// Converts output to HantoCoordinateImpl's
		for (HantoCoordinate hc: coordinateList){
			implList.add(new HantoCoordinateImpl(hc));
		}
		// Tests if one that should be in the list is in it. 
		HantoCoordinateImpl tester = new HantoCoordinateImpl(0,1);
		assertTrue(implList.contains(tester)); 
	}
	
	@Test
	public void sampleSinglePointNotInList()
	{
		Queue<HantoCoordinate> coordinateList = 
				(new NMoveCalculator(1)).calcMoveCoordinates(new HantoCoordinateImpl(0,0));
		Queue<HantoCoordinateImpl> implList = new LinkedList<HantoCoordinateImpl>();
		// Converts output to HantoCoordinateImpl's
		for (HantoCoordinate hc: coordinateList){
			implList.add(new HantoCoordinateImpl(hc));
		}
		// Tests if one that should be in the list is in it. 
		HantoCoordinateImpl tester = new HantoCoordinateImpl(0,4);
		assertFalse(implList.contains(tester)); 
	}
	
	@Test
	public void testEighteenPointsInNMoveCalculatorOfTwo()
	{
		mc = new NMoveCalculator(2);
		Queue<HantoCoordinate> coordinateList = mc.calcMoveCoordinates(new HantoCoordinateImpl(0,0));
		assertTrue(18 == coordinateList.size()); 
	}
	
	@Test
	public void samplePointInNMoveCalculatorOfTwo()
	{
		mc = new NMoveCalculator(2);
		Queue<HantoCoordinate> coordinateList = mc.calcMoveCoordinates(new HantoCoordinateImpl(0,0));
		Queue<HantoCoordinateImpl> implList = new LinkedList<HantoCoordinateImpl>();
		// Converts output to HantoCoordinateImpl's
		for (HantoCoordinate hc: coordinateList){
			implList.add(new HantoCoordinateImpl(hc));
		}
		// Tests if one that should be in the list is in it. 
		HantoCoordinateImpl tester = new HantoCoordinateImpl(0,2);
		assertTrue(implList.contains(tester)); 
		
	}
	
	@Test
	public void samplePointNotNMoveCalculatorOfTwo()
	{
		mc = new NMoveCalculator(2);
		Queue<HantoCoordinate> coordinateList = mc.calcMoveCoordinates(new HantoCoordinateImpl(0,0));
		Queue<HantoCoordinateImpl> implList = new LinkedList<HantoCoordinateImpl>();
		// Converts output to HantoCoordinateImpl's
		for (HantoCoordinate hc: coordinateList){
			implList.add(new HantoCoordinateImpl(hc));
		}
		// Tests if one that should be in the list is in it. 
		HantoCoordinateImpl tester = new HantoCoordinateImpl(0,6);
		assertFalse(implList.contains(tester)); 
		
	}

}
