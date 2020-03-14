package gui.updaters.shared;

import gui.components.LBJValueHolderComponent;
import gui.updaters.LBJValueUpdater;

/**
 * Updates components {@link String} value to upper case.
 */
public class LBJValueUpperCaseUpdater implements LBJValueUpdater<String> {

	@Override
	public void update(LBJValueHolderComponent<String> component) {
		component.setValue(component.getValue().toUpperCase());
	}

}
