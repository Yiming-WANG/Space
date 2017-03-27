package test.se450.model;

import static org.junit.Assert.*;

import org.junit.Test;

import main.se450.model.Ship;

public class ShipTest 
{

	@Test
	public void testGetMidpointX1X2() 
	{
		Ship ship = new Ship(50.0f, 50.0f, 200.0f, 200.0f, 0.0f, 0.0f, 0.0f, null, null);
				
		assertTrue(ship.getMidpointX1X2() == 125.0f);
	}

	
	@Test
	public void testMidpointY1Y2() 
	{
		Ship ship = new Ship(10.0f, 20.0f, 30.0f, 40.0f, 0.0f, 0.0f, 0.0f, null, null);
		
			assertFalse(ship.getMidpointY1Y2() == 30);
	}
	

}