package gui.updaters.shared;

import org.apache.commons.lang3.StringUtils;

import gui.components.LBJValueComponent;
import gui.updaters.LBJValueUpdater;

/**
 * Updates components {@link String} value to lower case.
 */
public class LBJLowerCaseUpdater implements LBJValueUpdater<String> {

	@Override
	public void update(LBJValueComponent<String> component) {
		component.setValue(StringUtils.lowerCase(component.getValue()));
	}

}
