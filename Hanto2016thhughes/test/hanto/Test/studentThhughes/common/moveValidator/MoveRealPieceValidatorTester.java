package hanto.Test.studentThhughes.common.moveValidator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import hanto.common.HantoException;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentThhughes.common.board.Board;
import hanto.studentThhughes.common.board.BoardImpl;
import hanto.studentThhughes.common.coordinate.HantoCoordinateImpl;
import hanto.studentThhughes.common.hantoPiece.HantoPieceImpl;
import hanto.studentThhughes.common.moveCounter.MoveCounterImpl;
import hanto.studentThhughes.common.moveValidator.MoveRealPieceValidator;

public class MoveRealPieceValidatorTester {

	Board theBoard;
	
	
	@Before
	public void setup() throws HantoException{
		theBoard = new BoardImpl();
		theBoard.placeOnBoard(new HantoPieceImpl(HantoPlayerColor.BLUE,HantoPieceType.BUTTERFLY),
				new HantoCoordinateImpl(0,0));
	}
	
	@Test
	public void testNotNull(){
		assertNotNull(new MoveRealPieceValidator());
	}
	
	@Test(expected=HantoException.class)
	public void testInvalidError() throws HantoException
	{
		(new MoveRealPieceValidator()).invalidError();
	}
	
	@Test
	public void testPlacingAPiece() throws HantoException
	{
		boolean result = (new MoveRealPieceValidator()).isValidMove(theBoard,
				new HantoPieceImpl(HantoPlayerColor.BLUE,HantoPieceType.BUTTERFLY), 
				null, 
				new HantoCoordinateImpl(0,1), null);
		assertTrue(result);
	}
	
	
	@Test
	public void testMovingAPieceThatDoesntExist() throws HantoException
	{
		boolean result = (new MoveRealPieceValidator()).isValidMove(theBoard,
				new HantoPieceImpl(HantoPlayerColor.BLUE,HantoPieceType.CRAB), 
				new MoveCounterImpl(), 
				new HantoCoordinateImpl(0,1), new HantoCoordinateImpl(0,0));
		assertFalse(result);
	}
	
	@Test
	public void testMovingAPieceThatIsLocatedInAnOccupiedPlace() throws HantoException
	{
		boolean result = (new MoveRealPieceValidator()).isValidMove(theBoard,
				new HantoPieceImpl(HantoPlayerColor.BLUE,HantoPieceType.BUTTERFLY), 
				new MoveCounterImpl(), 
				new HantoCoordinateImpl(0,1), new HantoCoordinateImpl(0,0));
		assertFalse(result);
	}
	
	@Test
	public void testMovingAPieceThatIsntTheTypeAtAnOccupiedLocation() throws HantoException
	{
		boolean result = (new MoveRealPieceValidator()).isValidMove(theBoard,
				new HantoPieceImpl(HantoPlayerColor.BLUE,HantoPieceType.CRAB), 
				new MoveCounterImpl(), 
				new HantoCoordinateImpl(0,1), new HantoCoordinateImpl(0,0));
		assertFalse(result);
	}
	
	
}

