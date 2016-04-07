/**
 * 
 */
package hanto.studentThhughes.common.frontier;

import java.util.LinkedList;
import java.util.Queue;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPiece;
import hanto.studentThhughes.common.coordinate.HantoCoordinateImpl;

/**
 * @author Troy
 *
 */
public class FrontierImpl implements Frontier {

	
	Queue<HantoCoordinate> frontier;
	
	public FrontierImpl(){
		frontier = new LinkedList<HantoCoordinate>();
	}
	
	/* (non-Javadoc)
	 * @see hanto.studentThhughes.common.frontier.Frontier#addToFrontier(hanto.common.HantoPiece, hanto.common.HantoCoordinate)
	 */
	@Override
	public boolean addToFrontier(HantoPiece piece, HantoCoordinate place) throws HantoException {
		if (place == null) throw new HantoException("Frontier Exception: cannot add null to frontier");
		if (inFrontier(new HantoCoordinateImpl(place))) throw new HantoException("Frontier Exception: coordinate is already in the frontier");
		frontier.add(new HantoCoordinateImpl(place));
		return true;
	}

	/* (non-Javadoc)
	 * @see hanto.studentThhughes.common.frontier.Frontier#inFrontier(hanto.common.HantoCoordinate)
	 */
	@Override
	public boolean inFrontier(HantoCoordinate place) {
		boolean result = false;
		try {
			if (place == null) throw new HantoException("Frontier Exception: cannot add null to frontier");
			result = frontier.contains(new HantoCoordinateImpl(place));
		} catch (HantoException e) {}
		return result;
	}

	/* (non-Javadoc)
	 * @see hanto.studentThhughes.common.frontier.Frontier#removeFromFrontier(hanto.common.HantoCoordinate)
	 */
	@Override
	public boolean removeFromFrontier(HantoCoordinate place) throws HantoException {
		if (place == null) throw new HantoException("Frontier Exception: cannot add null to frontier");
		if (!inFrontier(new HantoCoordinateImpl(place))) throw new HantoException("Frontier Exception: coordinate is not already in the frontier");
		frontier.remove(new HantoCoordinateImpl(place));
		
		return true;
	}
	


}
