/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package hanto.studentThhughes.common.gamestate;

import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentThhughes.common.board.Board;
import hanto.studentThhughes.common.movecounter.MoveCounter;

/**
 * This is a BoardValidator class that checks if the game has run out of moves. 
 * @author Troy
 *
 */
public class EndOfGameCalculator implements GameStateCalculator {
	int terminalNumber;
	
	/**
	 * Constructor
	 * @param maxNumberMoves
	 * 						
	 */
	public EndOfGameCalculator(int maxNumberMoves){
		terminalNumber = maxNumberMoves;
	}
	
	
	/* (non-Javadoc)
	 * @see hanto.studentThhughes.common.boardValidator.BoardValidator#getOutcome(hanto.studentThhughes.common.board.Board, hanto.studentThhughes.common.MoveCounter)
	 */
	@Override
	public MoveResult getOutcome(Board theBoard, MoveCounter counter) {
		MoveResult result = MoveResult.OK;
		if(counter.getNumberMoves(HantoPlayerColor.RED) == terminalNumber &&
				counter.getNumberMoves(HantoPlayerColor.BLUE) == terminalNumber){
			result = MoveResult.DRAW;
		}
		return result;
	}

}