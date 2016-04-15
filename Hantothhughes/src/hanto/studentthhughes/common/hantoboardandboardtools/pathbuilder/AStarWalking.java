/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package hanto.studentthhughes.common.hantoboardandboardtools.pathbuilder;

import java.util.LinkedList;
import java.util.Queue;

import hanto.common.HantoCoordinate;
import hanto.studentthhughes.common.coordinate.HantoCoordinateImpl;
import hanto.studentthhughes.common.hantoboardandboardtools.HantoBoard;

/**
 * This class implementation allows the user to build AStar on a HantoBoard such that
 * the pieces on the board are obstacles and avoided. This means that it will find the
 * shortest <em> walking </em> distance between two points while avoiding obstacles. 
 * 
 * 
 * @author Troy
 *
 */
public class AStarWalking extends AStarTemplate implements PathBuilder {

	/*
	 * (non-Javadoc)
	 * @see hanto.studentthhughes.common.hantoboardandboardtools.pathbuilder.AStarTemplate#getLegalNeighbors(hanto.studentthhughes.common.hantoboardandboardtools.HantoBoard, java.util.Queue)
	 */
	@Override
	protected Queue<HantoCoordinateImpl> getLegalNeighbors(HantoBoard theBoard, Queue<HantoCoordinate> neighbors) {
		
		Queue<HantoCoordinateImpl> implList = new LinkedList<HantoCoordinateImpl>();
		for(HantoCoordinate hc:  neighbors){
			HantoCoordinateImpl currentNeighbor = new HantoCoordinateImpl(hc);
			
			if(!theBoard.isLocationOccupied(hc) || start.equals(currentNeighbor)){
				implList.add(currentNeighbor);
			}	
		}
		return implList;
	}
}
