/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package hanto.studentthhughes.common;

import hanto.common.HantoGameID;
import hanto.common.HantoPieceType;

/**
 * Class to handle the maximum number of pieces or the maximum move distance
 * for a given piece in a given game type. 
 * @author Troy
 *
 */
public class HantoGamePieceInformation {
	
	
	int max_num_butterfly = 0;
	int max_num_sparrow = 0;
	int max_num_crab = 0;
	int max_num_horse = 0;
	
	int max_move_dist_butterfly = 0;
	int max_move_dist_sparrow = 0;
	int max_move_dist_crab = 0;
	int max_move_dist_horse = 0;
	
	/**
	 * Constructor for the game piece Information
	 * @param gameID
	 * 				HantoGameID of the given game.
	 */
	public HantoGamePieceInformation(HantoGameID gameID){
		switch (gameID) {
			case ALPHA_HANTO:
			case BETA_HANTO:
				break;
			case GAMMA_HANTO:
				configureGamma();
				break;
			case DELTA_HANTO:
				configureDelta();
				break;
			case EPSILON_HANTO:
				configureEpsilon();
				break;
			default:
			configureDefault();
				break;
		}
	}

	private void configureDefault() {
		max_num_butterfly = 0;
		max_num_sparrow = 0;
		max_num_crab = 0;
		max_num_horse = 0;
		
		max_move_dist_butterfly = 0;
		max_move_dist_sparrow = 0;
		max_move_dist_crab = 0;
		max_move_dist_horse = 0;
	}

	private void configureEpsilon() {
		max_num_butterfly = 1;
		max_num_sparrow = 2;
		max_num_crab = 6;
		max_num_horse = 4;
		
		max_move_dist_butterfly = 1;
		max_move_dist_sparrow = 5;
		max_move_dist_crab = 1;
		max_move_dist_horse = Integer.MAX_VALUE;
	}

	private void configureDelta() {
		max_num_butterfly = 1;
		max_num_sparrow = 4;
		max_num_crab = 4;
		max_num_horse = 0;
		
		max_move_dist_butterfly = 1;
		max_move_dist_sparrow = Integer.MAX_VALUE;
		max_move_dist_crab = 3;	
		max_move_dist_horse = 0;
	}

	private void configureGamma() {
		max_num_butterfly = 1;
		max_num_sparrow = 5;
		max_num_crab = 0;
		max_num_horse = 0;
		
		max_move_dist_butterfly = 1;
		max_move_dist_sparrow = 1;
		max_move_dist_crab = 0;
		max_move_dist_horse = 0;
	}
	
	
	/**
	 * Get the maximum number of pieces for a given board
	 * @param piece
	 * 				HantoPieceType : piece to get 
	 * @return
	 * 			int : representing the number of pieces
	 */
	public int getMaxNumPiece(HantoPieceType piece){
		int result = 0;
		switch(piece){
			case BUTTERFLY:
				result = max_num_butterfly;
				break;
			case SPARROW:
				result = max_num_sparrow;
				break;
			case CRAB:
				result = max_num_crab;
				break;
			case HORSE:
				result = max_num_horse;
				break;
			default: 
				result = 0;
		}
		return result;
	}
	
	/**
	 * Get the maximum piece distance for a given board
	 * @param piece
	 * 				HantoPieceType : piece to get 
	 * @return
	 * 			int : representing the maximum distance piece
	 */
	public int getMaxMoveDistOfPiece(HantoPieceType piece){
		int result = 0;
		switch(piece){
			case BUTTERFLY:
				result = max_move_dist_butterfly;
				break;
			case SPARROW:
				result = max_move_dist_sparrow;
				break;
			case CRAB:
				result = max_move_dist_crab;
				break;
			case HORSE:
				result = max_move_dist_horse;
				break;
			default: 
				result = 0;
		}
		return result;
	}
}