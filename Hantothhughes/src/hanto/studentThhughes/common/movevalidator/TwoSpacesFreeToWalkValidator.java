/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package hanto.studentThhughes.common.movevalidator;

import java.util.LinkedList;
import java.util.Queue;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPiece;
import hanto.studentThhughes.common.coordinate.HantoCoordinateImpl;
import hanto.studentThhughes.common.hantoboard.HantoBoard;
import hanto.studentThhughes.common.movecounter.MoveCounter;

/**
 * @author Troy
 *
 */
public class TwoSpacesFreeToWalkValidator implements MoveValidator {

	/* (non-Javadoc)
	 * @see hanto.studentThhughes.common.moveValidator.MoveValidator#isValidMove(hanto.studentThhughes.common.board.Board, hanto.common.HantoPiece, hanto.studentThhughes.common.moveCounter.MoveCounter, hanto.common.HantoCoordinate, hanto.common.HantoCoordinate)
	 */
	@Override
	public boolean isValidMove(HantoBoard theBoard, HantoPiece piece, MoveCounter counter, HantoCoordinate to,
			HantoCoordinate from) {
		boolean result = true;
		if(from !=null){
			Queue<HantoCoordinate> fromNeighbors = (new HantoCoordinateImpl(from)).getNeighbors();
			Queue<HantoCoordinate> toNeighbors = (new HantoCoordinateImpl(to)).getNeighbors();
			Queue<HantoCoordinateImpl> overlap = new LinkedList<HantoCoordinateImpl>();
			
			for(HantoCoordinate toN : toNeighbors){
				HantoCoordinateImpl toImpl = new HantoCoordinateImpl(toN);
				
				for(HantoCoordinate frN : fromNeighbors){
					HantoCoordinateImpl frImpl = new HantoCoordinateImpl(frN);
					boolean toAdd = toImpl.equals(frImpl) && !overlap.contains(frImpl);
					if(toAdd){
						overlap.add(frImpl);
					}
				}
			}
			// Iterate over the overlap and find if they have pieces in them
			for(HantoCoordinate oLap : overlap){
				if(!theBoard.isLocationOccupied(oLap)){
					result = true;
					break;
				}
				result = false;
			}
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see hanto.studentThhughes.common.moveValidator.MoveValidator#invalidError()
	 */
	@Override
	public void invalidError() throws HantoException {
		throw new HantoException("Movement Exception: Movement should have two free spaces to execute");

	}

}
