package hanto.studentThhughes.common.board;

import hanto.common.*;

/**
 * Interface to act as a container for the board. This allows there to be any underlying
 * implementation without concern for interface (thus the board could be a map or a list).
 * 
 * @author Troy
 *
 */
public interface Board {

	/**
	 * This method places something on the board. It returns a boolean indicating the success
	 * or failure of the method.
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
	 * 			HantoPiece in the HantoCoordinate
	 * @throws HantoException
	 * 						If there is no piece in the given location
	 * 						Or if the location is null.
	 */
	boolean removeFromBoard(HantoCoordinate where) throws HantoException;
}
