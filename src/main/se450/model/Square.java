package main.se450.model;

import java.awt.Color;

import main.se450.collections.LineCollection;
import main.se450.interfaces.IStrategy;

/**
 * Class for square
 * @author Yiming Wang
 *
 */
public class Square extends ShapeDroid
{
	/**
	 * Square constructor
	 * @param nLeft
	 * @param nTop
	 * @param nRight
	 * @param nBottom
	 * @param nX
	 * @param nY
	 * @param nRotation
	 * @param cColor
	 * @param iStrategy
	 */
	public Square(float nLeft, float nTop, float nRight, float nBottom, float nX, float nY, float nRotation, Color cColor, IStrategy iStrategy)
	{
		super(nLeft, nTop, nRight, nBottom, nX, nY, nRotation, cColor, iStrategy);
    }

	/**
	 * add lines into lineCollection, which is for future collision test.
	 */
    public void addSides(LineCollection lineCollection)
    {
    	if (lineCollection != null)
    	{
	    	lineCollection.add(new Line(getX1(), getY1(), getX2(), getY2(), getX(), getY(), getRotation(), getColor(), getStrategy()));
	        lineCollection.add(new Line(getX2(), getY2(), getX3(), getY3(), getX(), getY(), getRotation(), getColor(), getStrategy()));
	        lineCollection.add(new Line(getX3(), getY3(), getX4(), getY4(), getX(), getY(), getRotation(), getColor(), getStrategy()));
	        lineCollection.add(new Line(getX4(), getY4(), getX1(), getY1(), getX(), getY(), getRotation(), getColor(), getStrategy()));
    	}
    }
    
    
    
}
    