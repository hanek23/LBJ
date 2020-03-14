package gui.updaters;

import gui.components.LBJValueHolderComponent;

public interface LBJValueUpdater<T> {

	void update(LBJValueHolderComponent<T> component);

}
