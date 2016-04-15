package hanto.teststudentthhughes.common.turnactionvalidator.placechecker;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import hanto.common.HantoException;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentthhughes.common.coordinate.HantoCoordinateImpl;
import hanto.studentthhughes.common.hantoboardandboardtools.HantoBoard;
import hanto.studentthhughes.common.hantoboardandboardtools.HantoBoardImpl;
import hanto.studentthhughes.common.hantopiece.HantoPieceImpl;
import hanto.studentthhughes.common.movecounter.MoveCounterImpl;
import hanto.studentthhughes.common.turnactionvalidator.TurnActionValidator;
import hanto.studentthhughes.common.turnactionvalidator.placecheckers.CorrectNumberOfPieceTypeValidator;

public class NumberOfPiecesOfSpecificTypeValidatorTest {

	HantoBoard theBoard;
	TurnActionValidator mv;
	
	@Before
	public void setup() throws HantoException{
		theBoard = new HantoBoardImpl();
		mv = new CorrectNumberOfPieceTypeValidator(HantoPieceType.BUTTERFLY,1);
		theBoard.placeOnBoard(new HantoPieceImpl(HantoPlayerColor.BLUE,HantoPieceType.BUTTERFLY), 
				new HantoCoordinateImpl(0,0));
	}
	
	@Test
	public void testNotNull(){
		assertNotNull(new CorrectNumberOfPieceTypeValidator(HantoPieceType.BUTTERFLY,1));
	}
	
	@Test
	public void testMovePiece(){
		assertTrue(mv.isValidMove(theBoard, new HantoPieceImpl(HantoPlayerColor.BLUE,HantoPieceType.BUTTERFLY),
				new MoveCounterImpl(), new HantoCoordinateImpl(0,1), new HantoCoordinateImpl(0,0)));
	}
	
	
	@Test
	public void testPlaceSecondButterfly(){
		assertFalse(mv.isValidMove(theBoard, new HantoPieceImpl(HantoPlayerColor.BLUE,HantoPieceType.BUTTERFLY),
				new MoveCounterImpl(), new HantoCoordinateImpl(0,1), null));
	}
	
	@Test (expected=HantoException.class)
	public void testInvalidError() throws HantoException{
		mv.invalidError();
	}
	
	@Test
	public void testPlaceFirstSparrow(){
		mv = new CorrectNumberOfPieceTypeValidator(HantoPieceType.SPARROW,1);
		assertTrue(mv.isValidMove(theBoard, new HantoPieceImpl(HantoPlayerColor.BLUE,HantoPieceType.SPARROW),
				new MoveCounterImpl(), new HantoCoordinateImpl(0,1), null));
	}
	
	public void testPlaceSecondSparrow() throws HantoException{
		mv = new CorrectNumberOfPieceTypeValidator(HantoPieceType.SPARROW,2);
		theBoard.placeOnBoard(new HantoPieceImpl(HantoPlayerColor.BLUE,HantoPieceType.SPARROW),new HantoCoordinateImpl(0,1));
		assertTrue(mv.isValidMove(theBoard, new HantoPieceImpl(HantoPlayerColor.BLUE,HantoPieceType.SPARROW),
				new MoveCounterImpl(), new HantoCoordinateImpl(0,1), null));
	}
	
	public void testPlaceThirdSparrow() throws HantoException{
		mv = new CorrectNumberOfPieceTypeValidator(HantoPieceType.SPARROW,2);
		theBoard.placeOnBoard(new HantoPieceImpl(HantoPlayerColor.BLUE,HantoPieceType.SPARROW),new HantoCoordinateImpl(0,1));
		theBoard.placeOnBoard(new HantoPieceImpl(HantoPlayerColor.BLUE,HantoPieceType.SPARROW),new HantoCoordinateImpl(0,2));
		assertFalse(mv.isValidMove(theBoard, new HantoPieceImpl(HantoPlayerColor.BLUE,HantoPieceType.SPARROW),
				new MoveCounterImpl(), new HantoCoordinateImpl(0,1), null));
	}
	
	
	
}