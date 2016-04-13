package hanto.teststudentthhughes.common.boardvalidator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import hanto.common.MoveResult;
import hanto.studentThhughes.common.board.Board;
import hanto.studentThhughes.common.boardvalidator.AggragateBoardValidator;
import hanto.studentThhughes.common.boardvalidator.BoardValidator;
import hanto.studentThhughes.common.movecounter.MoveCounter;

public class AggragateBoardValidatorTest {

	class dummyValidator implements BoardValidator{
		
		MoveResult res;
		public dummyValidator(MoveResult custResult){
			res = custResult;
		}
		@Override
		public MoveResult getOutcome(Board theBoard, MoveCounter counter) {
			return res;
		}
	}
	
	private static AggragateBoardValidator av;
	private static dummyValidator ok;
	private static dummyValidator bw;
	private static dummyValidator rw;
	private static dummyValidator draw;
	
	@Before
	public void setup(){
		av = new AggragateBoardValidator();
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
