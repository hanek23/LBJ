package gui.forms.preferences;

import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Window;

import constants.Labels;
import constants.NamingConventions;
import constants.NamingConventions.LetterCase;
import gui.builders.LBJComboBoxBuilder;
import gui.components.LBJComboBox;
import gui.forms.LBJForm;
import gui.forms.LBJPreferencesForm;
import gui.utils.BeanSupplier;
import gui.utils.LBJFormUtils;
import utils.LBJPreferences;

public class LetterCaseConventionsForm extends LBJPreferencesForm {

	private LBJComboBox<LetterCase> tableNameCase;
	// cannot use default back button as I need to override a bit with applying and
	// reseting changes to preferences
	private Button backButton;
	private Button applyButton;
	private Button resetToDefaultButton;

	public LetterCaseConventionsForm(Window window, LBJForm previousForm) {
		super(window, previousForm);
	}

	@Override
	public void initializeComponents() {
		tableNameCase = createComboBoxLetterCase(Labels.TABLE_NAME,
				BeanSupplier.get(LBJPreferences.class).getTableNameCase());
	}

	private LBJComboBox<LetterCase> createComboBoxLetterCase(String name, LetterCase startValue) {
		return new LBJComboBoxBuilder<LetterCase>(name, this).addItem(LetterCase.NONE).addItem(LetterCase.LOWER)
				.addItem(LetterCase.UPPER).selectItem(startValue).build();
	}

	@Override
	public void initializeButtons() {
		applyButton = createApplyPreferencesButton(this);
		resetToDefaultButton = createResetPreferencesButton(this);
		backButton = createBackPreferencesButton(this);
	}

	/**
	 * Will set all components to current preference value
	 */
	@Override
	public void setComponentsToPreferenceValues() {
		tableNameCase.setValue(BeanSupplier.get(LBJPreferences.class).getTableNameCase());
	}

	@Override
	public void applyPreferences() {
		BeanSupplier.get(LBJPreferences.class).put(NamingConventions.PKEY_TABLE_NAME_CASE, tableNameCase.getValue().toString());
	}

	@Override
	public void addFormUpdaters() {
		// no form updaters

	}

	@Override
	public void addFormValidators() {
		// no form validators

	}

	@Override
	public void addComponents() {
		LBJFormUtils.addValueAndLabeledComponentTo(this, tableNameCase);
	}

	@Override
	public void addButtons() {
		LBJFormUtils.addEmptySpaceTo(this);
		LBJFormUtils.addButtonTo(this, backButton);
		LBJFormUtils.addButtonTo(this, resetToDefaultButton);
		LBJFormUtils.addButtonTo(this, applyButton);
	}

	@Override
	public String toString() {
		return Labels.PREFERENCES_FORM;
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
