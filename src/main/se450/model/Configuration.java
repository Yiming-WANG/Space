package main.se450.model;

import java.awt.Color;

import main.se450.interfaces.IStrategy;

/**
 * Configuratino class which is for storing the data from the configuration.json.
 * @author Yiming Wang
 *
 */
public class Configuration {
	
	private int framesPerSecond;
	private int repeatkeyspeed;
	private int width;
	private int height;
	private int shapes;
	private int shipwidth;
	private int shipheight;
	private float shotspeed;
	private float shotdiameter;
	private int shotlifetime;
	private float forwardthrust;
	private float reversethrust;
	private float friction;
	private float leftright;
	private Color color;
	private IStrategy borders;
	

	public Configuration(int FramesPerSecond, int Repeatkeyspeed, int Width, int Height, int Shapes, int Shipwidth, int Shipheight, 
			float ShotSpeed, float Shotdiameter, int Shotlifetime, float ForwardThrust, float ReverseThrust, float Friction, float LeftRight, 
			Color nColor, IStrategy iStrategy) {
		
		framesPerSecond = FramesPerSecond;
		repeatkeyspeed = Repeatkeyspeed;
		width = Width;
		height = Height;
		shapes = Shapes;
		shipwidth = Shipwidth;
		shipheight = Shipheight;
		shotspeed = ShotSpeed;
		shotdiameter = Shotdiameter;
		shotlifetime = Shotlifetime;
		forwardthrust = ForwardThrust;
		reversethrust = ReverseThrust;
		friction = Friction;
		leftright = LeftRight;
		color = nColor;
		borders = iStrategy;
		
	}
	
	public int getFramesPerSecond() {
		return framesPerSecond;
	}

	public int getRepeatkeyspeed() {
		return repeatkeyspeed;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getShapes() {
		return shapes;
	}

	public int getShipWidth() {
		return shipwidth;
	}

	public int getShipHeight() {
		return shipheight;
	}

	public float getShotSpeed() {
		return shotspeed;
	}
	
	public float getShotDiameter() {
		return shotdiameter;
	}

	public int getShotlifetime() {
		return shotlifetime;
	}

	public float getForwardthrust() {
		return forwardthrust;
	}

	public float getReversethrust() {
		return reversethrust;
	}

	public float getFriction() {
		return friction;
	}

	public float getLeftright() {
		return leftright;
	}
	
	public Color getColor() {
		return color;
	}

	public IStrategy getBorders() {
		return borders;
	}	

}
