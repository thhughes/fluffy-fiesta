package hanto.teststudentthhughes.actionfinder;

import static hanto.common.HantoPlayerColor.*;
import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPiece;
import static hanto.common.HantoPieceType.*;
import static hanto.common.HantoGameID.*;
import hanto.common.HantoPieceType;
import hanto.studentthhughes.actionfinder.ActionFinder;
import hanto.studentthhughes.common.hantoboardandboardtools.HantoBoard;
import hanto.studentthhughes.common.hantoboardandboardtools.HantoBoardImpl;
import hanto.studentthhughes.common.hantopiece.HantoPieceImpl;
import hanto.studentthhughes.common.movecounter.MoveCounter;
import hanto.studentthhughes.common.movecounter.MoveCounterImpl;
import hanto.tournament.HantoMoveRecord;

public class ActionFinderTest {

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
	
	
	private MoveCounter counter;
	private HantoBoard theBoard;
	private HantoPiece p1,p2,p3,p4;
	private HantoCoordinate hc1, hc2, hc3, hc4, hc5, hc6, hc7, hc8, hc9, hc10, hc11, hc12;
	private ActionFinder af;
	
	List<HantoCoordinate> validList;
	
	@Before
	public void setup() throws HantoException
	{
		theBoard = new HantoBoardImpl();
		counter = new MoveCounterImpl();
		p1 = new HantoPieceImpl(BLUE, BUTTERFLY);
		p2 = new HantoPieceImpl(RED, BUTTERFLY);
		p3 = new HantoPieceImpl(BLUE, SPARROW);
		p4 = new HantoPieceImpl(RED, SPARROW);
		hc1 = mc(0, 0); // b
		hc2 = mc(0, 1); // r
		hc3 = mc(0, -1);// b
		hc4 = mc(0, 2); // r
		hc5 = mc(0, -2);// b
		hc6 = mc(0, 3); // r
		hc7 = mc(0, -3);// b
		hc8 = mc(0, 4); // r
		hc9 = mc(0, -4);// b
		hc10 = mc(0, 5);// r
		hc11 = mc(0, -5);//b
		hc12 = mc(0, 6);// r
		
		af = new ActionFinder(DELTA_HANTO);
		
		theBoard.placeOnBoard(p1, hc1);
		theBoard.placeOnBoard(p2, hc2);
		theBoard.placeOnBoard(p3, hc3);
		theBoard.placeOnBoard(p4, hc4);

		for (int i = 0; i < 2; i++){
			counter.incrementNumberMoves(BLUE);
			counter.incrementNumberMoves(RED);
		}

		validList = new LinkedList<HantoCoordinate>();
		
		validList.add(mc(-2,1));
		validList.add(mc(-2,0));
		validList.add(mc(-1,-1));
		validList.add(mc(0,-1));
		validList.add(mc(1,-1));

		
	}
	
	@Test
	public void startingTests(){
		assertNotNull(af);
	}
	
	@Test
	public void testThatSomethingIsReturned(){
		assertNotNull(af.getAction(theBoard, counter, BLUE));
	}
	
	@Test
	public void testThatTheMoveHasValidLocation(){
		HantoMoveRecord hmr = af.getAction(theBoard, counter, BLUE);
		for(HantoCoordinate hc: validList){
			if(hc.getX() == hmr.getTo().getX() &&
					hc.getY() == hmr.getTo().getY()){
				assertNull(hmr.getFrom());
				assertTrue(true);
				return;
			}
		}
		assertFalse(true);
		
	}
	
	@Test
	public void testThatAMoveCanBeMade() throws HantoException{
		theBoard.placeOnBoard(p3, hc5);
		theBoard.placeOnBoard(p4, hc6);
		theBoard.placeOnBoard(p3, hc7);
		theBoard.placeOnBoard(p4, hc8);
		theBoard.placeOnBoard(p3, hc9);
		theBoard.placeOnBoard(p4, hc10);
		theBoard.placeOnBoard(p3, hc11);
		theBoard.placeOnBoard(p4, hc12);
		for (int i = 0; i < 4; i++){
			counter.incrementNumberMoves(BLUE);
			counter.incrementNumberMoves(RED);
		}
		

		af = new ActionFinder(GAMMA_HANTO);
		
		assertNotNull(af.getAction(theBoard, counter, BLUE));
	}
	
	@Test
	public void testAMoveIsAMoveAndNotAPlace() throws HantoException{
		theBoard.placeOnBoard(p3, hc5);
		theBoard.placeOnBoard(p4, hc6);
		theBoard.placeOnBoard(p3, hc7);
		theBoard.placeOnBoard(p4, hc8);
		theBoard.placeOnBoard(p3, hc9);
		theBoard.placeOnBoard(p4, hc10);
		theBoard.placeOnBoard(p3, hc11);
		theBoard.placeOnBoard(p4, hc12);
		for (int i = 0; i < 4; i++){
			counter.incrementNumberMoves(BLUE);
			counter.incrementNumberMoves(RED);
		}
		

		af = new ActionFinder(GAMMA_HANTO);
		
		assertNotNull(af.getMoveAction(theBoard, counter, BLUE));
	}
	
	@Test
	public void testAValidMoveExists() {
		assertTrue(af.isActionMakeable(theBoard, counter, BLUE));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	 * NOTE: Most, if not all, of the below testing helper functions must be attributed 
	 * to Gary Pollice, I did not write them. I am just using them because they make
	 * life infinitely easier and I'm sad I didn't thing/implement similar myself 
	 * earlier. 
	 */
	
	// Helper methods
	private HantoCoordinate mc(int x, int y)
	{
		return new TestHantoCoordinate(x, y);
	}
	

}
