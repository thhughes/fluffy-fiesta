package hanto.teststudentthhughes.delta;

import static hanto.common.HantoPieceType.*;
import static hanto.common.HantoPlayerColor.*;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoGameID;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentthhughes.HantoGameFactory;
import hanto.teststudentthhughes.delta.GammaTestsFromPolliceOnDelta.MoveData;
import hanto.teststudentthhughes.delta.GammaTestsFromPolliceOnDelta.TestHantoCoordinate;

public class DeltaUniqueTests {

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
	
	private static HantoGameFactory factory;
	private HantoGame game;
	
	@BeforeClass
	public static void initializeClass()
	{
		factory = HantoGameFactory.getInstance();
	}
	
	@Before
	public void setup()
	{
		// By default, blue moves first.
		game = factory.makeHantoGame(HantoGameID.DELTA_HANTO, BLUE);
	}
	
	@Test
	public void redWinsWhenBlueSurrenders() throws HantoException{
		MoveResult mr = game.makeMove(null, null, null);
		assertEquals(mr, MoveResult.RED_WINS);
	}
	
	@Test
	public void blueWinsWhenRedSurrenders() throws HantoException
	{
		game = factory.makeHantoGame(HantoGameID.DELTA_HANTO, RED);
		MoveResult mr = game.makeMove(null, null, null);
		assertEquals(mr, MoveResult.BLUE_WINS);
	}
	
	@Test(expected=HantoException.class)
	public void gameEndsAfterSomeoneSurrenders() throws HantoException
	{
		MoveResult mr = game.makeMove(null, null, null);
		assertEquals(mr, MoveResult.RED_WINS);
		mr = game.makeMove(SPARROW, null, makeCoordinate(0, 0));
	}
	
	@Test
	public void canPlaceACrab() throws HantoException{
		game.makeMove(CRAB, null, makeCoordinate(0, 0));
		checkPieceAt(0,0,BLUE,CRAB);
	}
	
	@Test
	public void crabMovesOnePlace() throws HantoException
	{
		makeMoves(md(BUTTERFLY,0,0), md(BUTTERFLY,0,1), 
				md(SPARROW,0,-1), md(SPARROW,0,2),
				md(CRAB,-1,-1), md(SPARROW,0,3),
				md(CRAB,-1,-1,-1,0));
		checkPieceAt(-1,0,BLUE,CRAB);
	}
	
	@Test
	public void crabMovesTwoPlace() throws HantoException
	{
		makeMoves(md(BUTTERFLY,0,0), md(BUTTERFLY,0,1), 
				md(SPARROW,0,-1), md(SPARROW,0,2),
				md(CRAB,-1,-1), md(SPARROW,0,3),
				md(CRAB,-1,-1,-1,1));
		checkPieceAt(-1,1,BLUE,CRAB);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
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
		final HantoPiece piece = game.getPieceAt(makeCoordinate(x, y));
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
	
	private MoveData md(HantoPieceType type, int fromX, int fromY, int toX, int toY)
	{
		return new MoveData(type, makeCoordinate(fromX, fromY), makeCoordinate(toX, toY));
	}
	
	/**
	 * Make the moves specified. If there is no exception, return the move result of
	 * the last move.
	 * @param moves
	 * @return the last move result
	 * @throws HantoException
	 */
	private MoveResult makeMoves(MoveData... moves) throws HantoException
	{
		MoveResult mr = null;
		for (MoveData md : moves) {
			mr = game.makeMove(md.type, md.from, md.to);
		}
		return mr;
	}
}