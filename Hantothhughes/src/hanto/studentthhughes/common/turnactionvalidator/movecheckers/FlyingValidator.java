/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package hanto.studentthhughes.common.turnactionvalidator.movecheckers;

import java.util.List;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPiece;
import hanto.studentthhughes.common.coordinate.HantoCoordinateImpl;
import hanto.studentthhughes.common.hantoboardandboardtools.HantoBoard;
import hanto.studentthhughes.common.hantoboardandboardtools.pathbuilder.AStarFlying;
import hanto.studentthhughes.common.hantoboardandboardtools.pathbuilder.PathBuilder;
import hanto.studentthhughes.common.movecounter.MoveCounterImpl;
import hanto.studentthhughes.common.turnactionvalidator.AbsTurnActionValidator;
import hanto.studentthhughes.common.turnactionvalidator.TurnActionValidator;

/**
 * This is a Turn Action Validator that that checks how far/where a piece that can 
 * fly can go. 
 * @author Troy
 *
 */
public class FlyingValidator extends AbsTurnActionValidator implements TurnActionValidator{

	HantoCoordinateImpl toImpl;
	HantoCoordinateImpl fromImpl;
	int maxDistance;
	PathBuilder astarFlyer = new AStarFlying();


	/**
	 * Constructor 
	 * @param maxDist
	 * 				Int : the max distance that the piece can fly
	 */
	public FlyingValidator(int maxDist)
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
			try{
				validResult = isFlyingPathValid(theBoard, to, from); 
			}
			catch (HantoException e){  // There is no valid path
				validResult = false;
			}
		}else{
			validResult = false;
		}
		
	}

	/**
	 * This function finds a path from one node to another and then returns a boolean on if
	 * the path is shorter than or equal to the maximum distance. 
	 * 
	 * @param theBoard
	 * 				HantoBoard
	 * @param to
	 * 			HantoCoordinateImpl
	 * @param from
	 * 			HantoCoordinateImpl
	 * @return
	 * 			Boolean : true if the flight path is valid
	 * @throws HantoException
	 * 			if there is no valid path to be taken
	 */
	private boolean isFlyingPathValid(HantoBoard theBoard, HantoCoordinateImpl to, HantoCoordinateImpl from)
			throws HantoException {
		final List<HantoCoordinate> path = astarFlyer.getPath(theBoard, from, to);
		// NOTE: PATH INCLUDES THE STARTING NODE, hence '-1'
		boolean someBoolean = ((path.size() - 1) <= maxDistance);
		return someBoolean;
	}
}
