/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package hanto.studentthhughes.delta;

import hanto.common.HantoCoordinate;
import hanto.common.HantoGame;
import hanto.common.HantoPiece;
import hanto.common.HantoPlayerColor;
import hanto.common.HantoPrematureResignationException;
import hanto.common.MoveResult;
import hanto.studentthhughes.TemplateHantoGame;
import hanto.studentthhughes.common.gamestateevaluator.GameStateEvaluator;
import hanto.studentthhughes.common.turnactionvalidator.TurnActionValidator;

/**
 * This is a delta hanto game implementation. 
 * It relies on the TemplateHantoGame and the 
 * TurnActionValidator and GameStateEvaluators that are created and passed to it. 
 * @author Troy
 *
 */
public class DeltaHantoGame extends TemplateHantoGame implements HantoGame {

	/**
	 * Constructor of a DeltaHantoGame.
	 * 
	 * @param firstMovePlayer
	 * 			HantoPlayerColor that represents the first player to move
	 * @param tav
	 * 		A TurnActionValidator that will be called to evaluate anytime 
	 *a move is made. This is what will determine if the turn is a legal action
	 *or if the action is illegal.
	 *
	 * @param gse
	 * 		A GameStateEvaluator that will be called at the end of every 
	 * action that is made. This is what will determine the outcome of an action
	 * after it is made. Meaning, this decides if a player has won, or the game
	 * is a draw.
	 */
	public DeltaHantoGame(HantoPlayerColor firstMovePlayer, TurnActionValidator tav, GameStateEvaluator gse) {
		super(firstMovePlayer, tav, gse);
		canSurrender = true;
	}


	/* (non-Javadoc)
	 * @see hanto.common.HantoGame#getPieceAt(hanto.common.HantoCoordinate)
	 */
	@Override
	public HantoPiece getPieceAt(HantoCoordinate where) {
		return super.getPieceAt(where);
	}




	
}
