/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package hanto.studentthhughes.delta;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentthhughes.common.colormanager.ColorManager;
import hanto.studentthhughes.common.coordinate.HantoCoordinateImpl;
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
public class DeltaHantoGame implements HantoGame {

	private ColorManager hantoColorManager;
	private HantoBoard hantoBoard = new HantoBoardImpl();
	private MoveCounterImpl hantoMC = new MoveCounterImpl();
	private MoveValidator hantoMV;
	private GameStateCalculator hantoBV;
	private boolean gameOver = false;
	private MoveResult surrenderOutcome = null;
	
	
	/**
	 * Constructor for a hanto game: 
	 * @param firstMovePlayer
	 * 					HantoPlayerColor : player who goes first
	 * @param mValidator
	 * 					MoveValidator :
	 * @param bValidator
	 * 					BoardValidator :
	 */
	public DeltaHantoGame(HantoPlayerColor firstMovePlayer, MoveValidator mValidator, GameStateCalculator bValidator){
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
		
		MoveResult result = MoveResult.OK;
		
		if(gameOver) throw new HantoException("Cannot make move, game is over");
		
		if(isSurrender(pieceType, from, to)) {
			handleSurrender();
		}
		else {
			if(to == null) throw new HantoException("Must provide non-null location");
			HantoCoordinateImpl safeFrom = null;
			if(from != null) 
				safeFrom = new HantoCoordinateImpl(from);
			
			if(!hantoMV.isValidMove(hantoBoard, new HantoPieceImpl(hantoColorManager.getCurrentColor(),pieceType), hantoMC, 
					new HantoCoordinateImpl(to), safeFrom))
			{
				hantoMV.invalidError();
			}
			
			placePiece(pieceType,from,to);
			
			result = hantoBV.getOutcome(hantoBoard, hantoMC);
			if(result != MoveResult.OK){
				gameOver = true;
			}
		}
		return (surrenderOutcome != null) ? surrenderOutcome : result;
	}


	private boolean isSurrender(HantoPieceType pieceType, HantoCoordinate from, HantoCoordinate to) {
		return pieceType == null && from == null && to == null;
	}

	private void handleSurrender() {
		gameOver = true;
		if(hantoColorManager.getCurrentColor() == HantoPlayerColor.RED){
			surrenderOutcome = MoveResult.BLUE_WINS;
		}else{
			surrenderOutcome = MoveResult.RED_WINS;
		}
		
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
		HantoPlayerColor curColor = hantoColorManager.getCurrentColor();
		HantoPiece movePiece = new HantoPieceImpl(curColor, pieceType);
		
		hantoMC.incrementNumberMoves(curColor);
		safePlace(movePiece,from,to);
		
		hantoColorManager.toggleCurrentColor();
	}
	
	private void safePlace(HantoPiece movePiece, HantoCoordinate from, HantoCoordinate to) throws HantoException
	{
		HantoCoordinateImpl safeTo = new HantoCoordinateImpl(to);
		if(from == null){
			hantoBoard.placeOnBoard(movePiece, safeTo);
			
		}else{
			hantoBoard.removeFromBoard(new HantoCoordinateImpl(from));
			hantoBoard.placeOnBoard(movePiece, safeTo);
			
		}
	}
}
