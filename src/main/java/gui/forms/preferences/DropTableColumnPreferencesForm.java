package gui.forms.preferences;

import static constants.NamingConventions.*;

import com.googlecode.lanterna.gui2.Window;

import constants.Labels;
import gui.builders.LBJCheckBoxBuilder;
import gui.components.LBJCheckBox;
import gui.forms.LBJForm;
import gui.forms.LBJPreferencesForm;
import gui.utils.BeanSupplier;
import gui.utils.LBJFormUtils;
import utils.LBJPreferences;

public class DropTableColumnPreferencesForm extends LBJPreferencesForm {

	private LBJCheckBox useLetterCaseConvensionCheckBox;
	private LBJCheckBox copyTableNameCheckBox;

	public DropTableColumnPreferencesForm(Window window, LBJForm previousForm) {
		super(window, previousForm);
	}

	@Override
	public void initializeComponents() {
		useLetterCaseConvensionCheckBox = new LBJCheckBoxBuilder(Labels.PREFERENCES_USE_LETTER_CASE_CONVENTIONS, this)
				.setChecked(BeanSupplier.get(LBJPreferences.class).getUseLetterCaseConventions()).build();

		copyTableNameCheckBox = new LBJCheckBoxBuilder(Labels.PREFERENCES_COPY_TABLE_NAME, this)
				.setChecked(BeanSupplier.get(LBJPreferences.class).getCopyTableName()).build();
	}

	@Override
	public void setComponentsToPreferenceValues() {
		useLetterCaseConvensionCheckBox.setValue(BeanSupplier.get(LBJPreferences.class).getUseLetterCaseConventions());
		copyTableNameCheckBox.setValue(BeanSupplier.get(LBJPreferences.class).getCopyTableName());
	}

	@Override
	public void applyToPreferences() {
		BeanSupplier.get(LBJPreferences.class).putBoolean(PKEY_USE_LETTER_CASE_CONVENTIONS,
				useLetterCaseConvensionCheckBox.getValue());
		BeanSupplier.get(LBJPreferences.class).putBoolean(PKEY_COPY_TABLE_NAME, copyTableNameCheckBox.getValue());
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

}
