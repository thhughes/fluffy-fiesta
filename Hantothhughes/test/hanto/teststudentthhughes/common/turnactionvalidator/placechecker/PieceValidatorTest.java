package hanto.teststudentthhughes.common.turnactionvalidator.placechecker;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import org.junit.Test;

import static hanto.common.HantoPieceType.*;

import hanto.common.*;
import hanto.studentthhughes.common.coordinate.HantoCoordinateImpl;
import hanto.studentthhughes.common.hantoboardandboardtools.HantoBoard;
import hanto.studentthhughes.common.hantopiece.HantoPieceImpl;
import hanto.studentthhughes.common.movecounter.MoveCounterImpl;
import hanto.studentthhughes.common.turnactionvalidator.TurnActionValidator;
import hanto.studentthhughes.common.turnactionvalidator.moveandplacechecks.GeneralPieceValidator;

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

	private static TurnActionValidator validator;
	
	@Test // 1
	public void testValidatorWithNoArgs(){
		validator = new GeneralPieceValidator();
		assertNotNull(validator);
	}
	
	@Test // 2 
	public void testValidatorWithEmptyList(){
		validator = new GeneralPieceValidator(new LinkedList<HantoPieceType>());
		assertFalse(validator.isValidMove(null, new HantoPieceImpl(HantoPlayerColor.BLUE,BUTTERFLY), 
				new MoveCounterImpl(), new HantoCoordinateImpl(0,0), null));
	}
	
	@Test //3 
	public void testValidatorWithItemsInListAndValidMove(){
		Queue<HantoPieceType> validList = new LinkedList<HantoPieceType>();
		validList.add(BUTTERFLY);
		validList.add(SPARROW);
		validator = new GeneralPieceValidator(validList);
		assertTrue(validator.isValidMove(new dummyBoard(), new HantoPieceImpl(HantoPlayerColor.BLUE,BUTTERFLY), 
				new MoveCounterImpl(), new HantoCoordinateImpl(0,0), null));

	}
	
	@Test //4 
	public void testValidatorWithItemsInListAndINValidMove(){
		Queue<HantoPieceType> validList = new LinkedList<HantoPieceType>();
		validList.add(BUTTERFLY);
		validList.add(SPARROW);
		validator = new GeneralPieceValidator(validList);
		assertFalse(validator.isValidMove(new dummyBoard(), new HantoPieceImpl(HantoPlayerColor.BLUE,CRAB), 
				new MoveCounterImpl(), 
				new HantoCoordinateImpl(0,0), null));

	}
	
	@Test(expected=HantoException.class) //5
	public void testFailureMessage() throws HantoException{
		validator = new GeneralPieceValidator();
		validator.invalidError();
	}
		
	
	
}
