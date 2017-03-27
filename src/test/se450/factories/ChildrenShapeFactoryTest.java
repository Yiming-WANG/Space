package test.se450.factories;

import static org.junit.Assert.*;

import org.junit.Test;

import main.se450.factories.ChildrenShapeFactory;
import main.se450.interfaces.IShape;
import main.se450.model.Circle;


public class ChildrenShapeFactoryTest {

	@Test
	public void testMakeChildrenShape(){
		IShape iShape = new Circle (2,2,4,4,1,1,0,null,null);
		iShape.setSizeScoreMultiplierChildren("medium",10,2,2);
		assertTrue( (ChildrenShapeFactory.makeChildrenShape (iShape, "small")).get(0).getScore() == 20 );
		assertTrue( (ChildrenShapeFactory.makeChildrenShape (iShape, "small")).get(0).getMultiplier() == 2 );
		assertTrue( (ChildrenShapeFactory.makeChildrenShape (iShape, "small")).get(0).getSize() == "small" );
		assertFalse((ChildrenShapeFactory.makeChildrenShape (iShape, "small")).get(0).getClass().getSimpleName() == "Square" );
	}
}
