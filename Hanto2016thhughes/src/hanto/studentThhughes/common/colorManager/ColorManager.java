/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package hanto.studentThhughes.common.colorManager;

import hanto.common.*;
import static hanto.common.HantoPlayerColor.*;

/**
 * Class used to manage the current color in the game
 * @author Troy
 *
 */
public class ColorManager {
	HantoPlayerColor currentColor;
	
	/**
	 * constructor
	 * @param firstMovePlayer
	 * 						HantoPlayerColor : of the first move player
	 */
	public ColorManager(HantoPlayerColor firstMovePlayer){
		currentColor = firstMovePlayer;
	}

	/**
	 * 
	 * @return
	 * 			HantoPlayerColor : of the current turn
	 */
	public HantoPlayerColor getCurrentColor(){
		return currentColor;
	}
	
	/**
	 * toggel the internal state of the color
	 */
	public void toggelCurrentColor(){
		if (currentColor == BLUE){
			currentColor = RED;
		}else{
			currentColor = BLUE;
		}
	}
	
}
