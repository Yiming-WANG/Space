package main.se450.interfaces;

import java.awt.Graphics;

import main.se450.collections.LineCollection;

public interface IShot extends IObservable {
	void update();

	boolean isExpired();

	void draw(Graphics g);
	
	void addSides(LineCollection l);
	
	LineCollection getLineCollection();
}
