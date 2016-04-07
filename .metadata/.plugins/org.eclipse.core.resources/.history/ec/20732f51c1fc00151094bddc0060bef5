package hanto.Test.studentThhughes.common.moveValidator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import hanto.studentThhughes.common.moveValidator.MoveValidator;
import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.studentThhughes.common.coordinate.HantoCoordinateImpl;
import hanto.studentThhughes.common.frontier.Frontier;
import hanto.studentThhughes.common.frontier.FrontierImpl;
import hanto.studentThhughes.common.moveValidator.LocationValidator;

public class LocationValidatorTest {

private static MoveValidator validator;
private static Frontier theFrontier; 
private static HantoCoordinate C1;
private static HantoCoordinate C2;

	@Before
	public void setup(){
		validator = new LocationValidator();
		theFrontier = new FrontierImpl();
		C1 = new HantoCoordinateImpl(0,0);
		C2 = new HantoCoordinateImpl(0,1);
		
	}
	
	@Test // 1
	public void testValidatorWithNoArgs(){
		assertNotNull(validator);
	}
	
	@Test(expected=HantoException.class) // 2
	public void testInvalidError() throws HantoException{
		validator.invalidError();
	}
	
	@Test // 3
	public void testCheckingWithAnEmptyFrontier(){
		assertTrue(validator.isValidMove(null, theFrontier, null, C1));
	}
	
	@Test // 4
	public void testCheckingTakenSpotWithAFrontierOfOneThing() throws HantoException{
		theFrontier.addToFrontier(null, C1);
		assertFalse(validator.isValidMove(null, theFrontier, null, C1));
	}
	
	@Test // 5
	public void testCheckingEmptySpotWithAFrontierOfOneThing() throws HantoException{
		theFrontier.addToFrontier(null, C1);
		assertTrue(validator.isValidMove(null, theFrontier, null, C2));
	}
	
	
	
}
