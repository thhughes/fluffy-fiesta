package hanto.teststudentthhughes.common.movevalidator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import hanto.common.HantoException;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentthhughes.common.coordinate.HantoCoordinateImpl;
import hanto.studentthhughes.common.hantoboard.HantoBoard;
import hanto.studentthhughes.common.hantoboard.HantoBoardImpl;
import hanto.studentthhughes.common.hantopiece.HantoPieceImpl;
import hanto.studentthhughes.common.movecounter.MoveCounterImpl;
import hanto.studentthhughes.common.movevalidator.MoveRealPieceValidator;

public class MoveRealPieceValidatorTester {

	HantoBoard theBoard;
	
	
	@Before
	public void setup() throws HantoException{
		theBoard = new HantoBoardImpl();
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
		assertTrue(result);
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

