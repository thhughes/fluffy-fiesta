package hanto.Test.studentThhughes.common.board;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentThhughes.common.hantoPiece.HantoPieceImpl;
import hanto.studentThhughes.common.board.Board;
import hanto.studentThhughes.common.board.BoardImpl;
import hanto.studentThhughes.common.coordinate.HantoCoordinateImpl;

public class BoardImplTest {
	private static Board theBoard;
	private static HantoPiece PA;
	private static HantoPiece PB;
	private static HantoCoordinate CA;
	private static HantoCoordinate CB;
	
	
	@Before
	public void setup(){
		theBoard = new BoardImpl();
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
}
