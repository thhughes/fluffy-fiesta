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
import hanto.studentThhughes.common.board.Board;
import hanto.studentThhughes.common.moveCounter.MoveCounter;

/**
 * This is an aggrigate move validator. It can add many validators together and 
 * run their isValidMove command. It makes it easier to chain validators
 * @author Troy
 *
 */
public class AggrigateValidator implements MoveValidator {

	Queue<MoveValidator> validatorList;
	Throwable caughtError;
	public AggrigateValidator(){
		validatorList = new LinkedList<MoveValidator>();
		
	}

	@Override
	public boolean isValidMove(Board theBoard, HantoPiece piece, MoveCounter counter, HantoCoordinate to, HantoCoordinate from) {
		boolean result = true;
		for(MoveValidator mv : validatorList){
			if(!mv.isValidMove(theBoard, piece, counter, to,from)){
				result = false;
				try{
					mv.invalidError();
				}catch (HantoException e){
					caughtError = e;
				}
				break;
			}
		}
		return result;
	}
	/**
	 * Add a validator to run
	 * @param toAdd
	 * 				MoveValidator
	 */
	public void addValidator(MoveValidator toAdd){
		validatorList.add(toAdd);
	}

	@Override
	public void invalidError() throws HantoException {
		throw (HantoException) caughtError;
		
	}
}
