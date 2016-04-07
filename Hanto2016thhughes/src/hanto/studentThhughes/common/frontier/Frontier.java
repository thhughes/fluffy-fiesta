package hanto.studentThhughes.common.frontier;

import hanto.common.*;


/**
 * This is a frontier, and will be used to represent the areas that a piece can be placed.
 * 
 * 	
 */
public interface Frontier {

	/**
	 * This method adds a hanto piece at a hanto coordinate to the frontier. 
	 * @param piece
	 * 				The implementation of a hanto piece that is being placed at. If this
	 * 				is null then the system will throw an exception
	 * @param place
	 * 				The implementation of a HantoCoordinate that the piece is being placed at
	 * @return
	 * 			Boolean : True if the object was correctly placed on the frontier
	 * @throws HantoException
	 */
	boolean addToFrontier(HantoPiece piece, HantoCoordinate place) throws HantoException;


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
