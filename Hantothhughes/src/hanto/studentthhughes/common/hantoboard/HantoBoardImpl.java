/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package hanto.studentthhughes.common.hantoboard;

import java.util.HashMap;
import java.util.Map;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPiece;
import hanto.common.HantoPlayerColor;
import hanto.studentthhughes.common.coordinate.HantoCoordinateImpl;

/**
 * @author Troy
 *
 */
public class HantoBoardImpl implements HantoBoard {

	Map<HantoCoordinateImpl,HantoPiece> theBoard;
	Map<HantoCoordinate,HantoPiece> dumbBoard;
	Map<HantoCoordinate,HantoPiece> redBoard;
	Map<HantoCoordinate,HantoPiece> blueBoard;
	
	
	public HantoBoardImpl(){
		theBoard = new HashMap<HantoCoordinateImpl,HantoPiece>();
		dumbBoard = new HashMap<HantoCoordinate,HantoPiece>();
		redBoard = new HashMap<HantoCoordinate,HantoPiece>();
		blueBoard = new HashMap<HantoCoordinate,HantoPiece>();
		
	}
	
	/**
	 * Copy Constructor of a BoardImpl from any board type
	 * @param someBoard
	 */
	public HantoBoardImpl(HantoBoard someBoard){
		this.redBoard = someBoard.getPlayerPieces(HantoPlayerColor.RED);
		this.blueBoard = someBoard.getPlayerPieces(HantoPlayerColor.BLUE);
		this.dumbBoard = someBoard.getPlayerPieces(null);
		theBoard = new HashMap<HantoCoordinateImpl,HantoPiece>();
		
		for(HantoCoordinate hc : this.dumbBoard.keySet()){
			theBoard.put(new HantoCoordinateImpl(hc), dumbBoard.get(hc));
		}
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
		dumbBoard.put(new HantoCoordinateImpl(where), piece);
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
		dumbBoard.remove(new HantoCoordinateImpl(where));
		playerBoard.remove(new HantoCoordinateImpl(where));
		return true;
	}

	@Override
	public Map<HantoCoordinate, HantoPiece> getPlayerPieces(HantoPlayerColor color) {
		if(color == null){
			return dumbBoard;
		}else if(color == HantoPlayerColor.RED){
			return redBoard;
		}
		return blueBoard;
	}
	

}