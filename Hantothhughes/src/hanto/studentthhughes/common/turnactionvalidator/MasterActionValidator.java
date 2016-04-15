/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package hanto.studentthhughes.common.turnactionvalidator;

import java.util.LinkedList;
import java.util.Queue;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPiece;
import hanto.studentthhughes.common.hantoboardandboardtools.HantoBoard;
import hanto.studentthhughes.common.movecounter.MoveCounter;

/**
 * This is an aggrigate move validator. It can add many validators together and 
 * run their isValidMove command. It makes it easier to chain validators
 * @author Troy
 *
 */
public class MasterActionValidator implements TurnActionValidator {

	Queue<TurnActionValidator> validatorList;
	Throwable caughtError;
	public MasterActionValidator(){
		validatorList = new LinkedList<TurnActionValidator>();
		
	}

	@Override
	public boolean isValidMove(HantoBoard theBoard, HantoPiece piece, MoveCounter counter, HantoCoordinate to, HantoCoordinate from) {
		boolean result = true;
		for(TurnActionValidator mv : validatorList){
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
	public void addValidator(TurnActionValidator toAdd){
		validatorList.add(toAdd);
	}

	@Override
	public void invalidError() throws HantoException {
		throw (HantoException) caughtError;
		
	}
}
