package hanto.studentthhughes.common.turnactionvalidator.movecheckers;

import java.util.List;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPiece;
import hanto.studentthhughes.common.coordinate.HantoCoordinateImpl;
import hanto.studentthhughes.common.hantoboardandboardtools.HantoBoard;
import hanto.studentthhughes.common.hantoboardandboardtools.pathbuilder.AStarFlying;
import hanto.studentthhughes.common.hantoboardandboardtools.pathbuilder.PathBuilder;
import hanto.studentthhughes.common.movecounter.MoveCounterImpl;
import hanto.studentthhughes.common.turnactionvalidator.AbsTurnActionValidator;
import hanto.studentthhughes.common.turnactionvalidator.TurnActionValidator;

public class FlyingValidator extends AbsTurnActionValidator implements TurnActionValidator{

	HantoCoordinateImpl toImpl;
	HantoCoordinateImpl fromImpl;
	int maxDistance;
	PathBuilder astarFlyer = new AStarFlying();
	
	public FlyingValidator(){
		maxDistance = 1;
	}

	public FlyingValidator(int maxDist)
	{
		maxDistance = maxDist;
	}
	
	/*
	 * (non-Javadoc)
	 * @see hanto.studentthhughes.common.movevalidator.AbsMoveValidator#handleMoveCheck(hanto.studentthhughes.common.hantoboard.HantoBoard, hanto.common.HantoPiece, hanto.studentthhughes.common.movecounter.MoveCounterImpl, hanto.studentthhughes.common.coordinate.HantoCoordinateImpl, hanto.studentthhughes.common.coordinate.HantoCoordinateImpl)
	 */
	@Override
	protected void handleMoveCheck(HantoBoard theBoard, HantoPiece piece, MoveCounterImpl counter,
			HantoCoordinateImpl to, HantoCoordinateImpl from) {
		if(!theBoard.isLocationOccupied(to)){
			List<HantoCoordinate> path = astarFlyer.getPath(theBoard, from, to);
			validResult = ((path.size()-1) <= maxDistance);   // NOTE: PATH INCLUDES THE STARTING NODE, so must remove it to get 'moves
		}else{
			validResult = false;
		}
		
	}
}
