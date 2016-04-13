package hanto.teststudentthhughes.common.movevalidator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.studentThhughes.common.coordinate.HantoCoordinateImpl;
import hanto.studentThhughes.common.frontier.Frontier;
import hanto.studentThhughes.common.frontier.FrontierImpl;
import hanto.studentThhughes.common.movevalidator.LocationValidator;
import hanto.studentThhughes.common.movevalidator.MoveValidator;

public class LocationValidatorTest {

private static MoveValidator validator;
private static Frontier theFrontier; 
private static HantoCoordinate C1;
private static HantoCoordinate C2;

//	@Before
//	public void setup(){
//		validator = new LocationValidator();
//		theFrontier = new FrontierImpl();
//		C1 = new HantoCoordinateImpl(0,0);
//		C2 = new HantoCoordinateImpl(0,1);
//		
//	}
//	
//	@Test // 1
//	public void testValidatorWithNoArgs(){
//		assertNotNull(validator);
//	}
//	
//	@Test(expected=HantoException.class) // 2
//	public void testInvalidError() throws HantoException{
//		validator.invalidError();
//	}
//	
//	@Test // 3
//	public void testCheckingStarterFrontierAvailability() throws HantoException{
//		assertTrue(validator.isValidMove(null, null, null, C1, null));
//	}
//	
//	@Test // 3
//	public void testCheckingStarterFrontierAvailabilityOfOffFrontierPlace() throws HantoException{
//		assertFalse(validator.isValidMove(null, null, null, C2, null));
//	}
//	
//	@Test // 5
//	public void testCheckingEmptySpotWithAFrontierOfOneThing() throws HantoException{
//		theFrontier.addToFrontier(C2);
//		assertTrue(validator.isValidMove(null, null, null, C2, null));
//	}
	
	
	
}