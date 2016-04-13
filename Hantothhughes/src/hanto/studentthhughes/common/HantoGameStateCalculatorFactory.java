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
import hanto.studentthhughes.common.gamestate.AggregateGameStateCalculator;
import hanto.studentthhughes.common.gamestate.EndOfGameCalculator;
import hanto.studentthhughes.common.gamestate.GameStateCalculator;
import hanto.studentthhughes.common.gamestate.PlayerWinCalculator;

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
	public GameStateCalculator makeHantoValidator(HantoGameID gameID){
		AggregateGameStateCalculator mv = null;
		switch (gameID) {
			case ALPHA_HANTO:
				break;
			case BETA_HANTO:
				break;
			case GAMMA_HANTO:
				mv = new AggregateGameStateCalculator();
				mv.addValidator(new EndOfGameCalculator(20));
				mv.addValidator(new PlayerWinCalculator(HantoPlayerColor.RED));
				mv.addValidator(new PlayerWinCalculator(HantoPlayerColor.BLUE));
				break;
			
			default:
				break;
		}
		return mv;
	}
}
