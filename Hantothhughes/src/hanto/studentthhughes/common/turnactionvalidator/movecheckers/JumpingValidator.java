/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package hanto.studentthhughes.common.turnactionvalidator.movecheckers;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPiece;
import hanto.studentthhughes.common.coordinate.HantoCoordinateImpl;
import hanto.studentthhughes.common.hantoboardandboardtools.HantoBoard;
import hanto.studentthhughes.common.movecounter.MoveCounterImpl;
import hanto.studentthhughes.common.turnactionvalidator.AbsTurnActionValidator;
import hanto.studentthhughes.common.turnactionvalidator.TurnActionValidator;

/**
 * This is a jumpingValidator, it is used to validate jumping moves in the HantoGame
 * @author Troy
 *
 */
public class JumpingValidator extends AbsTurnActionValidator implements TurnActionValidator {


	/*
	 * (non-Javadoc)
	 * @see hanto.studentthhughes.common.movevalidator.AbsMoveValidator#handleMoveCheck(hanto.studentthhughes.common.hantoboard.HantoBoard, hanto.common.HantoPiece, hanto.studentthhughes.common.movecounter.MoveCounterImpl, hanto.studentthhughes.common.coordinate.HantoCoordinateImpl, hanto.studentthhughes.common.coordinate.HantoCoordinateImpl)
	 */
	@Override
	protected void handleMoveCheck(HantoBoard theBoard, HantoPiece piece, MoveCounterImpl counter,
			HantoCoordinateImpl to, HantoCoordinateImpl from) {
		
		if(!theBoard.isLocationOccupied(to)){
			validResult =  isMovingAlongY(to, from) || 
					isMovingAlongX(to, from) ||
					isMovingAlongXY(to, from);
			
		}else{
			validResult = false;
		}
		
	}

	@Override
	public void invalidError() throws HantoException {
		throw new HantoException("Cannot jump this move.");

	}
	

	private boolean isMovingAlongX(HantoCoordinate start, HantoCoordinate end) {
		return Math.abs((start.getY() - end.getY())) == 0 && 
		Math.abs((start.getX() - end.getX())) > 0;
	}

	private boolean isMovingAlongY(HantoCoordinate start, HantoCoordinate end) {
		return Math.abs((start.getX() - end.getX())) == 0 && 
				Math.abs((start.getY() - end.getY())) > 0;
	}
	
	private boolean isMovingAlongXY(HantoCoordinate start, HantoCoordinate end) {
		return Math.abs((start.getX() - end.getX())) ==
				Math.abs((start.getY() - end.getY()));
	}
	

}
