package gui.updaters.shared;

import org.apache.commons.lang3.StringUtils;

import gui.components.LBJValueHolderComponent;
import gui.updaters.LBJValueUpdater;

public class LBJValueUpperCaseUpdater implements LBJValueUpdater<String> {

	@Override
	public void update(LBJValueHolderComponent<String> component) {
		component.setValue(StringUtils.upperCase(component.getValue()));
	}

}
