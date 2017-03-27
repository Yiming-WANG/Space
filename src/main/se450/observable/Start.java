package main.se450.observable;

import java.util.ArrayList;
import java.util.Iterator;

import main.se450.interfaces.IObservable;
import main.se450.interfaces.IStartObservable;
/**
 * Start class defines how Start event could be observed by registered observers. 
 * @author Yiming Wang
 *
 */
public class Start extends AbstractObserver implements IObservable {
	
private static Start start = new Start();
	
	private ArrayList<IStartObservable> startObservables = new ArrayList<IStartObservable>();
	
	private Start() {
		
		startObserver(this);
	}
	
	public static void startObserving(final IStartObservable iStartObservable){
		
		start.addObserver(iStartObservable);
	}
	
	public static void stopObserving(final IStartObservable iStartObservable){
		
		start.removeObserver(iStartObservable);
	}
	
	public static void start() {
		
		start.observed();
	}
	
	private synchronized void addObserver(final IStartObservable iStartObservable) {
		
		if (iStartObservable != null) {
			
			if (!startObservables.contains(iStartObservable)) {
				
				startObservables.add(iStartObservable);
			}
		}
	}
	
	private synchronized void removeObserver(final IStartObservable iStartObservable) {
		
		startObservables.remove(iStartObservable);
		
	}
	
	@Override
	public synchronized void update() {
		
		if (startObservables != null) {
			
			Iterator<IStartObservable> iiStartObservables = startObservables.iterator();
			
			while (iiStartObservables.hasNext()) {
				
				IStartObservable iStartObservable = iiStartObservables.next();
				
				if(iiStartObservables != null) {
					
					iStartObservable.start();
					
				}
			}
		}
	}

}
