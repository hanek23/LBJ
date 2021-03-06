package testutils;

import com.googlecode.lanterna.gui2.BasicWindow;

import constants.Labels;
import gui.forms.LBJForm;
import gui.forms.LBJWizardForm;

/**
 * Mock form ussually used as {@link LBJWizardForm#setPreviousForm(LBJForm)} in
 * tests
 */
public class LBJMockForm extends LBJForm {

	public LBJMockForm() {
		super(new BasicWindow(Labels.WINDOW_NAME));
		initializeForm();
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
	public void initializeButtons() {
		// no buttons
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
	public void addComponents() {
		// no components

	}

	@Override
	public void addButtons() {
		// no buttons

	}

	@Override
	public void initializeContent() {
		// mock
	}

}
