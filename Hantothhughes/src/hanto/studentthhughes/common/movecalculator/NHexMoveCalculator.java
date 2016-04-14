/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package hanto.studentthhughes.common.movecalculator;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import hanto.common.HantoCoordinate;
import hanto.studentthhughes.common.coordinate.HantoCoordinateImpl;
import hanto.studentthhughes.common.coordinate.HantoCoordinateImpl.*;

/**
 * @author Troy
 *
 */
public class NHexMoveCalculator implements MovePieceRangeCalculator {

	int numberOfHexesCanReach;
	public NHexMoveCalculator(int calcSize)
	{
		numberOfHexesCanReach = calcSize;
	}
	
	/* (non-Javadoc)
	 * @see hanto.studentThhughes.common.moveCalculator.MoveCalculator#calcMoveCoordinates(hanto.common.HantoCoordinate)
	 */
	@Override
	public List<HantoCoordinate> calcMoveCoordinates(HantoCoordinate starting) {
		Queue<HantoCoordinateImpl> nodesToGetSurroundings = new LinkedList<HantoCoordinateImpl>();
		Queue<HantoCoordinateImpl> reachableCoordinates = new LinkedList<HantoCoordinateImpl>();
		HantoCoordinateImpl inputCoord = new HantoCoordinateImpl(starting);
		nodesToGetSurroundings.add(inputCoord);
		
		if (numberOfHexesCanReach > 0)
		{
			for(int i = 0; i < numberOfHexesCanReach; i++)
			{
				Queue<HantoCoordinateImpl> newNodesToGetSurroundings = new LinkedList<HantoCoordinateImpl>();
				for (HantoCoordinateImpl surroundingNode : nodesToGetSurroundings)
				{
					for(HantoCoordinateImpl surroundingNodeNeighbor : hantoCoordToImpl(surroundingNode.getNeighbors()))
					{
						if(!reachableCoordinates.contains(surroundingNodeNeighbor) && !surroundingNodeNeighbor.equals(inputCoord))
						{
							reachableCoordinates.add(surroundingNodeNeighbor);
							newNodesToGetSurroundings.add(surroundingNodeNeighbor);
						}
					}
				}
				nodesToGetSurroundings.clear();
				nodesToGetSurroundings = new LinkedList<HantoCoordinateImpl>(newNodesToGetSurroundings);
			}
				
		}
		
		return implToCoord(reachableCoordinates);
	}
	
	private List<HantoCoordinateImpl> hantoCoordToImpl(Queue<HantoCoordinate> someList)
	{
		List<HantoCoordinateImpl> coordinates = new LinkedList<HantoCoordinateImpl>();
		for(HantoCoordinate hc : someList)
		{
			coordinates.add(new HantoCoordinateImpl(hc));
		}
		return coordinates;
	}
	
	private List<HantoCoordinate> implToCoord(Queue<HantoCoordinateImpl> someList)
	{
		List<HantoCoordinate> coordinates = new LinkedList<HantoCoordinate>();
		for(HantoCoordinateImpl hc : someList)
		{
			coordinates.add(new HantoCoordinateImpl(hc));
		}
		return coordinates;
	}

	

}
