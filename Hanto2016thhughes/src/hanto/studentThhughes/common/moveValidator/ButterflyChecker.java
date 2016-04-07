/**
 * 
 */
package hanto.studentThhughes.common.moveValidator;

import java.util.Collection;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentThhughes.common.MoveCounter;
import hanto.studentThhughes.common.board.Board;
import hanto.studentThhughes.common.frontier.Frontier;

/**
 * @author Troy
 *
 */
public class ButterflyChecker implements MoveValidator {

	/* (non-Javadoc)
	 * @see hanto.studentThhughes.common.moveValidator.MoveValidator#isValidMove(hanto.studentThhughes.common.board.Board, hanto.studentThhughes.common.frontier.Frontier, hanto.common.HantoPiece, hanto.common.HantoCoordinate, hanto.studentThhughes.common.MoveCounter)
	 */
	@Override
	public boolean isValidMove(Board theBoard, Frontier theFrontier, HantoPiece piece, HantoCoordinate where,
			MoveCounter counter) {
		HantoPlayerColor moveColor = piece.getColor();
		Collection<HantoPiece> playerPieces = theBoard.getPlayerPieces(moveColor).values();
		int numberButterflies = 0;
		
		for(HantoPiece hp : playerPieces){
			if(hp.getType() == HantoPieceType.BUTTERFLY){
				numberButterflies++;
			}
		}
		
		return !(numberButterflies > 0 && piece.getType() == HantoPieceType.BUTTERFLY);
	}

	/* (non-Javadoc)
	 * @see hanto.studentThhughes.common.moveValidator.MoveValidator#invalidError()
	 */
	@Override
	public void invalidError() throws HantoException {
		throw new HantoException("Butterfly Check Error: Cannot add two butterflies");

	}

}
