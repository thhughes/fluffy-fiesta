package hanto.Test.studentThhughes.common.coordinate;

import static org.junit.Assert.*;

import java.util.Queue;

import org.junit.Before;
import org.junit.Test;

import hanto.common.HantoCoordinate;
import hanto.studentThhughes.common.coordinate.HantoCoordinateImpl;

public class HantoCoordinateImplTest {
	
	private static HantoCoordinateImpl C1;
	private static HantoCoordinateImpl C2;
	
	@Before
	public void setup(){
		C1 = new HantoCoordinateImpl(0,0);
		C2 = new HantoCoordinateImpl(0,1);
		
	}
	
	@Test //1 
	public void testConstructor(){
		assertNotNull(C1);	
	}
	
	@Test //2 
	public void testCopyConstructor(){
		assertNotNull(new HantoCoordinateImpl(C1));
	}
	
	@Test //3 
	public void testGetters(){
		assertEquals(0,C1.getX());
		assertEquals(0,C1.getY());
	}
	
	@Test //4
	public void testCopyConstructorSetters(){
		HantoCoordinate C = new HantoCoordinateImpl(C1);
		assertEquals(C.getX(),C1.getX());
		assertEquals(C.getY(),C1.getY());
	}
	
	@Test //5
	public void testGetNeighbors(){
		Queue<HantoCoordinate> neighbors = C1.getNeighbors();
		int startX = C1.getX();
		int startY = C1.getY();
		
		assertTrue(neighbors.contains(new HantoCoordinateImpl(startX,startY+1)));
		assertTrue(neighbors.contains(new HantoCoordinateImpl(startX+1,startY)));
		assertTrue(neighbors.contains(new HantoCoordinateImpl(startX,startY-1)));
		assertTrue(neighbors.contains(new HantoCoordinateImpl(startX-1,startY)));
		assertTrue(neighbors.contains(new HantoCoordinateImpl(startX-1,startY+1)));
		assertTrue(neighbors.contains(new HantoCoordinateImpl(startX+1,startY-1)));
		
	}
}