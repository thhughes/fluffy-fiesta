package hanto.studentThhughes.common.moveValidator;

import hanto.common.*;
import hanto.studentThhughes.common.board.Board;
import hanto.studentThhughes.common.frontier.Frontier;
import hanto.studentThhughes.common.moveCounter.MoveCounter;

public interface MoveValidator {
	
	
	
	public boolean isValidMove(Board theBoard, Frontier theFrontier, 
			HantoPiece piece,MoveCounter counter,HantoCoordinate to, HantoCoordinate from);
	
	
	public void invalidError() throws HantoException;
}
