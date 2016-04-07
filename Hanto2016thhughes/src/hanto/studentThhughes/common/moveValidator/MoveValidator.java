package hanto.studentThhughes.common.moveValidator;

import hanto.common.*;
import hanto.studentThhughes.common.board.Board;
import hanto.studentThhughes.common.frontier.Frontier;

public interface MoveValidator {
	
	
	
	public boolean isValidMove(Board theBoard, Frontier theFrontier, 
			HantoPiece piece,HantoCoordinate where);
	
	
	public void invalidError() throws HantoException;
}
