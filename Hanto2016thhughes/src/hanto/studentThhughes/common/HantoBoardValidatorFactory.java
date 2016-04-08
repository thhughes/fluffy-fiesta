package hanto.studentThhughes.common;


import java.util.LinkedList;
import java.util.Queue;

import hanto.common.HantoGameID;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentThhughes.common.boardValidator.AggragateBoardValidator;
import hanto.studentThhughes.common.boardValidator.BoardValidator;
import hanto.studentThhughes.common.boardValidator.ColorWinValidator;
import hanto.studentThhughes.common.boardValidator.TerminalMoveCheck;


public class HantoBoardValidatorFactory {

	private static final HantoBoardValidatorFactory instance = new HantoBoardValidatorFactory();
	
	
	private HantoBoardValidatorFactory(){
		
	}
	
	public static HantoBoardValidatorFactory getInstance(){
		return instance;
	}
	
	public BoardValidator makeHantoValidator(HantoGameID gameID){
		AggragateBoardValidator mv = null;
		switch (gameID) {
			case ALPHA_HANTO:
				break;
			case BETA_HANTO:
				break;
			case GAMMA_HANTO:
				mv = new AggragateBoardValidator();
				mv.addValidator(new TerminalMoveCheck(20));
				mv.addValidator(new ColorWinValidator(HantoPlayerColor.RED));
				mv.addValidator(new ColorWinValidator(HantoPlayerColor.BLUE));
				break;
			
			default:
				break;
		}
		return mv;
	}
}
