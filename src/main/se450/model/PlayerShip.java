package main.se450.model;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import main.se450.collections.LineCollection;
import main.se450.interfaces.IFireObservable;
import main.se450.interfaces.IForwardThrustObservable;
import main.se450.interfaces.IHyperSpaceObservable;
import main.se450.interfaces.ILeftObservable;
import main.se450.interfaces.IReverseThrustObservable;
import main.se450.interfaces.IRightObservable;
import main.se450.interfaces.IShape;
import main.se450.interfaces.IShieldObservable;
import main.se450.interfaces.IStopObservable;
import main.se450.interfaces.IStrategy;
import main.se450.observable.Fire;
import main.se450.observable.ForwardThrust;
import main.se450.observable.HyperSpace;
import main.se450.observable.Left;
import main.se450.observable.ReverseThrust;
import main.se450.observable.Right;
import main.se450.observable.Shield;
import main.se450.observable.Stop;
import main.se450.singletons.ConfigurationManager;
import main.se450.singletons.DisplayManager;
import main.se450.singletons.ShapeList;
import main.se450.singletons.ShotList;
import main.se450.singletons.SoundManager;
import main.se450.utilities.Collide;

/**
 * Class for playership. This class contains the funciton of control the playership, including: moving, firing, shield, and hyperSpace.
 * @author Yiming Wang
 *
 */
public class PlayerShip extends Ship implements IForwardThrustObservable, IReverseThrustObservable, IRightObservable, ILeftObservable, IStopObservable, IFireObservable, IHyperSpaceObservable, IShieldObservable{
	
	private float shotSpeed;
	private float forwardThrust;
	private float reverseThrust;
	private float friction;
	private float leftRight;
	
	private final static float SHIP_WIDTH = ConfigurationManager.getConfigurationManager().getConfiguration().getShipWidth();
	private final static float SHIP_HEIGHT = ConfigurationManager.getConfigurationManager().getConfiguration().getShipHeight();
	
	private final static float LEFT = (DisplayManager.getDisplayManager().getWidth()- SHIP_WIDTH)/2;
	private final static float TOP = (DisplayManager.getDisplayManager().getHeight()- SHIP_HEIGHT)/2;
	
	private final static int SHOT_LIFE_TIME = ConfigurationManager.getConfigurationManager().getConfiguration().getShotlifetime();
	private final static float SHOT_DIAMETER = ConfigurationManager.getConfigurationManager().getConfiguration().getShotDiameter();
	
	private Color sColor;
	private IStrategy sIStrategy;
	
	private ShapeShield shapeShield;
	private boolean shieldOn = false;
	
	public PlayerShip(float nShotSpeed, float nForwardThrust, float nReverseThrust, float nFriction, float nLeftRight, float nX, float nY, float nRotation, Color cColor, IStrategy iStrategy) {
		super(LEFT, TOP, LEFT+SHIP_WIDTH, TOP+SHIP_HEIGHT, nX, nY, nRotation, cColor, iStrategy);
		
		shotSpeed = nShotSpeed;
		forwardThrust = nForwardThrust;
		reverseThrust = nReverseThrust;
		friction = nFriction;
		leftRight = nLeftRight;
		sColor=cColor;
		sIStrategy=iStrategy;
		
		ForwardThrust.startObserving(this);
		ReverseThrust.startObserving(this);
		Fire.startObserving(this);
		Stop.startObserving(this);
		Left.startObserving(this);
		Right.startObserving(this);
		HyperSpace.startObserving(this);
		Shield.startObserving(this);
	}
	
	/**
	 * re-place the playership to the start location (center of the space)
	 */
	public void newLife()
	{
		this.setX1(LEFT);
		this.setX4(LEFT);
		this.setY1(TOP);
		this.setY2(TOP);
		this.setX2(LEFT+SHIP_WIDTH);
		this.setX3(LEFT+SHIP_WIDTH);
		this.setY3(TOP+SHIP_HEIGHT);
		this.setY4(TOP+SHIP_HEIGHT);
		this.setX(0);
		this.setY(0);
	}
	
	/**
	 * stop observing.
	 */
	@Override
	public void finalize() {
		
		ForwardThrust.stopObserving(this);
		ReverseThrust.stopObserving(this);
		Fire.stopObserving(this);
		Stop.stopObserving(this);
		Left.stopObserving(this);
		Right.stopObserving(this);
		HyperSpace.stopObserving(this);
		Shield.stopObserving(this);
	}

	/**
	 * play forwardthrust sound, make playership forwardthrust.
	 */
	@Override
	public void forwardThrust() {
		
		SoundManager.getSoundManager().playForwardThrust();
		thrust(forwardThrust); 
			
	}
	
	/**
	 * play reversethrust sound, make playership reversethrust.
	 */
	@Override
	public void reverseThrust() {
		
		SoundManager.getSoundManager().playReverseThrust();
		thrust(-reverseThrust); 
	}
	
	/**
	 * make playership left turn.
	 */
	@Override
	public void left() {
		
		setRotation(-leftRight - (getRotation()/60.0f));
	}
	
	/**
	 * make playership right turn.
	 */
	@Override
	public void right() {
		
		setRotation(leftRight - (getRotation()/60.0f));
	}
	
	/**
	 * make playership stop
	 */
	@Override
	public void stop() {
		
		setX(0.0f);
		setY(0.0f);
	}

	/**
	 * update playership location. make friction. update shield and control the shield-on shape re-bound.
	 */
	@Override
	public void update() {
		
		setX(friction(getX()));
		setY(friction(getY()));
		super.update();
		setRotation(0.0f); 
		
		
		
		if (shieldOn)
		{		
			ArrayList<IShape> iShapeList = ShapeList.getShapeList().getShapes();
			if(iShapeList != null)
			{
				Iterator<IShape> iiShapes = iShapeList.iterator();
				while (iiShapes.hasNext())
				{
					IShape localShape = iiShapes.next();
					LineCollection shapeLineCollection = localShape.getLineCollection();
					LineCollection shieldLineCollection = shapeShield.getLineCollection();
				
					if (Collide.collided(shapeLineCollection, shieldLineCollection))
					{
						localShape.setX(-localShape.getX());
						localShape.setY(-localShape.getY());
					}	
				}								
			}
			updateShield();
		}
	}
	
	/**
	 * draw playership and if shield is on, draw shield
	 */
	@Override
	public void draw(Graphics graphics) {
		
		super.draw(graphics);
		if (shieldOn)
		{
			shapeShield.draw(graphics);
		}
	}
	
	/**
	 * set friction, which is for slightly slow down the moving playership. 
	 * @param nF:how fast to slow down playership
	 * @return nF
	 */
	private float friction (float nF) {
		
		nF = (nF * (1.0f - friction));
		
		if (Math.abs(nF) < friction) {
			
			nF = 0.0f;
		}
		
		return nF;
	}
	
	/**
	 * function for thrust.
	 * @param fThrust
	 */
	private void thrust(float fThrust) {
		
		float xB = getMidpointX1X2();//x tip of ship
		float xA = getMidpointX1X3();//x bottom of ship
		float yB = getMidpointY1Y2();//y tip of ship
		float yA = getMidpointY1Y3();//y bottom of ship
		
		float xDiff = xB - xA;
		float yDiff = yB - yA;
		
		float fX = 0.0f;
		float fY = 0.0f;
		if (xDiff == 0.0f)
		{
			fY += fThrust * Math.signum(yDiff);
		}
		else if (yDiff == 0.0f)
		{
			fX += fThrust * Math.signum(xDiff);
		}
		else
		{
			float ffDiff = Math.abs(xDiff) + Math.abs(yDiff);
			fX += fThrust * Math.sin(Math.toRadians(90 * xDiff / ffDiff));
			fY += fThrust * Math.sin(Math.toRadians(90 * yDiff / ffDiff));
		}
		
		setX(getX() + fX);
		setY(getY() + fY);

	}
	
	/**
	 * function for fire
	 */
	@Override
	public void fire(){
		
		SoundManager.getSoundManager().playFire();
		
		float xB = getMidpointX1X2(); //tip of ship
		float xA = getMidpointX1X3(); //bottom of ship
		float yB = getMidpointY1Y2(); //tip of ship
		float yA = getMidpointY1Y3(); //bottom of ship
		
		float xDiff = xB - xA;
		float yDiff = yB - yA;	
		
		float fX = 0.0f;
		float fY = 0.0f;
		
		if (xDiff == 0.0f) {
			
			fY += shotSpeed * Math.signum(yDiff);
		}
		
		else if (yDiff == 0.0f) {
			
			fX += shotSpeed * Math.signum(xDiff);
		}
		
		else {
			
			float ffDiff = Math.abs(xDiff) + Math.abs(yDiff);
			fX += shotSpeed * Math.sin(Math.toRadians(90 * xDiff / ffDiff));
			fY += shotSpeed * Math.sin(Math.toRadians(90 * yDiff / ffDiff));
			
		}

		ShotList.getShotList().addShots(new Shot(SHOT_LIFE_TIME,xB - SHOT_DIAMETER, yB - SHOT_DIAMETER, xB + SHOT_DIAMETER, yB + SHOT_DIAMETER, fX, fY,0, sColor, sIStrategy));	
		
	}

	/**
	 * funciton for hyperSpace
	 */
	@Override
	public void hyperSpace() {
		translateXY(-getMinX() + (float)Math.random() * (DisplayManager.getDisplayManager().getWidth()),-getMinY() + (float)Math.random() * (DisplayManager.getDisplayManager().getHeight()));
	}

	/**
	 * function to create shield
	 */
	@Override
	public void shield() {
		shapeShield = new ShapeShield (this.getX1(), this.getY1(), this.getX3(), this.getY3(), this.getX(), this.getY(), this.getRotation(), this.getColor(), this.getStrategy());
		shieldOn = true;
	}

	/**
	 * funciton to update shield location and check if shield is time out. turn of time out shield .
	 */
	private void updateShield()
	{
		shapeShield.update();
		shapeShield.setX1(this.getX1());
		shapeShield.setY1(this.getY1());
		shapeShield.setX3(this.getX3());
		shapeShield.setY3(this.getY3());
		shapeShield.setX(this.getX());
		shapeShield.setY(this.getY());
		
		if (shapeShield.isExpired())
		{
				shapeShield = null;
				shieldOn = false;
		}			
	}
	
	/**
	 * return shield status
	 * @return true or false of shield status
	 */
	public boolean getShieldStatus()
	{
		return shieldOn;
	}
	
	
}
