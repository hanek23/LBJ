package gui.updaters.shared;

import org.apache.commons.lang3.StringUtils;

import gui.components.LBJLabeledComponent;
import gui.updaters.LBJLabeledComponentUpdater;
import gui.utils.SimpleBean;

/**
 * Changes label to "label*" if component is enabled to symbol that component is
 * required, removes it otherwise
 */
public class LBJRequiredLabelComponentUpdater implements LBJLabeledComponentUpdater, SimpleBean {

	private static final String REQUIRED_ASTERISK = "*";

	@Override
	public void update(LBJLabeledComponent component) {
		if (component.isEnabled()) {
			addAsterisk(component);
			return;
		}
		removeAsterisk(component);
	}

	private void removeAsterisk(LBJLabeledComponent component) {
		String labelText = component.getLabel().getText();
		if (!StringUtils.endsWith(labelText, REQUIRED_ASTERISK)) {
			return;
		}
		component.getLabel().setText(StringUtils.chop(labelText));
	}

	private void addAsterisk(LBJLabeledComponent component) {
		String labelText = component.getLabel().getText();
		if (StringUtils.endsWith(labelText, REQUIRED_ASTERISK)) {
			return;
		}
		component.getLabel().setText(labelText + REQUIRED_ASTERISK);
	}

}
