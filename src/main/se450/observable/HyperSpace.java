package main.se450.observable;

import java.util.ArrayList;
import java.util.Iterator;

import main.se450.interfaces.IHyperSpaceObservable;
import main.se450.interfaces.IObservable;

/**
 * HyperSpace class defines how hyperSpace event could be observed by registered observers. 
 * @author Yiming Wang
 *
 */
public class HyperSpace extends AbstractObserver implements IObservable
{
	private static HyperSpace hyperSpace = new HyperSpace();
	
	private ArrayList<IHyperSpaceObservable> hyperSpaceObservables = new ArrayList<IHyperSpaceObservable>();

	private HyperSpace() {
		
		startObserver(this);
	}
	
	public static void startObserving(final IHyperSpaceObservable iHyperSpaceObservable){
		
		hyperSpace.addObserver(iHyperSpaceObservable);
	}

	public static void stopObserving(final IHyperSpaceObservable iHyperSpaceObservable){
		
		hyperSpace.removeObserver(iHyperSpaceObservable);
	}

	public static void hyperSpace() {
		
		hyperSpace.observed();
	}

	private synchronized void addObserver(final IHyperSpaceObservable iHyperSpaceObservable) {
		
		if (iHyperSpaceObservable != null) {
			
			if (!hyperSpaceObservables.contains(iHyperSpaceObservable)) {
				
				hyperSpaceObservables.add(iHyperSpaceObservable);
			}
		}
	}


	private synchronized void removeObserver(final IHyperSpaceObservable iHyperSpaceObservable) {
		
		hyperSpaceObservables.remove(iHyperSpaceObservable);
		
	}

	@Override
	public synchronized void update() {
		
		if (hyperSpaceObservables != null) {
			
			Iterator<IHyperSpaceObservable> iiHyperSpaceObservables = hyperSpaceObservables.iterator();
			
			while (iiHyperSpaceObservables.hasNext()) {
				
				IHyperSpaceObservable iHyperSpaceObservable = iiHyperSpaceObservables.next();
				
				if(iiHyperSpaceObservables != null) {
					
					iHyperSpaceObservable.hyperSpace();
					
				}
			}
		}
	}


}
