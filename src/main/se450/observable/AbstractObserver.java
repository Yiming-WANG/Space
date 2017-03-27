package main.se450.observable;

import main.se450.interfaces.IObservable;

/**
 * AbstractObserver class defines basic behaviors of observable object.
 * @author Yiming Wang
 *
 */
public class AbstractObserver {
	
	private IObservable iObservable = null;
	
	public AbstractObserver() {}
	
	/**
	 * Make IObservable parameter start to be observed. 
	 * @param iiObservable
	 */
	public synchronized void startObserver(final IObservable iiObservable) {
		
		iObservable = iiObservable;
	}
	
	/**
	 * stop observing
	 * @param iiObservable
	 */
	public synchronized void stopObserver(final IObservable iiObservable) {
		
		if (isObserving(iiObservable)) {
			
			iObservable = null;
		}
	}
	
	/**
	 * Check if the parameter is currently observing.
	 * @param iiObservable
	 * @return true if it is observing. otherwise return false.
	 */
	public synchronized boolean isObserving(final IObservable iiObservable) {
		
		return (iObservable == iiObservable);
	}
	
	/**
	 * The observable object calls update() to make changes, 
	 * the registered observers would be notified.
	 */
	protected void observed() {
		
		if (iObservable != null) {
			
			iObservable.update();
		}
	}

}
