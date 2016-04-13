package hanto.teststudentthhughes.common.movevalidator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import hanto.common.HantoException;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentThhughes.common.board.Board;
import hanto.studentThhughes.common.board.BoardImpl;
import hanto.studentThhughes.common.coordinate.HantoCoordinateImpl;
import hanto.studentThhughes.common.hantopiece.HantoPieceImpl;
import hanto.studentThhughes.common.movevalidator.ButterflyChecker;
import hanto.studentThhughes.common.movevalidator.MoveValidator;

public class ButterflyCheckerTest {

	Board theBoard;
	MoveValidator mv;
	
	@Before
	public void setup() throws HantoException{
		theBoard = new BoardImpl();
		mv = new ButterflyChecker();
		theBoard.placeOnBoard(new HantoPieceImpl(HantoPlayerColor.BLUE,HantoPieceType.BUTTERFLY), 
				new HantoCoordinateImpl(0,0));
	}
	
	@Test
	public void testNotNull(){
		assertNotNull(new ButterflyChecker());
	}
	
	@Test
	public void testMovePiece(){
		assertTrue(mv.isValidMove(theBoard, new HantoPieceImpl(HantoPlayerColor.BLUE,HantoPieceType.BUTTERFLY),
				null, new HantoCoordinateImpl(0,1), new HantoCoordinateImpl(0,0)));
	}
	
	@Test
	public void testPlaceSparrow(){
		assertTrue(mv.isValidMove(theBoard, new HantoPieceImpl(HantoPlayerColor.BLUE,HantoPieceType.SPARROW),
				null, new HantoCoordinateImpl(0,1), null));
	}
	
	@Test
	public void testPlaceSecondButterfly(){
		assertFalse(mv.isValidMove(theBoard, new HantoPieceImpl(HantoPlayerColor.BLUE,HantoPieceType.BUTTERFLY),
				null, new HantoCoordinateImpl(0,1), null));
	}
	
	@Test (expected=HantoException.class)
	public void testInvalidError() throws HantoException{
		mv.invalidError();
	}
	
}