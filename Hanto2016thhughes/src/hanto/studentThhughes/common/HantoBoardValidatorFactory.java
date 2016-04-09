/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package hanto.studentThhughes.common;




import hanto.common.HantoGameID;
import hanto.common.HantoPlayerColor;
import hanto.studentThhughes.common.boardValidator.AggragateBoardValidator;
import hanto.studentThhughes.common.boardValidator.BoardValidator;
import hanto.studentThhughes.common.boardValidator.ColorWinValidator;
import hanto.studentThhughes.common.boardValidator.TerminalMoveCheck;

/**
 * This is a factory for board validators
 * @author Troy
 *
 */
public class HantoBoardValidatorFactory {

	private static final HantoBoardValidatorFactory instance = new HantoBoardValidatorFactory();
	
	
	private HantoBoardValidatorFactory(){
		
	}
	
	public static HantoBoardValidatorFactory getInstance(){
		return instance;
	}
	/**
	 * Get the board validator for your type of game
	 * @param gameID
	 * 				HantoGameID : of the game type you want your validator to be
	 * @return
	 * 			BoardValidator : of the hantoGameId Passed
	 */
	public BoardValidator makeHantoValidator(HantoGameID gameID){
		AggragateBoardValidator mv = null;
		switch (gameID) {
			case ALPHA_HANTO:
				break;
			case BETA_HANTO:
				break;
			case GAMMA_HANTO:
				mv = new AggragateBoardValidator();
				mv.addValidator(new TerminalMoveCheck(20));
				mv.addValidator(new ColorWinValidator(HantoPlayerColor.RED));
				mv.addValidator(new ColorWinValidator(HantoPlayerColor.BLUE));
				break;
			
			default:
				break;
		}
		return mv;
	}
}