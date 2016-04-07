/**
 * 
 */
package hanto.studentThhughes.common.board;

import java.util.HashMap;
import java.util.Map;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPiece;
import hanto.studentThhughes.common.coordinate.HantoCoordinateImpl;
import hanto.studentThhughes.common.hantoPiece.HantoPieceImpl;

/**
 * @author Troy
 *
 */
public class BoardImpl implements Board {

	Map<HantoCoordinateImpl,HantoPieceImpl> theBoard;
	public BoardImpl(){
		theBoard = new HashMap<HantoCoordinateImpl,HantoPieceImpl>();
	}
	
	/* (non-Javadoc)
	 * @see hanto.studentThhughes.common.board.Board#placeOnBoard(hanto.common.HantoPiece, hanto.common.HantoCoordinate)
	 */
	@Override
	public boolean placeOnBoard(HantoPiece piece, HantoCoordinate where) throws HantoException {
		if (piece == null) throw new HantoException("Board Exception: Cannot place null on board");
		if (where == null) throw new HantoException("Board Exception: Cannot place piece at null on board");
		
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see hanto.studentThhughes.common.board.Board#getFromBoard(hanto.common.HantoCoordinate)
	 */
	@Override
	public HantoPiece getFromBoard(HantoCoordinate where) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see hanto.studentThhughes.common.board.Board#isLocationOccupied(hanto.common.HantoCoordinate)
	 */
	@Override
	public boolean isLocationOccupied(HantoCoordinate where) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeFromBoard(HantoCoordinate where) throws HantoException {
		if (where == null) throw new HantoException("Board Exception: Cannot get piece at null on board");
		if (theBoard.isEmpty()) throw new HantoException("Board Exception: Cannot remove from empty board");
		// TODO Auto-generated method stub
		return false;
	}

}
