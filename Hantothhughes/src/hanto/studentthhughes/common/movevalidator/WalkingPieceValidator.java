/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package hanto.studentthhughes.common.movevalidator;

import java.util.LinkedList;
import java.util.List;
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
	
	HantoCoordinateImpl toImpl;
	HantoCoordinateImpl fromImpl;
	int maxDistance;
	
	
	public WalkingPieceValidator(){
		maxDistance = 1;
	}

	public WalkingPieceValidator(int maxDist)
	{
		maxDistance = maxDist;
		
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
			List<HantoCoordinate> path = theBoard.getPath(from, to);
			result = checkPathForRoomToWalk(theBoard, path);
			result = result && ((path.size()-1) <= maxDistance);   // NOTE: PATH INCLUDES THE STARTING NODE, so must remove it to get 'moves'
		}

		return result;
	}

	/**
	 * Checks every hex-hex movement to ensure that there is enough room to walk that
	 * distance.
	 * @param theBoard
	 * @param path
	 * @return
	 */
	private boolean checkPathForRoomToWalk(HantoBoard theBoard, List<HantoCoordinate> path) {
		boolean result = true;
		
		for(int i = 0; i < (path.size() - 1); i++){
			Queue<HantoCoordinateImpl> sharedList = getSharedPoints(new HantoCoordinateImpl(path.get(i)),
																	new HantoCoordinateImpl(path.get(i+1)));
			result = result && containsEmptySharedSpace(theBoard, sharedList);
		}
		return result;
	}

	/**
	 * Checks if the given Queue<HantoCoordinateImpl contains at least 1 empty hex
	 * @param theBoard
	 * @param shared
	 * @return
	 */
	private boolean containsEmptySharedSpace(HantoBoard theBoard, Queue<HantoCoordinateImpl> shared)
	{
		boolean result = false;
		for(HantoCoordinateImpl hci : shared){
			if(!theBoard.isLocationOccupied(hci)){
				result = true;
				break;
			}
		}
		return result;
	}
	
	/**
	 * Creates a Queue<HantoCoordinateImpl> of points shared between the two input coordinates
	 * @param to
	 * @param from
	 * @return
	 */
	private Queue<HantoCoordinateImpl> getSharedPoints(HantoCoordinateImpl to, HantoCoordinateImpl from) {
		Queue<HantoCoordinateImpl> sharedPoints = new LinkedList<HantoCoordinateImpl>();
		Queue<HantoCoordinateImpl> toNeighbors = toImplList(to.getNeighbors());
		Queue<HantoCoordinateImpl> fromNeighbors = toImplList(from.getNeighbors());
		
		for(HantoCoordinateImpl toHC : toNeighbors){
			for(HantoCoordinateImpl fromHC : fromNeighbors){
				if(toHC.equals(fromHC) && !sharedPoints.contains(toHC)){
					sharedPoints.add(toHC);
				}
			}
		}
		
		return sharedPoints;
	}

	/**
	 * convert queue of HantoCoordinate to queue HantoCoordinateImpl
	 * @param neighbors
	 * @return
	 */
	private Queue<HantoCoordinateImpl> toImplList(Queue<HantoCoordinate> neighbors) {
		Queue<HantoCoordinateImpl> implList = new LinkedList<HantoCoordinateImpl>();
		for(HantoCoordinate hc : neighbors){
			implList.add(new HantoCoordinateImpl(hc));
		}
		return implList;
	}


	/* (non-Javadoc)
	 * @see hanto.studentthhughes.common.movevalidator.MoveValidator#invalidError()
	 */
	@Override
	public void invalidError() throws HantoException {
		throw new HantoException("Walking error, cannot walk there");

	}

}
