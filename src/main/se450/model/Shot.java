package main.se450.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import main.se450.collections.LineCollection;
import main.se450.interfaces.ICollide;
import main.se450.interfaces.IShot;
import main.se450.interfaces.IStrategy;

/**
 * class for shots. 
 * @author Yiming Wang
 *
 */
public class Shot extends Circle implements IShot, ICollide
{
	private Ellipse2D circle = new Ellipse2D.Float(0.0f, 0.0f, 0.0f, 0.0f);
	private int lifetime = 0;
	
	public Shot(int nLifetime, float nLeft, float nTop, float nRight, float nBottom, float nX, float nY, float nRotation, Color cColor, IStrategy iStrategy)
	{
		super(nLeft, nTop, nRight, nBottom, nX, nY, nRotation, cColor, iStrategy);
		lifetime = nLifetime;
	}

	/**
	 * draw shots. which is a small color full filled circle
	 */
	@Override
	public void draw(Graphics graphics)
	{
		//......TODO
		circle.setFrame(getMinX(), getMinY(), getWidth(), getHeight());
		
		Graphics2D g2d = (Graphics2D)(graphics);
		
		g2d.setColor(getColor());
		g2d.fill(circle);
		g2d.draw(circle);
	}
	
	@Override
	public void update()
	{
		--lifetime;
		super.update();
	}

	/**
	 * check if the lifetime of shot is expired
	 */
	@Override
	public boolean isExpired() 
	{
		return (lifetime < 0);
	}

	@Override
	public void addSides(LineCollection lineCollection)
	{
		super.addSides(lineCollection);
	}	
	
}