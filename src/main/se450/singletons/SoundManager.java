package main.se450.singletons;

import java.util.HashMap;

import main.se450.constants.Constants;
import main.se450.interfaces.ISound;
import main.se450.sound.Fire;
import main.se450.sound.ForwardThrust;
import main.se450.sound.PlayerShipExplosion;
import main.se450.sound.ReverseThrust;
import main.se450.sound.BigExplosion;
import main.se450.sound.MediumExplosion;
import main.se450.sound.SmallExplosion;

/**
 * SoundManager manages sounds that would be played.
 * @author Yiming Wang
 *
 */
public class SoundManager
{
	private static SoundManager soundManager = null;
	
	private HashMap<String,ISound> sounds = null;
	
	static
	{
		soundManager = new SoundManager();
	}
	
    private SoundManager()
    {
    	sounds = new HashMap<String,ISound>();
    	
    	sounds.put(Constants.FIRE,                   new Fire());
    	sounds.put(Constants.FORWARD_THRUST_PRESSED, new ForwardThrust());
    	sounds.put(Constants.REVERSE_THRUST_PRESSED, new ReverseThrust());
    	sounds.put(Constants.PLAYERSHIP_EXPLOSION,   new PlayerShipExplosion());
    	sounds.put(Constants.BIG_EXPLOSION,           new BigExplosion());
    	sounds.put(Constants.MEDIUM_EXPLOSION,        new MediumExplosion());
    	sounds.put(Constants.SMALL_EXPLOSION,         new SmallExplosion());
    	
    	
    }
    
	public final static SoundManager getSoundManager() 
	{
		return soundManager;
	}
	
	public void playFire()
	{
		sounds.get(Constants.FIRE).play();
	}
	
	public void playForwardThrust()
	{
		sounds.get(Constants.FORWARD_THRUST_PRESSED).play();
	}
	
	public void playReverseThrust()
	{
		sounds.get(Constants.REVERSE_THRUST_PRESSED).play();
	}
	
	public void playerShipHitShape()
	{
		sounds.get(Constants.PLAYERSHIP_EXPLOSION).play();
	}
	
	public void playBigExplosion()
	{
		sounds.get(Constants.BIG_EXPLOSION).play();
	}
	
	public void playMediumExplosion()
	{
		sounds.get(Constants.MEDIUM_EXPLOSION).play();
	}
	
	public void playSmallExplosion()
	{
		sounds.get(Constants.SMALL_EXPLOSION).play();
	}
}
      