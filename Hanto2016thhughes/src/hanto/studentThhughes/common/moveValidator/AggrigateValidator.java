package hanto.studentThhughes.common.moveValidator;

import java.util.Queue;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPiece;
import hanto.studentThhughes.common.board.Board;
import hanto.studentThhughes.common.frontier.Frontier;

public class AggrigateValidator implements MoveValidator {

	Queue<MoveValidator> validatorList;
	public AggrigateValidator(){
		validatorList = null;
		
	}

	@Override
	public boolean isValidMove(Board theBoard, Frontier theFrontier, HantoPiece piece, HantoCoordinate where) {
		// TODO Auto-generated method stub
		return true;
	}
	
	public void addValidator(MoveValidator toAdd){
		// TODO : add the validator to a list to run.
	}

	@Override
	public void invalidError() throws HantoException {
		throw new HantoException("AggrigateValidator Failure");
		
	}
}
