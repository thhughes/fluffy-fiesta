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
import static hanto.common.HantoPieceType.*;
import static hanto.common.HantoPlayerColor.*;


/**
 * The implementation of Beta Hanto.
 * @version Mar 16, 2016
 */
public class BetaHantoGame implements HantoGame
{
	private boolean firstMove = true;
	private HantoPlayerColor nextPlayerColor = null;
	private Queue<HantoPieceType> validPieces = new LinkedList<HantoPieceType>();
	private Queue<HantoCoordinate> frontier = new LinkedList<HantoCoordinate>();
	private Map<HantoCoordinate, HantoPiece> boardMap = new HashMap<HantoCoordinate, HantoPiece>();
	private int blueMoves = 0;
	private int redMoves = 0;
	private Map<HantoPieceType,Integer> bluePieceMap = new HashMap<HantoPieceType, Integer>();
	private Map<HantoPieceType,Integer> redPieceMap = new HashMap<HantoPieceType, Integer>();
	
	
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
		// Ensure the coordinates are 'safe' meaning they contain equals and hash
		HantoCoordinate safeTo = null; 
		if (to != null) safeTo = new HantoCoordinateImpl(to);
		if (to == null) throw new HantoException("There Must be a To location specified for Beta Hanto");
		
		if (from != null){
			throw new HantoException("Illegal Move: Cannot Move Pieces In BetaHanto");
		}
		
		
		// Check if it's a valid first move piece
		if(firstMove && validPieces.contains(pieceType)){
			if (!safeTo.equals(new HantoCoordinateImpl(0,0))){
				throw new HantoException("Invalid location for first move");
			}

			cachePiece(pieceType, safeTo);
			return MoveResult.OK;
		}
		// Check if it's a valid piece if it's not the first move
		else if(!firstMove && validPieces.contains(pieceType)){
			if(spotTaken(safeTo)){
				throw new HantoException("Invalid Location: Already Occupied");
			}
			if(!legalLocation(safeTo)){
				throw new HantoException("Invalid Locaiton: Cannot Move There");
			}
			if(!isLegalBoardMove(pieceType, safeTo)){
				throw new HantoException("Illegal Move: Cannot Make that Move");
			}
			cachePiece(pieceType, safeTo);
			return MoveResult.OK;
		}
		throw new HantoException("Invalid Piece");
	}


	/**
	 * This internally caches the piece in a frontier and in a hash of pieces based off it's location
	 * @param pieceType: HantoPieceType of the piece that is being passed. 
	 * @param safeTo : location of the hantoPiece as a HantoCoordinat
	 */
	private void cachePiece(HantoPieceType pieceType, HantoCoordinate safeTo) {
		
		expandFrontier(safeTo);
		HantoPlayerColor curColor = getAndToggleColor();
		HantoPieceImpl movePiece = new HantoPieceImpl(curColor, pieceType);
		incrementTeamMoves(curColor);
		
		updateCurrentPlayerMap(pieceType);
		
		boardMap.put(safeTo, movePiece);
		firstMove = false;
	}

	/*
	 * @see hanto.common.HantoGame#getPieceAt(hanto.common.HantoCoordinate)
	 */
	@Override
	public HantoPiece getPieceAt(HantoCoordinate where)
	{
		HantoCoordinate safeWhere  = null;
		if (where != null) safeWhere = new HantoCoordinateImpl(where);
		HantoPiece returnPiece = null;
		if(spotTaken(safeWhere)){
			returnPiece = (HantoPiece) boardMap.get(safeWhere);
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
	private HantoPlayerColor getAndToggleColor(){
		if (nextPlayerColor == BLUE){
			nextPlayerColor = RED;
			return BLUE;
		}
		nextPlayerColor = BLUE;
		return RED;
	}
	
	/**
	 * Get's the color of the player that is playing right now
	 * @return HantoPlayerColor
	 */
	private HantoPlayerColor currentColor(){
		return nextPlayerColor;
	}
	
	private void expandFrontier(HantoCoordinate newPoint){
		if (frontier.contains(newPoint)){
			frontier.remove(newPoint);
		}
		for (HantoCoordinate currentPoint : getSurroundingPoints(newPoint)){
			if(!frontier.contains(currentPoint) && !spotTaken(currentPoint)){
				frontier.add(currentPoint);
			}
		}
	}
	
	private Queue<HantoCoordinate> getSurroundingPoints(HantoCoordinate point){
		Queue<HantoCoordinate> surrounding = new LinkedList<HantoCoordinate>();
		int startX = point.getX();
		int startY = point.getY();
		
		surrounding.add(new HantoCoordinateImpl(startX,startY+1));
		surrounding.add(new HantoCoordinateImpl(startX+1,startY));
		surrounding.add(new HantoCoordinateImpl(startX,startY-1));
		surrounding.add(new HantoCoordinateImpl(startX-1,startY));
		surrounding.add(new HantoCoordinateImpl(startX-1,startY+1));
		surrounding.add(new HantoCoordinateImpl(startX+1,startY-1));
		
		return surrounding;
		
	}
	
	/**
	 * Checks if a spot is taken on the board and returns true if it is. False if it is not
	 * @param spot : HantoCoordinate representing the location to check
	 * @return boolean : representing if the spot is occupied.
	 */
	private boolean spotTaken(HantoCoordinate spot){
		return boardMap.containsKey(spot);
	}
	
	private boolean legalLocation(HantoCoordinate location){
		return frontier.contains(location);
	}
	
	/**
	 * Checks if a legal move is being made based off the turn number and the pieces on the board
	 * @param pt : HantoPieceType -> the type of piece being played this turn
	 * @param cord : hantoCoordinate -> where the peice is being placed
	 * @return Boolean : True if the move is legal. 
	 */
	private boolean isLegalBoardMove(HantoPieceType pt, HantoCoordinate cord){
		return !(currentPlayerMoveNumber() == 3 && 
				!((currentPlayerMap().containsKey(BUTTERFLY)) ^ pt == BUTTERFLY));
	}
	
	
	private int currentPlayerMoveNumber(){
		int numMoves = -1;
		if(currentColor() == BLUE){
			numMoves = blueMoves;
		}else if(currentColor() == RED){
			numMoves = redMoves;
		}
		return numMoves;
	}
	
	private Map<HantoPieceType,Integer> currentPlayerMap(){
		Map<HantoPieceType,Integer> resultMap = null;
		if (currentColor() == BLUE){
			resultMap = bluePieceMap;
		}else if(currentColor() == RED){
			resultMap = redPieceMap;
		}
		return resultMap;
	}
	
	private void updateCurrentPlayerMap(HantoPieceType newPiece){
		Map<HantoPieceType,Integer> updateMap = null;
		if (currentColor() == BLUE){
			updateMap = bluePieceMap;
		}else if(currentColor() == RED){
			updateMap = redPieceMap;
		}
		
		if(updateMap.containsKey(newPiece)){
			updateMap.put(newPiece, updateMap.get(newPiece) + 1);
		}else{
			updateMap.put(newPiece, new Integer(1));
		}
	}
	
	private void incrementTeamMoves(HantoPlayerColor col){
		if (col == BLUE){
			blueMoves++;
		}else{
			redMoves++;
		}
	}

}
