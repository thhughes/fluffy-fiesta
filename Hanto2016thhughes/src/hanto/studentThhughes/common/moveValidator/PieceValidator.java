/**
 * 
 */
package hanto.studentThhughes.common.moveValidator;

import java.util.LinkedList;
import java.util.Queue;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.studentThhughes.common.board.Board;
import hanto.studentThhughes.common.moveCounter.MoveCounter;

/**
 * @author Troy
 *
 */
public class PieceValidator implements MoveValidator {

	Queue<HantoPieceType> validList;
	
	/**
	 * Empty constructor, not recommended use. No pieces are valid with use of this.
	 */
	public PieceValidator(){
		validList = new LinkedList<HantoPieceType>();
	}
	
	/**
	 * Overloaded COnstructor
	 * @param inputList
	 * 					Queue<HantoPieceType> to be inputted into the allowed pieces
	 */
	public PieceValidator(Queue<HantoPieceType> inputList){
		validList = inputList;
	}
	
	
	/* (non-Javadoc)
	 * @see hanto.studentThhughes.common.moveValidator.MoveValidator#isValidMove(hanto.studentThhughes.common.board.Board, hanto.studentThhughes.common.frontier.Frontier, hanto.common.HantoPiece, hanto.common.HantoCoordinate)
	 */
	@Override
	public boolean isValidMove(Board theBoard, HantoPiece piece, MoveCounter counter, HantoCoordinate to, HantoCoordinate from) {
		return validList.contains(piece.getType());
	}

	@Override
	public void invalidError() throws HantoException {
		throw new HantoException("Invalid Piece Exception");
		
	}

}