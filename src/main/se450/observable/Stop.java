package main.se450.observable;

import java.util.ArrayList;
import java.util.Iterator;

import main.se450.interfaces.IObservable;
import main.se450.interfaces.IStopObservable;

/**
 * Stop class defines how Stop event could be observed by registered observers. 
 * @author Yiming Wang
 *
 */
public class Stop extends AbstractObserver implements IObservable {
	
private static Stop stop = new Stop();
	
	private ArrayList<IStopObservable> startObservables = new ArrayList<IStopObservable>();
	
	private Stop() {
		
		startObserver(this);
	}
	
	public static void startObserving(final IStopObservable iStopObservable){
		
		stop.addObserver(iStopObservable);
	}
	
	public static void stopObserving(final IStopObservable iStopObservable){
		
		stop.removeObserver(iStopObservable);
	}
	
	public static void stop() {
		
		stop.observed();
	}
	
	private synchronized void addObserver(final IStopObservable iStopObservable) {
		
		if (iStopObservable != null) {
			
			if (!startObservables.contains(iStopObservable)) {
				
				startObservables.add(iStopObservable);
			}
		}
	}
	
	private synchronized void removeObserver(final IStopObservable iStopObservable) {
		
		startObservables.remove(iStopObservable);
		
	}
	
	@Override
	public synchronized void update() {
		
		if (startObservables != null) {
			
			Iterator<IStopObservable> iiStopObservables = startObservables.iterator();
			
			while (iiStopObservables.hasNext()) {
				
				IStopObservable iStopObservable = iiStopObservables.next();
				
				if(iiStopObservables != null) {
					
					iStopObservable.stop();
					
				}
			}
		}
	}

}



