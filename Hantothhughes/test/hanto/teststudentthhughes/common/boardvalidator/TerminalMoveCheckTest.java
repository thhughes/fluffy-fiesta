package hanto.teststudentthhughes.common.boardvalidator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentthhughes.common.gamestatecalculator.EndOfGameCalculator;
import hanto.studentthhughes.common.gamestatecalculator.GameStateCalculator;
import hanto.studentthhughes.common.movecounter.MoveCounter;

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
	private static GameStateCalculator bv;
	
	@Before
	public void setup(){
		counter  = new dummyCounter(0);
		bv = new EndOfGameCalculator(2);
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
