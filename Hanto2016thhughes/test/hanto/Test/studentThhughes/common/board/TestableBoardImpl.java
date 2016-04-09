/**
 * 
 */
package hanto.Test.studentThhughes.common.board;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentThhughes.common.board.Board;
import hanto.studentThhughes.common.board.BoardImpl;
import hanto.studentThhughes.common.boardValidator.BoardValidator;
import hanto.studentThhughes.common.coordinate.HantoCoordinateImpl;
import hanto.studentThhughes.common.hantoPiece.HantoPieceImpl;

/**
 * @author Troy
 *
 */
public class TestableBoardImpl implements Board {


	private Board theBoard;
	HantoCoordinate hc1;
	HantoCoordinate hc2;
	HantoCoordinate hc3;
	HantoCoordinate hc4;
	HantoCoordinate hc5;
	HantoCoordinate hc6;
	HantoCoordinate hc7;
	
	

	
	public TestableBoardImpl() throws HantoException{
		
		theBoard = new BoardImpl();
		
		hc1 = new HantoCoordinateImpl(0,0); // B
		hc2 = new HantoCoordinateImpl(0,1); // R
		hc3 = new HantoCoordinateImpl(-1,2); 	// R
		hc4 = new HantoCoordinateImpl(1,-1); // B
		hc5 = new HantoCoordinateImpl(1,1);		// R
		hc6 = new HantoCoordinateImpl(0,-1); // B
		hc7 = new HantoCoordinateImpl(-1,0); // B
		
		placeOnBoard(new HantoPieceImpl(HantoPlayerColor.BLUE, HantoPieceType.BUTTERFLY), hc1);
		placeOnBoard(new HantoPieceImpl(HantoPlayerColor.RED,  HantoPieceType.BUTTERFLY), hc2);
		placeOnBoard(new HantoPieceImpl(HantoPlayerColor.RED, HantoPieceType.SPARROW), hc3);
		placeOnBoard(new HantoPieceImpl(HantoPlayerColor.BLUE,  HantoPieceType.SPARROW), hc4);
		placeOnBoard(new HantoPieceImpl(HantoPlayerColor.RED, HantoPieceType.SPARROW), hc5);
		placeOnBoard(new HantoPieceImpl(HantoPlayerColor.BLUE,  HantoPieceType.SPARROW), hc6);
		placeOnBoard(new HantoPieceImpl(HantoPlayerColor.BLUE, HantoPieceType.SPARROW), hc7);
		
	}
	
	
	/* (non-Javadoc)
	 * @see hanto.studentThhughes.common.board.Board#placeOnBoard(hanto.common.HantoPiece, hanto.common.HantoCoordinate)
	 */
	@Override
	public boolean placeOnBoard(HantoPiece piece, HantoCoordinate where) throws HantoException {
		return theBoard.placeOnBoard(piece, where);
	}

	/* (non-Javadoc)
	 * @see hanto.studentThhughes.common.board.Board#getFromBoard(hanto.common.HantoCoordinate)
	 */
	@Override
	public HantoPiece getFromBoard(HantoCoordinate where) {
		return theBoard.getFromBoard(where);
	}

	/* (non-Javadoc)
	 * @see hanto.studentThhughes.common.board.Board#isLocationOccupied(hanto.common.HantoCoordinate)
	 */
	@Override
	public boolean isLocationOccupied(HantoCoordinate where) {
		return theBoard.isLocationOccupied(where);
	}

	/* (non-Javadoc)
	 * @see hanto.studentThhughes.common.board.Board#removeFromBoard(hanto.common.HantoCoordinate)
	 */
	@Override
	public boolean removeFromBoard(HantoCoordinate where) throws HantoException {
		return theBoard.removeFromBoard(where);
	}

	/* (non-Javadoc)
	 * @see hanto.studentThhughes.common.board.Board#getPlayerPieces(hanto.common.HantoPlayerColor)
	 */
	@Override
	public Map<HantoCoordinate, HantoPiece> getPlayerPieces(HantoPlayerColor color) {
		return theBoard.getPlayerPieces(color);
	}

}