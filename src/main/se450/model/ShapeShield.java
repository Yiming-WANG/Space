package main.se450.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import main.se450.interfaces.IStrategy;

/**
 * class for shield (mostly for drawing).
 * @author Yiming Wang
 *
 */
public class ShapeShield extends Circle 
{
	private Ellipse2D circle = new Ellipse2D.Float(0.0f, 0.0f, 0.0f, 0.0f);
	

	private int lifetime = 100;

	public ShapeShield(float nLeft, float nTop, float nRight, float nBottom, float nX, float nY, float nRotation, Color cColor, IStrategy iStrategy)
	{
		super(nLeft, nTop, nRight, nBottom, nX, nY, nRotation, cColor, iStrategy);		
	}

	@Override
	public void draw(Graphics graphics)
	{
  		circle.setFrame(getMinX(), getMinY(), getWidth(), getHeight());
		
		Graphics2D g2d = (Graphics2D)(graphics);
		g2d.setColor(getColor());
		g2d.draw(circle);
	}
	

	/**
	 * update life time and other variables
	 */
	@Override
	public void update()
	{
		--lifetime;
		super.update();
	}
	
	/**
	 * check if the life time of shield is expired. 
	 * @return return true means expired. otherwise, false
	 */
	public boolean isExpired() 
	{
		return (lifetime < 0);
	}
	
}
