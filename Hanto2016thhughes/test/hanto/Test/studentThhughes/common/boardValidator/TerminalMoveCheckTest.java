package hanto.Test.studentThhughes.common.boardValidator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentThhughes.common.boardValidator.BoardValidator;
import hanto.studentThhughes.common.boardValidator.TerminalMoveCheck;
import hanto.studentThhughes.common.moveCounter.MoveCounter;

public class TerminalMoveCheckTest {
	private class dummyCounter implements MoveCounter{
		int numMoves;
		
		public dummyCounter(int numberOfMoves){
			numMoves = numberOfMoves;
		}
		
		@Override
		public int getNumberMoves(HantoPlayerColor player) {
			return numMoves;
		}

		@Override
		public void incrementNumberMoves(HantoPlayerColor player) {
			numMoves++;
		}
		
		public void setNumberOfMoves(int val){
			numMoves = val;
		}
	}
	
	
	private static MoveCounter counter;
	private static BoardValidator bv;
	
	@Before
	public void setup(){
		counter  = new dummyCounter(0);
		bv = new TerminalMoveCheck(2);
	}
	
	@Test //1 
	public void testNotNull(){
		assertNotNull(bv);
	}
	
	@Test //2 
	public void testTerminalMovesReturnsOKWhenNotTerminalMoves(){
		assertEquals(MoveResult.OK,bv.getOutcome(null, counter));
	}
	
	@Test //3
	public void testTerminalMovesReturnsDrawWhenTerminalMoves(){
		counter = new dummyCounter(2);
		assertEquals(MoveResult.DRAW, bv.getOutcome(null, counter));
	}
	
}
