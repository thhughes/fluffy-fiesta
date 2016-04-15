package hanto.teststudentthhughes.common.turnactionvalidator.moveandplacecheckers;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.Before;
import org.junit.Test;
import static hanto.common.HantoPieceType.*;
import static hanto.common.HantoPlayerColor.*;

import hanto.common.HantoException;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentthhughes.common.coordinate.HantoCoordinateImpl;
import hanto.studentthhughes.common.hantoboardandboardtools.HantoBoard;
import hanto.studentthhughes.common.hantoboardandboardtools.HantoBoardImpl;
import hanto.studentthhughes.common.hantopiece.HantoPieceImpl;
import hanto.studentthhughes.common.movecounter.MoveCounter;
import hanto.studentthhughes.common.turnactionvalidator.TurnActionValidator;
import hanto.studentthhughes.common.turnactionvalidator.moveandplacechecks.GeneralActionValidator;


public class GeneralActionValidatorTest {

	
	class dummyCounter implements MoveCounter{
		int count = 1;
		@Override
		public int getNumberMoves(HantoPlayerColor player) {
			return count;
		}

		@Override
		public void incrementNumberMoves(HantoPlayerColor player) {
			
		}
		
		public void changeCount(int c){
			count = c;
		}
		
	}
	
	HantoBoard theBoard;
	TurnActionValidator tav;
	HantoPiece hpt1;
	HantoPiece hpt2;
	Queue<HantoPieceType> validPieces;
	dummyCounter mc;
	
	@Before
	public void setup() throws HantoException{
		validPieces = new LinkedList<HantoPieceType>();
		validPieces.add(BUTTERFLY);
		validPieces.add(SPARROW);
		validPieces.add(CRAB);
		
		hpt1 = new HantoPieceImpl(BLUE,BUTTERFLY);
		hpt2 = new HantoPieceImpl(RED,BUTTERFLY);
		theBoard = new HantoBoardImpl();
		theBoard.placeOnBoard(hpt1, new HantoCoordinateImpl(0,0));
		theBoard.placeOnBoard(hpt2, new HantoCoordinateImpl(0,1));
		
		mc  =  new dummyCounter();
		
		tav = new GeneralActionValidator(validPieces);
	}
	// Placing a piece tests:
	@Test
	public void testPlacingAValidPieceSparrow(){
		assertTrue(tav.isValidMove(theBoard, new HantoPieceImpl(BLUE,SPARROW),
				mc, new HantoCoordinateImpl(0,-1), null));
	}
	
	@Test
	public void testPlacingAValidPieceCrab(){
		assertTrue(tav.isValidMove(theBoard, new HantoPieceImpl(BLUE,CRAB),
				mc, new HantoCoordinateImpl(0,-1), null));
	}
	
	@Test
	public void testPlacingINValidPieceCrab(){
		assertFalse(tav.isValidMove(theBoard, new HantoPieceImpl(BLUE,CRANE),
				mc, new HantoCoordinateImpl(0,-1), null));
	}
	
	@Test
	public void testPlacingNextToTeammatePiece(){
		assertTrue(tav.isValidMove(theBoard, new HantoPieceImpl(BLUE,CRAB),
				mc, new HantoCoordinateImpl(0,-1), null));
	}
	
	@Test
	public void testPlacingNextToOpponentPiece(){
		assertFalse(tav.isValidMove(theBoard, new HantoPieceImpl(RED,CRAB),
				mc, new HantoCoordinateImpl(0,-1), null));
	}
	
	@Test
	public void testPlacingNextToOpponentPieceWhenCounterSyasItsFirstTurn(){
		mc.changeCount(0);
		assertTrue(tav.isValidMove(theBoard, new HantoPieceImpl(RED,CRAB),
				mc, new HantoCoordinateImpl(0,-1), null));
	}
	
	@Test
	public void testMoveingPieceToEmptyLocation(){
		assertTrue(tav.isValidMove(theBoard, new HantoPieceImpl(BLUE,BUTTERFLY),
				mc, new HantoCoordinateImpl(1,0), new HantoCoordinateImpl(0,0)));
	}
	
	@Test
	public void testMoveingPieceToOccupiedLocation(){
		assertFalse(tav.isValidMove(theBoard, new HantoPieceImpl(BLUE,BUTTERFLY),
				mc, new HantoCoordinateImpl(0,1), new HantoCoordinateImpl(0,0)));
	}
	
	@Test
	public void testMoveingPieceWithDifferentColor(){
		assertFalse(tav.isValidMove(theBoard, new HantoPieceImpl(RED,BUTTERFLY),
				mc, new HantoCoordinateImpl(1,0), new HantoCoordinateImpl(0,0)));
	}
	
	@Test
	public void testMoveingPieceWithDifferentType(){
		assertFalse(tav.isValidMove(theBoard, new HantoPieceImpl(BLUE,CRAB),
				mc, new HantoCoordinateImpl(1,0), new HantoCoordinateImpl(0,0)));
	}
	
	@Test
	public void testMoveingFromLocationThatHasNoPiece(){
		assertFalse(tav.isValidMove(theBoard, new HantoPieceImpl(BLUE,BUTTERFLY),
				mc, new HantoCoordinateImpl(1,1), new HantoCoordinateImpl(1,0)));
	}
	
	@Test
	public void testMovingPieceToContiguousPiece(){
		assertTrue(tav.isValidMove(theBoard, new HantoPieceImpl(BLUE,BUTTERFLY),
				mc, new HantoCoordinateImpl(1,0), new HantoCoordinateImpl(0,0)));
	}
	
	@Test
	public void testMovingPieceToNonContiguousHex(){
		assertFalse(tav.isValidMove(theBoard, new HantoPieceImpl(BLUE,BUTTERFLY),
				mc, new HantoCoordinateImpl(2,0), new HantoCoordinateImpl(0,0)));
	}
	
	@Test(expected=HantoException.class)
	public void testInvalidErrorException() throws HantoException{
		tav.invalidError();
	}
	
}
