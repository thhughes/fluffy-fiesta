/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package hanto.studentthhughes.common.movevalidator;

import java.util.LinkedList;
import java.util.Queue;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPiece;
import hanto.studentthhughes.common.coordinate.HantoCoordinateImpl;
import hanto.studentthhughes.common.hantoboard.HantoBoard;
import hanto.studentthhughes.common.movecalculator.MovePieceRangeCalculator;
import hanto.studentthhughes.common.movecalculator.NHexMoveCalculator;
import hanto.studentthhughes.common.movecounter.MoveCounter;

/**
 * @author Troy
 *
 */
public class WalkingPieceValidator implements MoveValidator {
	
	MovePieceRangeCalculator rangeCalc;
	HantoCoordinateImpl toImpl;
	HantoCoordinateImpl fromImpl;
	
	
	public WalkingPieceValidator(){
		rangeCalc = new NHexMoveCalculator(1);
	}

	public WalkingPieceValidator(int maxDistance)
	{
		rangeCalc = new NHexMoveCalculator(maxDistance);
		
	}
	
	/* (non-Javadoc)
	 * @see hanto.studentthhughes.common.movevalidator.MoveValidator#isValidMove(hanto.studentthhughes.common.hantoboard.HantoBoard, hanto.common.HantoPiece, hanto.studentthhughes.common.movecounter.MoveCounter, hanto.common.HantoCoordinate, hanto.common.HantoCoordinate)
	 */
	@Override
	public boolean isValidMove(HantoBoard theBoard, HantoPiece piece, MoveCounter counter, HantoCoordinate to,
			HantoCoordinate from) {
		boolean result;
		
		if(from == null){
			result = true;
		}else{
			setCoordinatImpls(to,from);
			Queue<HantoCoordinateImpl> shared = getSharedPoints(toImpl,fromImpl);
			result = checkTwoEmptySpacesNearDestination(theBoard, shared);
		}

		return result;
	}

	private boolean checkTwoEmptySpacesNearDestination(HantoBoard theBoard, Queue<HantoCoordinateImpl> shared) {
		boolean result = false;
		for(HantoCoordinateImpl hci : shared){
			if(!theBoard.isLocationOccupied(hci)){
				result = true;
				break;
			}
		}
		return result;
	}

	private Queue<HantoCoordinateImpl> getSharedPoints(HantoCoordinateImpl to, HantoCoordinateImpl from) {
		Queue<HantoCoordinateImpl> sharedPoints = new LinkedList<HantoCoordinateImpl>();
		Queue<HantoCoordinateImpl> toNeighbors = toImplList(to.getNeighbors());
		Queue<HantoCoordinateImpl> fromNeighbors = toImplList(from.getNeighbors());
		
		for(HantoCoordinateImpl toHC : toNeighbors){
			for(HantoCoordinateImpl fromHC : fromNeighbors){
				if(toHC.equals(fromHC) && !sharedPoints.contains(to)){
					sharedPoints.add(to);
				}
			}
		}
		
		return sharedPoints;
	}

	private Queue<HantoCoordinateImpl> toImplList(Queue<HantoCoordinate> neighbors) {
		Queue<HantoCoordinateImpl> implList = new LinkedList<HantoCoordinateImpl>();
		for(HantoCoordinate hc : neighbors){
			implList.add(new HantoCoordinateImpl(hc));
		}
		return implList;
	}

	private void setCoordinatImpls(HantoCoordinate to, HantoCoordinate from) {
		toImpl = new HantoCoordinateImpl(to);
		fromImpl = new HantoCoordinateImpl(from);
		
		
	}

	/* (non-Javadoc)
	 * @see hanto.studentthhughes.common.movevalidator.MoveValidator#invalidError()
	 */
	@Override
	public void invalidError() throws HantoException {
		throw new HantoException("Walking error, cannot walk there");

	}

}
