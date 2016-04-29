package hanto.teststudentthhughes.common;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentthhughes.common.MoveData;
import hanto.studentthhughes.common.coordinate.HantoCoordinateImpl;
import hanto.studentthhughes.common.hantopiece.HantoPieceImpl;

public class MoveDataTest {

	MoveData toTest;
	HantoPiece piece;
	HantoCoordinate to;
	HantoCoordinate from;
	
	@Before
	public void setup(){
		piece = new HantoPieceImpl(HantoPlayerColor.BLUE, HantoPieceType.BUTTERFLY);
		to = new HantoCoordinateImpl(0,0);
		from = new HantoCoordinateImpl(0,1);
		toTest = new MoveData(piece, to, from);

	}
	
	@Test
	public void checkThatDataIsSet(){
		assertEquals(toTest.getTo().getX(),to.getX());
		assertEquals(toTest.getTo().getY(),to.getY());
		assertEquals(toTest.getFrom().getX(),from.getX());
		assertEquals(toTest.getFrom().getY(),from.getY());
		assertEquals(toTest.getPiece().getColor(), piece.getColor());
		assertEquals(toTest.getPiece().getType(), piece.getType());
		
		
	}
	
	
}
