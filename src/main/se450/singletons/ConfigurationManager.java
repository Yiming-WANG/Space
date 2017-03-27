package main.se450.singletons;

import java.awt.Color;

import main.se450.interfaces.IStrategy;
import main.se450.model.Configuration;

/**
 * ConfigurationManager class is to manage configuration class, it uses the data loaded from configuration json file to set configuration object. 
 * ConfigurationManager has method to get configuration object that means configuration values could be get when needed. 
 * @author Yiming Wang
 *
 */
public class ConfigurationManager {
	
	private static ConfigurationManager configurationManager;
	
	private Configuration configuration = null;
	
	static {
		
		configurationManager = new ConfigurationManager();
	}
	
	private ConfigurationManager() {}
	
	public final static ConfigurationManager getConfigurationManager() {
		
		return configurationManager;
	}
	
	public synchronized final Configuration getConfiguration() {
		return configuration;
	}
	
	public void setConfiguration(int FramesPerSecond, int Repeatkeyspeed, int Width, int Height, int Shapes, int Shipwidth, int Shipheight, 
			float ShotSpeed, float Shotdiameter, int Shotlifetime, float ForwardThrust, float ReverseThrust, float Friction, float LeftRight, Color nColor, IStrategy iStrategy) {
		configuration = new Configuration (FramesPerSecond, Repeatkeyspeed, Width, Height, Shapes, Shipwidth, Shipheight, ShotSpeed, Shotdiameter, Shotlifetime, ForwardThrust, ReverseThrust, Friction, LeftRight, nColor, iStrategy);
	}
	
}
