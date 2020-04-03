package gui.updaters;

import gui.components.LBJValueComponent;

/**
 * {@link LBJValueComponent}'s value updater.
 */
public interface LBJValueUpdater<T> {

	void update(LBJValueComponent<T> component);

}
