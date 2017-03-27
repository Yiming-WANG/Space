package main.se450.singletons;

import java.util.ArrayList;

import main.se450.interfaces.IShot;

/**
 * a singleton class to store shots.
 * @author Yiming Wang
 *
 */
public class ShotList
{
	private static ShotList shotList = null;
	
	private ArrayList<IShot> iShots = null;
	
	static
	{
		shotList = new ShotList();
	}
	
    private ShotList()
    {
    	iShots = new ArrayList<IShot>();
    }
    
	public final static ShotList getShotList() 
	{
		return shotList;
	}
	
	public final ArrayList<IShot> getShots()
	{
		return iShots;
	}
	
	public void addShots(final IShot iShot)
	{
		iShots.add(iShot);
	}
}