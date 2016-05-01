/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package hanto.studentthhughes.common.movecounter;


import hanto.common.HantoPlayerColor;

/**
 * This is an interface for move Counting systems. It will allow you to keep track 
 * of the number of moves in a given game.
 * @author Troy
 *
 */
public interface MoveCounter {
	
	
	/**
	 * get number of moves of a player
	 * @param player
	 * 				HantoPlayerColr : that you want the number of moves
	 * @return
	 * 			int : number of moves that were made
	 */
	int getNumberMoves(HantoPlayerColor player);
	
	/**
	 * increment the internal state of the HantoPlayer passed
	 * @param player
	 * 				HantoPlayerColor : of the one you want to increment
	 */
	void incrementNumberMoves(HantoPlayerColor player);
}
