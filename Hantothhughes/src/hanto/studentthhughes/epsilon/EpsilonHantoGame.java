package hanto.studentthhughes.epsilon;


import hanto.common.HantoGame;
import hanto.common.HantoGameID;
import hanto.common.HantoPlayerColor;
import hanto.common.HantoPrematureResignationException;
import hanto.common.MoveResult;
import hanto.studentthhughes.TemplateHantoGame;
import hanto.studentthhughes.actionfinder.ActionFinder;
import hanto.studentthhughes.common.gamestateevaluator.GameStateEvaluator;
import hanto.studentthhughes.common.turnactionvalidator.TurnActionValidator;

public class EpsilonHantoGame extends TemplateHantoGame implements HantoGame {

	ActionFinder af = new ActionFinder(HantoGameID.EPSILON_HANTO);
	
	public EpsilonHantoGame(HantoPlayerColor firstMovePlayer, TurnActionValidator tav, GameStateEvaluator gse) {
		super(firstMovePlayer, tav, gse);
		canSurrender = true;
	}

	@Override
	protected void handleSurrender() throws HantoPrematureResignationException{
		if(af.isActionMakeable(hantoBoard, moveCounter, hantoColorManager.getCurrentColor())){
			super.handleSurrender();
			throw new HantoPrematureResignationException();
		}else{
			super.handleSurrender();
		}
	}

}
