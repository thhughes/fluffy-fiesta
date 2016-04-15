package hanto.teststudentthhughes.common.gamestateevaluator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import hanto.common.MoveResult;
import hanto.studentthhughes.common.gamestateevaluator.GameStateEvaluator;
import hanto.studentthhughes.common.gamestateevaluator.MasterGameStateEvaluator;
import hanto.studentthhughes.common.hantoboardandboardtools.HantoBoard;
import hanto.studentthhughes.common.movecounter.MoveCounter;

public class AggragateBoardValidatorTest {

	class dummyValidator implements GameStateEvaluator{
		
		MoveResult res;
		public dummyValidator(MoveResult custResult){
			res = custResult;
		}
		@Override
		public MoveResult getOutcome(HantoBoard theBoard, MoveCounter counter) {
			return res;
		}
	}
	
	private static MasterGameStateEvaluator av;
	private static dummyValidator ok;
	private static dummyValidator bw;
	private static dummyValidator rw;
	private static dummyValidator draw;
	
	@Before
	public void setup(){
		av = new MasterGameStateEvaluator();
		ok = new dummyValidator(MoveResult.OK);
		bw = new dummyValidator(MoveResult.BLUE_WINS);
		rw = new dummyValidator(MoveResult.RED_WINS);
		draw = new dummyValidator(MoveResult.DRAW);
	}
	
	@Test //1
	public void getOKWithNoValidators(){
		assertEquals(av.getOutcome(null, null),MoveResult.OK);
	}
	
	@Test //2
	public void getRWWithRWValidator(){
		av.addValidator(rw);
		assertEquals(av.getOutcome(null, null),MoveResult.RED_WINS);
	}
	
	@Test //3
	public void getBWWithBWValidator(){
		av.addValidator(bw);
		assertEquals(av.getOutcome(null, null),MoveResult.BLUE_WINS);
	}
	
	@Test //4
	public void getDRAWWithDRAWValidator(){
		av.addValidator(draw);
		assertEquals(av.getOutcome(null, null),MoveResult.DRAW);
	}
	
	@Test //5
	public void getDRAWWithBWandRWValidator(){
		av.addValidator(rw);
		av.addValidator(bw);
		assertEquals(av.getOutcome(null, null),MoveResult.DRAW);
	}
	
	@Test //6
	public void getDRAWWithBWandDRAWValidator(){
		av.addValidator(draw);
		av.addValidator(bw);
		assertEquals(av.getOutcome(null, null),MoveResult.DRAW);
	}
	
	@Test //6
	public void getDRAWWithRWandDRAWValidator(){
		av.addValidator(draw);
		av.addValidator(rw);
		assertEquals(av.getOutcome(null, null),MoveResult.DRAW);
	}
	
	
}