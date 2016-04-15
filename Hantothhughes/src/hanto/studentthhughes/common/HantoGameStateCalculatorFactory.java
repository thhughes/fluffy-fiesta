/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package hanto.studentthhughes.common;




import hanto.common.HantoGameID;
import hanto.common.HantoPlayerColor;
import hanto.studentthhughes.common.gamestateevaluator.MaxTurnCountEvaluator;
import hanto.studentthhughes.common.gamestateevaluator.GameStateEvaluator;
import hanto.studentthhughes.common.gamestateevaluator.MasterGameStateEvaluator;
import hanto.studentthhughes.common.gamestateevaluator.PlayerButterflySurroundedEvaluator;

/**
 * This is a factory for board validators
 * @author Troy
 *
 */
public class HantoGameStateCalculatorFactory {

	private static final HantoGameStateCalculatorFactory instance = new HantoGameStateCalculatorFactory();
	
	
	private HantoGameStateCalculatorFactory(){
		
	}
	
	public static HantoGameStateCalculatorFactory getInstance(){
		return instance;
	}
	/**
	 * Get the board validator for your type of game
	 * @param gameID
	 * 				HantoGameID : of the game type you want your validator to be
	 * @return
	 * 			BoardValidator : of the hantoGameId Passed
	 */
	public GameStateEvaluator makeHantoValidator(HantoGameID gameID){
		MasterGameStateEvaluator mv = null;
		switch (gameID) {
			case ALPHA_HANTO:
				break;
			case BETA_HANTO:
				break;
			case GAMMA_HANTO:
				mv = new MasterGameStateEvaluator();
				mv.addValidator(new MaxTurnCountEvaluator(20));
				mv.addValidator(new PlayerButterflySurroundedEvaluator(HantoPlayerColor.RED));
				mv.addValidator(new PlayerButterflySurroundedEvaluator(HantoPlayerColor.BLUE));
				break;
			case DELTA_HANTO:
				mv = new MasterGameStateEvaluator();
				mv.addValidator(new PlayerButterflySurroundedEvaluator(HantoPlayerColor.RED));
				mv.addValidator(new PlayerButterflySurroundedEvaluator(HantoPlayerColor.BLUE));
				break;
			
			default:
				break;
		}
		return mv;
	}
}
