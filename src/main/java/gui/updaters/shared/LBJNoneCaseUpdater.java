package gui.updaters.shared;

import gui.components.LBJValueComponent;
import gui.updaters.LBJValueUpdater;
import gui.utils.SimpleBean;
import gui.validators.shared.LBJLowerCaseValidator;
import gui.validators.shared.LBJUpperCaseValidator;

/**
 * Third wheel of {@link LBJUpperCaseValidator} and
 * {@link LBJLowerCaseValidator}, does not update anything
 */
public class LBJNoneCaseUpdater implements LBJValueUpdater<String>, SimpleBean {

	@Override
	public void update(LBJValueComponent<String> component) {
		// nothing
	}

}
