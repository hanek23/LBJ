package testutils;

import gui.forms.LBJForm;
import gui.forms.LBJWizardForm;

/**
 * Mock form ussually used as {@link LBJWizardForm#setPreviousForm(LBJForm)} in
 * tests
 */
public class LBJMockForm extends LBJForm {

	public LBJMockForm() {
		super(null);
	}

	@Override
	public String toString() {
		return "MOCK FORM";
	}

	@Override
	public void initializeComponents() {
		// no components
	}

	@Override
	public void addFormUpdaters() {
		// no updaters
	}

	@Override
	public void addFormValidators() {
		// no validators

	}

	@Override
	public void addComponentsToContent() {
		// no components

	}

	@Override
	public void addButtonsToContent() {
		// no buttons

	}

	@Override
	public void initializeContent() {
		// mock
	}

}
