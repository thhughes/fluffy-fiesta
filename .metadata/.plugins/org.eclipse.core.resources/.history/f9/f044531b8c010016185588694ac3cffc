/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package hanto.studentThhughes.common.movevalidator;

import java.util.Collection;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentThhughes.common.hantoboard.HantoBoard;
import hanto.studentThhughes.common.movecounter.MoveCounter;

/**
 * @author Troy
 *
 */
public class ButterflyChecker implements MoveValidator {

	/* (non-Javadoc)
	 * @see hanto.studentThhughes.common.moveValidator.MoveValidator#isValidMove(hanto.studentThhughes.common.board.Board, hanto.studentThhughes.common.frontier.Frontier, hanto.common.HantoPiece, hanto.common.HantoCoordinate, hanto.studentThhughes.common.MoveCounter)
	 */
	@Override
	public boolean isValidMove(HantoBoard theBoard, HantoPiece piece, MoveCounter counter, HantoCoordinate to,
			HantoCoordinate from) {
		boolean result = true;
		
		if(from == null){
			HantoPlayerColor moveColor = piece.getColor();
			Collection<HantoPiece> playerPieces = theBoard.getPlayerPieces(moveColor).values();
			int numberButterflies = 0;
			
			for(HantoPiece hp : playerPieces){
				if(hp.getType() == HantoPieceType.BUTTERFLY){
					numberButterflies++;
				}
			}
			
			result = !(numberButterflies > 0 && piece.getType() == HantoPieceType.BUTTERFLY);
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see hanto.studentThhughes.common.moveValidator.MoveValidator#invalidError()
	 */
	@Override
	public void invalidError() throws HantoException {
		throw new HantoException("Butterfly Check Error: Cannot add two butterflies");

	}


}
