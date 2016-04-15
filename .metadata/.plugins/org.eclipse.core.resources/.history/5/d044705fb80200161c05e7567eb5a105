package hanto.studentthhughes.common;

import hanto.common.HantoPlayerColor;

import java.util.HashMap;
import java.util.Map;

import hanto.common.HantoException;
import hanto.common.HantoPieceType;


public class PlayerState {

	private int turnCount = 0;
	private final Map<HantoPieceType, Integer> maxNumberOfEachPiece;
	private Map<HantoPieceType,Integer> currentNumberOfPiece = new HashMap<HantoPieceType,Integer>();
	
	
	public PlayerState(HantoPlayerColor color, Map<HantoPieceType,Integer> pieceTypeWithLimit)
	{
		maxNumberOfEachPiece = pieceTypeWithLimit;
		initializeCurrentPieceMap();
		
	}

	
	/**
	 * Get's the turn number for this player state
	 * @return
	 * 			int 
	 * 				representing the turn number for this player
	 */
	public int getTurnNumber(){
		return turnCount;
	}
	
	/**
	 * Increments the counter for this players turn number
	 */
	public void incrementTurnNumber(){
		turnCount++;
	}
	
	/**
	 * Updates a player's piece map to record the accurate number of pieces 
	 * on the board
	 * @param hpt
	 * 			HantoPieceType
	 * @throws
	 * 			HantoException if the piece is not a valid pace
	 */
	public void recordPlacedPiece(HantoPieceType hpt) throws HantoException
	{
		if(currentNumberOfPiece.containsKey(hpt))
		{
			currentNumberOfPiece.put(hpt, currentNumberOfPiece.get(hpt)+1);
		}else{
			throw new HantoException("Piece Is Not valid");
		}
		
	}
	
	
	/**
	 * Initializes the current Piece Map to 0 for every piece. 
	 */
	private void initializeCurrentPieceMap() {
		for(HantoPieceType HPT : maxNumberOfEachPiece.keySet()){
			currentNumberOfPiece.put(HPT, new Integer(0));
		}
	}
	
	
	
}

