/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package hanto.studentthhughes.gamma;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentthhughes.common.colormanager.ColorManager;
import hanto.studentthhughes.common.coordinate.HantoCoordinateImpl;
import hanto.studentthhughes.common.frontier.Frontier;
import hanto.studentthhughes.common.frontier.FrontierImpl;
import hanto.studentthhughes.common.gamestatecalculator.GameStateCalculator;
import hanto.studentthhughes.common.hantoboard.HantoBoard;
import hanto.studentthhughes.common.hantoboard.HantoBoardImpl;
import hanto.studentthhughes.common.hantopiece.HantoPieceImpl;
import hanto.studentthhughes.common.movecounter.MoveCounterImpl;
import hanto.studentthhughes.common.movevalidator.MoveValidator;

/**
 * @author Troy
 *
 */
public class GammaHantoGame implements HantoGame {

	private ColorManager hantoColorManager;
	private Frontier hantoFrontier = new FrontierImpl();
	private HantoBoard hantoBoard = new HantoBoardImpl();
	private MoveCounterImpl hantoMC = new MoveCounterImpl();
	private MoveValidator hantoMV;
	private GameStateCalculator hantoBV;
	private boolean gameOver = false;
	
	
	/**
	 * Constructor for a hanto game: 
	 * @param firstMovePlayer
	 * 					HantoPlayerColor : player who goes first
	 * @param mValidator
	 * 					MoveValidator :
	 * @param bValidator
	 * 					BoardValidator :
	 */
	public GammaHantoGame(HantoPlayerColor firstMovePlayer, MoveValidator mValidator, GameStateCalculator bValidator){
		hantoColorManager = new ColorManager(firstMovePlayer);
		hantoMV = mValidator;
		hantoBV = bValidator;
		
	}
	
	
	/* (non-Javadoc)
	 * @see hanto.common.HantoGame#makeMove(hanto.common.HantoPieceType, hanto.common.HantoCoordinate, hanto.common.HantoCoordinate)
	 */
	@Override
	public MoveResult makeMove(HantoPieceType pieceType, HantoCoordinate from, HantoCoordinate to)
			throws HantoException {
		if(gameOver) throw new HantoException("Cannot make move, game is over");
		if(to == null) throw new HantoException("Must provide non-null location");
		HantoCoordinateImpl safeFrom = null;
		if(from != null) safeFrom = new HantoCoordinateImpl(from);
		
		if(!hantoMV.isValidMove(hantoBoard, new HantoPieceImpl(hantoColorManager.getCurrentColor(),pieceType), hantoMC, 
				new HantoCoordinateImpl(to), safeFrom))
		{
			hantoMV.invalidError();
		}
		
		placePiece(pieceType,from,to);
		
		MoveResult result =hantoBV.getOutcome(hantoBoard, hantoMC);
		if(result != MoveResult.OK){
			gameOver = true;
		}
		
		return result;
	}

	/* (non-Javadoc)
	 * @see hanto.common.HantoGame#getPieceAt(hanto.common.HantoCoordinate)
	 */
	@Override
	public HantoPiece getPieceAt(HantoCoordinate where) {
		return hantoBoard.getFromBoard(where);
	}

	/* (non-Javadoc)
	 * @see hanto.common.HantoGame#getPrintableBoard()
	 */
	@Override
	public String getPrintableBoard() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void placePiece(HantoPieceType pieceType, HantoCoordinate from, HantoCoordinate to) throws HantoException
	{		
		// Create the piece for internal hashing
		HantoPlayerColor curColor = hantoColorManager.getCurrentColor();
		HantoPiece movePiece = new HantoPieceImpl(curColor, pieceType);
		
		// Increment the team's move
		hantoMC.incrementNumberMoves(curColor);
		safePlace(movePiece,from,to);
		
		// Toggel the color
		hantoColorManager.toggleCurrentColor();
	}
	
	private void safePlace(HantoPiece movePiece, HantoCoordinate from, HantoCoordinate to) throws HantoException
	{
		HantoCoordinateImpl safeTo = new HantoCoordinateImpl(to);
		if(from == null){
			hantoBoard.placeOnBoard(movePiece, safeTo);
			
		}else{
			// Remove things first:
			hantoBoard.removeFromBoard(new HantoCoordinateImpl(from));
			hantoBoard.placeOnBoard(movePiece, safeTo);
			
		}
	}
}
