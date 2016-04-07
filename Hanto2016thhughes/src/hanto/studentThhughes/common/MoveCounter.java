package hanto.studentThhughes.common;

import static hanto.common.HantoPlayerColor.RED;

import hanto.common.HantoPlayerColor;

public class MoveCounter {
	HantoPlayerColor currentColor;
	
	int red;
	int blue;
	
	public MoveCounter(){
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