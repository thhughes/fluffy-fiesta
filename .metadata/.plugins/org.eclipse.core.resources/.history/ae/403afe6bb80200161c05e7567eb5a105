/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package hanto.studentthhughes.common.gamestatecalculator;

import hanto.common.MoveResult;
import hanto.studentthhughes.common.hantoboard.HantoBoard;
import hanto.studentthhughes.common.movecounter.MoveCounter;

/**
 * This is a board Validator, they are used to validate the state of the board and
 * decide on winners and losers
 * @author Troy
 *
 */
public interface GameStateCalculator {

	/**
	 * This is a validator that checks the result of the board based off the input board and the number
	 * of moves that have been taken. This returns 
	 * @param theBoard
	 * 				Board Implementation that gets checked for valid states
	 * @param counter
	 * 				Move Counter object to access the move data 
	 * @return MoveResult
	 * 					This returns the result of the board based off of the outcome. 
	 */
	MoveResult getOutcome(HantoBoard theBoard, MoveCounter counter);
}
