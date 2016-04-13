/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package hanto.studentThhughes.common.movecalculator;

import java.util.LinkedList;
import java.util.Queue;

import hanto.common.HantoCoordinate;
import hanto.studentThhughes.common.coordinate.HantoCoordinateImpl;
import hanto.studentThhughes.common.coordinate.HantoCoordinateImpl.*;

/**
 * @author Troy
 *
 */
public class NMoveCalculator implements MoveCalculator {

	int size;
	public NMoveCalculator(int calcSize)
	{
		size = calcSize;
	}
	
	/* (non-Javadoc)
	 * @see hanto.studentThhughes.common.moveCalculator.MoveCalculator#calcMoveCoordinates(hanto.common.HantoCoordinate)
	 */
	@Override
	public Queue<HantoCoordinate> calcMoveCoordinates(HantoCoordinate starting) {
		Queue<HantoCoordinateImpl> frontier = new LinkedList<HantoCoordinateImpl>();
		Queue<HantoCoordinateImpl> coordinates = new LinkedList<HantoCoordinateImpl>();
		HantoCoordinateImpl inputCoord = new HantoCoordinateImpl(starting);
		frontier.add(inputCoord);
		if (size > 0)
		{
			for(int i = 0; i < size; i++)
			{
				Queue<HantoCoordinateImpl> newObjects = new LinkedList<HantoCoordinateImpl>();
				for (HantoCoordinateImpl hci : frontier)
				{
					for(HantoCoordinateImpl newHci : hantoCoordToImpl(hci.getNeighbors()))
					{
						if(!coordinates.contains(newHci) && !newHci.equals(inputCoord))
						{
							coordinates.add(newHci);
							newObjects.add(newHci);
						}
					}
				}
				frontier.clear();
				frontier = new LinkedList<HantoCoordinateImpl>(newObjects);
			}
				
		}
		
		return implToCoord(coordinates);
	}
	
	private Queue<HantoCoordinateImpl> hantoCoordToImpl(Queue<HantoCoordinate> someList)
	{
		Queue<HantoCoordinateImpl> coordinates = new LinkedList<HantoCoordinateImpl>();
		for(HantoCoordinate hc : someList)
		{
			coordinates.add(new HantoCoordinateImpl(hc));
		}
		return coordinates;
	}
	
	private Queue<HantoCoordinate> implToCoord(Queue<HantoCoordinateImpl> someList)
	{
		Queue<HantoCoordinate> coordinates = new LinkedList<HantoCoordinate>();
		for(HantoCoordinateImpl hc : someList)
		{
			coordinates.add(new HantoCoordinateImpl(hc));
		}
		return coordinates;
	}

	

}