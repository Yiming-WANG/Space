package main.se450.observable;

import java.util.ArrayList;
import java.util.Iterator;

import main.se450.interfaces.IObservable;
import main.se450.interfaces.ILeftObservable;

/**
 * Left class defines how left event could be observed by registered observers. 
 * @author Yiming Wang
 *
 */
public class Left extends AbstractObserver implements IObservable {
	
private static Left left = new Left();
	
	private ArrayList<ILeftObservable> startObservables = new ArrayList<ILeftObservable>();
	
	private Left() {
		
		startObserver(this);
	}
	
	public static void startObserving(final ILeftObservable iLeftObservable){
		
		left.addObserver(iLeftObservable);
	}
	
	public static void stopObserving(final ILeftObservable iLeftObservable){
		
		left.removeObserver(iLeftObservable);
	}
	
	public static void left() {
		
		left.observed();
	}
	
	private synchronized void addObserver(final ILeftObservable iLeftObservable) {
		
		if (iLeftObservable != null) {
			
			if (!startObservables.contains(iLeftObservable)) {
				
				startObservables.add(iLeftObservable);
			}
		}
	}
	
	private synchronized void removeObserver(final ILeftObservable iLeftObservable) {
		
		startObservables.remove(iLeftObservable);
		
	}
	
	@Override
	public synchronized void update() {
		
		if (startObservables != null) {
			
			Iterator<ILeftObservable> iiLeftObservables = startObservables.iterator();
			
			while (iiLeftObservables.hasNext()) {
				
				ILeftObservable iLeftObservable = iiLeftObservables.next();
				
				if(iiLeftObservables != null) {
					
					iLeftObservable.left();
					
				}
			}
		}
	}

}



