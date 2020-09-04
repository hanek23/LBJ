package gui.forms.preferences;

import com.googlecode.lanterna.gui2.Window;

import constants.Labels;
import constants.NamingConventions;
import gui.builders.LBJTextBoxBuilder;
import gui.components.LBJTextBox;
import gui.forms.LBJForm;
import gui.forms.LBJPreferencesForm;
import gui.utils.BeanSupplier;
import gui.utils.LBJFormUtils;
import utils.LBJPreferences;

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
		primaryKeyName = new LBJTextBoxBuilder(Labels.CREATE_TABLE_PRIMARY_KEY_NAME, this)
				.value(BeanSupplier.get(LBJPreferences.class).getPrimaryKeyName()).required().build();

		primaryKeyConstraintName = new LBJTextBoxBuilder(Labels.CREATE_TABLE_PRIMARY_KEY_CONSTRAIN_NAME, this)
				.value(BeanSupplier.get(LBJPreferences.class).getPrimaryKeyConstraintName()).required().build();

		sequenceName = new LBJTextBoxBuilder(Labels.TABLE_SEQUENCE_NAME, this)
				.value(BeanSupplier.get(LBJPreferences.class).getSequenceName()).required().build();

		indexName = new LBJTextBoxBuilder(Labels.ADD_COLUMN_INDEX_NAME, this)
				.value(BeanSupplier.get(LBJPreferences.class).getIndexName()).required().build();

		foreignKeyName = new LBJTextBoxBuilder(Labels.ADD_COLUMN_FOREIGN_KEY_NAME, this)
				.value(BeanSupplier.get(LBJPreferences.class).getForeignKeyName()).required().build();
	}

	/**
	 * Will set all components to current preference value
	 */
	@Override
	public void setComponentsToPreferenceValues() {
		primaryKeyName.setValue(BeanSupplier.get(LBJPreferences.class).getPrimaryKeyName());
		primaryKeyConstraintName.setValue(BeanSupplier.get(LBJPreferences.class).getPrimaryKeyConstraintName());
		sequenceName.setValue(BeanSupplier.get(LBJPreferences.class).getSequenceName());
		indexName.setValue(BeanSupplier.get(LBJPreferences.class).getIndexName());
		foreignKeyName.setValue(BeanSupplier.get(LBJPreferences.class).getForeignKeyName());
	}

	@Override
	public void applyToPreferences() {
		BeanSupplier.get(LBJPreferences.class).put(NamingConventions.PKEY_PRIMARY_KEY_NAME, primaryKeyName.getValue());
		BeanSupplier.get(LBJPreferences.class).put(NamingConventions.PKEY_PRIMARY_KEY_CONSTRAINT_NAME,
				primaryKeyConstraintName.getValue());
		BeanSupplier.get(LBJPreferences.class).put(NamingConventions.PKEY_SEQUENCE_NAME, sequenceName.getValue());
		BeanSupplier.get(LBJPreferences.class).put(NamingConventions.PKEY_INDEX_NAME, indexName.getValue());
		BeanSupplier.get(LBJPreferences.class).put(NamingConventions.PKEY_FOREIGN_KEY_NAME, foreignKeyName.getValue());
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
