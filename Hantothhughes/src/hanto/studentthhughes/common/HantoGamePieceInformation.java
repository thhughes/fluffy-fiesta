package hanto.studentthhughes.common;

import hanto.common.HantoGameID;
import hanto.common.HantoPieceType;

public class HantoGamePieceInformation {
	
	
	int max_num_butterfly = 0;
	int max_num_sparrow = 0;
	int max_num_crab = 0;
	int max_num_horse = 0;
	
	int max_move_dist_butterfly = 0;
	int max_move_dist_sparrow = 0;
	int max_move_dist_crab = 0;
	int max_move_dist_horse = 0;
	
	public HantoGamePieceInformation(HantoGameID gameID){
		switch (gameID) {
			case ALPHA_HANTO:
			case BETA_HANTO:
				break;
			case GAMMA_HANTO:
				setGamma();
				break;
			case DELTA_HANTO:
				setDelta();
				break;
			case EPSILON_HANTO:
				setEpsilon();
				break;
			default:
			setDefault();
				break;
		}
	}

	private void setDefault() {
		max_num_butterfly = 0;
		max_num_sparrow = 0;
		max_num_crab = 0;
		max_num_horse = 0;
		
		max_move_dist_butterfly = 0;
		max_move_dist_sparrow = 0;
		max_move_dist_crab = 0;
		max_move_dist_horse = 0;
	}

	private void setEpsilon() {
		max_num_butterfly = 1;
		max_num_sparrow = 2;
		max_num_crab = 6;
		max_num_horse = 4;
		
		max_move_dist_butterfly = 1;
		max_move_dist_sparrow = 5;
		max_move_dist_crab = 1;
		max_move_dist_horse = Integer.MAX_VALUE;
	}

	private void setDelta() {
		max_num_butterfly = 1;
		max_num_sparrow = 4;
		max_num_crab = 4;
		max_num_horse = 0;
		
		max_move_dist_butterfly = 1;
		max_move_dist_sparrow = Integer.MAX_VALUE;
		max_move_dist_crab = 3;	
		max_move_dist_horse = 0;
	}

	private void setGamma() {
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
