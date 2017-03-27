package main.se450.observable;

import java.util.ArrayList;
import java.util.Iterator;

import main.se450.interfaces.IObservable;
import main.se450.interfaces.IForwardThrustObservable;

/**
 * ForwardThrust class defines how forwardThrust event could be observed by registered observers. 
 * @author Yiming Wang
 *
 */
public class ForwardThrust extends AbstractObserver implements IObservable {
	
private static ForwardThrust forwardThrust = new ForwardThrust();
	
	private ArrayList<IForwardThrustObservable> startObservables = new ArrayList<IForwardThrustObservable>();
	
	private ForwardThrust() {
		
		startObserver(this);
	}
	
	public static void startObserving(final IForwardThrustObservable iForwardThrustObservable){
		
		forwardThrust.addObserver(iForwardThrustObservable);
	}
	
	public static void stopObserving(final IForwardThrustObservable iForwardThrustObservable){
		
		forwardThrust.removeObserver(iForwardThrustObservable);
	}
	
	public static void forwardThrust() {
		
		forwardThrust.observed();
	}
	
	private synchronized void addObserver(final IForwardThrustObservable iForwardThrustObservable) {
		
		if (iForwardThrustObservable != null) {
			
			if (!startObservables.contains(iForwardThrustObservable)) {
				
				startObservables.add(iForwardThrustObservable);
			}
		}
	}
	
	private synchronized void removeObserver(final IForwardThrustObservable iForwardThrustObservable) {
		
		startObservables.remove(iForwardThrustObservable);
		
	}
	
	@Override
	public synchronized void update() {
		
		if (startObservables != null) {
			
			Iterator<IForwardThrustObservable> iiForwardThrustObservables = startObservables.iterator();
			
			while (iiForwardThrustObservables.hasNext()) {
				
				IForwardThrustObservable iForwardThrustObservable = iiForwardThrustObservables.next();
				
				if(iiForwardThrustObservables != null) {
					
					iForwardThrustObservable.forwardThrust();
					
				}
			}
		}
	}

}



