package hanto.studentthhughes.epsilon;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentthhughes.TemplateHantoGame;
import hanto.studentthhughes.common.gamestateevaluator.GameStateEvaluator;
import hanto.studentthhughes.common.turnactionvalidator.TurnActionValidator;

public class EpsilonHantoGame extends TemplateHantoGame implements HantoGame {

	public EpsilonHantoGame(HantoPlayerColor firstMovePlayer, TurnActionValidator tav, GameStateEvaluator gse) {
		super(firstMovePlayer, tav, gse);
		// TODO Auto-generated constructor stub
	}

	@Override
	public MoveResult makeMove(HantoPieceType pieceType, HantoCoordinate from, HantoCoordinate to)
			throws HantoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HantoPiece getPieceAt(HantoCoordinate where) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPrintableBoard() {
		// TODO Auto-generated method stub
		return null;
	}

}
