package main.se450.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

import main.se450.collections.LineCollection;
import main.se450.interfaces.IStrategy;

/**
 * Class for Circle.
 * @author Yiming Wang
 *
 */
public class Circle extends Shape 
{
	private Line2D    line   = new Line2D.Float(0.0f,0.0f,0.0f,0.0f);
	private	Ellipse2D circle = new Ellipse2D.Float(0.0f,0.0f,0.0f,0.0f);
	private LineCollection lineCollection = new LineCollection();
		
	/**
	 * Circle constructor
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
	public Circle(float nLeft, float nTop, float nRight, float nBottom, float nX, float nY, float nRotation, Color cColor, IStrategy iStrategy)
	{
		super(nLeft, nTop, nRight, nBottom, nX, nY, nRotation, cColor, iStrategy);
		createLines();
	}
	
	@Override
	public void update()
	{
		super.update();
		createLines();
	}
	
	@Override
	public void draw(Graphics graphics) 
	{
		line.setLine(getCenterX(), getCenterY(), getX1(), getY1());
  		circle.setFrame(getMinX(), getMinY(), getWidth(), getHeight());
  		
  		Graphics2D g2d = (Graphics2D)(graphics);
  		
  		g2d.setColor(getColor());
  		g2d.draw(circle);
  		g2d.draw(line);
	}

	@Override
	public float getMinX() 
	{
		return getCenterX() - getRadius();
	}

	@Override
	public float getMinY() 
	{
		return getCenterY() - getRadius();
	}

	@Override
	public float getMaxX() 
	{
		return getCenterX() + getRadius();
	}

	@Override
	public float getMaxY() 
	{
		return getCenterY() + getRadius();
	}
	
	/**
	 * 
	 * @return MidpointX1X3
	 */
	public float getCenterX()
	{
		return getMidpointX1X3();
	}

	/**
	 * 
	 * @return MidpointY1Y3
	 */
	public float getCenterY()
	{
		return getMidpointY1Y3();
	}

	/**
	 * return half of width of circle
	 * @return return radius
	 */
	public float getRadius()
	{
		return getWidth() * 0.5f; 
	}
	
	/**
	 * add lines in the lineCollection, which is for future collision test.
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

    /**
     * clear and addSides.
     */
    private void createLines()
    {
    	lineCollection.clear();

    	addSides(lineCollection);
    }
    
	@Override
	public LineCollection getLineCollection() {
		return lineCollection;
	}
}
      