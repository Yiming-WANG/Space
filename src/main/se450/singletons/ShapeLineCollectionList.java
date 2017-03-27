package main.se450.singletons;

import java.util.ArrayList;

import main.se450.collections.LineCollection;

/**
 * ShapeLineCollectionList is a singleton to store lineCollections.
 * @author Yiming Wang
 *
 */
public class ShapeLineCollectionList {
	private static ShapeLineCollectionList shapeLineCollectionList=null;
	private ArrayList<LineCollection> shapeLineCollections = null;
	
	static
	{
		shapeLineCollectionList = new ShapeLineCollectionList();
	}
	
    private ShapeLineCollectionList()
    {
    	shapeLineCollections = new ArrayList<LineCollection>();
    }
    
	public final static ShapeLineCollectionList getShotList() 
	{
		return shapeLineCollectionList;
	}
	
	public final ArrayList<LineCollection> getShots()
	{
		return shapeLineCollections;
	}
	
}
