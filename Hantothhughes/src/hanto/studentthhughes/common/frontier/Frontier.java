/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package hanto.studentthhughes.common.frontier;

import hanto.common.*;


/**
 * This is a frontier, and will be used to represent the areas that a piece can be placed.
 * 
 * 	
 */
public interface Frontier {

	/**
	 * This method adds a location to the frontier - the frontier is the available 
	 * points/locations that a HantoPiece can be placed on.
	 * @param place
	 * 				The implementation of a HantoCoordinate that the piece is being placed at
	 * @return
	 * 			Boolean : True if the object was correctly placed on the frontier
	 * @throws HantoException
	 */
	boolean addToFrontier(HantoCoordinate place) throws HantoException;


	/**
	 * This method checks the frontier to see if something is located on the frontier
	 * 
	 * @param place
	 * 				HantoCoordinate Implementation that represents the location on the frontier
	 * @return
	 * 			Boolean : True if there is something on the frontier.
	 */
	boolean inFrontier(HantoCoordinate place);
	
	
	/**
	 * This removes a piece from the frontier. 
	 * 
	 * @param place
	 * 				The hantoCoordinate that a hantoPiece is goign to be set at
	 * @return
	 * 			Boolean : True if it was successfuly removed.
	 * @throws HantoException
	 */
	boolean removeFromFrontier(HantoCoordinate place) throws HantoException;
	
}
