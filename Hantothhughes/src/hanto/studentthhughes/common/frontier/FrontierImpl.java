/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package hanto.studentthhughes.common.frontier;

import java.util.LinkedList;
import java.util.Queue;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.studentthhughes.common.coordinate.HantoCoordinateImpl;

/**
 * @author Troy
 *
 */
public class FrontierImpl implements Frontier {

	
	Queue<HantoCoordinate> frontier;
	
	public FrontierImpl(){
		frontier = new LinkedList<HantoCoordinate>();
		frontier.add(new HantoCoordinateImpl(0,0));
	}
	

	/* (non-Javadoc)
	 * @see hanto.studentThhughes.common.frontier.Frontier#inFrontier(hanto.common.HantoCoordinate)
	 */
	@Override
	public boolean isInFrontier(HantoCoordinate place) {
		boolean result = false;
		try {
			if (place == null) throw new HantoException("Frontier Exception: cannot add null to frontier");
			result = frontier.contains(new HantoCoordinateImpl(place));
		} catch (HantoException e) {
			result = false;
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see hanto.studentThhughes.common.frontier.Frontier#removeFromFrontier(hanto.common.HantoCoordinate)
	 */
	@Override
	public boolean canRemoveFromFrontier(HantoCoordinate place) throws HantoException {
		if (place == null) throw new HantoException("Frontier Exception: cannot add null to frontier");
		if (!isInFrontier(new HantoCoordinateImpl(place))) throw new HantoException("Frontier Exception: coordinate is not already in the frontier");
		frontier.remove(new HantoCoordinateImpl(place));
		
		return true;
	}

	@Override
	public boolean canAddToFrontier(HantoCoordinate place) throws HantoException {
		if (place == null) throw new HantoException("Frontier Exception: cannot add null to frontier");
		if (isInFrontier(new HantoCoordinateImpl(place))) throw new HantoException("Frontier Exception: coordinate is already in the frontier");
		frontier.add(new HantoCoordinateImpl(place));
		return true;
	}
	


}
