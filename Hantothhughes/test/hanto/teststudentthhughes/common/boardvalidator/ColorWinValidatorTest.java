package hanto.teststudentthhughes.common.boardvalidator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentthhughes.common.coordinate.HantoCoordinateImpl;
import hanto.studentthhughes.common.gamestatecalculator.GameStateCalculator;
import hanto.studentthhughes.common.gamestatecalculator.PlayerWinCalculator;
import hanto.studentthhughes.common.hantoboardandboardtools.HantoBoard;
import hanto.studentthhughes.common.hantoboardandboardtools.HantoBoardImpl;
import hanto.studentthhughes.common.hantopiece.HantoPieceImpl;

public class ColorWinValidatorTest {
	private static GameStateCalculator cv;
	private static HantoCoordinate hc1;
	private static HantoCoordinate hc2;
	private static HantoCoordinate hc3;
	private static HantoCoordinate hc4;
	private static HantoCoordinate hc5;
	private static HantoCoordinate hc6;
	private static HantoCoordinate hc7;
	
	private static HantoBoard theBoard;
	
	@Before
	public void setup(){
		hc1 = new HantoCoordinateImpl(0,0);
		hc2 = new HantoCoordinateImpl(0,1);
		hc3 = new HantoCoordinateImpl(1,0);
		hc4 = new HantoCoordinateImpl(1,-1);
		hc5 = new HantoCoordinateImpl(-1,1);
		hc6 = new HantoCoordinateImpl(0,-1);
		hc7 = new HantoCoordinateImpl(-1,0);
		
		theBoard = new HantoBoardImpl();
	}
	
	@Test //1 
	public void testConstructorNotNull(){
		assertNotNull(new PlayerWinCalculator(HantoPlayerColor.BLUE));
	}
	
	@Test //2
	public void testBoardWIthNoButterfly(){
		cv = new PlayerWinCalculator(HantoPlayerColor.BLUE);
		assertEquals(cv.getOutcome(theBoard, null),MoveResult.OK);
	}
	
	@Test //3
	public void testBoardWithOneButterflyAndNoSparrows() throws HantoException{
		cv = new PlayerWinCalculator(HantoPlayerColor.BLUE);
		theBoard.placeOnBoard(new HantoPieceImpl(HantoPlayerColor.RED,
				HantoPieceType.BUTTERFLY), hc1);
		assertEquals(cv.getOutcome(theBoard, null),MoveResult.OK);
	}
	
	@Test //4
	public void testBoardWithOneButterflyAndSurrounded() throws HantoException{
		cv = new PlayerWinCalculator(HantoPlayerColor.RED);
		theBoard.placeOnBoard(new HantoPieceImpl(HantoPlayerColor.BLUE,
				HantoPieceType.BUTTERFLY), hc1);
		theBoard.placeOnBoard(new HantoPieceImpl(HantoPlayerColor.BLUE,
				HantoPieceType.SPARROW), hc2);
		theBoard.placeOnBoard(new HantoPieceImpl(HantoPlayerColor.BLUE,
				HantoPieceType.SPARROW), hc3);
		theBoard.placeOnBoard(new HantoPieceImpl(HantoPlayerColor.BLUE,
				HantoPieceType.SPARROW), hc4);
		theBoard.placeOnBoard(new HantoPieceImpl(HantoPlayerColor.BLUE,
				HantoPieceType.SPARROW), hc5);
		theBoard.placeOnBoard(new HantoPieceImpl(HantoPlayerColor.BLUE,
				HantoPieceType.SPARROW), hc6);
		theBoard.placeOnBoard(new HantoPieceImpl(HantoPlayerColor.BLUE,
				HantoPieceType.SPARROW), hc7);
		
		assertEquals(cv.getOutcome(theBoard, null),MoveResult.RED_WINS);
	}
	
	
	@Test //4
	public void testBoardWithOneButterflyAndSurroundedWithBothTeamsPieces() throws HantoException{
		cv = new PlayerWinCalculator(HantoPlayerColor.RED);
		theBoard.placeOnBoard(new HantoPieceImpl(HantoPlayerColor.BLUE,
				HantoPieceType.BUTTERFLY), hc1);
		theBoard.placeOnBoard(new HantoPieceImpl(HantoPlayerColor.RED,
				HantoPieceType.SPARROW), hc2);
		theBoard.placeOnBoard(new HantoPieceImpl(HantoPlayerColor.BLUE,
				HantoPieceType.SPARROW), hc3);
		theBoard.placeOnBoard(new HantoPieceImpl(HantoPlayerColor.RED,
				HantoPieceType.SPARROW), hc4);
		theBoard.placeOnBoard(new HantoPieceImpl(HantoPlayerColor.BLUE,
				HantoPieceType.SPARROW), hc5);
		theBoard.placeOnBoard(new HantoPieceImpl(HantoPlayerColor.RED,
				HantoPieceType.SPARROW), hc6);
		theBoard.placeOnBoard(new HantoPieceImpl(HantoPlayerColor.BLUE,
				HantoPieceType.SPARROW), hc7);
		
		assertEquals(cv.getOutcome(theBoard, null),MoveResult.RED_WINS);
	}
}
