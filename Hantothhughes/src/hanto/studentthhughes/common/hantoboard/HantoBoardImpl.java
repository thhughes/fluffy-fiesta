/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package hanto.studentthhughes.common.hantoboard;

import java.util.List;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

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

	private static final int Node = 0;
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

	@Override
	public List<HantoCoordinate> getPath(HantoCoordinate start, HantoCoordinate end) {
		Map<HantoCoordinateImpl, HantoCoordinateImpl> aStarTree = aStar(start,end);
		return pathFromTree(aStarTree, new HantoCoordinateImpl(start), new HantoCoordinateImpl(end));
	}

	private List<HantoCoordinate> pathFromTree(
			Map<HantoCoordinateImpl, HantoCoordinateImpl> aStarTree, HantoCoordinateImpl start,
			HantoCoordinateImpl end) {
		List<HantoCoordinate> path = new LinkedList<HantoCoordinate>();
		HantoCoordinateImpl current = end;
		path.add(current);
		
		while(!current.equals(start)){
			path.add(0,aStarTree.get(current));
			current = aStarTree.get(current);
		}
		
		return path;
	}

	private Map<HantoCoordinateImpl, HantoCoordinateImpl> aStar(HantoCoordinate start, HantoCoordinate end) {
		PriorityQueue<Node> frontier = makePriorityQueue();
		frontier.add(new Node(new Integer(0), new HantoCoordinateImpl(start)));
		
		Map<HantoCoordinateImpl, HantoCoordinateImpl> came_from = new HashMap<HantoCoordinateImpl, HantoCoordinateImpl>();
		Map<HantoCoordinateImpl, Integer> cost_so_far = new HashMap<HantoCoordinateImpl, Integer>();
		
		came_from.put(new HantoCoordinateImpl(start), null);
		cost_so_far.put(new HantoCoordinateImpl(start), new Integer(0));
		
		HantoCoordinateImpl current; 
		while (!frontier.isEmpty()){
			current = frontier.poll().getCoordinate();
			
			if(current.equals(new HantoCoordinateImpl(end))){
				break;
			}
			
			for(HantoCoordinateImpl hci : getOpenNeighbors(current.getNeighbors())){
				Integer newCost = cost_so_far.get(current) + new Integer(1);
				if(!cost_so_far.containsKey(hci) || newCost < cost_so_far.get(hci)){
					cost_so_far.remove(hci);
					cost_so_far.put(hci, newCost);
					Integer priority = newCost + new Integer(1);
					frontier.add(new Node(priority, hci));
					came_from.remove(hci);
					came_from.put(hci, current);
				}
			}
		}
		return came_from;
	}

	private Queue<HantoCoordinateImpl> getOpenNeighbors(Queue<HantoCoordinate> neighbors) {
		Queue<HantoCoordinateImpl> implList = new LinkedList<HantoCoordinateImpl>();
		for(HantoCoordinate hc:  neighbors){
			if(!isLocationOccupied(hc)){
				implList.add(new HantoCoordinateImpl(hc));
			}	
		}
		return implList;
	}

	
	
	private PriorityQueue<Node> makePriorityQueue(){
		return new PriorityQueue<Node>(10, makeComparator());
	}
	
	private Comparator<Node> makeComparator() {
		// TODO Auto-generated method stub
		return new Comparator<Node>(){
			public int compare(Node n1, Node n2){
				return n1.getPriority().compareTo(n2.getPriority());
			}
		};
	}

	class Node{
		private Integer priority;
		private HantoCoordinateImpl location;
		
		public Node(Integer p, HantoCoordinateImpl l){
			priority = p;
			location = l;
		}
		
		public Integer getPriority(){
			return priority;
		}
		
		public HantoCoordinateImpl getCoordinate(){
			return location;
		}
	}
	

	

}
