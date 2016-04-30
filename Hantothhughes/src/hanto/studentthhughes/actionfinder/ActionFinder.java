package hanto.studentthhughes.actionfinder;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import hanto.common.HantoCoordinate;
import hanto.common.HantoGameID;

import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentthhughes.common.HantoGamePieceInformation;
import hanto.studentthhughes.common.HantoGameStateEvaluatorFactory;
import hanto.studentthhughes.common.HantoTurnActionValidatorFactory;
import hanto.studentthhughes.common.coordinate.HantoCoordinateImpl;
import hanto.studentthhughes.common.gamestateevaluator.GameStateEvaluator;
import hanto.studentthhughes.common.hantoboardandboardtools.HantoBoard;
import hanto.studentthhughes.common.hantopiece.HantoPieceImpl;
import hanto.studentthhughes.common.movecounter.MoveCounter;
import hanto.studentthhughes.common.turnactionvalidator.TurnActionValidator;
import hanto.tournament.HantoMoveRecord;

public class ActionFinder {
	TurnActionValidator tav;
	GameStateEvaluator gse;
	HantoGamePieceInformation pieceMaster;
	
	public ActionFinder(HantoGameID gameID){
		gse = HantoGameStateEvaluatorFactory.getInstance().makeHantoValidator(gameID);
		tav = HantoTurnActionValidatorFactory.getInstance().makeHantoValidator(gameID);
		pieceMaster = new HantoGamePieceInformation(gameID);
		
	}
	
	/**
	 * Gets a HantoMoveRecord for a given color at a given turn on a given board
	 * 
	 * @param theBoard
	 * 				HantoBoard : the board to check if a move can be made
	 * @param theCounter
	 * 				MoveCounter : information on the turns of the game for the board
	 * @param color
	 * 				HantoPlayerColor : the color of the player to check if an action is makeable
	 * @return
	 * 				HantoMoveRecord : that contains the move information for the action
	 */
	public HantoMoveRecord getAction(HantoBoard theBoard, MoveCounter theCounter, HantoPlayerColor color){
		HantoMoveRecord result = getPlaceAction(theBoard, theCounter, color);
		if(result == null){
			result = getMoveAction(theBoard, theCounter, color);
		}
		return result;
	}
	
	/**
	 * Checks to make sure that an action is able to be made
	 * 
	 * @param theBoard
	 * 				HantoBoard : the board to check if a move can be made
	 * @param theCounter
	 * 				MoveCounter : information on the turns of the game for the board
	 * @param color
	 * 				HantoPlayerColor : the color of the player to check if an action is makeable
	 * @return
	 * 				Boolean : true if an action is makeable.
	 */
	public boolean isActionMakeable(HantoBoard theBoard, MoveCounter theCounter, HantoPlayerColor color){
		boolean result = true;
		if(getAction(theBoard,theCounter,color) == null){
			result = false;
		}
		return result;
	}


	/**
	 * Get's an action for a move for a piece on a board of a given color
	 * 
	 * @param theBoard
	 * 				HantoBoard : The board that the move needs to be found on
	 * @param theCounter
	 * 				MoveCounter : The turn counter for the game
	 * @param color
	 * 				HantoPlayerColor : the color of the player
	 * @return
	 * 				HantoMoveRecord : A move record for moving a piece
	 */
	public HantoMoveRecord getMoveAction(HantoBoard theBoard, MoveCounter theCounter, HantoPlayerColor color) {
		Map<HantoCoordinate, HantoPiece> playerMap = theBoard.getPlayerPieces(color);
		Set<HantoCoordinate> allEmpty = getAllEmptyHexOnBoard(theBoard);
		HantoMoveRecord result = null;
		
		for(HantoCoordinate hc : playerMap.keySet()){
			result = findMoveForPiece(hc, playerMap.get(hc), theBoard, theCounter, allEmpty);
			if(result != null){
				break;
			}
		}
		
		
		return result;
	}


	/**
	 * This method get's an action of placing 
	 * @param theBoard
	 * @param theCounter
	 * @param color
	 * @return
	 */
	private HantoMoveRecord getPlaceAction(HantoBoard theBoard, MoveCounter theCounter, HantoPlayerColor color) {		
		HantoMoveRecord result = null;
		HantoPieceType piece = pickPieceToPlace(theBoard.getPlayerPieces(color).values());
		
		if(piece != null){
			result = pickPlaceToPutPiece(piece, theBoard, theCounter, color);
		}
		
		return result;
	}
	
	private HantoMoveRecord findMoveForPiece(HantoCoordinate from, HantoPiece piece, HantoBoard theBoard,
			MoveCounter theCounter, Set<HantoCoordinate> allEmpty) {
		HantoMoveRecord result = null;
		for(HantoCoordinate hc: allEmpty){
			if(tav.isValidMove(theBoard, piece, theCounter, hc, from)){
				result = new HantoMoveRecord(piece.getType(),from,hc);
				break;
			}
		}
		return result;
	}

	private Set<HantoCoordinate> getAllEmptyHexOnBoard(HantoBoard theBoard) {
		Set<HantoCoordinate> allEmpty = getEmptySpacesNearPlayerPieces(theBoard, HantoPlayerColor.BLUE);
		allEmpty.addAll(getEmptySpacesNearPlayerPieces(theBoard, HantoPlayerColor.RED));
		return allEmpty;
	}


	
	private HantoMoveRecord pickPlaceToPutPiece(HantoPieceType type, HantoBoard theBoard, 
			MoveCounter theCounter, HantoPlayerColor color) {
		
		HantoCoordinate placeLocation = findPlaceLocation(theBoard, theCounter, color, type);
		
		HantoMoveRecord result = null;
		if (placeLocation != null){
			result = new HantoMoveRecord(type, null, placeLocation);
		}
		return result;
	}

	private HantoCoordinate findPlaceLocation(HantoBoard theBoard, 
			MoveCounter theCounter, HantoPlayerColor color, HantoPieceType type) {
		
		Collection<HantoCoordinate> possibleLocations = getEmptySpacesNearPlayerPieces(theBoard, color);
		
		return pickPlaceLocation(possibleLocations, theBoard, theCounter, new HantoPieceImpl(color,type));
	}
	
	/**
	 * This picks a valid location from a set of possible locations to place a piece. 
	 * 
	 * @param possibleLocations
	 * 						Collection<HantoCoordinate> : collection of possible 
	 * locaitons that the piece could be placed
	 * @param theBoard
	 * 					HantoBoard
	 * @param theCounter
	 * 					HantoCounter
	 * @param piece
	 * 				HantoPieceImplementation
	 * @return
	 * 				HantoCoordinate : for the location of the placement.
	 */
	private HantoCoordinate pickPlaceLocation(Collection<HantoCoordinate> possibleLocations, HantoBoard theBoard,
			MoveCounter theCounter, HantoPiece piece) {
		
		HantoCoordinate result = null;
		for(HantoCoordinate hc: possibleLocations){
			if(tav.isValidMove(theBoard, piece, theCounter, hc, null)){
				result = hc;
				break;
			}
		}
		return result;
	}

	/**
	 * THis get's the empty hex's near a player color's hex's 
	 * 
	 * @param theBoard
	 * 				HantoBoard : the board to get the hex locations
	 * @param color
	 * 				HantoPlayerColor : the color of the hex's to get
	 * @return
	 * 			Set<HantoCoordinate> : representing empty hex's near a teammates color
	 */
	private Set<HantoCoordinate> getEmptySpacesNearPlayerPieces(HantoBoard theBoard, HantoPlayerColor color) {
		Collection<HantoCoordinate> playerPlaces = theBoard.getPlayerPieces(color).keySet();
		Set<HantoCoordinateImpl> possibleValues = new LinkedHashSet<HantoCoordinateImpl>();
		
		for(HantoCoordinate hc: playerPlaces){
			HantoCoordinateImpl hci = new HantoCoordinateImpl(hc);
			for(HantoCoordinate hcNeigh : hci.getNeighbors()){
				if(!theBoard.isLocationOccupied(hcNeigh)){
					HantoCoordinateImpl hciNeigh = new HantoCoordinateImpl(hcNeigh);
					possibleValues.add(hciNeigh);
				}
			}
		}
		return implSetToCoordSet(possibleValues);
	}

	

	private Set<HantoCoordinate> implSetToCoordSet(Set<HantoCoordinateImpl> possibleValues) {
		Set<HantoCoordinate> result = new LinkedHashSet<HantoCoordinate>();
		
		for(HantoCoordinateImpl hci : possibleValues){
			result.add(hci);
		}
		return result;
	}

	/**
	 * Finds a valid piece to place
	 * 
	 * @param values
	 * 				Collection<HantoPiece> that are currently on the board
	 * @return
	 * 			HantoPiece or Null : Piece if there is a valid one and null if there is not 
	 */
	private HantoPieceType pickPieceToPlace(Collection<HantoPiece> values) {
		HantoPieceType result = null;
		Map<HantoPieceType, Integer> pieceCount = getNumberPieceMap(values);
		for(HantoPieceType hpt : pieceCount.keySet()){
			if(pieceCount.get(hpt) < pieceMaster.getMaxNumPiece(hpt)){
				result = hpt;
				break;
			}

		}
		return result;
	}

	/**
	 * Get a map of HantoPieceType's to Integers that contains the count of 
	 * each of the types of pieces on the board. 
	 * 
	 * @param values
	 * 				Collection<HantoPiece> : of HantoPieces on a board
	 * @return
	 * 			Map<HantoPieceType, Integer> of the information on the board.
	 */
	private Map<HantoPieceType, Integer> getNumberPieceMap(Collection<HantoPiece> values) {
		Map<HantoPieceType, Integer> count = new HashMap<HantoPieceType, Integer>();
		for(HantoPiece hp : values){
			if(count.containsKey(hp.getType())){
				count.put(hp.getType(), new Integer(count.get(hp.getType()) + 1));
			}else{
				count.put(hp.getType(), new Integer(1));
			}
		}
		return count;
	}

}
