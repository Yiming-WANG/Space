package test.se450.gui;

import static org.junit.Assert.*;

import org.junit.Test;

import main.se450.gui.ShapeDisplay;
import main.se450.interfaces.IShape;
import main.se450.model.Circle;

public class ShapeDisplayTest {

	
	@Test
	public void testDownGradeSize() {
		IShape iShape = new Circle (2,2,4,4,1,1,0,null,null);
		iShape.setSizeScoreMultiplierChildren("medium",10,2,2);
		ShapeDisplay shapedisplay = new ShapeDisplay();
		
		assertTrue( (shapedisplay.downGradeSize(iShape).get(0).getSize() == "small") );
	}

}
