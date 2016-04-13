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

package hanto.studentthhughes.beta;



import java.util.*;

import hanto.common.*;
import hanto.studentthhughes.common.coordinate.HantoCoordinateImpl;
import hanto.studentthhughes.common.hantopiece.HantoPieceImpl;

import static hanto.common.HantoPieceType.*;
import static hanto.common.HantoPlayerColor.*;


/**
 * The implementation of Beta Hanto.
 * @version Mar 16, 2016
 */
public class BetaHantoGame implements HantoGame
{
	private boolean firstMove = true;
	private boolean gameOver = false;
	private HantoPlayerColor nextPlayerColor = null;
	private Queue<HantoPieceType> validPieces = new LinkedList<HantoPieceType>();
	private Queue<HantoCoordinate> frontier = new LinkedList<HantoCoordinate>();
	private Map<HantoCoordinate, HantoPiece> boardMap = new HashMap<HantoCoordinate, HantoPiece>();
	private int blueMoves = 0;
	private int redMoves = 0;
	private Map<HantoPieceType,Integer> bluePieceMap = new HashMap<HantoPieceType, Integer>();
	private Map<HantoPieceType,Integer> redPieceMap = new HashMap<HantoPieceType, Integer>();
	private Map<HantoCoordinate,Boolean> blueButterflyMap = new HashMap<HantoCoordinate,Boolean>();
	private Map<HantoCoordinate,Boolean> redButterflyMap = new HashMap<HantoCoordinate,Boolean>();
	
	
	/**
	 * 
	 * 
	 * @param firstMovePlayer
	 */
	public BetaHantoGame(HantoPlayerColor firstMovePlayer){
		nextPlayerColor = firstMovePlayer;
		
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
		if (gameOver){
			throw new HantoException("Illegal Move: Game has terminated");
		}
		// Ensure the coordinates are 'safe' meaning they contain equals and hash
		HantoCoordinate safeTo = null; 
		if (to != null) safeTo = new HantoCoordinateImpl(to);
		if (to == null) throw new HantoException("There Must be a To location specified for Beta Hanto");
		
		if (from != null){
			throw new HantoException("Illegal Move: Cannot Move Pieces In BetaHanto");
		}
		if (!validPieces.contains(pieceType)) throw new HantoException("Invalid Piece");						// 
		
		
		// Check if it's a valid first move piece
		if(firstMove){
			if (!safeTo.equals(new HantoCoordinateImpl(0,0))){
				throw new HantoException("Invalid location for first move");
			}

			cachePiece(pieceType, safeTo);
		}
		// Check if it's a valid piece if it's not the first move
		else {
			if(isSpotTaken(safeTo)){
				throw new HantoException("Invalid Location: Already Occupied");
			}
			if(!isLocationLegal(safeTo)){
				throw new HantoException("Invalid Locaiton: Cannot Move There");
			}
			if(!isLegalBoardMove(pieceType, safeTo)){
				throw new HantoException("Illegal Move: Cannot Make that Move");
			}
			
			if(pieceType == BUTTERFLY && getCurrentPlayerMap().containsKey(BUTTERFLY)){
				throw new HantoException("Illegal Move: Cannot place two butterflys");
			}
			// If the item is a butterfly, cehck if there's already been a butterfly
			cachePiece(pieceType, safeTo);
		}
		
		return evaluateBoardState();
		
	}

	/**
	 * This internally caches the piece in a frontier and in a hash of pieces based off it's location
	 * @param pieceType: HantoPieceType of the piece that is being passed. 
	 * @param safeTo : location of the hantoPiece as a HantoCoordinat
	 */
	private void cachePiece(HantoPieceType pieceType, HantoCoordinate safeTo) {
		
		// Expand the frontier of the point being placed
		expandFrontier(safeTo);
		
		// Create the piece for internal hashing
		HantoPlayerColor curColor = currentColor();
		HantoPiece movePiece = new HantoPieceImpl(curColor, pieceType);
		
		// Increment the team's move
		incrementTeamMoves(curColor);
		
		// Hash data for game progression
		updateCurrentPlayerMap(pieceType);
		boardMap.put(safeTo, movePiece);
		
		// Get the butterfly data and update 
		Map<HantoCoordinate,Boolean> butterflyMap = getCurrentPlayerButterflyMap();
		
		if (pieceType == BUTTERFLY){
			butterflyMap.clear();
			for (HantoCoordinate somePoint : getSurroundingPoints(safeTo)){
				butterflyMap.put(somePoint, isSpotTaken(somePoint));
			}
		}
		if (blueButterflyMap.containsKey(safeTo) && !blueButterflyMap.isEmpty()){
			blueButterflyMap.put(safeTo, isSpotTaken(safeTo));
		}
		if (redButterflyMap.containsKey(safeTo) && !redButterflyMap.isEmpty()){
			redButterflyMap.put(safeTo, isSpotTaken(safeTo));
		}
		
		
		incrementColor(); 
		firstMove = false;
	}
	
	private MoveResult evaluateBoardState()
	{
		MoveResult result = MoveResult.OK;
		int numWinners = 0;
		
		// CHECK FOR WINNER
		if (!blueButterflyMap.containsValue(false) && !blueButterflyMap.isEmpty()){
			result = MoveResult.RED_WINS;
			numWinners++;
		}
		if (!redButterflyMap.containsValue(false) && !redButterflyMap.isEmpty()){
			result = MoveResult.BLUE_WINS;
			numWinners++;
		}
		// GET DRAW CONDITION
		if (((blueMoves == 6 && redMoves == 6) && (numWinners == 0)) || (numWinners == 2)){
			result = MoveResult.DRAW;
		}
		// END THE GAME
		if(result != MoveResult.OK){
			gameOver = true;
		}
		return result;
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
		if(isSpotTaken(safeWhere)){
			returnPiece = boardMap.get(safeWhere);
		}
		return returnPiece;
	}

	/*
	 * @see hanto.common.HantoGame#getPrintableBoard()
	 */
	@Override
	public String getPrintableBoard()
	{
		return String.join("\n", boardMap.values().toString());
	}			
	
	// FRONTIER MANAGEMENT  ---------------------------------------------
	// FRONTIER MANAGEMENT  ------------Create a class for this-----
	// FRONTIER MANAGEMENT  --Use strategy pattern for placement rules?----
	
	private void expandFrontier(HantoCoordinate newPoint){
		if (frontier.contains(newPoint)){
			frontier.remove(newPoint);
		}
		for (HantoCoordinate currentPoint : getSurroundingPoints(newPoint)){
			if(!frontier.contains(currentPoint) && !isSpotTaken(currentPoint)){
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
	
	// LEGAL MOVE CHECKERS  ---------------------------------------------
	// LEGAL MOVE CHECKERS  ------Create Strategy Pattern Move Checker!--
	// LEGAL MOVE CHECKERS  ---------------------------------------------
	
	/**
	 * Checks if a spot is taken on the board and returns true if it is. False if it is not
	 * @param spot : HantoCoordinate representing the location to check
	 * @return boolean : representing if the spot is occupied.
	 */
	private boolean isSpotTaken(HantoCoordinate spot){
		return boardMap.containsKey(spot);
	}
	
	private boolean isLocationLegal(HantoCoordinate location){
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
				!((getCurrentPlayerMap().containsKey(BUTTERFLY)) ^ pt == BUTTERFLY));
	}
	
	
	// MAP MANAGEMENT COMMANDS  ---------------------------------------------
	// MAP MANAGEMENT COMMANDS  -----Map Class? ----------
	// MAP MANAGEMENT COMMANDS  ---------------------------------------------
	
	private Map<HantoPieceType,Integer> getCurrentPlayerMap(){
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
	
	private Map<HantoCoordinate,Boolean> getCurrentPlayerButterflyMap(){
		Map<HantoCoordinate,Boolean> resultMap = null;
		if (currentColor() == BLUE){
			resultMap = blueButterflyMap;
		}else if(currentColor() == RED){
			resultMap = redButterflyMap;
		}
		return resultMap;
	}
	
	// MOVE NUMBER COMMANDS ---------------------------------------------
	// MOVE NUMBER COMMANDS ---------------------------------------------
	// MOVE NUMBER COMMANDS ---------------------------------------------
	
	private void incrementTeamMoves(HantoPlayerColor col){
		if (col == BLUE){
			blueMoves++;
		}else{
			redMoves++;
		}
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

	// COLOR COMMANDS --------------------------------------------------
	// COLOR COMMANDS --------------------------------------------------
	// COLOR COMMANDS --------------------------------------------------
	/**
	 * Get's the color of the player that is playing right now
	 * @return HantoPlayerColor
	 */
	private HantoPlayerColor currentColor(){
		return nextPlayerColor;
	}
	
	/**
	 * Returns the color of the current move and toggles the internal color to prepare for 
	 * the next move
	 * @return HantoPlayerColor : representing the color of that move. 
	 */
	private void incrementColor(){
		if (nextPlayerColor == BLUE){
			nextPlayerColor = RED;
		}else{
			nextPlayerColor = BLUE;
		}
	}
}
