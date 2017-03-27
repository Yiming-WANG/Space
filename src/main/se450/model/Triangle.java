package main.se450.model;

import java.awt.Color;

import main.se450.collections.LineCollection;
import main.se450.interfaces.IStrategy;

/**
 * Class for Triangle
 * @author Yiming Wang
 *
 */
public class Triangle extends ShapeDroid
{
	/**
	 * Triangle constructor
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
	public Triangle(float nLeft, float nTop, float nRight, float nBottom, float nX, float nY, float nRotation, Color cColor, IStrategy iStrategy)
	{
		super(nLeft, nTop, nRight, nBottom, nX, nY, nRotation, cColor, iStrategy);
    }

	/**
	 * add lines into lineCollection, which is for further collision test.
	 */
    public void addSides(LineCollection lineCollection)
    {
    	if (lineCollection != null)
    	{
	    	lineCollection.add(new Line(getX4(),           getY4(),           getMidpointX1X2(), getMidpointY1Y2(), getX(), getY(), getRotation(), getColor(), getStrategy()));
	    	lineCollection.add(new Line(getMidpointX1X2(), getMidpointY1Y2(), getX3(),           getY3(),           getX(), getY(), getRotation(), getColor(), getStrategy()));
	    	lineCollection.add(new Line(getX3(),           getY3(),           getX4(),           getY4(),           getX(), getY(), getRotation(), getColor(), getStrategy()));
    	}
    }

    /**
     * get average of X1 and X2
     * @return average of X1 and X2
     */
	public float getMidpointX1X2()
	{
		return ((getX1() + getX2()) * 0.5f);
	}
	
	/**
	 * get average of Y1 and Y2
	 * @return average of Y1 and Y2
	 */
	public float getMidpointY1Y2()
	{
		return ((getY1() + getY2()) * 0.5f);
	}
}
    