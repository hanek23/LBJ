package gui.forms.preferences;

import static constants.NamingConventions.PKEY_COPY_TABLE_NAME;
import static constants.NamingConventions.PKEY_USE_LETTER_CASE_CONVENTIONS;

import com.googlecode.lanterna.gui2.Window;

import constants.Labels;
import gui.builders.LBJCheckBoxBuilder;
import gui.components.LBJCheckBox;
import gui.forms.LBJForm;
import gui.forms.LBJPreferencesForm;
import gui.utils.LBJFormUtils;
import main.LBJ;

public class DropTableColumnPreferencesForm extends LBJPreferencesForm {

	private LBJCheckBox useLetterCaseConvensionCheckBox;
	private LBJCheckBox copyTableNameCheckBox;

	public DropTableColumnPreferencesForm(Window window, LBJForm previousForm) {
		super(window, previousForm);
	}

	@Override
	public void initializeComponents() {
		useLetterCaseConvensionCheckBox = new LBJCheckBoxBuilder(Labels.PREFERENCES_USE_LETTER_CASE_CONVENTIONS, this)
				.setChecked(LBJ.preferences.getUseLetterCaseConventions()).build();

		copyTableNameCheckBox = new LBJCheckBoxBuilder(Labels.PREFERENCES_COPY_TABLE_NAME, this)
				.setChecked(LBJ.preferences.getCopyTableName()).build();
	}

	@Override
	public void setComponentsToPreferenceValues() {
		useLetterCaseConvensionCheckBox.setValue(LBJ.preferences.getUseLetterCaseConventions());
		copyTableNameCheckBox.setValue(LBJ.preferences.getCopyTableName());
	}

	@Override
	public void applyToPreferences() {
		LBJ.preferences.putBoolean(PKEY_USE_LETTER_CASE_CONVENTIONS, useLetterCaseConvensionCheckBox.getValue());
		LBJ.preferences.putBoolean(PKEY_COPY_TABLE_NAME, copyTableNameCheckBox.getValue());
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
		LBJFormUtils.addValueAndLabeledComponentTo(this, useLetterCaseConvensionCheckBox);
		LBJFormUtils.addValueAndLabeledComponentTo(this, copyTableNameCheckBox);
	}

	@Override
	public String toString() {
		return Labels.DROP_TABLE_COLUMN_PREFERENCES_FORM;
	}

	public LBJCheckBox getUseLetterCaseConvensionCheckBox() {
		return useLetterCaseConvensionCheckBox;
	}

	public LBJCheckBox getCopyTableNameCheckBox() {
		return copyTableNameCheckBox;
	}

}
