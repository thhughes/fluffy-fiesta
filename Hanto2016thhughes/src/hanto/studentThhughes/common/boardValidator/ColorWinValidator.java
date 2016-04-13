/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.studentThhughes.common.boardValidator;

import java.util.Map;
import java.util.Queue;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentThhughes.common.board.Board;
import hanto.studentThhughes.common.coordinate.HantoCoordinateImpl;
import hanto.studentThhughes.common.moveCounter.MoveCounter;

/**
 * This is a validator for the board that checks if a specific color won.
 * 
 * @author Troy
 *
 */
public class ColorWinValidator implements BoardValidator {
	
	HantoPlayerColor playerColor;
	/**
	 * Constructor of the ColorWinValidator
	 * @param col
	 * 			HantoPlayerColor : representing the color for this validator
	 */
	public ColorWinValidator(HantoPlayerColor col){
		playerColor = col;
	}
	
	/* (non-Javadoc)
	 * @see hanto.studentThhughes.common.boardValidator.BoardValidator#getOutcome(hanto.studentThhughes.common.board.Board, hanto.studentThhughes.common.moveCounter.MoveCounter)
	 */
	@Override
	public MoveResult getOutcome(Board theBoard, MoveCounter counter) {
		MoveResult result = MoveResult.OK;
		Map<HantoCoordinate, HantoPiece> playerPiece = getRivalMap(theBoard);
		HantoCoordinateImpl butterfly = null;
		for(HantoCoordinate hc: playerPiece.keySet()){
			if(playerPiece.get(hc).getType()==HantoPieceType.BUTTERFLY){
				butterfly = new HantoCoordinateImpl(hc);
				break;
			}
		}
		if(butterfly != null){
			Queue<HantoCoordinate> neighbors = butterfly.getNeighbors();
			// Set the winning team
			if(playerColor == HantoPlayerColor.RED){
				result = MoveResult.RED_WINS;
			}else{
				result = MoveResult.BLUE_WINS;
			}
			
			for(HantoCoordinate hc: neighbors){
				if(!theBoard.isLocationOccupied(new HantoCoordinateImpl(hc))){
					// Catch and remove the win.
					result = MoveResult.OK;
					break;
				}
			}
		}
		
		
		return result;
	}
	
	private Map<HantoCoordinate, HantoPiece> getRivalMap(Board theBoard){
		Map<HantoCoordinate, HantoPiece> result;
		if(playerColor == HantoPlayerColor.RED){
			result = theBoard.getPlayerPieces(HantoPlayerColor.BLUE);
		}else{
			result = theBoard.getPlayerPieces(HantoPlayerColor.RED);
		}
		
		return result;
	}

}
