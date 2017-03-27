package main.se450.observable;

import java.util.ArrayList;
import java.util.Iterator;

import main.se450.interfaces.IShieldObservable;
import main.se450.interfaces.IObservable;

/**
 * Shield class defines how Shield event could be observed by registered observers. 
 * @author Yiming Wang
 *
 */
public class Shield extends AbstractObserver implements IObservable{
	
	private static Shield shield = new Shield();
	
	private ArrayList<IShieldObservable> shieldObservables = new ArrayList<IShieldObservable>();
	

	private Shield() {
		
		startObserver(this);
	}
	

	public static void startObserving(final IShieldObservable iShieldObservable){
		
		shield.addObserver(iShieldObservable);
	}


	public static void stopObserving(final IShieldObservable iShieldObservable){
		
		shield.removeObserver(iShieldObservable);
	}


	public static void shield() {
		
		shield.observed();
	}


	private synchronized void addObserver(final IShieldObservable iShieldObservable) {
		
		if (iShieldObservable != null) {
			
			if (!shieldObservables.contains(iShieldObservable)) {
				
				shieldObservables.add(iShieldObservable);
			}
		}
	}


	private synchronized void removeObserver(final IShieldObservable iShieldObservable) {
		
		shieldObservables.remove(iShieldObservable);
		
	}


	@Override
	public synchronized void update() {
		
		if (shieldObservables != null) {
			
			Iterator<IShieldObservable> iiShieldObservables = shieldObservables.iterator();
			
			while (iiShieldObservables.hasNext()) {
				
				IShieldObservable iShieldObservable = iiShieldObservables.next();
				
				if(iiShieldObservables != null) {
					
					iShieldObservable.shield();
					
				}
			}
		}
	}
	
	
	

}
