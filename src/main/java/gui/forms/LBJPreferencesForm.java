package gui.forms;

import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Window;

import constants.Labels;
import gui.utils.BeanSupplier;
import utils.LBJPreferences;

/**
 * Adds possibility to apply and reset preferences.
 */
public abstract class LBJPreferencesForm extends LBJWizardForm {

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
	public abstract void applyPreferences();

	public Button createApplyPreferencesButton(LBJPreferencesForm form) {
		return new Button(Labels.BUTTON_PREFERENCES_APPLY, new Runnable() {
			@Override
			public void run() {
				if (!form.validate()) {
					return;
				}
				// apply and return to main menu
				form.applyPreferences();
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

}
