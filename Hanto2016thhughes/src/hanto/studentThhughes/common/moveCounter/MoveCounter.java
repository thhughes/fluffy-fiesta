package hanto.studentThhughes.common.moveCounter;


import hanto.common.HantoPlayerColor;

public interface MoveCounter {
	
	
	
	public int getNumberMoves(HantoPlayerColor player);
	
	public void incrementNumberMoves(HantoPlayerColor player);
}
