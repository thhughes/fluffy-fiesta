package hanto.studentThhughes.common;


import java.util.LinkedList;
import java.util.Queue;

import hanto.common.HantoGameID;
import hanto.common.HantoPieceType;
import hanto.studentThhughes.common.moveValidator.AggrigateValidator;
import hanto.studentThhughes.common.moveValidator.ButterflyChecker;
import hanto.studentThhughes.common.moveValidator.FirstMoveValidator;
import hanto.studentThhughes.common.moveValidator.LocationValidator;
import hanto.studentThhughes.common.moveValidator.MoveValidator;
import hanto.studentThhughes.common.moveValidator.PieceValidator;

public class HantoMoveValidatorFactory {

	private static final HantoMoveValidatorFactory instance = new HantoMoveValidatorFactory();
	
	
	private HantoMoveValidatorFactory(){
		
	}
	
	public static HantoMoveValidatorFactory getInstance(){
		return instance;
	}
	
	public MoveValidator makeHantoValidator(HantoGameID gameID){
		AggrigateValidator mv = null;
		switch (gameID) {
			case ALPHA_HANTO:
				break;
			case BETA_HANTO:
				Queue<HantoPieceType> valid = new LinkedList<HantoPieceType>();
				valid.add(HantoPieceType.SPARROW);
				valid.add(HantoPieceType.BUTTERFLY);
				
				mv = new AggrigateValidator();
				
				mv.addValidator(new LocationValidator());
				mv.addValidator(new PieceValidator(valid));
				mv.addValidator(new FirstMoveValidator());
				mv.addValidator(new ButterflyChecker());
				break;
			case GAMMA_HANTO:
				break;
			default:
				break;
		}
		return mv;
	}
}
