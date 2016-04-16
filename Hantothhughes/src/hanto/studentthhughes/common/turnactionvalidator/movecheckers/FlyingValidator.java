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
			final List<HantoCoordinate> path = astarFlyer.getPath(theBoard, from, to);
			
			// NOTE: PATH INCLUDES THE STARTING NODE, hence '-1'
			validResult = ((path.size() - 1) <= maxDistance);   
		}else{
			validResult = false;
		}
		
	}
}
