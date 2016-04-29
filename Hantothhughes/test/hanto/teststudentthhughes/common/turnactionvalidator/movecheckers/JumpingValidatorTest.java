package hanto.teststudentthhughes.common.turnactionvalidator.movecheckers;

import static hanto.common.HantoPieceType.*;
import static hanto.common.HantoPlayerColor.BLUE;
import static hanto.common.HantoPlayerColor.RED;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentthhughes.common.hantoboardandboardtools.HantoBoard;
import hanto.studentthhughes.common.hantoboardandboardtools.HantoBoardImpl;
import hanto.studentthhughes.common.hantopiece.HantoPieceImpl;
import hanto.studentthhughes.common.movecounter.MoveCounterImpl;
import hanto.studentthhughes.common.turnactionvalidator.TurnActionValidator;
import hanto.studentthhughes.common.turnactionvalidator.movecheckers.JumpingValidator;

public class JumpingValidatorTest {
	class MoveData {
		final HantoPieceType type;
		final HantoCoordinate from, to;
		
		private MoveData(HantoPieceType type, HantoCoordinate from, HantoCoordinate to) 
		{
			this.type = type;
			this.from = from;
			this.to = to;
		}
	}
	
	/**
	 * Internal class for these test cases.
	 * @version Sep 13, 2014
	 */
	class TestHantoCoordinate implements HantoCoordinate
	{
		private final int x, y;
		
		public TestHantoCoordinate(int x, int y)
		{
			this.x = x;
			this.y = y;
		}
		/*
		 * @see hanto.common.HantoCoordinate#getX()
		 */
		@Override
		public int getX()
		{
			return x;
		}

		/*
		 * @see hanto.common.HantoCoordinate#getY()
		 */
		@Override
		public int getY()
		{
			return y;
		}

	}
	

	private HantoBoard theBoard;
	private HantoPlayerColor currentColor;
	private TurnActionValidator tav;
	
	@Before
	public void setup() throws HantoException{
		theBoard = new HantoBoardImpl();
		currentColor = BLUE;
		
		tav = new JumpingValidator();
		
		fillBoard(md(BUTTERFLY,0,0),md(BUTTERFLY,0,1),
				md(HORSE,0,-1),md(HORSE,0,2),
				md(HORSE,1,-1),md(HORSE,0,3),
				md(HORSE,-1,1));//
	}
	
	@Test
	public void horseJumpsOneHex() throws HantoException
	{
		assertTrue(tav.isValidMove(theBoard, new HantoPieceImpl(BLUE,HORSE), new MoveCounterImpl(), 
				makeCoordinate(-1,2), makeCoordinate(-1,1)));
	}
	
	@Test
	public void horseJumpTwoHex() throws HantoException
	{
		assertTrue(tav.isValidMove(theBoard, new HantoPieceImpl(BLUE,HORSE), new MoveCounterImpl(), 
				makeCoordinate(-1,3), makeCoordinate(-1,1)));
	}
	
	@Test
	public void horseJumpsThreeHex() throws HantoException
	{
		assertTrue(tav.isValidMove(theBoard, new HantoPieceImpl(BLUE,HORSE), new MoveCounterImpl(), 
				makeCoordinate(-1,4), makeCoordinate(-1,1)));
	}
	
	@Test
	public void horseJumpsOverOneHex() throws HantoException
	{
		assertTrue(tav.isValidMove(theBoard, new HantoPieceImpl(BLUE,HORSE), new MoveCounterImpl(), 
				makeCoordinate(1,1), makeCoordinate(-1,1)));
	}
	
	@Test
	public void horseJumpsOverTwoHex() throws HantoException
	{
		assertTrue(tav.isValidMove(theBoard, new HantoPieceImpl(BLUE,HORSE), new MoveCounterImpl(), 
				makeCoordinate(2,-2), makeCoordinate(-1,1)));
	}
	
	@Test
	public void horseJumpstoPlaceItCannotMoveTo() throws HantoException
	{
		assertFalse(tav.isValidMove(theBoard, new HantoPieceImpl(BLUE,HORSE), new MoveCounterImpl(), 
				makeCoordinate(2,-1), makeCoordinate(-1,1)));
	}
	
	@Test
	public void horseFlysToLocationThatIsOccupied() throws HantoException
	{
		assertFalse(tav.isValidMove(theBoard, new HantoPieceImpl(BLUE,HORSE), new MoveCounterImpl(), 
				makeCoordinate(1,-1), makeCoordinate(-1,1)));
	}
	
	
	
	
	/*
	 * NOTE: Most, if not all, of the below testing helper functions must be attributed 
	 * to Gary Pollice, I did not write them. I am just using them because they make
	 * life infinitely easier and I'm sad I didn't thing/implement similar myself 
	 * earlier. 
	 */
	
	// Helper methods
	private HantoCoordinate makeCoordinate(int x, int y)
	{
		return new TestHantoCoordinate(x, y);
	}
	
	/**
	 * Make sure that the piece at the location is what you expect
	 * @param x x-coordinate
	 * @param y y-coordinate
	 * @param color piece color expected
	 * @param type piece type expected
	 */
	private void checkPieceAt(int x, int y, HantoPlayerColor color, HantoPieceType type)
	{
		final HantoPiece piece = theBoard.getFromBoard(makeCoordinate(x, y));
		assertEquals(color, piece.getColor());
		assertEquals(type, piece.getType());
	}
	
	
	/**
	 * Make a MoveData object given the piece type and the x and y coordinates of the
	 * desstination. This creates a move data that will place a piece (source == null)
	 * @param type piece type
	 * @param toX destination x-coordinate
	 * @param toY destination y-coordinate
	 * @return the desitred MoveData object
	 */
	private MoveData md(HantoPieceType type, int toX, int toY) 
	{
		return new MoveData(type, null, makeCoordinate(toX, toY));
	}
	
	
	/**
	 * Make the moves specified. If there is no exception, return the move result of
	 * the last move.
	 * @param moves
	 * @return the last move result
	 * @throws HantoException
	 */
	private void fillBoard(MoveData... moves) throws HantoException
	{
		MoveResult mr = null;
		for (MoveData md : moves) {
			theBoard.placeOnBoard(new HantoPieceImpl(color(), md.type), md.to);
		}
	}
	
	private HantoPlayerColor color(){
		if(currentColor == BLUE){
			return RED;
		}else{
			return BLUE;
		}
	}
}
