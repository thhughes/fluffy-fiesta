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
import hanto.studentThhughes.common.board.Board;
import hanto.studentThhughes.common.board.BoardImpl;
import hanto.studentThhughes.common.colorManager.ColorManager;
import hanto.studentThhughes.common.coordinate.HantoCoordinateImpl;
import hanto.studentThhughes.common.frontier.Frontier;
import hanto.studentThhughes.common.frontier.FrontierImpl;
import hanto.studentThhughes.common.hantoPiece.HantoPieceImpl;
import hanto.studentThhughes.common.moveCounter.MoveCounterImpl;
import hanto.studentThhughes.common.moveValidator.MoveValidator;

import static hanto.common.HantoPieceType.*;
import static hanto.common.HantoPlayerColor.*;


/**
 * The implementation of Beta Hanto.
 * @version Mar 16, 2016
 */
public class BetaHantoGameRefactored implements HantoGame
{

	private boolean gameOver = false;

	private Map<HantoPieceType,Integer> bluePieceMap = new HashMap<HantoPieceType, Integer>();
	private Map<HantoPieceType,Integer> redPieceMap = new HashMap<HantoPieceType, Integer>();
	private Map<HantoCoordinate,Boolean> blueButterflyMap = new HashMap<HantoCoordinate,Boolean>();
	private Map<HantoCoordinate,Boolean> redButterflyMap = new HashMap<HantoCoordinate,Boolean>();
	
	
	private ColorManager hantoColorManager;
	private Frontier hantoFrontier = new FrontierImpl();
	private Board hantoBoard = new BoardImpl();
	private MoveCounterImpl hantoMC = new MoveCounterImpl();
	private MoveValidator hantoMV;
	
	
	
	/**
	 * 
	 * 
	 * @param firstMovePlayer
	 */
	public BetaHantoGameRefactored(HantoPlayerColor firstMovePlayer, MoveValidator validator){
		hantoColorManager = new ColorManager(firstMovePlayer);
		hantoMV = validator;
		
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
		if (from != null) throw new HantoException("Illegal Move: Cannot Move Pieces In BetaHanto");
		
		if(!hantoMV.isValidMove(hantoBoard, new HantoPieceImpl(hantoColorManager.getCurrentColor(),pieceType), 
				hantoMC, safeTo, null))
		{
			hantoMV.invalidError();
		}						
		
		cachePiece(pieceType, safeTo);
		
		
		return evaluateBoardState();
		
	}

	/**
	 * This internally caches the piece in a frontier and in a hash of pieces based off it's location
	 * @param pieceType: HantoPieceType of the piece that is being passed. 
	 * @param safeTo : location of the hantoPiece as a HantoCoordinat
	 * @throws HantoException 
	 */
	private void cachePiece(HantoPieceType pieceType, HantoCoordinate safeTo) throws HantoException {
		
		// Expand the frontier of the point being placed
		for(HantoCoordinate hc : (new HantoCoordinateImpl(safeTo).getNeighbors())){
			try{
				hantoFrontier.addToFrontier(hc);
			}catch (HantoException e){
				// Do nothing, it's probably ok.
			}
		}
		
		// Create the piece for internal hashing
		HantoPlayerColor curColor = hantoColorManager.getCurrentColor();
		HantoPiece movePiece = new HantoPieceImpl(curColor, pieceType);
		
		// Increment the team's move
		hantoMC.incrementNumberMoves(curColor);
		
		// Hash data for game progression
		updateCurrentPlayerMap(pieceType);
		hantoBoard.placeOnBoard(movePiece, safeTo);
		
		// Get the butterfly data and update 
		Map<HantoCoordinate,Boolean> butterflyMap = getCurrentPlayerButterflyMap();
		
		if (pieceType == BUTTERFLY){
			butterflyMap.clear();
			for (HantoCoordinate somePoint : (new HantoCoordinateImpl(safeTo)).getNeighbors()){
				butterflyMap.put(somePoint, hantoBoard.isLocationOccupied(somePoint));
			}
		}
		if (blueButterflyMap.containsKey(safeTo) && !blueButterflyMap.isEmpty()){
			blueButterflyMap.put(safeTo, hantoBoard.isLocationOccupied(safeTo));
		}
		if (redButterflyMap.containsKey(safeTo) && !redButterflyMap.isEmpty()){
			redButterflyMap.put(safeTo, hantoBoard.isLocationOccupied(safeTo));
		}
		
		
		hantoColorManager.toggelCurrentColor();
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
		if (((hantoMC.getNumberMoves(BLUE) == 6 && hantoMC.getNumberMoves(RED) == 6) && (numWinners == 0)) || (numWinners == 2)){
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
		return hantoBoard.getFromBoard(where);
	}

	/*
	 * @see hanto.common.HantoGame#getPrintableBoard()
	 */
	@Override
	public String getPrintableBoard()
	{
		return "Running Get Printable Board: hello World";
	}			
	
	
	// MAP MANAGEMENT COMMANDS  ---------------------------------------------
	// MAP MANAGEMENT COMMANDS  -----Map Class? ----------
	// MAP MANAGEMENT COMMANDS  ---------------------------------------------
	
	private void updateCurrentPlayerMap(HantoPieceType newPiece){
		Map<HantoPieceType,Integer> updateMap = null;
		if (hantoColorManager.getCurrentColor() == BLUE){
			updateMap = bluePieceMap;
		}else if(hantoColorManager.getCurrentColor() == RED){
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
		if (hantoColorManager.getCurrentColor() == BLUE){
			resultMap = blueButterflyMap;
		}else if(hantoColorManager.getCurrentColor() == RED){
			resultMap = redButterflyMap;
		}
		return resultMap;
	}


}
