/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package hanto.studentThhughes.common.moveCalculator;

import java.util.Queue;

import hanto.common.HantoCoordinate;
/**
 * This interface is used as part of a strategy pattern to allow the system
 * to get a Queue of locations that the piece COULD move to. 
 * @author Troy
 *
 */
public interface MoveCalculator {
	
	/**
	 * Get the Queue of HantoCoordinate's that a piece at a given location could move to.
	 * @param starting
	 * @return
	 */
	public Queue<HantoCoordinate> calcMoveCoordinates(HantoCoordinate starting);
}
