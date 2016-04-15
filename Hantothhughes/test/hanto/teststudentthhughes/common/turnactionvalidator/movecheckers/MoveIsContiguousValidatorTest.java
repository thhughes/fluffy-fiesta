package hanto.teststudentthhughes.common.turnactionvalidator.movecheckers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import hanto.common.HantoException;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentthhughes.common.coordinate.HantoCoordinateImpl;
import hanto.studentthhughes.common.hantoboardandboardtools.HantoBoard;
import hanto.studentthhughes.common.hantoboardandboardtools.HantoBoardImpl;
import hanto.studentthhughes.common.hantopiece.HantoPieceImpl;
import hanto.studentthhughes.common.movecounter.MoveCounterImpl;
import hanto.studentthhughes.common.turnactionvalidator.TurnActionValidator;
import hanto.studentthhughes.common.turnactionvalidator.movecheckers.MoveIsContiguousValidator;

public class MoveIsContiguousValidatorTest {

	HantoBoard theBoard;
	TurnActionValidator tav;
	HantoPiece hpt1;
	HantoPiece hpt2;
	
	@Before
	public void setup() throws HantoException{
		hpt1 = new HantoPieceImpl(HantoPlayerColor.BLUE,HantoPieceType.BUTTERFLY);
		hpt2 = new HantoPieceImpl(HantoPlayerColor.RED,HantoPieceType.BUTTERFLY);
		theBoard = new HantoBoardImpl();
		theBoard.placeOnBoard(hpt1, new HantoCoordinateImpl(0,0));
		tav = new MoveIsContiguousValidator();
	}
	
	@Test
	public void testPlacingPieceOnContiguosFromFarAway(){
		assertTrue(tav.isValidMove(theBoard, hpt2, new MoveCounterImpl(), new HantoCoordinateImpl(0,1), new HantoCoordinateImpl(2,0)));
	}
	
	@Test
	public void testPlacingPieceOnContiguosFromCloseUp(){
		assertTrue(tav.isValidMove(theBoard, hpt2, new MoveCounterImpl(), new HantoCoordinateImpl(0,1), new HantoCoordinateImpl(1,1)));
	}
	
	@Test
	public void testPlacingPieceOnNonContiguousLocation(){
		assertFalse(tav.isValidMove(theBoard, hpt2, new MoveCounterImpl(), new HantoCoordinateImpl(-2,0), new HantoCoordinateImpl(2,0)));
	}
	
	@Test(expected=HantoException.class)
	public void testInvalidErrorException() throws HantoException{
		tav.invalidError();
	}
	
}
