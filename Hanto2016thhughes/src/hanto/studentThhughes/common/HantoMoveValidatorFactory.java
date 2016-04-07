package hanto.studentThhughes.common;


import hanto.common.HantoGameID;

import hanto.studentThhughes.common.moveValidator.MoveValidator;

public class HantoMoveValidatorFactory {

	private static final HantoMoveValidatorFactory instance = new HantoMoveValidatorFactory();
	
	
	private HantoMoveValidatorFactory(){
		
	}
	
	public static HantoMoveValidatorFactory getInstance(){
		return instance;
	}
	
	public MoveValidator makeHantoValidator(HantoGameID gameID){
		MoveValidator game = null;
		switch (gameID) {
			case ALPHA_HANTO:
				break;
			case BETA_HANTO:
				break;
			case GAMMA_HANTO:
				break;
			default:
				break;
		}
		return game;
	}
}
