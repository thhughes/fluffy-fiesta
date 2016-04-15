package hanto.studentthhughes;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentthhughes.common.colormanager.ColorManager;
import hanto.studentthhughes.common.coordinate.HantoCoordinateImpl;
import hanto.studentthhughes.common.gamestateevaluator.GameStateEvaluator;
import hanto.studentthhughes.common.hantoboardandboardtools.HantoBoard;
import hanto.studentthhughes.common.hantoboardandboardtools.HantoBoardImpl;
import hanto.studentthhughes.common.hantopiece.HantoPieceImpl;
import hanto.studentthhughes.common.movecounter.MoveCounterImpl;
import hanto.studentthhughes.common.turnactionvalidator.TurnActionValidator;

public abstract class TemplateHantoGame implements HantoGame {
	
	protected ColorManager hantoColorManager;
	protected HantoBoard hantoBoard = new HantoBoardImpl();
	protected MoveCounterImpl moveCounter = new MoveCounterImpl();
	protected TurnActionValidator actionValidator;
	protected GameStateEvaluator gameStateEvaluator;
	protected boolean gameOver = false;
	protected boolean canSurrender = false;

	protected MoveResult result = MoveResult.OK;
	protected MoveResult surrenderOutcome = null;
	
	protected HantoPlayerColor playerColor;
	protected HantoCoordinateImpl safeTo = null;
	protected HantoCoordinateImpl safeFrom = null;
	protected HantoPieceImpl currentPiece = null;
	
	
	/**
	 * Constructor for a hanto game: 
	 * @param firstMovePlayer
	 * 					HantoPlayerColor : player who goes first
	 * @param mValidator
	 * 					MoveValidator :
	 * @param bValidator
	 * 					BoardValidator :
	 */
	public TemplateHantoGame(HantoPlayerColor firstMovePlayer, TurnActionValidator tav, GameStateEvaluator gse){
		hantoColorManager = new ColorManager(firstMovePlayer);
		actionValidator = tav;
		gameStateEvaluator = gse;
		
	}

	@Override
	public MoveResult makeMove(HantoPieceType pieceType, HantoCoordinate from, HantoCoordinate to)
			throws HantoException {

		if(gameOver) 
			handleGameOver();
		
		playerColor = hantoColorManager.getCurrentColor();
		if(canSurrender && isPlayerSurrendering(pieceType, from, to)){
			handleSurrender();
		}else{
			currentPiece = new HantoPieceImpl(hantoColorManager.getCurrentColor(),pieceType);
			setCurrentCoordinates(to,from);
			preActionValidators();
			checkActionValidators();
			postActionValidators();
			updateInternalState();
			preGameStateEvaluation();
			result = checkGameStateEvaluators();
			postGameStateEvaluation();
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
	
	
	protected void postGameStateEvaluation() {
		// TODO Override this to add variety in future implementations if needed
		
	}

	protected void preGameStateEvaluation() {
		// TODO Override this to add variety in future implementations if needed
		
	}

	protected void postActionValidators() {
		// TODO Override this to add variety in future implementations if needed
		
	}

	protected void preActionValidators() {
		// TODO Override this to add variety in future implementations if needed
		
	}

	/**
	 * This method checks all the game state evaluators to build a 
	 * game state outcome for the action taken by this player
	 * @return
	 */
	protected MoveResult checkGameStateEvaluators(){
		MoveResult result;
		result = gameStateEvaluator.getOutcome(hantoBoard, moveCounter);
		if(result != MoveResult.OK){
			gameOver = true;
		}
		return result;
	}
	
	
	/**
	 * This function places a piece on the board.
	 * 
	 * @throws HantoException
	 * 					If there is an error in the board implementation when trying to place the piece. 
	 */
	private void updateInternalState() throws HantoException
	{		
		moveCounter.incrementNumberMoves(playerColor);
		updateBoardState();
		hantoColorManager.toggleCurrentColor();
	}
	
	/**
	 * This function checks if the action type is a place or a move. If it is place it updates the board
	 * by placing the 'safeTo' piece on the board. If it is a move, it removes the 'safeFrom' place and
	 * places the 'safeTo' piece on the board. 
	 * @throws HantoException
	 */
	protected void updateBoardState() throws HantoException
	{
		if(safeFrom == null){
			hantoBoard.placeOnBoard(currentPiece, safeTo);
			
		}else{
			hantoBoard.removeFromBoard(new HantoCoordinateImpl(safeFrom));
			hantoBoard.placeOnBoard(currentPiece, safeTo);
		}
	}
	
	/**
	 * This method checks all of the action validators to ensure that the move that is about to be made is a valid move. 
	 * 
	 * @throws HantoException
	 * 			Throws an exception if the action that the player wants to make is illegal.
	 */
	protected void checkActionValidators() throws HantoException
	{
		if(!actionValidator.isValidMove(hantoBoard, currentPiece, moveCounter, safeTo, safeFrom))
		{
			actionValidator.invalidError();
		}
		
	}

	/**
	 * This method error checks the coordinates that the player's move could involve. 
	 * 
	 * @param to
	 * 			HantoCoordinate
	 * @param from
	 * 			HantoCoordinate
	 * @throws HantoException
	 * 				Throws exception if the 'to' location is null
	 */
	protected void setCurrentCoordinates(HantoCoordinate to, HantoCoordinate from) throws HantoException {
		safeTo = null;
		safeFrom = null;
		
		if(to == null)
			throw new HantoException("Cannot do anything with a null to location");
		safeTo = new HantoCoordinateImpl(to);
		if(from != null)
			safeFrom = new HantoCoordinateImpl(from);
		
		
	}

	/**
	 * This method is here to handle the outcome if a surrender condition occurs
	 */
	protected void handleSurrender() {
		if(playerColor == HantoPlayerColor.RED){
			result = MoveResult.BLUE_WINS;
		}else{
			result = MoveResult.RED_WINS;
		}
		gameOver = true;
	}

	/**
	 * This function checks if a player is surrendering.
	 * 
	 * @param pieceType
	 * 				HantoPieceType
	 * @param from
	 * 				HantoCoordinate
	 * @param to
	 * 			HantoCoordinate
	 * @return
	 * 			Boolean : True if the player is surrendering
	 */
	protected boolean isPlayerSurrendering(HantoPieceType pieceType, HantoCoordinate from, HantoCoordinate to) {
		return (pieceType == null) && (from == null) && (to == null);
	}

	/**
	 * This function handles what to do if there is a game over. 
	 * 
	 * @throws HantoException
	 */
	protected void handleGameOver() throws HantoException {
		throw new HantoException("Game is over, cannot make moves after game has terminated");
	}


	@Override
	public String getPrintableBoard() {
		// TODO Auto-generated method stub
		return null;
	}

}