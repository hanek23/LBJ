package gui.updaters;

import gui.components.LBJValueHolderComponent;

/**
 * {@link LBJValueHolderComponent}'s value updater.
 */
public interface LBJValueUpdater<T> {

	void update(LBJValueHolderComponent<T> component);

}
