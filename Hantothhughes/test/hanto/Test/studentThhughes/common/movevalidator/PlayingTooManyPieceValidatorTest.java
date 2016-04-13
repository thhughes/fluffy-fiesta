package hanto.Test.studentThhughes.common.movevalidator;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import hanto.Test.studentThhughes.common.board.TestableBoardImpl;
import hanto.common.HantoException;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentThhughes.common.board.Board;
import hanto.studentThhughes.common.board.BoardImpl;
import hanto.studentThhughes.common.coordinate.HantoCoordinateImpl;
import hanto.studentThhughes.common.hantopiece.HantoPieceImpl;
import hanto.studentThhughes.common.movecounter.MoveCounter;
import hanto.studentThhughes.common.movevalidator.MoveValidator;
import hanto.studentThhughes.common.movevalidator.PlayingTooManyPieceValidator;

public class PlayingTooManyPieceValidatorTest {
	
	private static Board theBoard;
	private static MoveValidator mv;
	private static MoveCounter mc;
	
	@Before
	public void setup() throws HantoException{
		theBoard = new BoardImpl();
		mv = new PlayingTooManyPieceValidator(2);
		
	}
	
	@Test
	public void testAddingOnePieceToEmptyBoard() throws HantoException{
		HantoPiece piece = new HantoPieceImpl(HantoPlayerColor.BLUE, HantoPieceType.BUTTERFLY);
		theBoard.placeOnBoard(piece, new HantoCoordinateImpl(0,0));
		assertTrue(mv.isValidMove(theBoard, piece, null, new HantoCoordinateImpl(0,1), null));
	}
	
	@Test
	public void testAddingEnoughPieceToEmptyBoardToError() throws HantoException{
		HantoPiece piece = new HantoPieceImpl(HantoPlayerColor.BLUE, HantoPieceType.BUTTERFLY);
		theBoard.placeOnBoard(piece, new HantoCoordinateImpl(0,0));
		theBoard.placeOnBoard(piece, new HantoCoordinateImpl(0,1));
		assertFalse(mv.isValidMove(theBoard, piece, null, new HantoCoordinateImpl(0,1), null));
	}
	
	@Test
	public void testMovingAPiece() throws HantoException{
		HantoPiece piece = new HantoPieceImpl(HantoPlayerColor.BLUE, HantoPieceType.BUTTERFLY);
		theBoard.placeOnBoard(piece, new HantoCoordinateImpl(0,0));
		assertTrue(mv.isValidMove(theBoard, piece, null,new HantoCoordinateImpl(0,0), new HantoCoordinateImpl(0,1)));
	}
	
	@Test(expected=HantoException.class)
	public void testInvalidError() throws HantoException{
		mv.invalidError();
	}
	
	

}
