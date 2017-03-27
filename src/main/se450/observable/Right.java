package main.se450.observable;

import java.util.ArrayList;
import java.util.Iterator;

import main.se450.interfaces.IObservable;
import main.se450.interfaces.IRightObservable;

/**
 * Right class defines how right event could be observed by registered observers. 
 * @author Yiming Wang
 *
 */
public class Right extends AbstractObserver implements IObservable {
	
private static Right right = new Right();
	
	private ArrayList<IRightObservable> startObservables = new ArrayList<IRightObservable>();
	
	private Right() {
		
		startObserver(this);
	}
	
	public static void startObserving(final IRightObservable iRightObservable){
		
		right.addObserver(iRightObservable);
	}
	
	public static void stopObserving(final IRightObservable iRightObservable){
		
		right.removeObserver(iRightObservable);
	}
	
	public static void right() {
		
		right.observed();
	}
	
	private synchronized void addObserver(final IRightObservable iRightObservable) {
		
		if (iRightObservable != null) {
			
			if (!startObservables.contains(iRightObservable)) {
				
				startObservables.add(iRightObservable);
			}
		}
	}
	
	private synchronized void removeObserver(final IRightObservable iRightObservable) {
		
		startObservables.remove(iRightObservable);
		
	}
	
	@Override
	public synchronized void update() {
		
		if (startObservables != null) {
			
			Iterator<IRightObservable> iiRightObservables = startObservables.iterator();
			
			while (iiRightObservables.hasNext()) {
				
				IRightObservable iRightObservable = iiRightObservables.next();
				
				if(iiRightObservables != null) {
					
					iRightObservable.right();
					
				}
			}
		}
	}

}



