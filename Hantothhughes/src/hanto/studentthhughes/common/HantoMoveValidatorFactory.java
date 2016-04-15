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
import hanto.studentthhughes.common.movevalidator.MasterValidator;
import hanto.studentthhughes.common.movevalidator.MoveValidator;
import hanto.studentthhughes.common.movevalidator.dualchecks.GeneralPieceValidator;
import hanto.studentthhughes.common.movevalidator.movecheckers.LocationValidator;
import hanto.studentthhughes.common.movevalidator.movecheckers.MoveRealPieceValidator;
import hanto.studentthhughes.common.movevalidator.movecheckers.WalkingValidator;
import hanto.studentthhughes.common.movevalidator.placecheckers.ButterflyPlacedInTimeValidator;
import hanto.studentthhughes.common.movevalidator.placecheckers.CorrectNumberOfPieceTypeValidator;
import hanto.studentthhughes.common.movevalidator.placecheckers.FirstMoveValidator;
import hanto.teststudentthhughes.common.movevalidator.PieceSpecificMoveValidator;

/**
 * This is a factory for MoveValidator
 * @author Troy
 *
 */
public class HantoMoveValidatorFactory {

	int max_num_butterfly;
	int max_num_sparrow;
	int max_num_crab;
	
	int max_move_dist_butterfly;
	int max_move_dist_sparrow;
	int max_move_dist_crab;
	
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
		Queue<HantoPieceType> valid = buildValidList(gameID);
		setPieceMaximumAndMovementDistance(gameID);
		
		MasterValidator mv = new MasterValidator();
		
		switch (gameID) {
			case ALPHA_HANTO:
				break;
			case BETA_HANTO:
				break;
			case GAMMA_HANTO:
				mv.addValidator(new CorrectNumberOfPieceTypeValidator(
						HantoPieceType.BUTTERFLY,max_num_butterfly));
				mv.addValidator(new CorrectNumberOfPieceTypeValidator(
						HantoPieceType.SPARROW,max_num_sparrow));
				
				mv.addValidator(new PieceSpecificMoveValidator(HantoPieceType.BUTTERFLY,
						new WalkingValidator(max_move_dist_butterfly)));
				mv.addValidator(new PieceSpecificMoveValidator(HantoPieceType.SPARROW,
						new WalkingValidator(max_move_dist_sparrow)));

				mv.addValidator(new GeneralPieceValidator(valid));
				
				mv.addValidator(new LocationValidator());
				mv.addValidator(new FirstMoveValidator());
				mv.addValidator(new ButterflyPlacedInTimeValidator(4));
				mv.addValidator(new MoveRealPieceValidator());
				break;
			case DELTA_HANTO:
				
				mv.addValidator(new CorrectNumberOfPieceTypeValidator(
						HantoPieceType.BUTTERFLY,max_num_butterfly));
				mv.addValidator(new CorrectNumberOfPieceTypeValidator(
						HantoPieceType.SPARROW,max_num_sparrow));
				mv.addValidator(new CorrectNumberOfPieceTypeValidator(
						HantoPieceType.CRAB,max_num_crab));
				
				mv.addValidator(new PieceSpecificMoveValidator(HantoPieceType.CRAB,
						new WalkingValidator(max_move_dist_crab)));
				mv.addValidator(new PieceSpecificMoveValidator(HantoPieceType.BUTTERFLY,
						new WalkingValidator(max_move_dist_butterfly)));
				mv.addValidator(new PieceSpecificMoveValidator(HantoPieceType.SPARROW,
						new WalkingValidator(max_move_dist_sparrow)));
				
				mv.addValidator(new GeneralPieceValidator(valid));
				
				mv.addValidator(new LocationValidator());
				mv.addValidator(new FirstMoveValidator());
				mv.addValidator(new ButterflyPlacedInTimeValidator(4));
				mv.addValidator(new MoveRealPieceValidator());
				
			default:
				break;
		}
		return mv;
	}

	private Queue<HantoPieceType> buildValidList(HantoGameID gameID) {
		Queue<HantoPieceType> valid = new LinkedList<HantoPieceType>();
		
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
		default:	
		}
		return valid;
	}
	
	private void setPieceMaximumAndMovementDistance(HantoGameID gameID){
		switch(gameID){
		case GAMMA_HANTO:
			 max_num_butterfly = 1;
			 max_num_sparrow = 5;
			
			 max_move_dist_butterfly = 1;
			 max_move_dist_sparrow = 3;
			break;
		case DELTA_HANTO:
			max_num_butterfly = 1;
			 max_num_sparrow = 5;
			 max_num_crab = 4;
			
			 max_move_dist_butterfly = 1;
			 max_move_dist_sparrow = 3;
			 max_move_dist_crab = 3;
			break;
		default:	
		}
	}
}
