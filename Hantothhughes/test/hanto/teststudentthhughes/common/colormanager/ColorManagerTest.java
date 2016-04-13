package hanto.teststudentthhughes.common.colormanager;

import static org.junit.Assert.*;

import org.junit.Test;

import hanto.common.HantoPlayerColor;
import hanto.studentthhughes.common.colormanager.ColorManager;

public class ColorManagerTest {
	private static ColorManager cm; 
	
	@Test // 1
	public void testConstructor(){
		assertNotNull(cm = new ColorManager(HantoPlayerColor.BLUE));
	}
	
	@Test // 2
	public void testGetter(){
		cm = new ColorManager(HantoPlayerColor.BLUE);
		assertEquals(HantoPlayerColor.BLUE, cm.getCurrentColor());
	}
	
	@Test //3
	public void testToggleOnce(){
		cm = new ColorManager(HantoPlayerColor.BLUE);
		assertEquals(HantoPlayerColor.BLUE, cm.getCurrentColor());
		cm.toggelCurrentColor();
		assertEquals(HantoPlayerColor.RED, cm.getCurrentColor());
	}
	
	@Test //4
	public void testToggleTwice(){
		cm = new ColorManager(HantoPlayerColor.BLUE);
		assertEquals(HantoPlayerColor.BLUE, cm.getCurrentColor());
		cm.toggelCurrentColor();
		assertEquals(HantoPlayerColor.RED, cm.getCurrentColor());
		cm.toggelCurrentColor();
		assertEquals(HantoPlayerColor.BLUE, cm.getCurrentColor());
		
	}
}
