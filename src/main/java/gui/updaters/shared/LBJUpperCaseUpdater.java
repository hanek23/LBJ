package gui.updaters.shared;

import gui.components.LBJValueComponent;
import gui.updaters.LBJValueUpdater;

/**
 * Updates components {@link String} value to upper case.
 */
public class LBJUpperCaseUpdater implements LBJValueUpdater<String> {

	@Override
	public void update(LBJValueComponent<String> component) {
		component.setValue(component.getValue().toUpperCase());
	}

}
