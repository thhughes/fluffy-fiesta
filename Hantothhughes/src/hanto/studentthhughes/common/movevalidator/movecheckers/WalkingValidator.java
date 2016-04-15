/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package hanto.studentthhughes.common.movevalidator.movecheckers;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPiece;
import hanto.studentthhughes.common.coordinate.HantoCoordinateImpl;
import hanto.studentthhughes.common.hantoboard.HantoBoard;
import hanto.studentthhughes.common.movecounter.MoveCounterImpl;
import hanto.studentthhughes.common.movevalidator.AbsMoveValidator;
import hanto.studentthhughes.common.movevalidator.MoveValidator;

/**
 * This is a validator to measure and rule on pieces walking 
 * 
 * @author Troy
 *
 */
public class WalkingValidator extends AbsMoveValidator implements MoveValidator {

	HantoCoordinateImpl toImpl;
	HantoCoordinateImpl fromImpl;
	int maxDistance;
	
	public WalkingValidator(){
		maxDistance = 1;
	}

	public WalkingValidator(int maxDist)
	{
		maxDistance = maxDist;
	}
	
	/*
	 * (non-Javadoc)
	 * @see hanto.studentthhughes.common.movevalidator.AbsMoveValidator#handleMoveCheck(hanto.studentthhughes.common.hantoboard.HantoBoard, hanto.common.HantoPiece, hanto.studentthhughes.common.movecounter.MoveCounterImpl, hanto.studentthhughes.common.coordinate.HantoCoordinateImpl, hanto.studentthhughes.common.coordinate.HantoCoordinateImpl)
	 */
	@Override
	protected void handleMoveCheck(HantoBoard theBoard, HantoPiece piece, MoveCounterImpl counter,
			HantoCoordinateImpl to, HantoCoordinateImpl from) {
		if(!theBoard.isLocationOccupied(to)){
			List<HantoCoordinate> path = theBoard.getPath(from, to);
			validResult = checkPathForRoomToWalk(theBoard, path);
			validResult = validResult && ((path.size()-1) <= maxDistance);   // NOTE: PATH INCLUDES THE STARTING NODE, so must remove it to get 'moves
		}else{
			validResult = false;
		}
		
	}
	
	/**
	 * Checks every hex-hex movement to ensure that there is enough room to walk that
	 * distance.
	 * @param theBoard
	 * 				HantoBoard
	 * @param path
	 * 			List<HantoCoordinate>
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
	 * 				HantoBoard
	 * @param shared
	 * 				Queue<HantoCoordinateImpl>
	 * @return
	 * 			Boolean : True if there is at least one empty hex in the list.
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
	 * 			HantoCoordinateImpl
	 * @param from
	 * 			HantoCoordinateImpl
	 * @return
	 * 			Queue<HantoCoordinateImpl> of the shared neighbors between to and from
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
	 * 
	 * @param neighbors
	 * 				Queue<HantoCoordinate> 
	 * @return
	 * 			Queue<HantoCoordinateImpl> 
	 */
	private Queue<HantoCoordinateImpl> toImplList(Queue<HantoCoordinate> neighbors) {
		Queue<HantoCoordinateImpl> implList = new LinkedList<HantoCoordinateImpl>();
		for(HantoCoordinate hc : neighbors){
			implList.add(new HantoCoordinateImpl(hc));
		}
		return implList;
	}
}
