package hanto.teststudentthhughes.common;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import hanto.common.HantoPlayerColor;
import hanto.studentThhughes.common.movecounter.MoveCounterImpl;

public class MoveCounterTest {
	private static MoveCounterImpl mc;
	
	@Before
	public void setup(){
		mc = new MoveCounterImpl();
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
