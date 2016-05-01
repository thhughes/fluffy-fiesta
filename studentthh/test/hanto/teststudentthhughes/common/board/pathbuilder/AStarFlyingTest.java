package hanto.teststudentthhughes.common.board.pathbuilder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentthhughes.common.coordinate.HantoCoordinateImpl;
import hanto.studentthhughes.common.hantoboardandboardtools.HantoBoard;
import hanto.studentthhughes.common.hantoboardandboardtools.HantoBoardImpl;
import hanto.studentthhughes.common.hantoboardandboardtools.pathbuilder.AStarFlying;
import hanto.studentthhughes.common.hantoboardandboardtools.pathbuilder.PathBuilder;
import hanto.studentthhughes.common.hantopiece.HantoPieceImpl;

public class AStarFlyingTest {
	private static final PathBuilder astar = new AStarFlying();
	private static HantoBoard theBoard;
	private static HantoPiece PA;
	private static HantoPiece PB;
	private static HantoCoordinate CA;
	private static HantoCoordinate CB;
	
	
	@Before
	public void setup(){
		theBoard = new HantoBoardImpl();
		PA = new HantoPieceImpl(HantoPlayerColor.RED,HantoPieceType.SPARROW);
		PB = new HantoPieceImpl(HantoPlayerColor.RED,HantoPieceType.BUTTERFLY);
		CA = new HantoCoordinateImpl(0,0);
		CB = new HantoCoordinateImpl(0,1);
	}
	
	// Path Planning 
		@Test
		public void testPathBuildFromStraightLineLength() throws HantoException
		{	
			List<HantoCoordinate> path = astar.getPath(theBoard, new HantoCoordinateImpl(0,-1), new HantoCoordinateImpl(0,2));
			assertTrue(path.size() == 4);
		}
		
		@Test
		public void testPathBuildFromStraightLineValues() throws HantoException
		{
			List<HantoCoordinate> path = astar.getPath(theBoard, new HantoCoordinateImpl(0,-1), new HantoCoordinateImpl(0,2));
			TestPathContents(path, hc(0,-1),hc(0,0),hc(0,1),hc(0,2));
		}
		
		
		@Test
		public void testPathCalculationAvoidingObstacleLength() throws HantoException
		{	
			theBoard.placeOnBoard(PA, CA);
			theBoard.placeOnBoard(PB,CB);
			List<HantoCoordinate> path = astar.getPath(theBoard, new HantoCoordinateImpl(0,-1), new HantoCoordinateImpl(0,2));
			assertTrue(path.size() == 4);
		}
		
		@Test
		public void testPathCalculationAvoidingObstacleValues() throws HantoException
		{	
			theBoard.placeOnBoard(PA, CA);
			theBoard.placeOnBoard(PB,CB);
			List<HantoCoordinate> path = astar.getPath(theBoard, new HantoCoordinateImpl(0,-1), new HantoCoordinateImpl(0,2));
			TestPathContents(path,hc(0,-1),hc(0,0),hc(0,1),hc(0,2));
		}
		
		@Test
		public void testPathBetweenNeighboringPointsTestLength() throws HantoException{
			List<HantoCoordinate> path = astar.getPath(theBoard, new HantoCoordinateImpl(0,0), new HantoCoordinateImpl(0,1));
			assertEquals(path.size(),2);
		}
		
		@Test
		public void testPathBetweenNeighboringPointsTestValues() throws HantoException{
			List<HantoCoordinate> path = astar.getPath(theBoard, new HantoCoordinateImpl(0,0), new HantoCoordinateImpl(0,1));
			TestPathContents(path,hc(0,0),hc(0,1));
		}
		
		
		// Helper Functions
		private HantoCoordinateImpl hc(int x, int y) {
			return new HantoCoordinateImpl(x,y);
		}
		
		
		private List<HantoCoordinateImpl> coordinatePathToImplPath(List<HantoCoordinate> path){
			List<HantoCoordinateImpl> implList = new LinkedList<HantoCoordinateImpl>();
			for(HantoCoordinate hc: path){
				implList.add(new HantoCoordinateImpl(hc));
			}
			return implList;
		}
		
		private void TestPathContents(List<HantoCoordinate> path, HantoCoordinateImpl...coordinateImpls){
			int counter = 0;
			for(HantoCoordinateImpl hci : coordinateImpls){
				assertTrue(coordinatePathToImplPath(path).contains(hci));
				counter++;
			}
			assertEquals(path.size(),counter);
		}
}
