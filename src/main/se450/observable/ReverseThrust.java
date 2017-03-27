package main.se450.observable;

import java.util.ArrayList;
import java.util.Iterator;

import main.se450.interfaces.IObservable;
import main.se450.interfaces.IReverseThrustObservable;

/**
 * ReverseThrust class defines how reverseThrust event could be observed by registered observers. 
 * @author Yiming Wang
 *
 */
public class ReverseThrust extends AbstractObserver implements IObservable {
	
private static ReverseThrust reverseThrust = new ReverseThrust();
	
	private ArrayList<IReverseThrustObservable> startObservables = new ArrayList<IReverseThrustObservable>();
	
	private ReverseThrust() {
		
		startObserver(this);
	}
	
	public static void startObserving(final IReverseThrustObservable iReverseThrustObservable){
		
		reverseThrust.addObserver(iReverseThrustObservable);
	}
	
	public static void stopObserving(final IReverseThrustObservable iReverseThrustObservable){
		
		reverseThrust.removeObserver(iReverseThrustObservable);
	}
	
	public static void reverseThrust() {
		
		reverseThrust.observed();
	}
	
	private synchronized void addObserver(final IReverseThrustObservable iReverseThrustObservable) {
		
		if (iReverseThrustObservable != null) {
			
			if (!startObservables.contains(iReverseThrustObservable)) {
				
				startObservables.add(iReverseThrustObservable);
			}
		}
	}
	
	private synchronized void removeObserver(final IReverseThrustObservable iReverseThrustObservable) {
		
		startObservables.remove(iReverseThrustObservable);
		
	}
	
	@Override
	public synchronized void update() {
		
		if (startObservables != null) {
			
			Iterator<IReverseThrustObservable> iiReverseThrustObservables = startObservables.iterator();
			
			while (iiReverseThrustObservables.hasNext()) {
				
				IReverseThrustObservable iReverseThrustObservable = iiReverseThrustObservables.next();
				
				if(iiReverseThrustObservables != null) {
					
					iReverseThrustObservable.reverseThrust();
					
				}
			}
		}
	}

}