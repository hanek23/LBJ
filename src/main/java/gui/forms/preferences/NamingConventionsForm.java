package gui.forms.preferences;

import com.googlecode.lanterna.gui2.Window;

import constants.Labels;
import constants.NamingConventions;
import gui.builders.LBJTextBoxBuilder;
import gui.components.LBJTextBox;
import gui.forms.LBJForm;
import gui.forms.LBJPreferencesForm;
import gui.utils.LBJFormUtils;
import main.LBJ;

public class NamingConventionsForm extends LBJPreferencesForm {

	private LBJTextBox primaryKeyName;
	private LBJTextBox primaryKeyConstraintName;
	private LBJTextBox sequenceName;
	private LBJTextBox indexName;
	private LBJTextBox foreignKeyName;

	public NamingConventionsForm(Window window, LBJForm previousForm) {
		super(window, previousForm);
	}

	@Override
	public void initializeComponents() {
		primaryKeyName = new LBJTextBoxBuilder(Labels.PRIMARY_KEY_NAME, this).value(LBJ.preferences.getPrimaryKeyName())
				.required().build();

		primaryKeyConstraintName = new LBJTextBoxBuilder(Labels.PRIMARY_KEY_CONSTRAIN_NAME, this)
				.value(LBJ.preferences.getPrimaryKeyConstraintName()).required().build();

		sequenceName = new LBJTextBoxBuilder(Labels.SEQUENCE_NAME, this).value(LBJ.preferences.getSequenceName())
				.required().build();

		indexName = new LBJTextBoxBuilder(Labels.INDEX_NAME, this).value(LBJ.preferences.getIndexName()).required()
				.build();

		foreignKeyName = new LBJTextBoxBuilder(Labels.FOREIGN_KEY_NAME, this).value(LBJ.preferences.getForeignKeyName())
				.required().build();
	}

	/**
	 * Will set all components to current preference value
	 */
	@Override
	public void setComponentsToPreferenceValues() {
		primaryKeyName.setValue(LBJ.preferences.getPrimaryKeyName());
		primaryKeyConstraintName.setValue(LBJ.preferences.getPrimaryKeyConstraintName());
		sequenceName.setValue(LBJ.preferences.getSequenceName());
		indexName.setValue(LBJ.preferences.getIndexName());
		foreignKeyName.setValue(LBJ.preferences.getForeignKeyName());
	}

	@Override
	public void applyToPreferences() {
		LBJ.preferences.put(NamingConventions.PKEY_PRIMARY_KEY_NAME, primaryKeyName.getValue());
		LBJ.preferences.put(NamingConventions.PKEY_PRIMARY_KEY_CONSTRAINT_NAME, primaryKeyConstraintName.getValue());
		LBJ.preferences.put(NamingConventions.PKEY_SEQUENCE_NAME, sequenceName.getValue());
		LBJ.preferences.put(NamingConventions.PKEY_INDEX_NAME, indexName.getValue());
		LBJ.preferences.put(NamingConventions.PKEY_FOREIGN_KEY_NAME, foreignKeyName.getValue());
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
		LBJFormUtils.addValueAndLabeledComponentTo(this, primaryKeyName);
		LBJFormUtils.addValueAndLabeledComponentTo(this, primaryKeyConstraintName);
		LBJFormUtils.addValueAndLabeledComponentTo(this, sequenceName);
		LBJFormUtils.addValueAndLabeledComponentTo(this, indexName);
		LBJFormUtils.addValueAndLabeledComponentTo(this, foreignKeyName);
	}

	@Override
	public String toString() {
		return Labels.NAMING_CONVENTIONS_FORM;
	}

	public LBJTextBox getPrimaryKeyName() {
		return primaryKeyName;
	}

	public LBJTextBox getPrimaryKeyConstraintName() {
		return primaryKeyConstraintName;
	}

	public LBJTextBox getSequenceName() {
		return sequenceName;
	}

	public LBJTextBox getIndexName() {
		return indexName;
	}

	public LBJTextBox getForeignKeyName() {
		return foreignKeyName;
	}

	public void setForeignKeyName(LBJTextBox foreignKeyName) {
		this.foreignKeyName = foreignKeyName;
	}

}
