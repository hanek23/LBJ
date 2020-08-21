package gui.updaters.shared;

import gui.components.LBJValueComponent;
import gui.updaters.LBJValueUpdater;
import gui.utils.SimpleBean;

/**
 * Updates components {@link String} value to upper case.
 */
public class LBJUpperCaseUpdater implements LBJValueUpdater<String>, SimpleBean<LBJUpperCaseUpdater> {

	@Override
	public void update(LBJValueComponent<String> component) {
		component.setValue(component.getValue().toUpperCase());
	}

}
