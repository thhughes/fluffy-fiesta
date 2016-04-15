package hanto.teststudentthhughes.common.turnactionvalidator.placechecker;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentthhughes.common.coordinate.HantoCoordinateImpl;
import hanto.studentthhughes.common.hantoboardandboardtools.HantoBoard;
import hanto.studentthhughes.common.hantoboardandboardtools.HantoBoardImpl;
import hanto.studentthhughes.common.hantopiece.HantoPieceImpl;
import hanto.studentthhughes.common.movecounter.MoveCounter;
import hanto.studentthhughes.common.turnactionvalidator.*;
import hanto.studentthhughes.common.turnactionvalidator.placecheckers.FirstMoveValidator;

public class FristMoveValidatorTest {
	
	
	class dummyCounter implements MoveCounter
	{

		@Override
		public int getNumberMoves(HantoPlayerColor player) {
			return 0;
		}

		@Override
		public void incrementNumberMoves(HantoPlayerColor player) {
			
		}
		
	}
	
	private static HantoBoard theBoard;
	private static MoveCounter counter;
	private static TurnActionValidator mv;
	private static HantoPiece hp1;
	private static HantoPiece hp2;
	private static HantoPiece hp3;
	private static HantoCoordinate hc1;
	private static HantoCoordinate hc2;
	
	
	@Before
	public void setup(){
		theBoard = new HantoBoardImpl();
		counter = new dummyCounter();
		mv = new FirstMoveValidator();
		hp1 = new HantoPieceImpl(HantoPlayerColor.BLUE, HantoPieceType.BUTTERFLY);
		hp2 = new HantoPieceImpl(HantoPlayerColor.RED, HantoPieceType.BUTTERFLY);
		hp3 = new HantoPieceImpl(HantoPlayerColor.BLUE, HantoPieceType.SPARROW);
		hc1 = new HantoCoordinateImpl(0,0);
		hc2 = new HantoCoordinateImpl(0,1);
		
	}
	
	@Test //1 
	public void testNotNull(){
		assertNotNull(new FirstMoveValidator());
	}
	
	@Test //2
	public void testPlacingButterflyAtCenter(){
		assertTrue(mv.isValidMove(theBoard, hp1, counter, hc1, null));
	}
	
	@Test //3
	public void testPlacingSparrowflyAtCenter(){
		assertTrue(mv.isValidMove(theBoard, hp3, counter, hc1, null));
	}
	
	@Test //4
	public void testPlacingSparrowflyAtNotCenter(){
		assertFalse(mv.isValidMove(theBoard, hp3, counter, hc2, null));
	}
	
	@Test(expected=HantoException.class) //5
	public void testInvalidError() throws HantoException{
		mv.invalidError();
	}
	
	

}
