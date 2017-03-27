package main.se450.interfaces;

import main.se450.collections.LineCollection;

public interface ICollide extends IObservable{
	void addSides(LineCollection lineCollection);
}
