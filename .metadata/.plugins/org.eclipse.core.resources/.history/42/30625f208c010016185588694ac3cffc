package hanto.teststudentthhughes.common.hantopiece;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentThhughes.common.hantopiece.HantoPieceImpl;

public class HantoPieceImplTest {
	private static HantoPiece hp;
	
	@Before
	public void setup(){
		hp = new HantoPieceImpl(HantoPlayerColor.BLUE,HantoPieceType.BUTTERFLY);
	}
	
	@Test
	public void testSetters(){
		assertTrue(hp.getColor() == HantoPlayerColor.BLUE);
		assertTrue(hp.getType() == HantoPieceType.BUTTERFLY);
	}
}
