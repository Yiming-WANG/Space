package main.se450.observable;

import java.util.ArrayList;
import java.util.Iterator;

import main.se450.interfaces.IObservable;
import main.se450.interfaces.IFireObservable;

/**
 * The Fire class defines how the observable fire event could be observed by registered observers. 
 * @author Yiming Wang
 *
 */
public class Fire extends AbstractObserver implements IObservable {
	
private static Fire fire = new Fire();
	
	private ArrayList<IFireObservable> startObservables = new ArrayList<IFireObservable>();
	
	/**
	 * Make Fire events to be observable.
	 */
	private Fire() {
		
		startObserver(this);
	}
	
	/**
	 * Start observing.
	 * @param iFireObservable the IFireObservable which is going to be observable.
	 */
	public static void startObserving(final IFireObservable iFireObservable){
		
		fire.addObserver(iFireObservable);
	}
	
	/**
	 * Stop observing.
	 * @param iFireObservable the IFireObservable which is going to stop being observable.
	 */
	public static void stopObserving(final IFireObservable iFireObservable){
		
		fire.removeObserver(iFireObservable);
	}
	
	/**
	 * fire event has occured, the observer would be notified.
	 */
	public static void fire() {
		
		fire.observed();
	}
	
	
	private synchronized void addObserver(final IFireObservable iFireObservable) {
		
		if (iFireObservable != null) {
			
			if (!startObservables.contains(iFireObservable)) {
				
				startObservables.add(iFireObservable);
			}
		}
	}
	
	private synchronized void removeObserver(final IFireObservable iFireObservable) {
		
		startObservables.remove(iFireObservable);
		
	}
	
	@Override
	public synchronized void update() {
		
		if (startObservables != null) {
			
			Iterator<IFireObservable> iiFireObservables = startObservables.iterator();
			
			while (iiFireObservables.hasNext()) {
				
				IFireObservable iFireObservable = iiFireObservables.next();
				
				if(iiFireObservables != null) {
					
					iFireObservable.fire();
					
				}
			}
		}
	}

}



