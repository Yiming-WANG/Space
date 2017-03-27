package main.se450.factories;

import java.util.ArrayList;

import main.se450.interfaces.IShape;
import main.se450.model.Circle;
import main.se450.model.Ship;
import main.se450.model.Square;
import main.se450.model.Triangle;

/**
 * ChildrenFactory would make children shapes when large/medium shapes hit by shots. 
 * It would make small shapes when medium shape is hit, and make medium shapes when large shape is hit.
 * @author Yiming Wang
 */
public class ChildrenShapeFactory 
{
	private ChildrenShapeFactory()
	{
	}
	
	
	/**
	 * makeChildrenShape function would create down-graded (large would become medium, medium would become small) child shapes when a mother shape got shot. 
	 * The amount of down-graded children and score of each children are difined from the json file.
	 * @param IShape iShape is the shape that got shot, would be break into down-graded shapes.
	 * @param String size is the size of the shape.
	 * @return a static arraylist of down-graded shapes.
	 */
	public static ArrayList <IShape> makeChildrenShape(IShape iShape, String size)
	{
		float left = iShape.getMinX();
		float top = iShape.getMinY();
		float width = (iShape.getMaxX() - iShape.getMinX())*0.7f;
		float height = (iShape.getMaxY() - iShape.getMinY())*0.7f;
		
		ArrayList <IShape> iShapes = new ArrayList <IShape> ();
		
		if(iShape.getClass().getSimpleName().equals("Circle"))
		{
			IShape s;
			for (int i = iShape.getChildren(); i>0;i--)
			{
				s=new Circle(left,top,left+width,top+height,iShape.getX()*Math.round((Math.random()*6-3)),-iShape.getY()*Math.round((Math.random()*6-3)),iShape.getRotation(),iShape.getColor(),iShape.getStrategy());
				s.setSizeScoreMultiplierChildren(size, iShape.getScore()*iShape.getMultiplier(), iShape.getMultiplier(), iShape.getChildren());
				iShapes.add(s);
			}
			
		}
		if(iShape.getClass().getSimpleName().equals("Ship"))
		{
			IShape s;
			for (int i = iShape.getChildren(); i>0;i--)
			{
				s=new Ship(left,top,left+width,top+height,iShape.getX()*Math.round((Math.random()*6-3)),-iShape.getY()*Math.round((Math.random()*6-3)),iShape.getRotation(),iShape.getColor(),iShape.getStrategy());
				s.setSizeScoreMultiplierChildren(size, iShape.getScore()*iShape.getMultiplier(), iShape.getMultiplier(), iShape.getChildren());
				iShapes.add(s);
			}
		}
		if(iShape.getClass().getSimpleName().equals("Triangle"))
		{
			IShape s;
			for (int i = iShape.getChildren(); i>0;i--)
			{
				s=new Triangle(left,top,left+width,top+height,iShape.getX()*Math.round((Math.random()*6-3)),-iShape.getY()*Math.round((Math.random()*6-3)),iShape.getRotation(),iShape.getColor(),iShape.getStrategy());
				s.setSizeScoreMultiplierChildren(size, iShape.getScore()*iShape.getMultiplier(), iShape.getMultiplier(), iShape.getChildren());
				iShapes.add(s);
			}
		}
		if(iShape.getClass().getSimpleName().equals("Square"))
		{
			IShape s;
			for (int i = iShape.getChildren(); i>0;i--)
			{
				s=new Square(left,top,left+width,top+height,iShape.getX()*Math.round((Math.random()*6-3)),-iShape.getY()*Math.round((Math.random()*6-3)),iShape.getRotation(),iShape.getColor(),iShape.getStrategy());
				s.setSizeScoreMultiplierChildren(size, iShape.getScore()*iShape.getMultiplier(), iShape.getMultiplier(), iShape.getChildren());
				iShapes.add(s);
			}
		}
		return iShapes;
	}
	
}
