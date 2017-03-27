package main.se450.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import javax.swing.JPanel;
import main.se450.factories.ChildrenShapeFactory;
import main.se450.factories.JSONFileShapeListFactory;
import main.se450.interfaces.IObservable;
import main.se450.interfaces.IShape;
import main.se450.interfaces.IShot;
import main.se450.interfaces.IStartObservable;
import main.se450.model.Configuration;
import main.se450.model.PlayerShip;
import main.se450.observable.Score;
import main.se450.observable.Start;
import main.se450.singletons.ConfigurationManager;
import main.se450.singletons.DisplayManager;
import main.se450.singletons.ShapeList;
import main.se450.singletons.ShotList;
import main.se450.singletons.SoundManager;
import main.se450.utilities.Collide;

/*
 * Name     : Yiming Wang
 * Depaul#  : 1537204
 * Class    : SE 450
 * Project  : Final
 * Due Date : 03/13/2017
 *
 * class ShapeDisplay
 *
 */

/**
 * ShapeDisplay class handles most of the functions of display including start display, collide, disappear of shots.
 * @author Yiming Wang
 *
 */
public class ShapeDisplay extends JPanel implements IObservable, IStartObservable
{

	private static final long serialVersionUID = 1L;
	private PlayerShip playerShip = null;
		
	public ShapeDisplay()
	{
		Start.startObserving(this);
	}
	
	@Override
	public void validateTree()
	{
		super.validateTree();

		Dimension dimension = getSize();
		
		DisplayManager.getDisplayManager().setDisplaySize(dimension.width, dimension.height);
	}
	
	/**
	 * paint function would draw the "black space" background of the game.
	 * after checking collide and lifetime of shots, paint functin would draw playership, shapes and shots.
	 */
	public void paint(Graphics graphics) 
  	{
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, DisplayManager.getDisplayManager().getWidth(), DisplayManager.getDisplayManager().getHeight());
		
		updateArtifacts();
		updatecollisions();
		
		if (playerShip != null) 
		{
			playerShip.update();
			playerShip.draw(graphics);
		}
		
		final ArrayList<IShot> iShotList = ShotList.getShotList().getShots();
		if (iShotList != null)
		{
			Iterator<IShot> iiShots = iShotList.iterator();
			while (iiShots.hasNext())
			{
				IShot iShots = iiShots.next();
				if (iShots != null)
				{
					iShots.update();
					iShots.draw(graphics);
				}
			}
		}
		
		
		final ArrayList<IShape> iShapeList = ShapeList.getShapeList().getShapes();
		if (iShapeList != null)
		{
			
			Iterator<IShape> iiShapes = iShapeList.iterator();
			
			while (iiShapes.hasNext())
			{
				IShape iShape = iiShapes.next();
				
				if (iShape != null)
				{
					iShape.update();
					iShape.draw(graphics);
				}
			}
		} 

    }  	

	@Override
	public void update() 
	{
		repaint();
	}
	
	/**
	 * updateArtifacts function would remove expired shots or update shot location
	 */
	private void updateArtifacts() 
	{
		ArrayList<IShot> iShots = ShotList.getShotList().getShots();
		if (iShots != null && !iShots.isEmpty())
		{
			ListIterator<IShot> iiShots = iShots.listIterator();
			while (iiShots.hasNext())
			{
				IShot iiShot = iiShots.next();
				if (iiShot.isExpired())
				{
					iiShots.remove();
				}
			}
		}
	}
	
	/**
	 * updatecollision function would check if a shape is hit by a shot, or hit by playership.
	 * If a shape is hit by a shot. Check the shape size: if size is small, the shape would be disappeared. otherwise, downgrade size: large become medium, medium become small.
	 * 
	 * if a shape is hit by a playership. Check if Shield of ship is on: 
	 * If it is on, shape would be re-bound. Nothing happens to playership.
	 * If it is off, nothing happens to shape. Playership would be distroyed and re-placed to the center of the space. The game score would be set to 0.
	 */
	private void updatecollisions() 
	{
			ArrayList<IShape> iShapes = ShapeList.getShapeList().getShapes();
			
			if (iShapes != null)
			{
				ArrayList<IShape> tempChildrenShapes = new ArrayList<IShape> ();
				boolean notCollided;
				
				ListIterator<IShape> iiShapes = iShapes.listIterator();
				while (iiShapes.hasNext())
				{
					IShape iiShape = iiShapes.next();
					ArrayList<IShot> iShots = ShotList.getShotList().getShots();
					notCollided=true;
					
					if(iShots !=null)
					{
						ListIterator<IShot> iiShots = iShots.listIterator();
											
						while (notCollided && iiShots.hasNext())
						{
							IShot iiShot = iiShots.next();
							
							if (Collide.collided(iiShape.getLineCollection(), iiShot.getLineCollection()))//means collided
							{
								iiShots.remove();
								iiShapes.remove();
								notCollided=false;
								Score.addScore(iiShape.getScore());
								if (iiShape.getSize().equals("small"))
								{
									SoundManager.getSoundManager().playSmallExplosion();
								}
								if(!iiShape.getSize().equals("small"))
								{	
									tempChildrenShapes.addAll(downGradeSize(iiShape));
								}							
							}
						}
					}
					
					if(playerShip!=null && !playerShip.getShieldStatus())
					{
						if(Collide.collided(iiShape.getLineCollection(), playerShip.getLineCollection()))
						{
							SoundManager.getSoundManager().playerShipHitShape();
							playerShip.newLife();
							Score.clearScore();
						}
					}
				}
				ShapeList.getShapeList().addShapes(tempChildrenShapes);
			}
	}

	/**
	 * downGradeSize function would downgrade the size of iShape: large would be come medium, medium would become small.
	 * @param iShape is the shape which was hit. Would create down-grade child shapes.
	 * @return an arraylist of down-graded shapes.
	 */
	public ArrayList <IShape> downGradeSize(IShape iShape)
	{
		ArrayList <IShape> iShapes = new ArrayList <IShape> ();
		if(iShape.getSize().equals("large"))
		{
			SoundManager.getSoundManager().playBigExplosion();
			iShapes=ChildrenShapeFactory.makeChildrenShape(iShape, "medium");
		}
		else if(iShape.getSize().equals("medium"))
		{	
			SoundManager.getSoundManager().playMediumExplosion();
			iShapes=ChildrenShapeFactory.makeChildrenShape(iShape, "small");
		}
		
		return iShapes;
	}
	
	
	/**
	 * reset everything of the game and start the game:
	 * place ship, start score observing and set score to 0. Read data from shapes.json
	 * Clear the singleton ShapeList.
	 */
	@Override
	public void start() 
	{
		placeShip();
		Score.score();
		Score.clearScore();
		final ArrayList<IShape> iShapes = JSONFileShapeListFactory.makeShape("shapes.json");
		ShapeList.getShapeList().getShapes().clear();
		ShapeList.getShapeList().addShapes(iShapes);
	}
	
	/**
	 * If the application is just open, create new playership object (start the game).
	 * If the game is already started, reset playership.
	 */
	private void placeShip()
	{
		ConfigurationManager configurationManager = ConfigurationManager.getConfigurationManager();
		
		if(configurationManager != null) 
		{
			Configuration configuration = configurationManager.getConfiguration();
			
			if (configuration != null) 
			{
				if(playerShip==null)				
					playerShip = new PlayerShip(configuration.getShotSpeed(), configuration.getForwardthrust(), configuration.getReversethrust(), configuration.getFriction(), 
							configuration.getLeftright(), 0.0f, 0.0f, 0.0f, configuration.getColor(), configuration.getBorders());
				else 
					playerShip.newLife();
			}
		}	
	}
}



      