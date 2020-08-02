package gui.updaters;

import gui.components.LBJComponent;
import gui.components.LBJLabeledComponent;

/**
 * {@link LBJLabeledComponent}'s updater.
 */
public interface LBJLabeledComponentUpdater extends LBJComponentUpdater {

	default void update(LBJComponent component) {
		update((LBJLabeledComponent) component);
	}

	void update(LBJLabeledComponent component);

}
