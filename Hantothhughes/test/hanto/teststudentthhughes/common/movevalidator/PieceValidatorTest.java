package hanto.teststudentthhughes.common.movevalidator;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import org.junit.Test;

import static hanto.common.HantoPieceType.*;

import hanto.common.*;
import hanto.studentthhughes.common.coordinate.HantoCoordinateImpl;
import hanto.studentthhughes.common.hantoboard.HantoBoard;
import hanto.studentthhughes.common.hantopiece.HantoPieceImpl;
import hanto.studentthhughes.common.movevalidator.MoveValidator;
import hanto.studentthhughes.common.movevalidator.PieceValidator;

public class PieceValidatorTest {
	
	class dummyBoard implements HantoBoard{

		Map<HantoCoordinate, HantoPiece> playerMap = new HashMap<HantoCoordinate, HantoPiece>();
		
		public dummyBoard(){
			playerMap.put(new HantoCoordinateImpl(0,0), new HantoPieceImpl(HantoPlayerColor.BLUE,BUTTERFLY));
			playerMap.put(new HantoCoordinateImpl(0,1), new HantoPieceImpl(HantoPlayerColor.BLUE,SPARROW));
		}
		@Override
		public boolean placeOnBoard(HantoPiece piece, HantoCoordinate where) throws HantoException {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public HantoPiece getFromBoard(HantoCoordinate where) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean isLocationOccupied(HantoCoordinate where) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean removeFromBoard(HantoCoordinate where) throws HantoException {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Map<HantoCoordinate, HantoPiece> getPlayerPieces(HantoPlayerColor color) {
			
			return playerMap;
		}
		
	}

	private static MoveValidator validator;
	
	@Test // 1
	public void testValidatorWithNoArgs(){
		validator = new PieceValidator();
		assertNotNull(validator);
	}
	
	@Test // 2 
	public void testValidatorWithEmptyList(){
		validator = new PieceValidator(new LinkedList<HantoPieceType>());
		assertFalse(validator.isValidMove(null, new HantoPieceImpl(HantoPlayerColor.BLUE,BUTTERFLY), null, null, null));
	}
	
	@Test //3 
	public void testValidatorWithItemsInListAndValidMove(){
		Queue<HantoPieceType> validList = new LinkedList<HantoPieceType>();
		validList.add(BUTTERFLY);
		validList.add(SPARROW);
		validator = new PieceValidator(validList);
		assertTrue(validator.isValidMove(new dummyBoard(), new HantoPieceImpl(HantoPlayerColor.BLUE,BUTTERFLY), null, null, null));

	}
	
	@Test //4 
	public void testValidatorWithItemsInListAndINValidMove(){
		Queue<HantoPieceType> validList = new LinkedList<HantoPieceType>();
		validList.add(BUTTERFLY);
		validList.add(SPARROW);
		validator = new PieceValidator(validList);
		assertFalse(validator.isValidMove(null, new HantoPieceImpl(HantoPlayerColor.BLUE,CRAB), null, null, null));

	}
	
	@Test(expected=HantoException.class) //5
	public void testFailureMessage() throws HantoException{
		validator = new PieceValidator();
		validator.invalidError();
	}
		
	
	
}
