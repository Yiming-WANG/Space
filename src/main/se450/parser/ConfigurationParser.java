package main.se450.parser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import main.se450.exceptions.BadStrategyException;
import main.se450.factories.StrategyFactory;
import main.se450.interfaces.IStrategy;
import main.se450.singletons.ConfigurationManager;
import main.se450.utilities.Extractor;

/**
 * ConfigurationParser is a class for reading configuration data from json file. 
 * @author Yiming Wang
 *
 */
public class ConfigurationParser {

	
	private ConfigurationParser() {}

	public static void loadConfiguration(String fileName)//, final Dimension dimension)
	{
		try
		{ 
	        JSONParser parser = new JSONParser();
	 
	        Object obj = parser.parse(new FileReader(fileName));
	 
	        JSONObject jsonObject = (JSONObject) obj;
	 
	        JSONArray jsonArray = (JSONArray) jsonObject.get("game");
	        
	        Iterator<?> jsonIterator = jsonArray.iterator();
	        while (jsonIterator.hasNext())
	        {
	        	JSONObject configuration = (JSONObject)jsonIterator.next();
	        	if (configuration.containsKey("framespersecond"))
	        	{
	        		try
	        		{
	        		IStrategy iStrategy = StrategyFactory.makeStrategy(configuration.get("borders").toString());	
	        		ConfigurationManager.getConfigurationManager().setConfiguration(Extractor.extractInteger(configuration.get("framespersecond")),
	        																		Extractor.extractInteger(configuration.get("repeatkeyspeed")),
	        																		Extractor.extractInteger(configuration.get("width")),
	        																		Extractor.extractInteger(configuration.get("height")),
															        				Extractor.extractInteger(configuration.get("shapes")),
															        				Extractor.extractInteger(configuration.get("shipwidth")),
															        				Extractor.extractInteger(configuration.get("shipheight")), 
															        				Extractor.extractFloat(configuration.get("shotspeed")),
															        				Extractor.extractFloat(configuration.get("shotdiameter")),
															        				Extractor.extractInteger(configuration.get("shotlifetime")),
															        				Extractor.extractFloat(configuration.get("forwardthrust")), 
															        				Extractor.extractFloat(configuration.get("reversethrust")), 
															        				Extractor.extractFloat(configuration.get("friction")),
															        				Extractor.extractFloat(configuration.get("leftright")), 
															        				Extractor.extractColor(configuration.get("color")), iStrategy);     	
        			}
        			catch (BadStrategyException e)
        			{
	        			System.out.println(e);
        			}

	        	}
	        }
		}
        catch(FileNotFoundException eFileNotFound)
        {
        }
        catch(IOException eIOException)
        {
        	
        }
        catch(ParseException eParseException)
        {
        }
		
		
	}

}