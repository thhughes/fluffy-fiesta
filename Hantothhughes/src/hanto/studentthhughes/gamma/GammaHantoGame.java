/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package hanto.studentthhughes.gamma;

import hanto.common.HantoCoordinate;
import hanto.common.HantoGame;
import hanto.common.HantoPiece;
import hanto.common.HantoPlayerColor;
import hanto.studentthhughes.TemplateHantoGame;
import hanto.studentthhughes.common.gamestateevaluator.GameStateEvaluator;
import hanto.studentthhughes.common.turnactionvalidator.TurnActionValidator;

/**
 * @author Troy
 *
 */
public class GammaHantoGame extends TemplateHantoGame implements HantoGame {

	public GammaHantoGame(HantoPlayerColor firstMovePlayer, TurnActionValidator tav, GameStateEvaluator gse) {
		super(firstMovePlayer, tav, gse);
	}

	/* (non-Javadoc)
	 * @see hanto.common.HantoGame#getPieceAt(hanto.common.HantoCoordinate)
	 */
	@Override
	public HantoPiece getPieceAt(HantoCoordinate where) {
		return super.getPieceAt(where);
	}



	
}
