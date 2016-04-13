/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package hanto.studentthhughes.common.movevalidator;

import hanto.common.*;
import hanto.studentthhughes.common.hantoboard.HantoBoard;
import hanto.studentthhughes.common.movecounter.MoveCounter;

/**
 * This is an interface for validating a move that is made on the board. This is 
 * part of a strategy pattern to allow the system to easily be expanded for piece 
 * validation.
 * @author Troy
 *
 */
public interface MoveValidator {
	
	
	/**
	 * This method checks if the move is valid, returns a boolean: true is a valid method. 
	 * @param theBoard
	 * 				Board Implementation
	 * @param piece
	 * 				HantoPiece Implementation represneting the piece to be placed. 
	 * @param counter
	 * 				MoveCounter implementation
	 * @param to
	 * 			HantoCoordinate Implementation representating the starting location of the peice
	 * @param from
	 * 			HantoCoordinate Implementation representating the end location of the peice
	 * @return
	 * 			boolean : true if the move is valid
	 */
	boolean isValidMove(HantoBoard theBoard, HantoPiece piece, 
			MoveCounter counter,HantoCoordinate to,HantoCoordinate from);
	
	
	/**
	 * Error message for a validator.
	 * @throws HantoException
	 */
	void invalidError() throws HantoException;
}
