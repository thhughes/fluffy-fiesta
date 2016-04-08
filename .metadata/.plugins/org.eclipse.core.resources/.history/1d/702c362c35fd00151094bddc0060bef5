package hanto.studentThhughes.common.moveCounter;

import static hanto.common.HantoPlayerColor.RED;

import hanto.common.HantoPlayerColor;

public class MoveCounterImpl implements MoveCounter{
	HantoPlayerColor currentColor;
	
	int red;
	int blue;
	
	public MoveCounterImpl(){
		red = 0;
		blue = 0;
	}
	
	public int getNumberMoves(HantoPlayerColor player){
		int result = -1;
		if(player == RED){
			result = red;
		}else{
			result = blue;
		}
		return result;
	}
	
	public void incrementNumberMoves(HantoPlayerColor player){
		if(player == RED){
			red++;
		}else{
			blue++;
		}
	}

}