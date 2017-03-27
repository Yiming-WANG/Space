package main.se450.interfaces;

import java.awt.Color;
import java.awt.Graphics;

import main.se450.collections.LineCollection;

public interface IShape 
{
	void update();
	
	void draw(Graphics g);
	
	float getMinX();
	
	float getMinY();
	
	float getMaxX();
	
	float getMaxY();
	
	void addSides(LineCollection l);
	
	LineCollection getLineCollection();
	
	
	public float getX1();
	
	public float getY1();
	
	public float getX2();
	
	public float getY2();
	
	public float getX3();
	
	public float getY3();
	
	public float getX4();
	
	public float getY4();
	
	public float getX();
	
	public float getY();
	
	public float getRotation();
	
	public Color getColor();
	
	public IStrategy getStrategy();
	
	public String getSize() ;

	public void setSize(String size);

	public int getScore() ;

	public int getMultiplier();

	public int getChildren();
	
	public void setSizeScoreMultiplierChildren(String size, int score, int multiplier, int children);
	
	public void setX(float x);
	
	public void setY(float y);
}
      