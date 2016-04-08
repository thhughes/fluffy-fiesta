/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package hanto.studentThhughes.common;


import java.util.LinkedList;
import java.util.Queue;

import hanto.common.HantoGameID;
import hanto.common.HantoPieceType;
import hanto.studentThhughes.common.moveValidator.AggrigateValidator;
import hanto.studentThhughes.common.moveValidator.ButterflyChecker;
import hanto.studentThhughes.common.moveValidator.ButterflyPlacedInTimeValidator;
import hanto.studentThhughes.common.moveValidator.FirstMoveValidator;
import hanto.studentThhughes.common.moveValidator.LimitMoveLengthToOneValidator;
import hanto.studentThhughes.common.moveValidator.LocationValidator;
import hanto.studentThhughes.common.moveValidator.MoveRealPieceValidator;
import hanto.studentThhughes.common.moveValidator.MoveValidator;
import hanto.studentThhughes.common.moveValidator.PieceValidator;
import hanto.studentThhughes.common.moveValidator.PlaceBySameColorValidator;
import hanto.studentThhughes.common.moveValidator.PlayingTooManyPieceValidator;
import hanto.studentThhughes.common.moveValidator.TwoSpacesFreeToWalkValidator;

/**
 * This is a factory for MoveValidator
 * @author Troy
 *
 */
public class HantoMoveValidatorFactory {

	private static final HantoMoveValidatorFactory instance = new HantoMoveValidatorFactory();
	
	
	private HantoMoveValidatorFactory(){
		
	}
	
	public static HantoMoveValidatorFactory getInstance(){
		return instance;
	}
	
	/**
	 * Get the move validator for your type of game
	 * @param gameID
	 * 				HantoGameID : of the game type you want your validator to be
	 * @return
	 * 			MoveValidator : of the hantoGameId Passed
	 */
	public MoveValidator makeHantoValidator(HantoGameID gameID){
		AggrigateValidator mv = null;
		switch (gameID) {
			case ALPHA_HANTO:
				break;
			case BETA_HANTO:
				break;
			case GAMMA_HANTO:
				Queue<HantoPieceType> valid = new LinkedList<HantoPieceType>();
				valid.add(HantoPieceType.SPARROW);
				valid.add(HantoPieceType.BUTTERFLY);
				
				mv = new AggrigateValidator();
				
				mv.addValidator(new LocationValidator());
				mv.addValidator(new PieceValidator(valid));
				mv.addValidator(new FirstMoveValidator());
				mv.addValidator(new ButterflyChecker());
				mv.addValidator(new ButterflyPlacedInTimeValidator(4));
				mv.addValidator(new PlaceBySameColorValidator());
				mv.addValidator(new LimitMoveLengthToOneValidator());
				mv.addValidator(new TwoSpacesFreeToWalkValidator());
				mv.addValidator(new MoveRealPieceValidator());
				mv.addValidator(new PlayingTooManyPieceValidator(6));
				break;
			
			default:
				break;
		}
		return mv;
	}
}
