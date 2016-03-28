/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Copyright ©2016 Gary F. Pollice
 *******************************************************************************/

package hanto.studentThhughes.beta;



import java.util.*;

import hanto.common.*;
import hanto.studentThhughes.common.*;

/**
 * The implementation of Beta Hanto. Please note, that this class utilizes the .equals and .hashCode functions. 
 * All objects passed to this class should have the appropriate methods implemented to allow for proper 
 * functionality.
 * @version Mar 16, 2016
 */
public class BetaHantoGame implements HantoGame
{
	private boolean firstMove = true;
	private HantoPlayerColor nextPlayerColor = null;
	private Queue<HantoPieceType> validPieces = new LinkedList<HantoPieceType>();
	private Map<HantoCoordinate, HantoPiece> pieceHash = new HashMap<HantoCoordinate, HantoPiece>();
	
	
	
	public BetaHantoGame(HantoPlayerColor firstMovePlayer){
		this.nextPlayerColor = firstMovePlayer;
		
		// Fill the list: 
		validPieces.add(HantoPieceType.SPARROW);
		validPieces.add(HantoPieceType.BUTTERFLY);
		
	}
	

	/*
	 * @see hanto.common.HantoGame#makeMove(hanto.common.HantoPieceType, hanto.common.HantoCoordinate, hanto.common.HantoCoordinate)
	 */
	@Override
	public MoveResult makeMove(HantoPieceType pieceType, HantoCoordinate from,
			HantoCoordinate to) throws HantoException
	{
		
		if(firstMove && validPieces.contains(pieceType)){
			if (to.getX() != 0 && to.getY() != 0){
				throw new HantoException("Invalid location for first move");
			}

			HantoPieceImpl movePiece = new HantoPieceImpl(getColor(), pieceType);
			pieceHash.put(to, movePiece);
			firstMove = false;
			return MoveResult.OK;
		}
		else if(!firstMove && validPieces.contains(pieceType)){
			if(spotTaken(to)){
				throw new HantoException("Invalid Location: Already Occupied");
			}
			if(!legalLocation(to)){
				throw new HantoException("Invalid Locaiton: Cannot Move There");
			}
			HantoPieceImpl movePiece = new HantoPieceImpl(getColor(), pieceType);
			pieceHash.put(to, movePiece);
			return MoveResult.OK;
		}
		throw new HantoException("Invalid Piece");
	}

	/*
	 * @see hanto.common.HantoGame#getPieceAt(hanto.common.HantoCoordinate)
	 */
	@Override
	public HantoPiece getPieceAt(HantoCoordinate where)
	{
		HantoPiece returnPiece = null;
		if(spotTaken(where)){
			returnPiece = (HantoPiece) pieceHash.get(where);
		}
		return returnPiece;
	}

	/*
	 * @see hanto.common.HantoGame#getPrintableBoard()
	 */
	@Override
	public String getPrintableBoard()
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Returns the color of the current move and toggles the internal color to prepare for 
	 * the next move
	 * @return HantoPlayerColor : representing the color of that move. 
	 */
	private HantoPlayerColor getColor(){
		if (nextPlayerColor == HantoPlayerColor.BLUE){
			nextPlayerColor = HantoPlayerColor.RED;
			return HantoPlayerColor.BLUE;
		}
		nextPlayerColor = HantoPlayerColor.BLUE;
		return HantoPlayerColor.RED;
	}
	
	/**
	 * Checks if a spot is taken on the board and returns true if it is. False if it is not
	 * @param spot : HantoCoordinate representing the location to check
	 * @return boolean : representing if the spot is occupied.
	 */
	private boolean spotTaken(HantoCoordinate spot){
		return pieceHash.containsKey(spot);
	}
	
	private boolean legalLocation(HantoCoordinate location) throws HantoException{
		// @TODO
		Queue<HantoCoordinate> frontier = new LinkedList<HantoCoordinate>();
		Iterator it = pieceHash.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry pair = (Map.Entry)it.next();
			System.out.println(pair.getKey() + " = " + pair.getValue());
	        it.remove(); // avoids a ConcurrentModificationException
		}
		throw new HantoException("Finish This Please");
	}

}
