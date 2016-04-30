/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package hanto.studentthhughes.common;


import java.util.LinkedList;
import java.util.Queue;

import hanto.common.HantoGameID;
import hanto.common.HantoPieceType;
import static hanto.common.HantoPieceType.*;

import hanto.studentthhughes.common.turnactionvalidator.MasterActionValidator;
import hanto.studentthhughes.common.turnactionvalidator.PieceSpecificTurnActionValidator;
import hanto.studentthhughes.common.turnactionvalidator.TurnActionValidator;
import hanto.studentthhughes.common.turnactionvalidator.moveandplacechecks.GeneralActionValidator;
import hanto.studentthhughes.common.turnactionvalidator.movecheckers.FlyingValidator;
import hanto.studentthhughes.common.turnactionvalidator.movecheckers.JumpingValidator;
import hanto.studentthhughes.common.turnactionvalidator.movecheckers.WalkingValidator;
import hanto.studentthhughes.common.turnactionvalidator.placecheckers.ButterflyPlacedInTimeValidator;
import hanto.studentthhughes.common.turnactionvalidator.placecheckers.CorrectNumberOfPieceTypeValidator;
import hanto.studentthhughes.common.turnactionvalidator.placecheckers.FirstMoveValidator;

/**
 * This is a factory for MoveValidator
 * @author Troy
 *
 */
public class HantoTurnActionValidatorFactory {

	int max_num_butterfly;
	int max_num_sparrow;
	int max_num_crab;
	int max_num_horse;
	
	int max_move_dist_butterfly;
	int max_move_dist_sparrow;
	int max_move_dist_crab;
	int max_move_dist_horse;
	
	private static final HantoTurnActionValidatorFactory instance = 
			new HantoTurnActionValidatorFactory();
	
	
	private HantoTurnActionValidatorFactory(){
		
	}
	
	public static HantoTurnActionValidatorFactory getInstance(){
		return instance;
	}
	
	/**
	 * Get the move validator for your type of game
	 * @param gameID
	 * 				HantoGameID : of the game type you want your validator to be
	 * @return
	 * 			MoveValidator : of the hantoGameId Passed
	 */
	public TurnActionValidator makeHantoValidator(HantoGameID gameID){
		final Queue<HantoPieceType> valid = buildValidList(gameID);
		HantoGamePieceInformation pieceMaster = new HantoGamePieceInformation(gameID);
		
		final MasterActionValidator mav = new MasterActionValidator();
		
		// Add general validators:
		mav.addValidator(new GeneralActionValidator(valid));
		
		mav.addValidator(new FirstMoveValidator());
		mav.addValidator(new ButterflyPlacedInTimeValidator(4));
		
		switch (gameID) {
			case ALPHA_HANTO:
			case BETA_HANTO:
				break;
			case GAMMA_HANTO:
				mav.addValidator(new CorrectNumberOfPieceTypeValidator(
						BUTTERFLY, pieceMaster.getMaxNumPiece(BUTTERFLY)));
				mav.addValidator(new CorrectNumberOfPieceTypeValidator(
						SPARROW, pieceMaster.getMaxNumPiece(SPARROW)));
				
				mav.addValidator(new PieceSpecificTurnActionValidator(BUTTERFLY,
						new WalkingValidator(pieceMaster.getMaxMoveDistOfPiece(BUTTERFLY))));
				mav.addValidator(new PieceSpecificTurnActionValidator(SPARROW,
						new WalkingValidator(pieceMaster.getMaxMoveDistOfPiece(SPARROW))));

				
				break;
			case DELTA_HANTO:
				
				mav.addValidator(new CorrectNumberOfPieceTypeValidator(
						BUTTERFLY, pieceMaster.getMaxNumPiece(BUTTERFLY)));
				mav.addValidator(new CorrectNumberOfPieceTypeValidator(
						SPARROW, pieceMaster.getMaxNumPiece(SPARROW)));
				mav.addValidator(new CorrectNumberOfPieceTypeValidator(
						CRAB, pieceMaster.getMaxNumPiece(CRAB)));
				
				mav.addValidator(new PieceSpecificTurnActionValidator(CRAB,
						new WalkingValidator(pieceMaster.getMaxMoveDistOfPiece(CRAB))));
				mav.addValidator(new PieceSpecificTurnActionValidator(BUTTERFLY,
						new WalkingValidator(pieceMaster.getMaxMoveDistOfPiece(BUTTERFLY))));
				mav.addValidator(new PieceSpecificTurnActionValidator(SPARROW,
						new FlyingValidator(pieceMaster.getMaxMoveDistOfPiece(SPARROW))));
			
			case EPSILON_HANTO:
				
				mav.addValidator(new CorrectNumberOfPieceTypeValidator(
						BUTTERFLY, pieceMaster.getMaxNumPiece(BUTTERFLY)));
				mav.addValidator(new CorrectNumberOfPieceTypeValidator(
						SPARROW, pieceMaster.getMaxNumPiece(SPARROW)));
				mav.addValidator(new CorrectNumberOfPieceTypeValidator(
						CRAB, pieceMaster.getMaxNumPiece(CRAB)));
				mav.addValidator(new CorrectNumberOfPieceTypeValidator(
						HORSE, pieceMaster.getMaxNumPiece(HORSE)));
				
				mav.addValidator(new PieceSpecificTurnActionValidator(CRAB,
						new WalkingValidator(pieceMaster.getMaxMoveDistOfPiece(CRAB))));
				mav.addValidator(new PieceSpecificTurnActionValidator(BUTTERFLY,
						new WalkingValidator(pieceMaster.getMaxMoveDistOfPiece(BUTTERFLY))));
				mav.addValidator(new PieceSpecificTurnActionValidator(SPARROW,
						new FlyingValidator(pieceMaster.getMaxMoveDistOfPiece(SPARROW))));
				mav.addValidator(new PieceSpecificTurnActionValidator(HORSE,
						new JumpingValidator()));
				
			default:
				break;
		}
		return mav;
	}

	
	private Queue<HantoPieceType> buildValidList(HantoGameID gameID) {
		final Queue<HantoPieceType> valid = new LinkedList<HantoPieceType>();
		
		switch(gameID){
		case GAMMA_HANTO:
			valid.clear();
			valid.add(HantoPieceType.SPARROW);
			valid.add(HantoPieceType.BUTTERFLY);
			break;
		case DELTA_HANTO:
			valid.clear();
			valid.add(HantoPieceType.SPARROW);
			valid.add(HantoPieceType.BUTTERFLY);
			valid.add(HantoPieceType.CRAB);
			break;
		case EPSILON_HANTO:
			valid.clear();
			valid.add(HantoPieceType.SPARROW);
			valid.add(HantoPieceType.BUTTERFLY);
			valid.add(HantoPieceType.CRAB);
			valid.add(HantoPieceType.HORSE);
			break;
		default:
			break;
		}
		return valid;
	}
	

}
