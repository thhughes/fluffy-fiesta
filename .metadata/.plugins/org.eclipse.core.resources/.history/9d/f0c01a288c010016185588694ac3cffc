/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package hanto.studentThhughes.common.movecounter;

import static hanto.common.HantoPlayerColor.RED;

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
	
	public MoveCounterImpl(){
		red = 0;
		blue = 0;
	}
	
	public int getNumberMoves(HantoPlayerColor player){
		int result = -1;
		if(player == RED){
			result = red;
		}else{
			result = blue;
		}
		return result;
	}
	
	public void incrementNumberMoves(HantoPlayerColor player){
		if(player == RED){
			red++;
		}else{
			blue++;
		}
	}

}