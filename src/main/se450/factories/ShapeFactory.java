package main.se450.factories;

import java.awt.Color;

import main.se450.exceptions.BadShapeException;
import main.se450.exceptions.UnsupportedShapeException;
import main.se450.interfaces.IShape;
import main.se450.interfaces.IStrategy;
import main.se450.model.Circle;
import main.se450.model.Ship;
import main.se450.model.Square;
import main.se450.model.Triangle;
/**
 * ShapeFactory would make shapes by it's type. 
 * @author Yiming Wang
 *
 */
public class ShapeFactory
{
	private ShapeFactory()
	{
	}
	
	public static IShape makeShape(final String type, float nLeft, float nTop, float nRight, float nBottom, float x, float y, float rotation, 
			Color cColor, IStrategy iStrategy, String nSize, int nScore, int nMultiplier, int nChildren) throws BadShapeException, UnsupportedShapeException
	{
		IShape iShape = null;

    	if (type.equals("Circle"))
    	{
    		iShape = new Circle(nLeft, nTop, nRight, nBottom, x, y, rotation, cColor, iStrategy);
    		iShape.setSizeScoreMultiplierChildren(nSize, nScore, nMultiplier, nChildren);
    	}
        else if (type.equals("Square"))
    	{
    		iShape = new Square(nLeft, nTop, nRight, nBottom, x, y, rotation, cColor, iStrategy);
    		iShape.setSizeScoreMultiplierChildren(nSize, nScore, nMultiplier, nChildren);
    	}
    	else if (type.equals("Line"))
    	{
    		throw new UnsupportedShapeException(type);
    	}
    	else if (type.equals("Triangle"))
    	{
    		iShape = new Triangle(nLeft, nTop, nRight, nBottom, x, y, rotation, cColor, iStrategy);
    		iShape.setSizeScoreMultiplierChildren(nSize, nScore, nMultiplier, nChildren);
    	}
    	else if (type.equals("Ship"))
    	{
    		iShape = new Ship(nLeft, nTop, nRight, nBottom, x, y, rotation, cColor, iStrategy);
    		iShape.setSizeScoreMultiplierChildren(nSize, nScore, nMultiplier, nChildren);
    	}
    	else
    	{
    		throw new BadShapeException(type);
    	}
		
		return iShape;
	}
}
      