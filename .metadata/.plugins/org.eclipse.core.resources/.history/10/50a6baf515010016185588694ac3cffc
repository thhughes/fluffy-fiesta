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
import hanto.studentThhughes.common.board.Board;
import hanto.studentThhughes.common.moveCounter.MoveCounter;

/**
 * @author Troy
 *
 */
public class ButterflyChecker implements MoveValidator {

	/* (non-Javadoc)
	 * @see hanto.studentThhughes.common.moveValidator.MoveValidator#isValidMove(hanto.studentThhughes.common.board.Board, hanto.studentThhughes.common.frontier.Frontier, hanto.common.HantoPiece, hanto.common.HantoCoordinate, hanto.studentThhughes.common.MoveCounter)
	 */
	@Override
	public boolean isValidMove(Board theBoard, HantoPiece piece, MoveCounter counter, HantoCoordinate to,
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
