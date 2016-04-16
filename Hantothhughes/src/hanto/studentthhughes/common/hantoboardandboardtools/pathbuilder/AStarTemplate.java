/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package hanto.studentthhughes.common.hantoboardandboardtools.pathbuilder;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import hanto.common.HantoCoordinate;
import hanto.studentthhughes.common.coordinate.HantoCoordinateImpl;
import hanto.studentthhughes.common.hantoboardandboardtools.HantoBoard;

/**
 * This is a template for the search algorithm aStar. The notable function to get 
 * overridden in this the getNeighbors function. By overloading it it's possible to
 * Drastically change how the algorithm searches. 
 * @author Troy
 *
 */
public abstract class AStarTemplate implements PathBuilder{

	HantoCoordinateImpl start;
	HantoCoordinateImpl end;

	
	
	public List<HantoCoordinate> getPath(HantoBoard theBoard, 
			HantoCoordinate start, HantoCoordinate end){
		this.start = new HantoCoordinateImpl(start);
		this.end = new HantoCoordinateImpl(end);
		return makeAStarPath(theBoard, start, end);
	}
	
	
	/**
	 * Function finds the path from one hex to another hex and avoids obstacles
	 * that are on the board (obstacles being occupied hex's)
	 * @param theBoard 
	 * 				HantoBoard that the coordinates are on
	 * @param start
	 * 				HantoCoordinate representing the start of the search
	 * @param end
	 * 				HantoCoordinate representing the end of the search
	 * @return
	 * 			List<HantoCoordinate> where the first entry is the 
	 * 			start and the last entry is the end
	 */
	protected List<HantoCoordinate> makeAStarPath(HantoBoard theBoard, 
			HantoCoordinate start, HantoCoordinate end) {
		
		final Map<HantoCoordinateImpl, HantoCoordinateImpl> aStarTree = 
				aStar(theBoard, start, end);
		
		return pathFromTree(aStarTree, 
				new HantoCoordinateImpl(start), new HantoCoordinateImpl(end));
	}

	/**
	 * Converts an aStar tree to a path
	 * 
	 * @param aStarTree
	 * 				Map<HantoCoordinateImpl, HantoCoordinateImpl>
	 *  where it's structured as to<key> from<value>
	 * @param start
	 * 				HantoCoordinateImpl
	 * @param end
	 * 				HantoCoordinateImpl
	 * @return
	 * 			List<HantoCoordinate>
	 */
	protected List<HantoCoordinate> pathFromTree(
			Map<HantoCoordinateImpl, HantoCoordinateImpl> aStarTree, HantoCoordinateImpl start,
			HantoCoordinateImpl end) {
		
		final List<HantoCoordinate> path = new LinkedList<HantoCoordinate>();
		HantoCoordinateImpl current = end;
		path.add(current);
		
		while(!current.equals(start)){
			path.add(0, aStarTree.get(current));
			current = aStarTree.get(current);
		}
		
		return path;
	}
	
	/**
	 * AStar implementation for HantoCoordinates
	 * @param theBoard 
	 * 				HantoBoard that the coordinates are on
	 * @param start
	 * 				HantoCoordinate
	 * @param end
	 * 				HantoCoordinate
	 * @return
	 * 			Map<HantoCoordinateImpl, HantoCoordinateImpl> Stored as to->from, meaning if 
	 * 			the system travels from a->b, b will be the key for a. 
	 */
	protected Map<HantoCoordinateImpl, HantoCoordinateImpl> aStar(
			HantoBoard theBoard, HantoCoordinate start, HantoCoordinate end) {
		
		final PriorityQueue<Node> frontier = makePriorityQueue();
		frontier.add(new Node(new Integer(0), new HantoCoordinateImpl(start)));
		
		final Map<HantoCoordinateImpl, HantoCoordinateImpl> came_from = 
				new HashMap<HantoCoordinateImpl, HantoCoordinateImpl>();
		
		final Map<HantoCoordinateImpl, Integer> cost_so_far = 
				new HashMap<HantoCoordinateImpl, Integer>();
		
		came_from.put(new HantoCoordinateImpl(start), null);
		cost_so_far.put(new HantoCoordinateImpl(start), new Integer(0));
		
		HantoCoordinateImpl current; 
		while (!frontier.isEmpty()){
			current = frontier.poll().getCoordinate();
			
			if(current.equals(new HantoCoordinateImpl(end))){
				break;
			}
			
			for(HantoCoordinateImpl hci : getLegalNeighbors(theBoard, current.getNeighbors())){
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

	/**
	 * This get's the nonOccupied neighbors of a given list of neighbors
	 * @param theBoard
	 * 				HantoBoard that the players are on 
	 * @param neighbors
	 * 				Queue<HantoCoordinate>
	 * @return
	 * 			Queue<HantoCoordinateImpl> of nodes that are not occupied.
	 */
	protected Queue<HantoCoordinateImpl> getLegalNeighbors(
			HantoBoard theBoard, Queue<HantoCoordinate> neighbors) {
		
		final Queue<HantoCoordinateImpl> implList = new LinkedList<HantoCoordinateImpl>();
		for(HantoCoordinate hc:  neighbors){
			if(!theBoard.isLocationOccupied(hc)){
				implList.add(new HantoCoordinateImpl(hc));
			}	
		}
		return implList;
	}

	
	/**
	 * Factory method for a Priority Queue of Nodes
	 * 
	 * @return
	 * 			PriorityQueue<Node> that represents the frontier of the search
	 */
	protected PriorityQueue<Node> makePriorityQueue(){
		return new PriorityQueue<Node>(10, makeComparator());
	}
	
	/**
	 * Factory method for a node comparator
	 * @return
	 * 			Comparator<Node> : to allow the system to compare two nodes
	 */
	protected Comparator<Node> makeComparator() {
		return new Comparator<Node>(){
			public int compare(Node n1, Node n2){
				return n1.getPriority().compareTo(n2.getPriority());
			}
		};
	}

	/**
	 * Node class for aStar
	 * 
	 * @author Troy
	 *
	 */
	class Node{
		private final Integer priority;
		private final HantoCoordinateImpl location;
		
		/**
		 * constructor
		 * @param p
		 * 			Integer : priority of this node
		 * @param l
		 * 			HantoCoordinateImpl : location 
		 */
		Node(Integer p, HantoCoordinateImpl l){
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
