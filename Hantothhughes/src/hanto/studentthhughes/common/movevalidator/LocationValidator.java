/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package hanto.studentthhughes.common.movevalidator;

import java.util.Queue;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPiece;
import hanto.common.HantoPlayerColor;
import hanto.studentthhughes.common.coordinate.HantoCoordinateImpl;
import hanto.studentthhughes.common.hantoboard.HantoBoard;
import hanto.studentthhughes.common.movecounter.MoveCounter;

/**
 * This class is used to make sure that the piece is moved to a contiguous location. 
 * 
 * @author Troy
 *
 */
public class LocationValidator implements MoveValidator {

	HantoCoordinateImpl boardOrigin = new HantoCoordinateImpl(0,0);
	
	/* (non-Javadoc)
	 * @see hanto.studentThhughes.common.moveValidator.MoveValidator#isValidMove(hanto.studentThhughes.common.board.Board, hanto.studentThhughes.common.frontier.Frontier, hanto.common.HantoPiece, hanto.common.HantoCoordinate)
	 */
	@Override
	public boolean isValidMove(HantoBoard theBoard, HantoPiece piece, MoveCounter counter, HantoCoordinate to, HantoCoordinate from) {
		boolean result = true;
		
		HantoCoordinateImpl toImpl = new HantoCoordinateImpl(to);
		if(isFirstMoveOfGame(counter)){
			if(!boardOrigin.equals(toImpl)){
				result = false;
			}
		}else if(!isMoveAPlace(from)){
			HantoCoordinateImpl fromImpl = new HantoCoordinateImpl(from);
			result = result && 
					checkIfMoveIsToContiguousPeice(theBoard, toImpl, fromImpl) &&
					checkIfPlaceLocationIsNotOccupied(theBoard, toImpl, fromImpl);
		}
		return result;
	}

	private boolean checkIfPlaceLocationIsNotOccupied(HantoBoard theBoard, HantoCoordinateImpl to,
			HantoCoordinateImpl from) {
		// TODO Auto-generated method stub
		return !theBoard.isLocationOccupied(to);
	}

	private boolean checkIfMoveIsToContiguousPeice(HantoBoard theBoard, HantoCoordinateImpl to, HantoCoordinateImpl from) {
		boolean result = false;
		Queue<HantoCoordinate> neighbors = to.getNeighbors();
		
		for(HantoCoordinate hc : neighbors){
			if(theBoard.isLocationOccupied(new HantoCoordinateImpl(hc))){
				if (!areHantoCoordinatesEqual(from, hc)){
					result = true;
					break;
				}
			}
		}
		return result;
	}

	private boolean isMoveAPlace(HantoCoordinate from) {
		return from == null;
	}

	private boolean areHantoCoordinatesEqual(HantoCoordinate from, HantoCoordinate hc) {
		return (new HantoCoordinateImpl(hc)).equals(new HantoCoordinateImpl(from));
	}

	private boolean isFirstMoveOfGame(MoveCounter counter) {
		return counter.getNumberMoves(HantoPlayerColor.BLUE) == 0 &&
				counter.getNumberMoves(HantoPlayerColor.RED) == 0;
	}

	/* (non-Javadoc)
	 * @see hanto.studentThhughes.common.moveValidator.MoveValidator#invalidError()
	 */
	@Override
	public void invalidError() throws HantoException {
		throw new HantoException("Invalid Location Exception");

	}

}
