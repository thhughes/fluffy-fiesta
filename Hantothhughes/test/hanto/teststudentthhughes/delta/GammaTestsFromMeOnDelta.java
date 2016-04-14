package hanto.teststudentthhughes.delta;

import static hanto.common.HantoPieceType.*;
import static hanto.common.HantoPlayerColor.*;
import static hanto.common.MoveResult.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoGameID;
import hanto.common.HantoPieceType;
import hanto.common.MoveResult;
import hanto.studentthhughes.HantoGameFactory;
import hanto.studentthhughes.common.coordinate.HantoCoordinateImpl;

public class GammaTestsFromMeOnDelta {

	
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
	
	
	@Test //1
	public void testNotNull(){
		assertNotNull(game);
	}
	
	@Test//2
	public void makeSingleMoveCorrectly() throws HantoException{
		MoveResult result = game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoCoordinateImpl(0,0));
		assertEquals(MoveResult.OK,result);
		assertNotNull(game.getPieceAt(new HantoCoordinateImpl(0,0)));
		assertTrue((game.getPieceAt(new HantoCoordinateImpl(0,0)).getType() == HantoPieceType.BUTTERFLY));
	}
	
	@Test//3
	public void placingThreePiecesOnTheBoardCorrectly() throws HantoException{
		MoveResult result = game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoCoordinateImpl(0,0));
		assertEquals(MoveResult.OK,result);
		result = game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoCoordinateImpl(0,1));
		assertEquals(MoveResult.OK,result);
		result = game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(0,-1));
		assertEquals(MoveResult.OK,result);
		

		assertNotNull(game.getPieceAt(new HantoCoordinateImpl(0,-1)));
		assertTrue((game.getPieceAt(new HantoCoordinateImpl(0,-1)).getType() == HantoPieceType.SPARROW));
	}
	
	@Test(expected=HantoException.class)//4
	public void placingThreePiecesOnTheBoardInCorrectlyBasedOffTheirColors() throws HantoException{
		MoveResult result = game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoCoordinateImpl(0,0));
		assertEquals(MoveResult.OK,result);
		result = game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoCoordinateImpl(0,1));
		assertEquals(MoveResult.OK,result);
		result = game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(0,2));
		
	}
	
	@Test //5
	public void moveButterflyOnSecondRedTurn() throws HantoException{
		MoveResult result = game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoCoordinateImpl(0,0));
		assertEquals(MoveResult.OK,result);
		result = game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoCoordinateImpl(0,1));
		assertEquals(MoveResult.OK,result);
		result = game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(0,-1));
		assertEquals(MoveResult.OK,result);
		result = game.makeMove(HantoPieceType.BUTTERFLY, new HantoCoordinateImpl(0,1), new HantoCoordinateImpl(1,0));
		assertEquals(MoveResult.OK,result);
		assertNull(game.getPieceAt(new HantoCoordinateImpl(0,1)));
		assertNotNull(game.getPieceAt(new HantoCoordinateImpl(1,0)));
		
	}
	
	@Test(expected=HantoException.class) //6
	public void moveButterflyOnSecondTurnRedToDisjointPlace() throws HantoException{
		MoveResult result = game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoCoordinateImpl(0,0));
		assertEquals(MoveResult.OK,result);
		result = game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoCoordinateImpl(0,1));
		assertEquals(MoveResult.OK,result);
		result = game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(0,-1));
		assertEquals(MoveResult.OK,result);
		result = game.makeMove(HantoPieceType.BUTTERFLY, new HantoCoordinateImpl(0,1), new HantoCoordinateImpl(0,2));
		
	}
	
	@Test(expected=HantoException.class) //7
	public void moveButterflyOnSecondTurnRedLegalPlaceThatsTooFarAway() throws HantoException{
		MoveResult result = game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoCoordinateImpl(0,0));
		assertEquals(MoveResult.OK,result);
		result = game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoCoordinateImpl(0,1));
		assertEquals(MoveResult.OK,result);
		result = game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(0,-1));
		assertEquals(MoveResult.OK,result);
		result = game.makeMove(HantoPieceType.BUTTERFLY, new HantoCoordinateImpl(0,1), new HantoCoordinateImpl(0,-2));
		
	}
	
	@Test(expected=HantoException.class)  //8
	public void testMovingMostlySurroundedPiece() throws HantoException{
		MoveResult result = game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoCoordinateImpl(0,0)); //b
		result = game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoCoordinateImpl(0,1)); 			 //r
		result = game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(0,-1));			 //b
		result = game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(-1,2));			 //r
		result = game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(-1,0));			 //b
		result = game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(1,1));				 //r
		result = game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(1,-1));// Setup	 //b
		result = game.makeMove(HantoPieceType.SPARROW, new HantoCoordinateImpl(-1,2), 					//r
				new HantoCoordinateImpl(-1,1));			 //r
		result = game.makeMove(HantoPieceType.SPARROW, new HantoCoordinateImpl(0,0), 					//b
				new HantoCoordinateImpl(1,0));			 //r

	}
	
	@Test  //9
	public void testMovingMostlyNotSurroundedPiece() throws HantoException{
		MoveResult result = game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoCoordinateImpl(0,0)); //b
		result = game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoCoordinateImpl(0,1)); 			 //r
		result = game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(0,-1));			 //b
		result = game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(-1,2));			 //r
		result = game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(-1,0));			 //b
		result = game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(1,1));				 //r
		result = game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(1,-2));// Setup	 //b
		result = game.makeMove(HantoPieceType.SPARROW, new HantoCoordinateImpl(-1,2), 					//r
				new HantoCoordinateImpl(-1,1));			 //r
		result = game.makeMove(HantoPieceType.BUTTERFLY, new HantoCoordinateImpl(0,0), 					//b
				new HantoCoordinateImpl(1,0));			 //r
		assertEquals(game.getPieceAt(new HantoCoordinateImpl(1,0)).getType(),HantoPieceType.BUTTERFLY);
		assertNull(game.getPieceAt(new HantoCoordinateImpl(0,0)));
	}
	
	@Test(expected=HantoException.class) //10
	public void movePieceThatDoesntExistEGWrongLocation() throws HantoException{
		MoveResult result = game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoCoordinateImpl(0,0));
		assertEquals(MoveResult.OK,result);
		result = game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoCoordinateImpl(0,1));
		assertEquals(MoveResult.OK,result);
		result = game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(0,-1));
		assertEquals(MoveResult.OK,result);
		result = game.makeMove(HantoPieceType.BUTTERFLY, new HantoCoordinateImpl(3,0), new HantoCoordinateImpl(1,0));
		
	}
	
	@Test(expected=HantoException.class) //11
	public void movePieceThatDoesntExistEGWrongPiece() throws HantoException{
		MoveResult result = game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoCoordinateImpl(0,0));
		assertEquals(MoveResult.OK,result);
		result = game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoCoordinateImpl(0,1));
		assertEquals(MoveResult.OK,result);
		result = game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(0,-1));
		assertEquals(MoveResult.OK,result);
		result = game.makeMove(HantoPieceType.SPARROW, new HantoCoordinateImpl(0,1), new HantoCoordinateImpl(1,0));
		
	}
	
	@Test(expected=HantoException.class)  //12
	public void testAddingTooManyPieces() throws HantoException{
		MoveResult result = game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoCoordinateImpl(0,0)); //b
		result = game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoCoordinateImpl(0,1)); 			 //r
		result = game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(0,-1));			 //b
		result = game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(0,2));				 //r
		result = game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(0,-2));			 //b
		result = game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(0,3));				 //r
		result = game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(0,-3));			 //b
		result = game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(0,4));				 //r
		result = game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(0,-4));			 //b
		result = game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(0,5));				 //r
		result = game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(0,-5));			 //b
		result = game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(0,6));				 //r
		result = game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(0,-6));			 //b
	}
	
	@Test(expected=HantoException.class)  //12
	public void testnotPlayingButterflyRightAway() throws HantoException{
		MoveResult result = game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(0,0)); //b
		result = game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(0,1)); 			 //r
		result = game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(0,-1));			 //b
		result = game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(0,2));				 //r
		result = game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(0,-2));			 //b
		result = game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(0,3));				 //r
		result = game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(0,-3));			 //b
		result = game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(0,4));				 //r
		result = game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(0,-4));			 //b
		
	}
	
	@Test(expected=HantoException.class)  //12
	public void testPlayingIllegalItem() throws HantoException{
		MoveResult result = game.makeMove(HantoPieceType.CRANE, null, new HantoCoordinateImpl(0,0)); //b
	}
	
	@Test(expected=HantoException.class) //13
	public void testPlacingPieceOnInvalidTileFirstTime() throws HantoException{
		MoveResult result = game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoCoordinateImpl(1,0)); //b
		
	}
	
	
	@Test //14
	public void testThatRedCanWin() throws HantoException{
		MoveResult result = game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoCoordinateImpl(0,0)); 	//b
		result = game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoCoordinateImpl(-1,0)); 			 	//r
		result = game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(1,0));			 		//b
		result = game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(-2,1));				 //r
		result = game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(0,1));			 		//b
		result = game.makeMove(HantoPieceType.SPARROW, new HantoCoordinateImpl(-2,1), new HantoCoordinateImpl(-1,1));				 //r
		result = game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(1,-1));			 		//b
		result = game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(-1,-1));			 		//r
		result = game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(2,0));			 		//b
		result = game.makeMove(HantoPieceType.SPARROW, new HantoCoordinateImpl(-1,-1), new HantoCoordinateImpl(0,-1));			 		//r
		assertEquals(result,MoveResult.RED_WINS);
	}
	
	@Test // 15
	public void redAndBlueWinResultingInDraw() throws HantoException
	{
		MoveResult result = game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoCoordinateImpl(0,0)); 	//b
		result = game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoCoordinateImpl(-1,0)); 			 	//r
		result = game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(1,0));			 		//b
		result = game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(-2,1));				 //r
		result = game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(0,1));			 		//b
		result = game.makeMove(HantoPieceType.SPARROW, new HantoCoordinateImpl(-2,1), new HantoCoordinateImpl(-1,1));				 //r
		result = game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(1,-1));			 		//b
		result = game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(-1,-1));			 		//r
		result = game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(2,0));			 		//b
		result = game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(-2,1));			 		//r
		result = game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(1,-2));			 		//b
		result = game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(-2,0));			 		//r
		result = game.makeMove(HantoPieceType.SPARROW, new HantoCoordinateImpl(1,-2), new HantoCoordinateImpl(0,-1)); 		//b
		assertEquals(MoveResult.DRAW,result);
		
	}
	
	@Test(expected=HantoException.class)  // 16
	public void testGameOver() throws HantoException
	{
		MoveResult result = game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoCoordinateImpl(0,0)); 	//b
		result = game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoCoordinateImpl(-1,0)); 			 	//r
		result = game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(1,0));			 		//b
		result = game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(-2,1));				 //r
		result = game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(0,1));			 		//b
		result = game.makeMove(HantoPieceType.SPARROW, new HantoCoordinateImpl(-2,1), new HantoCoordinateImpl(-1,1));				 //r
		result = game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(1,-1));			 		//b
		result = game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(-1,-1));			 		//r
		result = game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(2,0));			 		//b
		result = game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(-2,1));			 		//r
		result = game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(1,-2));			 		//b
		result = game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(-2,0));			 		//r
		result = game.makeMove(HantoPieceType.SPARROW, new HantoCoordinateImpl(1,-2), new HantoCoordinateImpl(0,-1)); 		//b
		assertEquals(MoveResult.DRAW,result);
		result = game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(-2,2));
		
	}
	
	
	@Test(expected=HantoException.class)  // 17
	public void testPassingNullTo() throws HantoException
	{
		game.makeMove(HantoPieceType.BUTTERFLY, null, null); 	//b
		
	}
	
	
	
	
	
	
	
}