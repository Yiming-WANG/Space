package main.se450.factories;

import main.se450.exceptions.BadStrategyException;
import main.se450.interfaces.IStrategy;
import main.se450.strategies.PassThruStrategy;
import main.se450.strategies.ReboundStrategy;

/**
 * StrategyFactory would automately set iStrategy of each shape.
 * @author Yiming Wang
 *
 */
public class StrategyFactory
{
	private StrategyFactory()
	{
	}
	
	public static IStrategy makeStrategy(final String sStrategy) throws BadStrategyException
	{
		IStrategy iStrategy = null;

    	if (sStrategy.equals("PassThru"))
    	{
    		iStrategy = new PassThruStrategy();
    	}
        else if (sStrategy.equals("Rebound"))
    	{
        	iStrategy = new ReboundStrategy();
    	}
    	else
    	{
    		throw new BadStrategyException(sStrategy);
    	}
		
		return iStrategy;
	}
}
      