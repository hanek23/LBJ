package gui.forms;

import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Window;

import constants.Labels;
import gui.utils.BeanSupplier;
import gui.utils.LBJFormUtils;
import utils.LBJPreferences;

/**
 * Adds possibility to apply and reset preferences.
 */
public abstract class LBJPreferencesForm extends LBJWizardForm {

	// cannot use default back button as I need to override a bit with applying and
	// reseting changes to preferences
	private Button backButton;
	private Button applyButton;
	private Button resetToDefaultButton;

	public LBJPreferencesForm(Window window, LBJForm previousForm) {
		super(window, previousForm);
	}

	/**
	 * Will set all components to current preference value
	 */
	public abstract void setComponentsToPreferenceValues();

	/**
	 * Will save values of user filled values to preferences
	 */
	public abstract void applyToPreferences();

	@Override
	public void initializeButtons() {
		applyButton = createApplyPreferencesButton(this);
		resetToDefaultButton = createResetPreferencesButton(this);
		backButton = createBackPreferencesButton(this);
	}

	@Override
	public void addButtons() {
		LBJFormUtils.addEmptySpaceTo(this);
		LBJFormUtils.addButtonTo(this, backButton);
		LBJFormUtils.addButtonTo(this, resetToDefaultButton);
		LBJFormUtils.addButtonTo(this, applyButton);
	}

	public Button createApplyPreferencesButton(LBJPreferencesForm form) {
		return new Button(Labels.BUTTON_PREFERENCES_APPLY, new Runnable() {
			@Override
			public void run() {
				if (!form.validate()) {
					return;
				}
				// apply and return to previous form
				form.applyToPreferences();
				form.goToPreviousForm();
			}

		});
	}

	public Button createResetPreferencesButton(LBJPreferencesForm form) {
		return new Button(Labels.BUTTON_PREFERENCES_RESET, new Runnable() {
			@Override
			public void run() {
				BeanSupplier.get(LBJPreferences.class).setDefaultPreferences();
				form.setComponentsToPreferenceValues();
			}

		});

	}

	public Button createBackPreferencesButton(LBJPreferencesForm form) {
		return new Button(Labels.BUTTON_BACK, new Runnable() {
			@Override
			public void run() {
				setComponentsToPreferenceValues();
				goToPreviousForm();
			}

		});
	}

	@Override
	public Button getBackButton() {
		return backButton;
	}

	public Button getApplyButton() {
		return applyButton;
	}

	public Button getResetToDefaultButton() {
		return resetToDefaultButton;
	}

}
