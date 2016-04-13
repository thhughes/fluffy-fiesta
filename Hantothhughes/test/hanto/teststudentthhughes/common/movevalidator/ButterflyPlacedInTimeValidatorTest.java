package hanto.teststudentthhughes.common.movevalidator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentThhughes.common.board.Board;
import hanto.studentThhughes.common.board.BoardImpl;
import hanto.studentThhughes.common.coordinate.HantoCoordinateImpl;
import hanto.studentThhughes.common.hantopiece.HantoPieceImpl;
import hanto.studentThhughes.common.movecounter.MoveCounter;
import hanto.studentThhughes.common.movecounter.MoveCounterImpl;
import hanto.studentThhughes.common.movevalidator.ButterflyPlacedInTimeValidator;
import hanto.studentThhughes.common.movevalidator.MoveValidator;

public class ButterflyPlacedInTimeValidatorTest {
	
	class dummyCounter implements MoveCounter
	{

		@Override
		public int getNumberMoves(HantoPlayerColor player) {
			return 1;
		}

		@Override
		public void incrementNumberMoves(HantoPlayerColor player) {
			
		}
	}

	Board theBoard;
	MoveValidator mv;
	MoveCounter mc;
	
	@Before
	public void setup() throws HantoException{
		mc = new dummyCounter();
		theBoard = new BoardImpl();
		theBoard.placeOnBoard(new HantoPieceImpl(HantoPlayerColor.BLUE,HantoPieceType.SPARROW), 
				new HantoCoordinateImpl(0,0));
		theBoard.placeOnBoard(new HantoPieceImpl(HantoPlayerColor.RED,HantoPieceType.SPARROW), 
				new HantoCoordinateImpl(0,1));
	}
	
	@Test
	public void testNotNull(){
		assertNotNull(new ButterflyPlacedInTimeValidator(1));
	}
	
	@Test
	public void testAddingButterflyInTime() throws HantoException
	{
		mv = new ButterflyPlacedInTimeValidator(2);
		assertTrue(mv.isValidMove(theBoard, new HantoPieceImpl(HantoPlayerColor.BLUE,HantoPieceType.BUTTERFLY),
				mc, new HantoCoordinateImpl(1,0), null));
	}
	
	@Test
	public void testNotAddingButterflyWhenItsTheLastTurn() throws HantoException
	{
		mv = new ButterflyPlacedInTimeValidator(1);
		assertFalse(mv.isValidMove(theBoard, new HantoPieceImpl(HantoPlayerColor.BLUE,HantoPieceType.SPARROW),
				mc, new HantoCoordinateImpl(1,0), null));
	}
	
	@Test
	public void testAddingPieceToThingThatAlreadyHasButterfly() throws HantoException
	{
		theBoard.placeOnBoard(new HantoPieceImpl(HantoPlayerColor.BLUE,HantoPieceType.BUTTERFLY), 
				new HantoCoordinateImpl(-1,1));
		theBoard.placeOnBoard(new HantoPieceImpl(HantoPlayerColor.RED,HantoPieceType.BUTTERFLY), 
				new HantoCoordinateImpl(-2,1));
		mv = new ButterflyPlacedInTimeValidator(2);
		assertTrue(mv.isValidMove(theBoard, new HantoPieceImpl(HantoPlayerColor.BLUE,HantoPieceType.SPARROW),
				mc, new HantoCoordinateImpl(1,0), null));
	}
	
	@Test(expected=HantoException.class)
	public void testInvalidError() throws HantoException
	{
		(new ButterflyPlacedInTimeValidator(1)).invalidError();
	}
	
	
	
}