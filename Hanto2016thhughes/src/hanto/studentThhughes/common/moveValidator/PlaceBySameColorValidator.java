/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package hanto.studentThhughes.common.moveValidator;

import java.util.LinkedList;
import java.util.Queue;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPiece;
import hanto.common.HantoPlayerColor;
import hanto.studentThhughes.common.board.Board;
import hanto.studentThhughes.common.coordinate.HantoCoordinateImpl;
import hanto.studentThhughes.common.moveCounter.MoveCounter;

/**
 * @author Troy
 *
 */
public class PlaceBySameColorValidator implements MoveValidator {

	/* (non-Javadoc)
	 * @see hanto.studentThhughes.common.moveValidator.MoveValidator#isValidMove(hanto.studentThhughes.common.board.Board, hanto.studentThhughes.common.frontier.Frontier, hanto.common.HantoPiece, hanto.studentThhughes.common.moveCounter.MoveCounter, hanto.common.HantoCoordinate, hanto.common.HantoCoordinate)
	 */
	@Override
	public boolean isValidMove(Board theBoard, HantoPiece piece, MoveCounter counter, HantoCoordinate to,
			HantoCoordinate from) {
		
		
		boolean result = true;
		if (from == null && counter.getNumberMoves(HantoPlayerColor.BLUE) > 0 
				&& counter.getNumberMoves(HantoPlayerColor.RED) > 0){
			Queue<HantoPlayerColor> touchingPlayers = new LinkedList<HantoPlayerColor>();
			Queue<HantoCoordinate> neighbors = (new HantoCoordinateImpl(to)).getNeighbors();
			
			for(HantoCoordinate hc : neighbors){
				if(theBoard.isLocationOccupied(hc)){
					touchingPlayers.add(theBoard.getFromBoard(hc).getColor());
				}
			}
			
			for(HantoPlayerColor hpc : touchingPlayers){
				if(hpc != piece.getColor()){
					result = false;
					break;
				}
			}
			
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see hanto.studentThhughes.common.moveValidator.MoveValidator#invalidError()
	 */
	@Override
	public void invalidError() throws HantoException {
		throw new HantoException("Placement Error: may not be touching enemy pieces");

	}

}
