/**
 * 
 */
package hanto.studentThhughes.common.board;

import java.util.HashMap;
import java.util.Map;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPiece;
import hanto.common.HantoPlayerColor;
import hanto.studentThhughes.common.coordinate.HantoCoordinateImpl;

/**
 * @author Troy
 *
 */
public class BoardImpl implements Board {

	Map<HantoCoordinateImpl,HantoPiece> theBoard;
	Map<HantoCoordinate,HantoPiece> redBoard;
	Map<HantoCoordinate,HantoPiece> blueBoard;
	public BoardImpl(){
		theBoard = new HashMap<HantoCoordinateImpl,HantoPiece>();
		redBoard = new HashMap<HantoCoordinate,HantoPiece>();
		blueBoard = new HashMap<HantoCoordinate,HantoPiece>();
		
	}
	
	/* (non-Javadoc)
	 * @see hanto.studentThhughes.common.board.Board#placeOnBoard(hanto.common.HantoPiece, hanto.common.HantoCoordinate)
	 */
	@Override
	public boolean placeOnBoard(HantoPiece piece, HantoCoordinate where) throws HantoException {
		if (piece == null) throw new HantoException("Board Exception: Cannot place null on board");
		if (where == null) throw new HantoException("Board Exception: Cannot place piece at null on board");
		HantoCoordinateImpl cleanPiece = new HantoCoordinateImpl(where);
		
		if(theBoard.containsKey(cleanPiece)){
			theBoard.remove(cleanPiece);
		}
		Map<HantoCoordinate,HantoPiece> playerBoard = getPlayerPieces(piece.getColor());
		if(playerBoard.containsKey(cleanPiece)){
			playerBoard.remove(cleanPiece);
		}
		theBoard.put(new HantoCoordinateImpl(where), piece);
		playerBoard.put(new HantoCoordinateImpl(where), piece);
		return true;
	}

	/* (non-Javadoc)
	 * @see hanto.studentThhughes.common.board.Board#getFromBoard(hanto.common.HantoCoordinate)
	 */
	@Override
	public HantoPiece getFromBoard(HantoCoordinate where) {
		HantoPiece thePiece = null;
		if(where != null && theBoard.containsKey(new HantoCoordinateImpl(where))){
			thePiece = theBoard.get(new HantoCoordinateImpl(where));
		}
		return thePiece;
	}

	/* (non-Javadoc)
	 * @see hanto.studentThhughes.common.board.Board#isLocationOccupied(hanto.common.HantoCoordinate)
	 */
	@Override
	public boolean isLocationOccupied(HantoCoordinate where) {
		boolean result = false;
		if(where != null){
			if(theBoard.containsKey(new HantoCoordinateImpl(where))){
				result = true;
			}
		}
		return result;
	}

	@Override
	public boolean removeFromBoard(HantoCoordinate where) throws HantoException {
		if (where == null) throw new HantoException("Board Exception: Cannot get piece at null on board");
		if (theBoard.isEmpty()) throw new HantoException("Board Exception: Cannot remove from empty board");
		if (!theBoard.containsKey(new HantoCoordinateImpl(where))) throw new HantoException("Board Exception: Cannot remove piece that's not on board");
		
		Map<HantoCoordinate, HantoPiece> playerBoard = getPlayerPieces(theBoard.get(new HantoCoordinateImpl(where)).getColor());
		theBoard.remove(new HantoCoordinateImpl(where));
		playerBoard.remove(new HantoCoordinateImpl(where));
		return true;
	}

	@Override
	public Map<HantoCoordinate, HantoPiece> getPlayerPieces(HantoPlayerColor color) {
		if(color == HantoPlayerColor.RED){
			return redBoard;
		}
		return blueBoard;
	}
	

}
