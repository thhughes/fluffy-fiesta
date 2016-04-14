/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package hanto.studentthhughes.common.hantoboard;

import java.util.List;
import java.util.Map;

import hanto.common.*;

/**
 * Interface to act as a container for the board. This allows there to be any underlying
 * implementation without concern for interface (thus the board could be a map or a list).
 * 
 * @author Troy
 *
 */
public interface HantoBoard {

	/**
	 * This method places something on the board. It returns a boolean indicating the success
	 * or failure of the method. This method does not perform any type of error checking on 
	 * the location. It simly checks if the location is valid and places the piece at the 
	 * coordinate. 
	 * 
	 * @param piece
	 * 				HantoPiece Implementation to be placed
	 * @param where
	 * 				HantoCoordinate Implementation representing the location of the piece
	 * @return
	 * 			Boolean : True if the piece was placed.
	 * @throws HantoException
	 */
	boolean placeOnBoard(HantoPiece piece, HantoCoordinate where) throws HantoException;
	
	
	/**
	 * This gets a piece from the board at a given hantoCoordinate.
	 * 
	 * @param where
	 * 				HantoCoordinate location that the piece should exist at
	 * @return
	 * 			HantoPiece <if a piece exists> or Null
	 */
	HantoPiece getFromBoard(HantoCoordinate where);
	
	
	/**
	 * Checks if a location on the board is occupied and returns a boolean assocated.
	 * @param where
	 * 				HantoCoordinate implementation representing the location to check
	 * @return
	 * 			boolean: True if the location is occupied
	 */
	boolean isLocationOccupied(HantoCoordinate where);
	
	
	/**
	 * Checks if a piece is on the board and removes it from the board. 
	 * @param where
	 * 				HantoCoordinate implementation representing the location of the piece
	 * @return
	 * 			boolean: true if it's removed
	 * @throws HantoException
	 * 						If there is no piece in the given location
	 * 						Or if the location is null.
	 */
	boolean removeFromBoard(HantoCoordinate where) throws HantoException;
	
	/**
	 * This returns a map of the pieces in play by a given player. This map is 
	 * build with hantoCoordinates as the key and hantoPieces as the value.
	 * @param color
	 * 				HantoPlayerColor or null: If color, return the colors board. If null, dump the whole board
	 * @return
	 * 			Map of the pieces from HantoCoordinate to HantoPiece. All pieces will have the same color. 
	 */
	Map<HantoCoordinate,HantoPiece> getPlayerPieces(HantoPlayerColor color);
	
	
	List<HantoCoordinate> getPath(HantoCoordinate start,HantoCoordinate end);
}
