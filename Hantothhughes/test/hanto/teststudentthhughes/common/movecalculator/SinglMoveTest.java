package hanto.teststudentthhughes.common.movecalculator;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.Test;

import hanto.common.HantoCoordinate;
import hanto.studentthhughes.common.coordinate.HantoCoordinateImpl;
import hanto.studentthhughes.common.movecalculator.MoveCalculator;
import hanto.studentthhughes.common.movecalculator.SingleMoveCalculator;
public class SinglMoveTest {

	MoveCalculator mc;
	
	
	@Test
	public void testNotNull()
	{
		assertNotNull(new SingleMoveCalculator());
	}
	
	
	@Test
	public void testSixResponsesFromSingleMoveCalculator()
	{
		Queue<HantoCoordinate> coordinateList = 
				(new SingleMoveCalculator()).calcMoveCoordinates(new HantoCoordinateImpl(0,0));
		assertTrue(6 == coordinateList.size()); 
	}
	
	@Test
	public void sampleSinglePointInList()
	{
		Queue<HantoCoordinate> coordinateList = 
				(new SingleMoveCalculator()).calcMoveCoordinates(new HantoCoordinateImpl(0,0));
		Queue<HantoCoordinateImpl> implList = new LinkedList<HantoCoordinateImpl>();
		for (HantoCoordinate hc: coordinateList){
			implList.add(new HantoCoordinateImpl(hc));
		}
		HantoCoordinateImpl tester = new HantoCoordinateImpl(0,1);
		assertTrue(implList.contains(tester)); 
	}
}
