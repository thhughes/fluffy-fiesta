package hanto.teststudentthhughes.common.movevalidator;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.Test;

import static hanto.common.HantoPieceType.*;

import hanto.common.*;
import hanto.studentthhughes.common.hantopiece.HantoPieceImpl;
import hanto.studentthhughes.common.movevalidator.MoveValidator;
import hanto.studentthhughes.common.movevalidator.PieceValidator;

public class PieceValidatorTest {

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
		assertTrue(validator.isValidMove(null, new HantoPieceImpl(HantoPlayerColor.BLUE,BUTTERFLY), null, null, null));

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
