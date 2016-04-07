package hanto.Test.studentThhughes.common;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import hanto.common.HantoPlayerColor;
import hanto.studentThhughes.common.MoveCounter;

public class MoveCounterTest {
	private static MoveCounter mc;
	
	@Before
	public void setup(){
		mc = new MoveCounter();
	}
	
	@Test
	public void testNotNull(){
		assertNotNull(mc);
	}
	
	
	@Test 
	public void testInitialization(){
		assertEquals(mc.getNumberMoves(HantoPlayerColor.RED),
				mc.getNumberMoves(HantoPlayerColor.BLUE));
		assertEquals(0,mc.getNumberMoves(HantoPlayerColor.RED));
	}
	
	@Test
	public void testIncrementing(){
		mc.incrementNumberMoves(HantoPlayerColor.RED);
		mc.incrementNumberMoves(HantoPlayerColor.BLUE);
		assertEquals(mc.getNumberMoves(HantoPlayerColor.RED),
				mc.getNumberMoves(HantoPlayerColor.BLUE));
		assertEquals(1,mc.getNumberMoves(HantoPlayerColor.RED));
	}
}
