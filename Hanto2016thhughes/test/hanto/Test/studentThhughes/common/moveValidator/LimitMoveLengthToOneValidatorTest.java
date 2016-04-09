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
import hanto.studentThhughes.common.moveValidator.LimitMoveLengthToOneValidator;
import hanto.studentThhughes.common.moveValidator.MoveValidator;

public class LimitMoveLengthToOneValidatorTest {

	Board theBoard;
	MoveValidator mv;
	
	@Before
	public void setup(){
		theBoard = new BoardImpl();
		mv = new LimitMoveLengthToOneValidator();
	}
	
	@Test
	public void testNotNull(){
		assertNotNull(new LimitMoveLengthToOneValidator());
	}
	
	@Test
	public void placePieceNoMove() throws HantoException
	{
		assertTrue(mv.isValidMove(theBoard, new HantoPieceImpl(HantoPlayerColor.BLUE, HantoPieceType.BUTTERFLY),
				null, 
				new HantoCoordinateImpl(0,0), null));
	}
	
	@Test
	public void movePieceOneLength() throws HantoException
	{
		assertTrue(mv.isValidMove(theBoard, new HantoPieceImpl(HantoPlayerColor.BLUE, HantoPieceType.BUTTERFLY),
				null, 
				new HantoCoordinateImpl(0,0), new HantoCoordinateImpl(0,1)));
	}
	
	@Test
	public void movePieceTwoLength() throws HantoException
	{
		assertFalse(mv.isValidMove(theBoard, new HantoPieceImpl(HantoPlayerColor.BLUE, HantoPieceType.BUTTERFLY),
				null, 
				new HantoCoordinateImpl(0,0), new HantoCoordinateImpl(0,2)));
	}
	
	@Test(expected=HantoException.class)
	public void testInvalidError() throws HantoException
	{
		mv.invalidError();
	}
}
