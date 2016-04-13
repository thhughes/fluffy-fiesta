package hanto.teststudentthhughes.common.board;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentthhughes.common.coordinate.HantoCoordinateImpl;
import hanto.studentthhughes.common.hantoboard.HantoBoard;
import hanto.studentthhughes.common.hantoboard.HantoBoardImpl;
import hanto.studentthhughes.common.hantopiece.HantoPieceImpl;

public class BoardImplTest {
	private static HantoBoard theBoard;
	private static HantoPiece PA;
	private static HantoPiece PB;
	private static HantoCoordinate CA;
	private static HantoCoordinate CB;
	
	
	@Before
	public void setup(){
		theBoard = new HantoBoardImpl();
		PA = new HantoPieceImpl(HantoPlayerColor.RED,HantoPieceType.SPARROW);
		PB = new HantoPieceImpl(HantoPlayerColor.RED,HantoPieceType.BUTTERFLY);
		CA = new HantoCoordinateImpl(0,0);
		CB = new HantoCoordinateImpl(0,1);
	}
	
	@Test //1 
	public void testConstructor(){
		assertNotNull(theBoard);
	}
	
	@Test(expected=HantoException.class) // 2
	public void testNullPlaceDestination() throws HantoException{
		theBoard.placeOnBoard(PA, null);
	}
	
	@Test(expected=HantoException.class) // 3
	public void testValidDestinationNullPiece() throws HantoException{
		theBoard.placeOnBoard(null, CA);
	}
	
	@Test(expected=HantoException.class) // 4
	public void testRemovingFromEmptyBoard() throws HantoException{
		theBoard.removeFromBoard(CA);
	}
	
	@Test(expected=HantoException.class) // 4
	public void testRemovingFromEmptyBoardWithNullAsLocation() throws HantoException{
		theBoard.removeFromBoard(null);
	}
	
	@Test // 5
	public void testCorrectlyAddingSomethingToBoard() throws HantoException{
		assertTrue(theBoard.placeOnBoard(PA, CA));
	}
	
	@Test // 5
	public void canAddSomethingToFilledSPot() throws HantoException{
		theBoard.placeOnBoard(PA, CA);
		assertTrue(theBoard.placeOnBoard(PB, CA));
	}
	
	@Test //6
	public void canGetSomethingFromEmptyBoard() throws HantoException{
		assertNull(theBoard.getFromBoard(CA));
	}
	
	@Test //7
	public void canGetSomethingFromBoard() throws HantoException{
		theBoard.placeOnBoard(PA, CA);
		assertEquals(PA.getColor(),theBoard.getFromBoard(CA).getColor());
		assertEquals(PA.getType(),theBoard.getFromBoard(CA).getType());
	}
	
	@Test(expected=HantoException.class) // 8
	public void removePieceThatsNotOnBoard() throws HantoException{
		theBoard.placeOnBoard(PA, CA);
		theBoard.removeFromBoard(CB);
	}
	
	@Test //9
	public void removePieceFromBoard() throws HantoException{
		theBoard.placeOnBoard(PA, CA);
		assertTrue(theBoard.removeFromBoard(CA));
	}
	
	@Test //10
	public void boardIsEmptyAndLocationsFree(){
		assertFalse(theBoard.isLocationOccupied(CA));
	}
	
	@Test //10
	public void boardIsNotEmptyAndEmptyLocationIsQueried() throws HantoException{
		theBoard.placeOnBoard(PA, CB);
		assertFalse(theBoard.isLocationOccupied(CA));
	}
	
	@Test //10
	public void boardIsNotEmptyAndOccupiedLocationIsQueried() throws HantoException{
		theBoard.placeOnBoard(PA, CB);
		assertTrue(theBoard.isLocationOccupied(CB));
	}
	
	@Test// 11
	public void testCopyConstructor() throws HantoException{
		theBoard.placeOnBoard(PA, CB);
		HantoBoard newBoard = new HantoBoardImpl(theBoard);
		assertTrue(newBoard.isLocationOccupied(CB));
		assertFalse(newBoard.isLocationOccupied(CA));
		assertEquals(theBoard.getFromBoard(CB).getColor(),
				newBoard.getFromBoard(CB).getColor());
		assertEquals(theBoard.getFromBoard(CB).getType(),
				newBoard.getFromBoard(CB).getType());
		
		
	}
}
