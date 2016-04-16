/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package hanto.studentthhughes.common.movecounter;

import static hanto.common.HantoPlayerColor.*;

import hanto.common.HantoPlayerColor;

/**
 * Move counter implementation
 * @author Troy
 *
 */
public class MoveCounterImpl implements MoveCounter{
	HantoPlayerColor currentColor;
	
	int red;
	int blue;
	
	/**
	 * Constructor of the MoveCounter
	 */
	public MoveCounterImpl(){
		red = 0;
		blue = 0;
	}
	
	/**
	 * Copy constructor
	 * 
	 * @param otherCounter
	 * 					MoveCounter Implementation
	 * 
	 */
	public MoveCounterImpl(MoveCounter otherCounter)
	{
		this.red = otherCounter.getNumberMoves(RED);
		this.blue = otherCounter.getNumberMoves(BLUE);
	}
	
	/*
	 * (non-Javadoc)
	 * @see hanto.studentthhughes.common.movecounter.MoveCounter#getNumberMoves(hanto.common.HantoPlayerColor)
	 */
	public int getNumberMoves(HantoPlayerColor player){
		int result = -1;
		if(player == RED){
			result = red;
		}else{
			result = blue;
		}
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * @see hanto.studentthhughes.common.movecounter.MoveCounter#incrementNumberMoves(hanto.common.HantoPlayerColor)
	 */
	public void incrementNumberMoves(HantoPlayerColor player){
		if(player == RED){
			red++;
		}else{
			blue++;
		}
	}
	
	/**
	 * Returns boolean if it is the first move of the game. 
	 * 
	 * @return
	 * 			boolean : True if it's the first move of the game. 
	 */
	public boolean isFirstMoveOfGame(){
		return (red == 0) && (blue == 0);
	}
	
	/**
	 * Returns boolean if both red and blue have gone
	 * greater than 1 time. 
	 * 
	 * @return
	 * 			boolean : True if red and blue have gone more than once.
	 */
	public boolean bothPlayersHaveGoneMoreThanOnce(){
		return (red > 0) && (blue > 0);
	}

}