package hanto.studentThhughes.common.moveValidator;

import static hanto.common.HantoPieceType.BUTTERFLY;

import java.util.Collection;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentThhughes.common.board.Board;
import hanto.studentThhughes.common.frontier.Frontier;
import hanto.studentThhughes.common.moveCounter.MoveCounter;

public class ButterflyPlacedInTimeValidator implements MoveValidator{
	Integer numberOfTurns;
	public ButterflyPlacedInTimeValidator(int numTurns){
		numberOfTurns = new Integer(numTurns);
	}
	
	
	@Override
	public boolean isValidMove(Board theBoard, Frontier theFrontier, HantoPiece piece, MoveCounter counter,
			HantoCoordinate to, HantoCoordinate from) {
		HantoPlayerColor moveColor = piece.getColor();
		Collection<HantoPiece> playerPieces = theBoard.getPlayerPieces(moveColor).values();
		boolean playerHasButterfly = false;
		for (HantoPiece hp : playerPieces){
			if(hp.getType() == HantoPieceType.BUTTERFLY){
				playerHasButterfly = true;
			}
		}
		
		boolean result = true;
		if(counter.getNumberMoves(piece.getColor()) == numberOfTurns){
			if(!playerHasButterfly && piece.getType() != HantoPieceType.BUTTERFLY){
				result = false;
			}
		}
		
		return result;
	}
	
	
	
	/*
	 * (non-Javadoc)
	 * @see hanto.studentThhughes.common.moveValidator.MoveValidator#invalidError()
	 */

	@Override
	public void invalidError() throws HantoException {
		throw new HantoException("Butterfly Placing Error: Must place butterfly by "+numberOfTurns.toString()+" Turns");
		
	}

}
