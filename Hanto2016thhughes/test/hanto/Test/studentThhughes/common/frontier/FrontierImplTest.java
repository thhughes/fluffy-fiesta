package hanto.Test.studentThhughes.common.frontier;

import static org.junit.Assert.*;

import org.junit.*;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.studentThhughes.common.coordinate.HantoCoordinateImpl;
import hanto.studentThhughes.common.frontier.Frontier;
import hanto.studentThhughes.common.frontier.FrontierImpl;

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
		theFrontier.addToFrontier(null);
	}
	
	@Test(expected=HantoException.class) //1 
	public void removeNothingFromFrontier() throws HantoException{
		theFrontier.removeFromFrontier(null);
	}
	
	@Test //2
	public void addSomethingToFrontier() throws HantoException{
		assertTrue(theFrontier.addToFrontier(C2));
	}
	
	@Test(expected=HantoException.class) //3
	public void removeSomethingThatsNotInFrontier() throws HantoException{
		theFrontier.removeFromFrontier(C2);
	}
	
	@Test // 4
	public void testCheckingANullCoordinateIsInTheFrontier() throws HantoException{
		assertFalse(theFrontier.inFrontier(null));
	}
	
	@Test // 5
	public void checkIfACoordinateIsIntheFrontier() throws HantoException{
		assertTrue(theFrontier.inFrontier(C1));
	}
	
	@Test //6 
	public void RemoveACoordinateFromTheFrontier() throws HantoException{
		assertTrue(theFrontier.removeFromFrontier(C1));
	}
	
	@Test(expected=HantoException.class) //7 
	public void addSomethingToFrontierThatsAlreadythere() throws HantoException{
		theFrontier.addToFrontier(C1);
		theFrontier.addToFrontier(C1);
	}
		
}