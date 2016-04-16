package hanto.teststudentthhughes.common.frontier;

import static org.junit.Assert.*;

import org.junit.*;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.studentthhughes.common.coordinate.HantoCoordinateImpl;
import hanto.studentthhughes.common.frontier.Frontier;
import hanto.studentthhughes.common.frontier.FrontierImpl;

public class FrontierImplTest {

	public static Frontier theFrontier;
	public static HantoCoordinate C1;
	public static HantoCoordinate C2;
	
	@Before
	public void setup(){
		theFrontier = new FrontierImpl();
		C1 = new HantoCoordinateImpl(0,0);
		C2 = new HantoCoordinateImpl(1,1);
	}
	
	@Test(expected=HantoException.class) //1 
	public void addNothingToTheFrontier() throws HantoException{
		theFrontier.canAddToFrontier(null);
	}
	
	@Test(expected=HantoException.class) //1 
	public void removeNothingFromFrontier() throws HantoException{
		theFrontier.canRemoveFromFrontier(null);
	}
	
	@Test //2
	public void addSomethingToFrontier() throws HantoException{
		assertTrue(theFrontier.canAddToFrontier(C2));
	}
	
	@Test(expected=HantoException.class) //3
	public void removeSomethingThatsNotInFrontier() throws HantoException{
		theFrontier.canRemoveFromFrontier(C2);
	}
	
	@Test // 4
	public void testCheckingANullCoordinateIsInTheFrontier() throws HantoException{
		assertFalse(theFrontier.isInFrontier(null));
	}
	
	@Test // 5
	public void checkIfACoordinateIsIntheFrontier() throws HantoException{
		assertTrue(theFrontier.isInFrontier(C1));
	}
	
	@Test //6 
	public void RemoveACoordinateFromTheFrontier() throws HantoException{
		assertTrue(theFrontier.canRemoveFromFrontier(C1));
	}
	
	@Test(expected=HantoException.class) //7 
	public void addSomethingToFrontierThatsAlreadythere() throws HantoException{
		theFrontier.canAddToFrontier(C1);
		theFrontier.canAddToFrontier(C1);
	}
		
}
