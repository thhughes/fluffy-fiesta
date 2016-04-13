package hanto.Test.studentThhughes.common.moveCalculator;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.Test;

import hanto.common.HantoCoordinate;
import hanto.studentThhughes.common.coordinate.HantoCoordinateImpl;
import hanto.studentThhughes.common.moveCalculator.MoveCalculator;
import hanto.studentThhughes.common.moveCalculator.SingleMoveCalculator;
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
