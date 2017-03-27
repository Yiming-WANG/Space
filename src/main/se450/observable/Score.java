package main.se450.observable;

import java.util.ArrayList;
import java.util.Iterator;

import main.se450.interfaces.IScoreObservable;
import main.se450.interfaces.IObservable;

/**
 * Score class defines how score event could be observed by registered observers. 
 * @author Yiming Wang
 *
 */
public class Score extends AbstractObserver implements IObservable
{
	private static Score score = new Score();
	private static int totalScore;
	
	private ArrayList<IScoreObservable> scoreObservables = new ArrayList<IScoreObservable>();

	private Score() {
		
		startObserver(this);
	}
	
	public static void addScore(int nScore)
	{
		totalScore += nScore;
	}
	
	public static int getTotalScores()
	{
		return totalScore;
	}
	
	public static void clearScore()
	{
		totalScore = 0;
	}

	public static void startObserving(final IScoreObservable iScoreObservable){
		
		score.addObserver(iScoreObservable);
	}

	public static void stopObserving(final IScoreObservable iScoreObservable){
		
		score.removeObserver(iScoreObservable);
	}

	public static void score() {
		
		score.observed();
	}

	private synchronized void addObserver(final IScoreObservable iScoreObservable) {
		
		if (iScoreObservable != null) {
			
			if (!scoreObservables.contains(iScoreObservable)) {
				
				scoreObservables.add(iScoreObservable);
			}
		}
	}

	private synchronized void removeObserver(final IScoreObservable iScoreObservable)
	{		
		scoreObservables.remove(iScoreObservable);		
	}

	@Override
	public synchronized void update() {
		
		if (scoreObservables != null) {
			
			Iterator<IScoreObservable> iiScoreObservables = scoreObservables.iterator();
			
			while (iiScoreObservables.hasNext()) {
				
				IScoreObservable iScoreObservable = iiScoreObservables.next();
				
				if(iiScoreObservables != null) {
					
					iScoreObservable.score();
					
				}
			}
		}
	}

}
