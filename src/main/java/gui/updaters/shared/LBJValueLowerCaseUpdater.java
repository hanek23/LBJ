package gui.updaters.shared;

import org.apache.commons.lang3.StringUtils;

import gui.components.LBJValueHolderComponent;
import gui.updaters.LBJValueUpdater;

/**
 * Updates components {@link String} value to lower case.
 */
public class LBJValueLowerCaseUpdater implements LBJValueUpdater<String> {

	@Override
	public void update(LBJValueHolderComponent<String> component) {
		component.setValue(StringUtils.lowerCase(component.getValue()));
	}

}
