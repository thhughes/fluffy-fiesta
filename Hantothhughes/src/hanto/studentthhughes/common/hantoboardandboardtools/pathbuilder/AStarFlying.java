package hanto.studentthhughes.common.hantoboardandboardtools.pathbuilder;

import java.util.LinkedList;
import java.util.Queue;

import hanto.common.HantoCoordinate;
import hanto.studentthhughes.common.coordinate.HantoCoordinateImpl;
import hanto.studentthhughes.common.hantoboardandboardtools.HantoBoard;

/**
 * This class is used for finding all the locations that a piece can fly to. 
 * This version of AStar does not check the endpoints when, just ensures 
 * that there exists a path. Therfore, this implementation is capable of
 * finding a path that goes from a hex to an occupied hex.
 * @author Troy
 *
 */
public class AStarFlying extends AStarTemplate implements PathBuilder {

	
	/*
	 * (non-Javadoc)
	 * @see hanto.studentthhughes.common.hantoboardandboardtools.pathbuilder.AStarTemplate#getLegalNeighbors(hanto.studentthhughes.common.hantoboardandboardtools.HantoBoard, java.util.Queue)
	 */
	@Override
	protected Queue<HantoCoordinateImpl> getLegalNeighbors(HantoBoard theBoard, Queue<HantoCoordinate> neighbors) {
		
		Queue<HantoCoordinateImpl> implList = new LinkedList<HantoCoordinateImpl>();
		for(HantoCoordinate hc:  neighbors){
			HantoCoordinateImpl currentNeighbor = new HantoCoordinateImpl(hc);
			
			implList.add(currentNeighbor);
		}
		return implList;
	}
}
