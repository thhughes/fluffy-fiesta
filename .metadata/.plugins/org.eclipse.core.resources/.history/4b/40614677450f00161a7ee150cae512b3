/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package hanto.studentthhughes.epsilon;


import hanto.common.HantoGame;
import hanto.common.HantoGameID;
import hanto.common.HantoPlayerColor;
import hanto.common.HantoPrematureResignationException;
import hanto.studentthhughes.TemplateHantoGame;
import hanto.studentthhughes.actionfinder.ActionFinder;
import hanto.studentthhughes.common.gamestateevaluator.GameStateEvaluator;
import hanto.studentthhughes.common.turnactionvalidator.TurnActionValidator;

/**
 * HantoGame for an Epsilon game.
 * @author Troy
 *
 */
public class EpsilonHantoGame extends TemplateHantoGame implements HantoGame {

	ActionFinder af = new ActionFinder(HantoGameID.EPSILON_HANTO);
	
	public EpsilonHantoGame(HantoPlayerColor firstMovePlayer, TurnActionValidator tav, GameStateEvaluator gse) {
		super(firstMovePlayer, tav, gse);
		canSurrender = true;
	}

	@Override
	protected void handleSurrender() throws HantoPrematureResignationException{
		if(af.isActionMakeable(hantoBoard, moveCounter, hantoColorManager.getCurrentColor())){
			super.handleSurrender();
			throw new HantoPrematureResignationException();
		}else{
			super.handleSurrender();
		}
	}

}
