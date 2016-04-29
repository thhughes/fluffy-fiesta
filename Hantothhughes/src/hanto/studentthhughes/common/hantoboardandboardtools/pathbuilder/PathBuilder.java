/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package hanto.studentthhughes.common.hantoboardandboardtools.pathbuilder;

import java.util.List;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.studentthhughes.common.hantoboardandboardtools.HantoBoard;

/**
 * This is an interface for any type of search algorithm class type that
 * wants to be created. Not sure what could be needed in the future so 
 * is here just in case
 * @author Troy
 *
 */
public interface PathBuilder {

	/**
	 * This function serves to return a path between the start and end point coordinates on the
	 * board that is given. 
	 * 
	 * @param theBoard
	 * 				HantoBoard
	 * @param start
	 * 				HantoCoordinate : Starting location of the search
	 * @param end
	 * 				HantoCoordinate : End destination of the search
	 * @return
	 * 			List<HantoCoordinate> : the path
	 */
	List<HantoCoordinate> getPath(HantoBoard theBoard, HantoCoordinate start, HantoCoordinate end) throws HantoException;
	
}
