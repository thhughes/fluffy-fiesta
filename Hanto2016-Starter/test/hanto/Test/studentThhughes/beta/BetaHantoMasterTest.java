/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.Test.studentThhughes.beta;

import static hanto.common.HantoPieceType.*;
import static hanto.common.MoveResult.*;
import static hanto.common.HantoPlayerColor.*;
import static org.junit.Assert.*;
import hanto.common.*;
import hanto.studentThhughes.HantoGameFactory;

import org.junit.*;

/**
 * Test cases for Beta Hanto.
 * @version Sep 14, 2014
 */
public class BetaHantoMasterTest
{
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
		
		private BetaHantoMasterTest getOuterType() {
			return BetaHantoMasterTest.this;
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
		game = factory.makeHantoGame(HantoGameID.BETA_HANTO, BLUE);
	}	
	
	@Test	// 1
	public void bluePlacesInitialButterflyAtOrigin() throws HantoException
	{
		final MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		assertEquals(OK, mr);
		final HantoPiece p = game.getPieceAt(makeCoordinate(0, 0));
		assertEquals(BLUE, p.getColor());
		assertEquals(BUTTERFLY, p.getType());
	}
	
	@Test	// 2
	public void bluePlacesInitialSparrowAtOrigin() throws HantoException
	{
		final MoveResult mr = game.makeMove(SPARROW, null, makeCoordinate(0, 0));
		assertEquals(OK, mr);
		final HantoPiece p = game.getPieceAt(makeCoordinate(0, 0));
		assertEquals(BLUE, p.getColor());
		assertEquals(SPARROW, p.getType());
	}
	
	@Test	// 3
	public void redMakesValidMoveAfterBluePlacesButterfly() throws HantoException
	{
		MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		assertEquals(OK, mr);
		mr = game.makeMove(BUTTERFLY, null, makeCoordinate(1,-1));
		final HantoPiece p = game.getPieceAt(makeCoordinate(1, -1));
		assertEquals(RED, p.getColor());
		assertEquals(BUTTERFLY, p.getType());
	}
	
	@Test(expected=HantoException.class)	// 4
	public void redMakesinValidMoveAfterBluePlacesButterfly() throws HantoException
	{
		MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		assertEquals(OK, mr);
		mr = game.makeMove(BUTTERFLY, null, makeCoordinate(1,-2));
		final HantoPiece p = game.getPieceAt(makeCoordinate(1, -2));
		assertEquals(RED, p.getColor());
		assertEquals(BUTTERFLY, p.getType());
	}
	
	@Test	// 5
	public void blueMakesValidSecondMovePlacesSparrowSparrow() throws HantoException
	{
		MoveResult mr = game.makeMove(SPARROW, null, makeCoordinate(0, 0));
		assertEquals(OK, mr);
		mr = game.makeMove(SPARROW,  null, makeCoordinate(1,-1));
		mr = game.makeMove(SPARROW, null, makeCoordinate(1,-2));
		final HantoPiece p = game.getPieceAt(makeCoordinate(1, -2));
		assertEquals(BLUE, p.getColor());
		assertEquals(SPARROW, p.getType());
	}
	
	@Test(expected=HantoException.class)	// 6
	public void blueTriesToMoveSparrow() throws HantoException
	{
		MoveResult mr = game.makeMove(SPARROW, null, makeCoordinate(0, 0));
		assertEquals(OK, mr);
		mr = game.makeMove(SPARROW,  null, makeCoordinate(1,-1));
		mr = game.makeMove(SPARROW, makeCoordinate(0,0), makeCoordinate(1,-2));
		
	}
	
	@Test(expected=HantoException.class) // 7
	public void blueTriesToPlaceSparrowOnFourthTurn() throws HantoException
	{
		MoveResult mr = game.makeMove(SPARROW, null, makeCoordinate(0,0)); // blue
		assertEquals(OK, mr);
		mr = game.makeMove(SPARROW, null, makeCoordinate(0,1)); // red
		assertEquals(OK, mr);
		mr = game.makeMove(SPARROW, null, makeCoordinate(0,2)); // blue
		assertEquals(OK, mr);
		mr = game.makeMove(SPARROW, null, makeCoordinate(0,3)); // red
		assertEquals(OK, mr);
		mr = game.makeMove(SPARROW, null, makeCoordinate(0,4)); // blue
		assertEquals(OK, mr);
		mr = game.makeMove(SPARROW, null, makeCoordinate(0,5)); // red
		assertEquals(OK, mr);
		mr = game.makeMove(SPARROW, null, makeCoordinate(0,6)); // blue
	}
	
	@Test // 8
	public void blueTriesToPlaceButterflyOnFourthTurn() throws HantoException
	{
		MoveResult mr = game.makeMove(SPARROW, null, makeCoordinate(0,0)); // blue
		assertEquals(OK, mr);
		mr = game.makeMove(SPARROW, null, makeCoordinate(0,1)); // red
		assertEquals(OK, mr);
		mr = game.makeMove(SPARROW, null, makeCoordinate(0,2)); // blue
		assertEquals(OK, mr);
		mr = game.makeMove(SPARROW, null, makeCoordinate(0,3)); // red
		assertEquals(OK, mr);
		mr = game.makeMove(SPARROW, null, makeCoordinate(0,4)); // blue
		assertEquals(OK, mr);
		mr = game.makeMove(SPARROW, null, makeCoordinate(0,5)); // red
		assertEquals(OK, mr);
		mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0,6)); // blue
		assertEquals(OK, mr);
	}
	
	@Test(expected=HantoException.class) // 9
	public void redTriesToPlaceSparrowOnFourthTurn() throws HantoException
	{
		MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0,0)); // blue
		assertEquals(OK, mr);
		mr = game.makeMove(SPARROW, null, makeCoordinate(0,1)); // red
		assertEquals(OK, mr);
		mr = game.makeMove(SPARROW, null, makeCoordinate(0,2)); // blue
		assertEquals(OK, mr);
		mr = game.makeMove(SPARROW, null, makeCoordinate(0,3)); // red
		assertEquals(OK, mr);
		mr = game.makeMove(SPARROW, null, makeCoordinate(0,4)); // blue
		assertEquals(OK, mr);
		mr = game.makeMove(SPARROW, null, makeCoordinate(0,5)); // red
		assertEquals(OK, mr);
		mr = game.makeMove(SPARROW, null, makeCoordinate(0,6)); // blue
		assertEquals(OK, mr);
		mr = game.makeMove(SPARROW, null, makeCoordinate(0,6)); // RED
	}
	
	@Test(expected=HantoException.class) // 10
	public void redTriesToPlaceDove() throws HantoException
	{
		MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0,0)); // blue
		assertEquals(OK, mr);
		mr = game.makeMove(DOVE, null, makeCoordinate(0,1)); // red
		
	}
	
	@Test(expected=HantoException.class) // 11
	public void blueTriesToPlacePieceWithNullLocation() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, null); // blue
	}
	
	@Test(expected=HantoException.class) // 12
	public void redTriesToPlaceDoveOnFourthTurn() throws HantoException
	{
		MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0,0)); // blue
		assertEquals(OK, mr);
		mr = game.makeMove(SPARROW, null, makeCoordinate(0,1)); // red
		assertEquals(OK, mr);
		mr = game.makeMove(SPARROW, null, makeCoordinate(0,2)); // blue
		assertEquals(OK, mr);
		mr = game.makeMove(SPARROW, null, makeCoordinate(0,3)); // red
		assertEquals(OK, mr);
		mr = game.makeMove(SPARROW, null, makeCoordinate(0,4)); // blue
		assertEquals(OK, mr);
		mr = game.makeMove(SPARROW, null, makeCoordinate(0,5)); // red
		assertEquals(OK, mr);
		mr = game.makeMove(SPARROW, null, makeCoordinate(0,6)); // blue
		assertEquals(OK, mr);
		mr = game.makeMove(DOVE, null, makeCoordinate(0,6)); // RED
	}
	
	@Test // 13
	public void gameEndsInDrawAfterTwelveMoves() throws HantoException
	{
		MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0,0)); // blue
		assertEquals(OK, mr);
		mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0,1)); // red
		assertEquals(OK, mr);
		mr = game.makeMove(SPARROW, null, makeCoordinate(0,2)); // blue
		assertEquals(OK, mr);
		mr = game.makeMove(SPARROW, null, makeCoordinate(0,3)); // red
		assertEquals(OK, mr);
		mr = game.makeMove(SPARROW, null, makeCoordinate(0,4)); // blue
		assertEquals(OK, mr);
		mr = game.makeMove(SPARROW, null, makeCoordinate(0,5)); // red
		assertEquals(OK, mr);
		mr = game.makeMove(SPARROW, null, makeCoordinate(0,6)); // blue
		assertEquals(OK, mr);
		mr = game.makeMove(SPARROW, null, makeCoordinate(0,7)); // RED
		assertEquals(OK, mr);
		mr = game.makeMove(SPARROW, null, makeCoordinate(0,8)); // blue
		assertEquals(OK, mr);
		mr = game.makeMove(SPARROW, null, makeCoordinate(0,9)); // red
		assertEquals(OK, mr);
		mr = game.makeMove(SPARROW, null, makeCoordinate(0,10)); // blue
		assertEquals(OK, mr);
		mr = game.makeMove(SPARROW, null, makeCoordinate(0,11)); // red
		assertEquals(DRAW, mr);
	}
	
	@Test // 13
	public void blueLosesInSevenTurns() throws HantoException
	{
		MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0,0)); // blue
		assertEquals(OK, mr);
		mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0,1)); // red
		assertEquals(OK, mr);
		mr = game.makeMove(SPARROW, null, makeCoordinate(-1,1)); // blue
		assertEquals(OK, mr);
		mr = game.makeMove(SPARROW, null, makeCoordinate(-1,0)); // red
		assertEquals(OK, mr);
		mr = game.makeMove(SPARROW, null, makeCoordinate(0,-1)); // blue
		assertEquals(OK, mr);
		mr = game.makeMove(SPARROW, null, makeCoordinate(1,-1)); // red
		assertEquals(OK, mr);
		mr = game.makeMove(SPARROW, null, makeCoordinate(1,0)); // blue
		assertEquals(RED_WINS, mr);
	}
	
	@Test // 14
	public void redLosesInEightTurns() throws HantoException
	{
		MoveResult mr = game.makeMove(SPARROW, null, makeCoordinate(0,0)); // blue
		assertEquals(OK, mr);
		mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0,1)); // red
		assertEquals(OK, mr);
		mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0,2)); // blue
		assertEquals(OK, mr);
		mr = game.makeMove(SPARROW, null, makeCoordinate(-1,2)); // red
		assertEquals(OK, mr);
		mr = game.makeMove(SPARROW, null, makeCoordinate(-1,1)); // blue
		assertEquals(OK, mr);
		mr = game.makeMove(SPARROW, null, makeCoordinate(1,1)); // red
		assertEquals(OK, mr);
		mr = game.makeMove(SPARROW, null, makeCoordinate(1,0)); // blue
		assertEquals(BLUE_WINS, mr);
	}
	
	@Test // 15
	public void blueWinsGameOnTwelfthMove() throws HantoException
	{
		MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0,0)); // blue
		assertEquals(OK, mr);
		mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0,1)); // red
		assertEquals(OK, mr);
		mr = game.makeMove(SPARROW, null, makeCoordinate(0,2)); // blue
		assertEquals(OK, mr);
		mr = game.makeMove(SPARROW, null, makeCoordinate(0,3)); // red
		assertEquals(OK, mr);
		mr = game.makeMove(SPARROW, null, makeCoordinate(0,4)); // blue
		assertEquals(OK, mr);
		mr = game.makeMove(SPARROW, null, makeCoordinate(0,5)); // red
		assertEquals(OK, mr);
		mr = game.makeMove(SPARROW, null, makeCoordinate(0,6)); // blue
		assertEquals(OK, mr);
		mr = game.makeMove(SPARROW, null, makeCoordinate(0,7)); // RED
		assertEquals(OK, mr);
		mr = game.makeMove(SPARROW, null, makeCoordinate(-1,1)); // blue
		assertEquals(OK, mr);
		mr = game.makeMove(SPARROW, null, makeCoordinate(-1,2)); // red
		assertEquals(OK, mr);
		mr = game.makeMove(SPARROW, null, makeCoordinate(1,1)); // blue
		assertEquals(OK, mr);
		mr = game.makeMove(SPARROW, null, makeCoordinate(1,0)); // red
		assertEquals(BLUE_WINS, mr);
	}
	@Test // 16
	public void redWinsGameOnTwelfthMove() throws HantoException
	{
		MoveResult mr = game.makeMove(SPARROW, null, makeCoordinate(0,0)); // blue
		assertEquals(OK, mr);
		mr = game.makeMove(SPARROW, null, makeCoordinate(1,0)); // red
		assertEquals(OK, mr);
		mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0,1)); // blue
		assertEquals(OK, mr);
		mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0,2)); // red
		assertEquals(OK, mr);
		mr = game.makeMove(SPARROW, null, makeCoordinate(0,3)); // blue
		assertEquals(OK, mr);
		mr = game.makeMove(SPARROW, null, makeCoordinate(0,4)); // red
		assertEquals(OK, mr);
		mr = game.makeMove(SPARROW, null, makeCoordinate(0,5)); // blue
		assertEquals(OK, mr);
		mr = game.makeMove(SPARROW, null, makeCoordinate(0,6)); // RED
		assertEquals(OK, mr);
		mr = game.makeMove(SPARROW, null, makeCoordinate(-1,1)); // blue
		assertEquals(OK, mr);
		mr = game.makeMove(SPARROW, null, makeCoordinate(-1,2)); // red
		assertEquals(OK, mr);
		mr = game.makeMove(SPARROW, null, makeCoordinate(0,7)); // blue
		assertEquals(OK, mr);
		mr = game.makeMove(SPARROW, null, makeCoordinate(1,1)); // red
		assertEquals(RED_WINS, mr);
	}
	
	@Test // 17
	public void redAndBlueWinResultingInDraw() throws HantoException
	{
		MoveResult mr = game.makeMove(SPARROW, null, makeCoordinate(0,0)); // blue
		assertEquals(OK, mr);
		mr = game.makeMove(SPARROW, null, makeCoordinate(0,1)); // red
		assertEquals(OK, mr);
		mr = game.makeMove(BUTTERFLY, null, makeCoordinate(-1,1)); // blue
		assertEquals(OK, mr);
		mr = game.makeMove(BUTTERFLY, null, makeCoordinate(-1,0)); // red
		assertEquals(OK, mr);
		mr = game.makeMove(SPARROW, null, makeCoordinate(0,-1)); // blue
		assertEquals(OK, mr);
		mr = game.makeMove(SPARROW, null, makeCoordinate(-1,-1)); // red
		assertEquals(OK, mr);
		mr = game.makeMove(SPARROW, null, makeCoordinate(-2,0)); // blue
		assertEquals(OK, mr);
		mr = game.makeMove(SPARROW, null, makeCoordinate(-1,2)); // RED
		assertEquals(OK, mr);
		mr = game.makeMove(SPARROW, null, makeCoordinate(-2,2)); // blue
		assertEquals(OK, mr);
		mr = game.makeMove(SPARROW, null, makeCoordinate(-2,1)); // red
		assertEquals(DRAW, mr);
	}
	
	
	
	
	// Helper methods
	private HantoCoordinate makeCoordinate(int x, int y)
	{
		return new TestHantoCoordinate(x, y);
	}
}
