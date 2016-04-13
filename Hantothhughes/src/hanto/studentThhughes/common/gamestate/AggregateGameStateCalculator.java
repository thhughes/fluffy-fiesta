/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package hanto.studentThhughes.common.gamestate;

import java.util.LinkedList;
import java.util.Queue;

import hanto.common.MoveResult;
import hanto.studentThhughes.common.board.Board;
import hanto.studentThhughes.common.movecounter.MoveCounter;

/**
 * @author Troy
 *
 */
public class AggregateGameStateCalculator implements GameStateCalculator {
	
	Queue<GameStateCalculator> validatorList;
	
	public AggregateGameStateCalculator(){
		validatorList = new LinkedList<GameStateCalculator>();
		
	}
	
	/**
	 * This function adds a validator to the aggrigate to be run. 
	 * 
	 * @param toAdd
	 * 				BoardValidator : is used in the aggrigate's getOutcome function
	 */
	public void addValidator(GameStateCalculator toAdd){
		validatorList.add(toAdd);
	}

	
	/* (non-Javadoc)
	 * @see hanto.studentThhughes.common.boardValidator.BoardValidator#getOutcome(hanto.studentThhughes.common.board.Board, hanto.studentThhughes.common.moveCounter.MoveCounter)
	 */
	@Override
	public MoveResult getOutcome(Board theBoard, MoveCounter counter) {
		Queue<MoveResult> results = new LinkedList<MoveResult>();
		
		for(GameStateCalculator validator: validatorList){
			results.add(validator.getOutcome(theBoard, counter));
		}
		
		MoveResult result = MoveResult.OK;
		if(results.contains(MoveResult.DRAW) || 
				(results.contains(MoveResult.BLUE_WINS) && results.contains(MoveResult.RED_WINS))){
			result = MoveResult.DRAW;
		}else if(results.contains(MoveResult.BLUE_WINS)){
			result = MoveResult.BLUE_WINS;
		}else if(results.contains(MoveResult.RED_WINS)){
			result = MoveResult.RED_WINS;
		}
		return result;
	}

}