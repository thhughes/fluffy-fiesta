package hanto.studentThhughes.common.moveValidator;

import java.util.LinkedList;
import java.util.Queue;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPiece;
import hanto.studentThhughes.common.MoveCounter;
import hanto.studentThhughes.common.board.Board;
import hanto.studentThhughes.common.frontier.Frontier;

public class AggrigateValidator implements MoveValidator {

	Queue<MoveValidator> validatorList;
	Throwable caughtError;
	public AggrigateValidator(){
		validatorList = new LinkedList<MoveValidator>();
		
	}

	@Override
	public boolean isValidMove(Board theBoard, Frontier theFrontier, HantoPiece piece, HantoCoordinate where, MoveCounter counter) {
		boolean result = true;
		for(MoveValidator mv : validatorList){
			if(!mv.isValidMove(theBoard, theFrontier, piece, where,counter)){
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
	
	public void addValidator(MoveValidator toAdd){
		validatorList.add(toAdd);
	}

	@Override
	public void invalidError() throws HantoException {
		throw new HantoException("AggrigateValidator Failure: ",caughtError);
		
	}
}
